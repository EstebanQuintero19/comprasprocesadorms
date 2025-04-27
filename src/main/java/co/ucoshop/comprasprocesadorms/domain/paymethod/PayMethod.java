package co.ucoshop.comprasprocesadorms.domain.paymethod;

import co.ucoshop.ucoshopapi.domain.client.Client;
import co.ucoshop.ucoshopapi.domain.paymethodtype.PayMethodType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Data
@Entity
@Table(name="pay_method")
public class PayMethod {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pay_method")
    private UUID id;

    @Setter
    @Getter
    @Column(name = "owner", nullable = false)
    private String owner;

    @Setter
    @Getter
    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    @Setter
    @Getter
    @Column(name = "expire_date", nullable = false)
    private String expireDate;

    @Setter
    @Getter
    @Column(name = "cvc", nullable = false)
    private String cvc;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    @JsonBackReference("client-paymethod")  // ✅ Evita la serialización infinita
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_pay_method_type", nullable = false)
    private PayMethodType payMethodType;  // Relación con el tipo de método de pago

    public PayMethod(){

    }

    public PayMethod(UUID id, PayMethodType payMethodType, String owner, String cardNumber, String expireDate, String cvc) {
        setId(id);
        setPayMethodType(payMethodType);
        setOwner(owner);
        setCardNumber(cardNumber);
        setExpireDate(expireDate);
        setCvc(cvc);
    }


    @JsonIgnore
    public PayMethodType getType() {
        return payMethodType;
    }

    public void setPayMethodType(PayMethodType payMethodType) {
        this.payMethodType = payMethodType;
    }

}
