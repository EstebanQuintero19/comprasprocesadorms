package co.ucoshop.comprasprocesadorms.domain.product;


import co.ucoshop.comprasprocesadorms.domain.category.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String name;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;
    private String email;
    private String description;
    private int quantity;

    public Product() {
    }

    public Product(final UUID id, final String name, final BigDecimal price, final Category categoryId, final String email, final String description, final int quantity) {
        setId(id);
        setName(name);
        setPrice(price);
        setCategory(categoryId);
        setEmail(email);
        setDescription(description);
        setQuantity(quantity);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
}
