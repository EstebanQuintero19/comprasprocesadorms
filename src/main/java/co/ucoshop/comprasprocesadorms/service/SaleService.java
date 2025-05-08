package co.ucoshop.comprasprocesadorms.service;

import co.ucoshop.comprasprocesadorms.domain.address.Address;
import co.ucoshop.comprasprocesadorms.domain.paymethod.PayMethod;
import co.ucoshop.comprasprocesadorms.domain.product.Product;
import co.ucoshop.comprasprocesadorms.domain.sales.Sale;
import co.ucoshop.comprasprocesadorms.domain.sales.SaleProduct;
import co.ucoshop.comprasprocesadorms.repository.address.IAddressRepository;
import co.ucoshop.comprasprocesadorms.repository.paymethod.IPayMethodRepository;
import co.ucoshop.comprasprocesadorms.repository.product.IProductRepository;
import co.ucoshop.comprasprocesadorms.repository.sales.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final ISaleRepository saleRepository;
    private final IPayMethodRepository payMethodRepository;
    private final IAddressRepository addressRepository;
    private final IProductRepository productRepository;

    @Autowired
    public SaleService(ISaleRepository saleRepository, IProductRepository productRepository, IPayMethodRepository payMethodRepository,
                       IAddressRepository addressRepository) {
        this.saleRepository = saleRepository;
        this.payMethodRepository = payMethodRepository;
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
    }

    public Sale saveSale(Sale sale) {
        try{
        validateAddress(sale.getAddress());
        validatePaymentMethod(sale.getPaymentMethod());

        sale.setPurchaseDate(LocalDate.now());
        sale.setDeliveryDate(sale.getPurchaseDate().plusDays(15));
        List<SaleProduct> detallesVenta = new ArrayList<>();

        for (SaleProduct saleProduct: sale.getSaleProducts()){
            Optional<Product> product = productRepository.findById(saleProduct.getProducto().getId());

            if(product.isPresent()){
                SaleProduct saleProductToSave = new SaleProduct();
                saleProductToSave.setSale(sale);
                saleProductToSave.setProducto(product.get());
                saleProductToSave.setCantidad(saleProduct.getCantidad());
                BigDecimal subTotal = product.get().getPrice().multiply(new BigDecimal(saleProduct.getCantidad()));
                saleProductToSave.setSubTotal(subTotal);
                detallesVenta.add(saleProductToSave);
            }else {
                throw new IllegalArgumentException("El producto con ID " + saleProduct.getProducto().getId() + " no existe.");
            }
        }

        sale.setSaleProducts(detallesVenta);
        BigDecimal total = detallesVenta.stream()
                .map(SaleProduct::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        sale.setTotalPurchase(total);
        calcularCostoEnvio(sale);
        return saleRepository.save(sale);
    }
        catch (IllegalArgumentException e){
            throw e;
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error al guardar la venta: " + e.getMessage());
        }
    }

    private void validatePaymentMethod(PayMethod paymentMethod) {
        if (paymentMethod == null || payMethodRepository.findById(paymentMethod.getId()).isEmpty()) {
            throw new IllegalArgumentException("El método de pago con ID " + (paymentMethod != null ? paymentMethod.getId() : "null") + " no existe.");
        }
    }

    private void validateAddress(Address address) {
        if (address == null || addressRepository.findById(address.getId()).isEmpty()) {
            throw new IllegalArgumentException("La dirección con ID " + (address != null ? address.getId() : "null") + " no existe.");
        }
    }

    private void calcularCostoEnvio(Sale sale) {
        if (sale.isForPickup()) {
            sale.setShippingCost(BigDecimal.ZERO);
            return;
        }

        BigDecimal envio = new BigDecimal(10000);
        BigDecimal minimoEnvioGratis = new BigDecimal(150000);
        boolean cobraEnvio = sale.getTotalPurchase().compareTo(minimoEnvioGratis) < 0;
        sale.setShippingCost(cobraEnvio ? envio : BigDecimal.ZERO);

        if (cobraEnvio) {
            sale.setTotalPurchase(sale.getTotalPurchase().add(envio));
        }
    }
}