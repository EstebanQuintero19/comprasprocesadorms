package co.ucoshop.comprasprocesadorms.messaging.bill;


import co.ucoshop.comprasprocesadorms.domain.bill.Bill;
import co.ucoshop.comprasprocesadorms.service.bill.BillService;
import co.ucoshop.comprasprocesadorms.util.gson.MapperJsonObjeto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class BillReceiver {

    private BillService billService;
    private final MapperJsonObjeto mapperJsonObjeto;

    public BillReceiver(MapperJsonObjeto mapperJsonObjeto, BillService billService) {
        this.mapperJsonObjeto = mapperJsonObjeto;
        this.billService = billService;
    }

    @RabbitListener(queues = "${sales.queues.bill.create-queue-name}")
    public void receiveCreateMessage (String message){
        try{

            billService.createBill(obtenerObjetoDeMensaje(message).get());
            System.out.println("Mensaje " + message);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }

    }

    @RabbitListener(queues = "${sales.queues.bill.delete-queue-name}")
    public void receiveDeleteMessage(String message) {
        try {
            System.out.println("Mensaje recibido: " + message);

            // Elimina comillas si las hay
            String cleanedMessage = message.replace("\"", "").trim();

            UUID billId = UUID.fromString(cleanedMessage);
            billService.deleteBill(billId);
        } catch (Exception e) {
            System.out.println("Error al procesar mensaje: " + e.getMessage());
            e.printStackTrace();
        }
    }



    private Optional<Bill> obtenerObjetoDeMensaje(String mensaje) {
        return mapperJsonObjeto.ejecutar(mensaje, Bill.class);
        }
}
