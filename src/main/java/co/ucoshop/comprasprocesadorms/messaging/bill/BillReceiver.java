package co.ucoshop.comprasprocesadorms.messaging.bill;


import co.ucoshop.comprasprocesadorms.domain.bill.Bill;
import co.ucoshop.comprasprocesadorms.service.bill.BillService;
import co.ucoshop.comprasprocesadorms.util.gson.MapperJsonObjeto;
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

    @RabbitListener(queues = "${sales.queues.bill.create.qn}")
    public void receiveCreateMessage (String message){
        try{
            billService.createBill(obtenerObjetoDeMensaje(message).get());
        }catch (Exception e){
            System.out.println(e);
        }

    }

    @RabbitListener(queues = "${sales.queues.bill.delete.qn}")
    public void receiveDeleteMessage(String message){
        try{
            billService.deleteBill(UUID.fromString(message));
        }catch (Exception e){
            System.out.println(e);
        }

    }

    private Optional<Bill> obtenerObjetoDeMensaje(String mensaje) {
        return mapperJsonObjeto.ejecutar(mensaje, Bill.class);
        }
}
