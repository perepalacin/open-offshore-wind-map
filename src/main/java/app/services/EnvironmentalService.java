package app.services;

import app.domain.EnvironmentalMsgData;
import app.repostitories.EnvironmentalRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnvironmentalService {

    private static final Logger logger = LoggerFactory.getLogger(EnvironmentalService.class);
    private final EnvironmentalRepository environmentalRepository;

    @KafkaListener(
            id = "environmental-data-service",
            topics = "environmental-data",
            containerFactory = "kafkaListenerContainerFactoryEnvironmentData",
            errorHandler = "environmentalDataCustomErrorHandler"
    )
    public void listenForEnvironmentalData(ConsumerRecord<String, EnvironmentalMsgData> record) {
        try {
            EnvironmentalMsgData msg = record.value();
            logger.info("Received Environmental data: key={}, msgId={}, platformId={}",
                    record.key(), msg.getId(), msg.getPlatformId());

            boolean isMsgValid = validateMsg(msg);
            msg.setValid(isMsgValid);

            long msgId = environmentalRepository.saveEnvironmentalMsg(msg);
            logger.info("Environmental message stored with ID: {}", msgId);
        } catch (Exception e) {
            logger.error("Failed to process EnvironmentalMsgData: {}", record.value(), e);
        }
    }

    private boolean validateMsg(EnvironmentalMsgData msg) {
        if (msg.getWaveHeight() < 0 || msg.getWaveHeight() > 50) {
            return false;
        }

        if (msg.getWavePeriod() < 1 || msg.getWavePeriod() > 30) {
            return false;
        }

        if (msg.getWaterTemperature() < -5 || msg.getWaterTemperature() > 40) {
            return false;
        }

        if (msg.getAirHumidity() < 0 || msg.getAirHumidity() > 100) {
            return false;
        }

        if (msg.getWindSpeed() < 0 || msg.getWindSpeed() > 80) {
            return false;
        }
        if (msg.getWindDirection() < 0 || msg.getWindDirection() > 360) {
            return false;
        }

        if (msg.getSeaCurrentDirectionSurface() < 0 || msg.getSeaCurrentDirectionSurface() > 360) {
            return false;
        }

        if (msg.getSeaCurrentSpeedSurface() < 0 || msg.getSeaCurrentSpeedSurface() > 10) {
            return false;
        }
        // ... repeat for seaCurrentDirection5m, seaCurrentSpeed5m, etc.

        if (msg.getPhLevel() < 0 || msg.getPhLevel() > 14) {
            return false;
        }

        return true;
    }
}
