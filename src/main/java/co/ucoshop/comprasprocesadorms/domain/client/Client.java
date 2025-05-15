package co.ucoshop.comprasprocesadorms.domain.client;

import co.ucoshop.comprasprocesadorms.domain.documenttype.DocumentType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Data
@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "phone")
    private String phone;

    @Column(name = "document")
    private Integer document;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_document_type")
    private DocumentType documentType;


    public Client() {
    }

    public Client(UUID id, String name, String email, LocalDate birthDate, String phone, DocumentType documentType,
                  Integer document, String password) {
        setId(id);
        setName(name);
        setEmail(email);
        setBirthDate(birthDate);
        setPhone(phone);
        setDocumentType(documentType);
        setDocument(document);
        setPassword(password);
    }
}
