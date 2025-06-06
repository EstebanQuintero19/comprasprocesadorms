package co.ucoshop.comprasprocesadorms.domain.cart;

import co.ucoshop.comprasprocesadorms.domain.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "item_cart")
public class ItemCart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "item_cart_id", updatable = false, nullable = false)
    private UUID itemCartId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private BigDecimal subtotal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    public ItemCart(UUID itemCartId, Product product, Integer quantity, BigDecimal unitPrice, BigDecimal subtotal, Cart cart) {
        setItemCartId(itemCartId);
        setProduct(product);
        setQuantity(quantity);
        setUnitPrice(unitPrice);
        setSubtotal(subtotal);
        setCart(cart);
    }

    public ItemCart() {

    }

    public UUID getItemCartId() {
        return itemCartId;
    }

    public void setItemCartId(UUID itemCartId) {
        this.itemCartId = itemCartId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
