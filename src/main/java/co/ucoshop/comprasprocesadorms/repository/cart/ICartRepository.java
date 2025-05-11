package co.ucoshop.comprasprocesadorms.repository.cart;

import co.ucoshop.comprasprocesadorms.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ICartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByUserEmail(String userEmail);
}
