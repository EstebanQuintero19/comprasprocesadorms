package co.ucoshop.comprasprocesadorms.domain.category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "id_category", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Category(final UUID id, final String name) {
        setId(id);
        setName(name);
    }

    public Category() {
    }
}
