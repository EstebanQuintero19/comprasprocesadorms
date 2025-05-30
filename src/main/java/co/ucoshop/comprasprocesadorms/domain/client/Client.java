package co.ucoshop.comprasprocesadorms.domain.client;
/*
import co.ucoshop.ucoshopapi.domain.address.Address;
import co.ucoshop.ucoshopapi.domain.documenttype.DocumentType;
import co.ucoshop.ucoshopapi.domain.paymethod.PayMethod;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
@Setter
@Getter
@Data
@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_client",nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "id_document_type", nullable = false)
    private DocumentType documentType;

    @Column(name = "document", nullable = false)
    private int document;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("client-address")  // ✅ Controla la relación con Address
    private List<Address> addresses;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("client-paymethod")  // ✅ Controla la relación con PayMethod
    private List<PayMethod> payMethods;

    public Client() {
    }

    public Client(
            UUID id,
            String name,
            String email,
            Date birthDate,
            String phone,
            DocumentType documentType,
            int document,
            String password,
            List<Address>addresses,
            List<PayMethod> payMethods
    ) {
        setId(id);
        setName(name);
        setEmail(email);
        setBirthDate(birthDate);
        setPhone(phone);
        setDocumentType(documentType);
        setDocument(document);
        setPassword(password);
        setAddresses(addresses);
        setPayMethods(payMethods);
    }


}

 */
