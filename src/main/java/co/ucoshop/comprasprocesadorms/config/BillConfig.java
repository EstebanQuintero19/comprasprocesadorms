package co.ucoshop.comprasprocesadorms.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sales.queues.bill")
@Getter
@Setter
public class BillConfig {

    private String createEx;
    private String createRk;
    private String createQn;

    private String deleteEx;
    private String deleteRk;
    private String deleteQn;

}
