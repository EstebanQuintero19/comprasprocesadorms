package co.ucoshop.comprasprocesadorms.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "compras.queues")
@Getter
@Setter
public class SaleConfig {
    private String exchangeName;
    private String routingKey;
    private String queueName;

}
