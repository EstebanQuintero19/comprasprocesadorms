package co.ucoshop.comprasprocesadorms.config.cart;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "sales.queues.cart")
@PropertySource("classpath:application.properties")
public class PropertiesCartQueue {
    private String addItemEx;
    private String addItemRk;
    private String addItemQn;

    private String reduceItemEx;
    private String reduceItemRk;
    private String reduceItemQn;

    private String deleteItemEx;
    private String deleteItemRk;
    private String deleteItemQn;

    private String clearEx;
    private String clearRk;
    private String clearQn;
}
