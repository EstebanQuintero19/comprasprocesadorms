package co.ucoshop.comprasprocesadorms.service.bill;

import co.ucoshop.comprasprocesadorms.domain.bill.Bill;
import co.ucoshop.comprasprocesadorms.domain.sales.Sale;
import co.ucoshop.comprasprocesadorms.repository.bill.BillRepository;
import co.ucoshop.comprasprocesadorms.repository.sales.ISaleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final ISaleRepository saleRepository;

    @Autowired
    public BillService(BillRepository billRepository, ISaleRepository saleRepository) {
        this.billRepository = billRepository;
        this.saleRepository = saleRepository;
    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Optional<Bill> getBillById(UUID id) {
        return billRepository.findById(id);
    }

    public Bill createBill(Bill bill) {
        if (bill.getSale() == null || bill.getSale().getIdSale() == null) {
            throw new IllegalArgumentException("La factura debe estar asociada a una venta válida.");
        }

        Sale sale = saleRepository.findById(bill.getSale().getIdSale())
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con ID: " + bill.getSale().getIdSale()));

        BigDecimal shippingCost = sale.getShippingCost();
        BigDecimal total = sale.getTotalPurchase();
        BigDecimal totalWithoutShippingCost = total.subtract(shippingCost);
        BigDecimal ivaRate = new BigDecimal("0.19");

        BigDecimal subtotal = totalWithoutShippingCost.subtract(total.multiply(ivaRate));

        BigDecimal iva = total.subtract(subtotal);

        bill.setTotalAmount(total);
        bill.setSubtotalAmount(subtotal);
        bill.setVat(iva);
        bill.setSale(sale);

        return billRepository.save(bill);
    }



    public void deleteBill(UUID id) {
        if (!billRepository.existsById(id)) {
            throw new EntityNotFoundException("Factura no encontrada con ID: " + id);
        }
        billRepository.deleteById(id);
    }
}
