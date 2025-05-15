package co.ucoshop.comprasprocesadorms.messaging.sale;

import co.ucoshop.comprasprocesadorms.domain.sales.Sale;
import co.ucoshop.comprasprocesadorms.service.SaleService;
import co.ucoshop.comprasprocesadorms.util.gson.MapperJsonObjeto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SaleReceiver {

    private SaleService saleService;
    private final MapperJsonObjeto mapperJsonObjeto;

    public SaleReceiver(SaleService saleService, MapperJsonObjeto mapperJsonObjeto) {
        this.saleService = saleService;
        this.mapperJsonObjeto = mapperJsonObjeto;
    }

    @RabbitListener(queues = "${sales.queues.sale.create-queue-name}")
    public void receiveMessageProcessClient(String message) {
        try {
            System.out.println("Mensaje recibido: " + message);
            saleService.saveSale(obtenerObjetoDeMensaje(message).get());
            System.out.println("Mensaje recibido: " + message);
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    private Optional<Sale> obtenerObjetoDeMensaje(String mensaje) {
        return mapperJsonObjeto.ejecutar(mensaje, Sale.class);
    }
}
