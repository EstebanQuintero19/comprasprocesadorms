package co.ucoshop.comprasprocesadorms.messaging.bill;

import co.ucoshop.comprasprocesadorms.config.BillConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class BillSender {

    private RabbitTemplate rabbitTemplate;
    private BillConfig billConfig ;

    public BillSender(RabbitTemplate rabbitTemplate,BillConfig billConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.billConfig = billConfig;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(billConfig.getCreateEx(),billConfig.getCreateRk(),message);
    }
}
