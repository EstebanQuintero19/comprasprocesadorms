package co.ucoshop.comprasprocesadorms.domain.sales;

/*
import co.ucoshop.comprasprocesadorms.domain.address.Address;
import co.ucoshop.comprasprocesadorms.domain.paymethod.PayMethod;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "Sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Sale", updatable = false)
    private UUID idSale;

    @Column(name = "email")
    private String email;

    @Column(name = "for_Pickup")
    private boolean forPickup;

    @Column(name = "total_Purchase")
    private BigDecimal totalPurchase;

    @ManyToOne
    @JoinColumn(name = "Payment_Method_id")
    private PayMethod paymentMethod;

    @ManyToOne
    @JoinColumn (name = "address_id" )
    private Address address;

    @Column(name = "purchase_Date")
    private LocalDate purchaseDate;

    @Column(name = "delivery_Date" )
    private LocalDate deliveryDate;

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }

    @Column(name = "shipping_cost")
    private BigDecimal shippingCost;

    @OneToMany (mappedBy = "sale" , fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleProduct> saleProducts;

    public Sale(UUID idSale, String email, boolean forPickup, BigDecimal totalPurchase, PayMethod paymentMethod, Address address, LocalDate purchaseDate, LocalDate deliveryDate, BigDecimal shippingCost, List<SaleProduct> saleProducts) {
        this.idSale = idSale;
        this.email = email;
        this.forPickup = forPickup;
        this.totalPurchase = totalPurchase;
        this.paymentMethod = paymentMethod;
        this.address = address;
        this.purchaseDate = purchaseDate;
        this.deliveryDate = deliveryDate;
        this.shippingCost = shippingCost;
        this.saleProducts = saleProducts;
    }

    public Sale() {
    }

    public UUID getIdSale() {
        return idSale;
    }

    public void setIdSale(UUID idSale) {
        this.idSale = idSale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isForPickup() {
        return forPickup;
    }

    public void setForPickup(boolean forPickup) {
        this.forPickup = forPickup;
    }

    public BigDecimal getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(BigDecimal totalPurchase) {
        this.totalPurchase = totalPurchase;
    }

    public PayMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PayMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<SaleProduct> getSaleProducts() {
        return saleProducts;
    }

    public void setSaleProducts(List<SaleProduct> saleProducts) {
        this.saleProducts = saleProducts;
    }
}


 */