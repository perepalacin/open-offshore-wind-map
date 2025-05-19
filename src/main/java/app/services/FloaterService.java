package app.services;

import app.domain.FloaterMsgData;
import app.repostitories.FloaterRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FloaterService {

    private static final Logger logger = LoggerFactory.getLogger(FloaterService.class);
    private final FloaterRepository floaterRepository;

    @KafkaListener(id = "floater-data-service", topics = "floater-data", containerFactory = "kafkaListenerContainerFactoryFloaterData", errorHandler = "floaterDataCustomErrorHandler")
    public void listenForFloaterData(ConsumerRecord<String, FloaterMsgData> record) {
        try {
            logger.info("Received Floater data: key={}, floaterId={}, platformId={}",
                    record.key(), record.value().getId(), record.value().getPlatformId());

            FloaterMsgData msg = record.value();
            boolean isMsgValid = validateMsg(msg);
            msg.setValid(isMsgValid);

            long msgId = floaterRepository.saveFloaterMsg(msg);
            logger.info("Floater message stored with ID: {}", msgId);
        } catch (Exception e) {
            logger.error("Failed to process FloaterMsgData: {}", record.value(), e);
        }
    }

    private boolean validateMsg(FloaterMsgData msg) {
        if (msg.getPitch() < -20 || msg.getPitch() > 20) return false;
        if (msg.getRoll() < -20 || msg.getRoll() > 20) return false;

        if (msg.getYaw() < 0 || msg.getYaw() > 360) return false;

        if (msg.getSurge() < -100 || msg.getSurge() > 100) return false;
        if (msg.getSway() < -100 || msg.getSway() > 100) return false;
        if (msg.getHeave() < -100 || msg.getHeave() > 100) return false;

        if (msg.getLatitude() < -90 || msg.getLatitude() > 90) return false;
        if (msg.getLongitude() < -180 || msg.getLongitude() > 180) return false;

        return true;
    }
}
