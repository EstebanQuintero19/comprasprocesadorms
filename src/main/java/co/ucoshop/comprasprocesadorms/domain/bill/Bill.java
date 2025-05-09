package co.ucoshop.comprasprocesadorms.domain.bill;

import co.ucoshop.comprasprocesadorms.domain.sales.Sale;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID billId; // facturaId
    private BigDecimal subtotalAmount;
    private BigDecimal totalAmount;
    private BigDecimal vat;
    private BigDecimal shippingCost;
    private LocalDateTime issueDate;
    @PrePersist
    public void onCreate() {this.issueDate = LocalDateTime.now();}
    @OneToOne(optional = true)
    @JoinColumn(name = "sale", nullable = true)
    private Sale sale; // sale

    public Bill(UUID billId, BigDecimal subtotalAmount, BigDecimal totalAmount, BigDecimal vat, Sale sale) {
        this.billId = billId;
        this.subtotalAmount = subtotalAmount;
        this.totalAmount = totalAmount;
        this.vat = vat;
        this.issueDate = LocalDateTime.now();
        this.sale = sale;
    }

    public Bill() {}

    public UUID getBillId() {
        return billId;
    }

    public void setBillId(UUID billId) {
        this.billId = billId;
    }

    public BigDecimal getSubtotalAmount() {
        return subtotalAmount;
    }

    public void setSubtotalAmount(BigDecimal subtotalAmount) {
        this.subtotalAmount = subtotalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }
}
