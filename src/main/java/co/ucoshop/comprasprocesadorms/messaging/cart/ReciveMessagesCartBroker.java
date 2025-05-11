package co.ucoshop.comprasprocesadorms.messaging.cart;

import co.ucoshop.comprasprocesadorms.domain.cart.Cart;
import co.ucoshop.comprasprocesadorms.service.cart.CartService;
import co.ucoshop.comprasprocesadorms.util.gson.MapperJsonObjeto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReciveMessagesCartBroker {

    @Autowired
    private CartService cartService;

    private final MapperJsonObjeto mapperJsonObjeto;

    public ReciveMessagesCartBroker(MapperJsonObjeto mapperJsonObjeto) {
        this.mapperJsonObjeto = mapperJsonObjeto;
    }

    //@RabbitListener(queues = "${cart.queue-recibir.cart.queue-name}")
    public void reciveMessagesProcessCart(String message) {
        try {
            //cartService.save(obtenerObjetoDeMensaje(message).get());
        }catch (Exception e){
            System.out.println(e);
        }


    }

    private Optional<Cart> obtenerObjetoDeMensaje(String message) {
        return mapperJsonObjeto.ejecutar(message,Cart.class);
    }
}
