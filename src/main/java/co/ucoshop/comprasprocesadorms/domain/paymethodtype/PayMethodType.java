package co.ucoshop.comprasprocesadorms.domain.paymethodtype;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Data
@Entity
@Table(name = "payMethodType")
public class PayMethodType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_pay_method_type", nullable = false)
    private UUID id;

    @Column(name="name", nullable = false)
    private String name;

    public PayMethodType(){

    }

    public PayMethodType(UUID id, String name) {
        setId(id);
        setName(name);
    }

}
