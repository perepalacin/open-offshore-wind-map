package app.services;

import app.domain.dto.TurbineDataDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TurbineService {

    private static final Logger logger = LoggerFactory.getLogger(TurbineService.class);

    @KafkaListener(id = "turbine-data-service", topics = "turbine-data", containerFactory = "kafkaListenerContainerFactoryJson")
    public void listenForTurbineData(ConsumerRecord<String, TurbineDataDto> record) {
        logger.info("Received TurbineDataDto: {} - {} - {}", record.key(), record.value().getId(), record.value().getTurbineId());
    }
}
