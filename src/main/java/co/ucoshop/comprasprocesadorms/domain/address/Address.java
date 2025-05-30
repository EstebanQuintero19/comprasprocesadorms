package co.ucoshop.comprasprocesadorms.domain.address;
/*
import co.ucoshop.comprasprocesadorms.domain.client.Client;
import co.ucoshop.comprasprocesadorms.domain.country.Country;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Entity
@Table(name= "address")
public class Address {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_address", nullable = false)
    private UUID id;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "id_country", nullable = false)
    private Country country;

    @Setter
    @Getter
    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    @JsonBackReference("client-address")  // ✅ Evita la serialización infinita
    private Client client;

    public Address() {
    }

    public Address(UUID id, Country country, String address) {
        setId(id);
        setCountry(country);
        setAddress(address);
    }

}

 */
