package co.ucoshop.comprasprocesadorms.domain.address;

import co.ucoshop.comprasprocesadorms.domain.client.Client;
import co.ucoshop.comprasprocesadorms.domain.country.Country;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
@Entity
@Table(name= "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "id_country", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    public Address() {
    }

    public Address(UUID id, Country country, String address, Client client) {
        setId(id);
        setCountry(country);
        setAddress(address);
        setClient(client);
    }
}

 */
