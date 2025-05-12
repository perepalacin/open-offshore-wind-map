package app.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import app.domain.dto.TurbineDataDto;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    private <K, V> ConsumerFactory<K, V> generateFactory(Deserializer<K> keyDeserializer, Deserializer<V> valueDeserializer) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(props, keyDeserializer, valueDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TurbineDataDto> kafkaListenerContainerFactoryJson() {
        ConcurrentKafkaListenerContainerFactory<String, TurbineDataDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        var consumerFactory = generateFactory(new StringDeserializer(), new JsonDeserializer<>(TurbineDataDto.class, false));
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
