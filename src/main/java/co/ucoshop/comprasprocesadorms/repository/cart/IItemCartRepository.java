package co.ucoshop.comprasprocesadorms.repository.cart;

import co.ucoshop.comprasprocesadorms.domain.cart.Cart;
import co.ucoshop.comprasprocesadorms.domain.cart.ItemCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IItemCartRepository extends JpaRepository<ItemCart, UUID> {
    List<ItemCart> findByCart_CartId(UUID cartId);
    List<ItemCart> findByCart(Cart cart);
}