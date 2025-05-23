package co.ucoshop.comprasprocesadorms.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "compras.procesar")
@PropertySource("classpath:compras.properties")
@Getter
@Setter
public class cartQueueConfig {
    private String exchangeName;
    private String routingKey;
    private String queueName;

}
