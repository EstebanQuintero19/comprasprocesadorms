package co.ucoshop.comprasprocesadorms.service.cart;

import co.ucoshop.comprasprocesadorms.domain.cart.Cart;
import co.ucoshop.comprasprocesadorms.domain.cart.ItemCart;
import co.ucoshop.comprasprocesadorms.domain.product.Product;
import co.ucoshop.comprasprocesadorms.repository.cart.ICartRepository;
import co.ucoshop.comprasprocesadorms.repository.cart.IItemCartRepository;
import co.ucoshop.comprasprocesadorms.repository.product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CartService {

    private final ICartRepository cartRepository;
    private final IProductRepository productRepository;
    public final IItemCartRepository itemCartRepository;

    @Autowired
    public CartService(ICartRepository cartRepository, IProductRepository productRepository, IItemCartRepository itemCartRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.itemCartRepository = itemCartRepository;
    }

    //Listar items
    public Map<String, Object> getCartDataByEmail(String email) {
        Cart cart = getCartByUserEmail(email);
        List<ItemCart> items = itemCartRepository.findByCart(cart);
        if (validateAndUpdateItemPrices(items)) {
            cart.setTotal(calculateTotal(items));
            cartRepository.save(cart);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("cartId", cart.getCartId());
        response.put("userEmail", cart.getUserEmail());
        response.put("total", cart.getTotal());
        response.put("items", items);
        return response;
    }


    //Registrar items carrito o agregar
    public Cart addItemCart(String userEmail, UUID productId, Integer quantity) {
        Cart cart = getOrCreateCart(userEmail);
        Product product = getProductById(productId);
        List<ItemCart> cartItems = getItemsByCartId(cart.getCartId());
        boolean updated = validateAndUpdateItemPrices(cartItems);
        Optional<ItemCart> existingItem = cartItems.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();
        if (existingItem.isPresent()) {
            updateItem(existingItem.get(),quantity,product.getPrice());
        }else{
            addNewItem(cart,product,quantity);
        }
        if (updated) {
            cart.setTotal(calculateTotal(cartItems));
        }
        return cartRepository.save(cart);
    }


    //Reducir items carrito
    public Cart reduceItemCart(String userEmail, UUID productId, Integer quantity) {
        Cart cart = getCartByUserEmail(userEmail);
        Product product = getProductById(productId);
        List<ItemCart> cartItems = getItemsByCartId(cart.getCartId());
        validateAndUpdateItemPrices(cartItems);
        ItemCart item = findItemInCart(cartItems, productId);
        if (item != null) {
            updateOrRemoveItem(item,product,quantity);
            BigDecimal total = calculateTotal(cartItems);
            cart.setTotal(total);
            return cartRepository.save(cart);
        }
        return cart;
    }




    // Eliminar item
    public Cart removeItemCart(String userEmail, UUID productId) {
        Cart cart = getCartByUserEmail(userEmail);
        ItemCart item = findItemInCartOrThrow(cart,productId);
        itemCartRepository.delete(item);
        updateCartTotal(cart);
        return cartRepository.save(cart);
    }

    //Vaciar carrito
    public Cart clearCart(String userEmail) {
        Cart cart = getCartByUserEmail(userEmail);
        clearCartItems(cart);
        resetCartTotal(cart);
        return cartRepository.save(cart);
    }


    private Cart getCartByUserEmail(String userEmail) {
        return cartRepository.findByUserEmail(userEmail).orElseThrow(()-> new NoSuchElementException("Carrito no encontrado"));
    }

    private Cart getOrCreateCart(String userEmail) {
        return cartRepository.findByUserEmail(userEmail).orElseGet(()->createNewCart(userEmail));
    }

    private Product getProductById(UUID productId) {
        return productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("Producto no encontrado"));
    }

    private List<ItemCart> getItemsByCartId(UUID cartId) {
        return itemCartRepository.findByCart_CartId(cartId);
    }

    private BigDecimal calculateTotal(List<ItemCart> items) {
        return items.stream().map(ItemCart::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private boolean validateAndUpdateItemPrices(List<ItemCart> items) {
        boolean updated = false;
        for (ItemCart item : items) {
            BigDecimal currentPrice = item.getProduct().getPrice();
            if (item.getUnitPrice().compareTo(currentPrice) != 0) {
                item.setUnitPrice(currentPrice);
                item.setSubtotal(currentPrice.multiply(BigDecimal.valueOf(item.getQuantity())));
                itemCartRepository.save(item);
                updated = true;
            }
        }
        return updated;
    }



    private Cart createNewCart(String userEmail) {
        Cart newCart = new Cart();
        newCart.setUserEmail(userEmail);
        return cartRepository.save(newCart);
    }

    private void updateItem(ItemCart item, Integer quantity, BigDecimal unitPrice) {
        item.setQuantity(item.getQuantity() + quantity);
        item.setSubtotal(unitPrice.multiply(BigDecimal.valueOf(item.getQuantity())));
        itemCartRepository.save(item);
    }

    private void addNewItem(Cart cart, Product product, Integer quantity) {
        ItemCart newItem = new ItemCart();
        newItem.setCart(cart);
        newItem.setProduct(product);
        newItem.setQuantity(quantity);
        newItem.setUnitPrice(product.getPrice());
        newItem.setSubtotal(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        itemCartRepository.save(newItem);
    }

    private ItemCart findItemInCart(List<ItemCart> cartItems, UUID productId) {
        return cartItems.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    private void updateOrRemoveItem(ItemCart item, Product product, Integer quantity) {
        int newQuantity = item.getQuantity() - quantity;
        if (newQuantity > 0) {
            item.setQuantity(newQuantity);
            item.setSubtotal(product.getPrice().multiply(new BigDecimal(newQuantity)));
            itemCartRepository.save(item);
        }else {
            itemCartRepository.delete(item);
        }
    }

    private ItemCart findItemInCartOrThrow(Cart cart, UUID productId) {
        return itemCartRepository.findByCart_CartId(cart.getCartId()).stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en el carrito"));
    }

    private void updateCartTotal(Cart cart) {
        List<ItemCart> updatedItems = getItemsByCartId(cart.getCartId());
        validateAndUpdateItemPrices(updatedItems);
        cart.setTotal(calculateTotal(updatedItems));
    }

    private void clearCartItems(Cart cart) {
        List<ItemCart> items = getItemsByCartId(cart.getCartId());
        itemCartRepository.deleteAll(items);
    }

    private void resetCartTotal(Cart cart) {
        cart.setTotal(BigDecimal.ZERO);
    }

}
