package co.ucoshop.comprasprocesadorms.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "mensaje.certificacion-sales")
public class PropertiesSaleQueue {
    private String exchangeName;
    private String routingKey;
    private String queueName;
}
