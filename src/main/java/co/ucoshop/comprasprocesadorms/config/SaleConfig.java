package co.ucoshop.comprasprocesadorms.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sales.queues.sale")
@Getter
@Setter
public class SaleConfig {
    private String createExchangeName;
    private String createRoutingKey;
    private String createQueueName;
}
