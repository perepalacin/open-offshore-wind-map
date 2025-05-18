package app.config;

import java.util.HashMap;
import java.util.Map;

import app.services.TurbineService;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import app.domain.TurbineMsgData;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    private <K, V> ConsumerFactory<K, V> generateFactory(Deserializer<K> keyDeserializer, Deserializer<V> valueDeserializer) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new DefaultKafkaConsumerFactory<>(props, keyDeserializer, valueDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TurbineMsgData> kafkaListenerContainerFactoryTurbineData() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        var consumerFactory = new DefaultKafkaConsumerFactory<>(
            props,
            new ErrorHandlingDeserializer<>(new StringDeserializer()),
            new ErrorHandlingDeserializer<>(new JsonDeserializer<>(TurbineMsgData.class, false))
        );

        ConcurrentKafkaListenerContainerFactory<String, TurbineMsgData> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
