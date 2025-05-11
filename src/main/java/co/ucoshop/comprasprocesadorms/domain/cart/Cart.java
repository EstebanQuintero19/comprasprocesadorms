package co.ucoshop.comprasprocesadorms.domain.cart;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID cartId;

    @Column(name = "userEmail", nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private BigDecimal total;

    public Cart() {}


    public Cart(UUID cartId, String userEmail, BigDecimal total) {
        this.cartId = cartId;
        this.userEmail = userEmail;
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }
}
