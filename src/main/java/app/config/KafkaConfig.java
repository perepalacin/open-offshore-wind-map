package app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("production")
public class KafkaConfig {

    @Bean
    public NewTopic turbineTopic() {
        return TopicBuilder.name("turbine-data")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic floaterTopic() {
        return TopicBuilder.name("floater-data")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic environmentTopic() {
        return TopicBuilder.name("environment-data")
                .partitions(10)
                .replicas(1)
                .build();
    }


}
