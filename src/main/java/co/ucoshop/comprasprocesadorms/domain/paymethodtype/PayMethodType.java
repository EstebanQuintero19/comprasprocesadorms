package co.ucoshop.comprasprocesadorms.domain.paymethodtype;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
@Entity
@Table(name = "pay_method_type")
public class PayMethodType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name="name")
    private String name;


    public PayMethodType(){
    }

    public PayMethodType(UUID id, String name) {
        setId(id);
        setName(name);
    }
}
