package co.ucoshop.comprasprocesadorms.messaging.cart;

import co.ucoshop.comprasprocesadorms.domain.cart.Cart;
import co.ucoshop.comprasprocesadorms.service.cart.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ReciveMessagesCartBroker {

    @Autowired
    private CartService cartService;

    public ReciveMessagesCartBroker(CartService cartService) {
        this.cartService = cartService;
    }


    @RabbitListener(queues = "${sales.queues.cart.add-item-qn}")
    @SendTo
    public String receiveAddItem(String message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Convertir el JSON en un Map
            Map<String, Object> data = mapper.readValue(message, Map.class);
            // Extraer y convertir los valores
            int quantity = (int) data.get("quantity");
            UUID productId = UUID.fromString((String) data.get("productId"));
            String userEmail = (String) data.get("userEmail");
            Cart cart  = cartService.addItemCart(userEmail, productId, quantity);
            String json = mapper.writeValueAsString(cart);
            System.out.println("json: " + json);
            return json;
        } catch (Exception e) {
            return buildErrorJson("GENERIC_ERROR", "Error procesando el mensaje: " + e.getMessage());

        }
    }

    @RabbitListener(queues = "${sales.queues.cart.reduce-item-qn}")
    @SendTo
    public String receiveReduceItem(String message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Convertir el JSON en un Map
            Map<String, Object> data = mapper.readValue(message, Map.class);
            // Extraer y convertir los valores
            int quantity = (int) data.get("quantity");
            UUID productId = UUID.fromString((String) data.get("productId"));
            String userEmail = (String) data.get("userEmail");
            Cart cart  = cartService.reduceItemCart(userEmail, productId, quantity);
            String json = mapper.writeValueAsString(cart);
            System.out.println("json: " + json);
            return json;
        } catch (Exception e) {
            return buildErrorJson("GENERIC_ERROR", "Error procesando el mensaje: " + e.getMessage());
        }
    }

    @RabbitListener(queues = "${sales.queues.cart.delete-item-qn}")
    @SendTo
    public String receiveRemoveItem (String message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Convertir el JSON en un Map
            Map<String, Object> data = mapper.readValue(message, Map.class);
            // Extraer y convertir los valores
            UUID productId = UUID.fromString((String) data.get("productId"));
            String userEmail = (String) data.get("userEmail");
            Cart cart  = cartService.removeItemCart(userEmail, productId);
            String json = mapper.writeValueAsString(cart);
            System.out.println("json: " + json);
            return json;
        } catch (Exception e) {
            return buildErrorJson("GENERIC_ERROR", "Error procesando el mensaje: " + e.getMessage());
        }
    }

    @RabbitListener(queues = "${sales.queues.cart.clear-qn}")
    @SendTo
    public String receiveClearCart(String message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Convertir el JSON en un Map
            Map<String, Object> data = mapper.readValue(message, Map.class);
            // Extraer y convertir los valores
            String userEmail = (String) data.get("userEmail");
            Cart cart  = cartService.clearCart(userEmail);
            String json = mapper.writeValueAsString(cart);
            System.out.println("json: " + json);
            return json;
        } catch (Exception e) {
            return buildErrorJson("GENERIC_ERROR", "Error procesando el mensaje: " + e.getMessage());
        }
    }

    private String buildErrorJson(String code, String message) {
        try {
            Map<String, String> error = new HashMap<>();
            error.put("error", code);
            error.put("message", message);
            return new ObjectMapper().writeValueAsString(error);
        } catch (Exception e) {
            return "{\"error\": \"JSON_BUILD_ERROR\", \"message\": \"No se pudo construir la respuesta de error\"}";
        }
    }

}
