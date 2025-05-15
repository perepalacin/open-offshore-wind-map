package app.services;

import app.domain.TurbineMsgData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.serializer.DeserializationException;
import org.springframework.stereotype.Service;

@Service
public class TurbineService {

    private static final Logger logger = LoggerFactory.getLogger(TurbineService.class);

    @KafkaListener(id = "turbine-data-service", topics = "turbine-data", containerFactory = "kafkaListenerContainerFactoryTurbineData")
    public void listenForTurbineData(ConsumerRecord<String, TurbineMsgData> record) throws DeserializationException {
        logger.info("Received TurbineDataDto: {} - {} - {}", record.key(), record.value().getId(), record.value().getTurbineId());
    }
}
