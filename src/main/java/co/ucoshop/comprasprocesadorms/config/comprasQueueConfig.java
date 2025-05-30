package co.ucoshop.comprasprocesadorms.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "")
@PropertySource("classpath:application.properties")
@Getter
@Setter
public class comprasQueueConfig {
    private String exchangeName;
    private String routingKey;
    private String queueName;

}
