package co.ucoshop.comprasprocesadorms.domain.country;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Data
@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id_country",nullable = false)
    private UUID id;

    @Column(name="name")
    private String name;

    public Country(){
    }

    public Country(UUID id, String name) {
        setId(id);
        setName(name);
    }

}
