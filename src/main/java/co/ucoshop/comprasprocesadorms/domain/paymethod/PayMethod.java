package co.ucoshop.comprasprocesadorms.domain.paymethod;

import co.ucoshop.comprasprocesadorms.domain.client.Client;
import co.ucoshop.comprasprocesadorms.domain.paymethodtype.PayMethodType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
@Entity
@Table(name="pay_method")
public class PayMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "owner")
    private String owner;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expire_date")
    private String expireDate;

    @Column(name = "cvc")
    private String cvc;

    @ManyToOne
    @JoinColumn(name = "id_pay_method_type")
    private PayMethodType payMethodType;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;


    public PayMethod(){
    }

    public PayMethod(UUID id, String owner, String cardNumber, String expireDate, String cvc,
                     PayMethodType payMethodType, Client client) {
        setId(id);
        setOwner(owner);
        setCardNumber(cardNumber);
        setExpireDate(expireDate);
        setCvc(cvc);
        setPayMethodType(payMethodType);
        setClient(client);
    }
}
