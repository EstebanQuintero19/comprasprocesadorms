package co.ucoshop.comprasprocesadorms.domain.documenttype;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
@Entity
@Table(name = "document_type")
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;


    public DocumentType() {
    }

    public DocumentType(UUID id, String name) {
        setId(id);
        setName(name);
    }
}
