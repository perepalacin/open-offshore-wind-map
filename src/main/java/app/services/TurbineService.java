package app.services;

import app.domain.TurbineDetails;
import app.domain.TurbineMsgData;
import app.enums.TurbineOperationalErrorTypes;
import app.repostitories.TurbineRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TurbineService {

    private static final Logger logger = LoggerFactory.getLogger(TurbineService.class);
    private final TurbineRepository turbineRepository;

    @KafkaListener(id = "turbine-data-service", topics = "turbine-data", containerFactory = "kafkaListenerContainerFactoryTurbineData", errorHandler = "turbineDataCustomErrorHandler")
    public void listenForTurbineData(ConsumerRecord<String, TurbineMsgData> record) {
        try {
            logger.info("Received Data from the turbine: {} - {} - {}", record.key(), record.value().getId(), record.value().getId());
            TurbineMsgData msg = record.value();
            msg.setValid(true);
            Boolean isMsgValid = validateMsg(record.value());
            TurbineDetails turbineDetails = turbineRepository.getById(record.value().getTurbineId());
            Set<Short> errors = checkForErrors(record.value(), turbineDetails);
            if (turbineDetails == null) {
                msg.setValid(false);
            }
            long msgId = turbineRepository.saveTurbineMsg(msg);
            if (errors.size() != 0) {
                turbineRepository.saveTurbineErrors(errors, msgId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Received invalid TurbineDataDto: {}", record.value().toString());
        }
    }

    private TurbineDetails getById(long id) {
        return turbineRepository.getById(id);
    }

    private static boolean validateMsg (TurbineMsgData msg) {
        if (msg.getWindDirection() > 360 || msg.getWindDirection() < 0) return false;
        if (msg.getWindSpeed() < 0) return false;
        if (msg.getRotorSpeed() < 0) return false;
        if (msg.getYawAngle() > 360 || msg.getYawAngle() < 0) return false;
        if (msg.getPitchAngle() > 10 || msg.getPitchAngle() < -10) return false;
        if (msg.getPowerOutput() < 0 || msg.getPowerOutput() > 30_000_000) return false;
        if (msg.getReactivePower() < 0 || msg.getReactivePower() > 30_000_000) return false;
        if (msg.getGridFrequency() < 0 || msg.getGridFrequency() > 80) return false;
        if (msg.getVoltage() < 0) return false;
        if (msg.getCurrent() < 0) return false;
        if (msg.getVibrationLevels() < 0 || msg.getVibrationLevels() > 10_000) return false;
        if (msg.getHydraulicPressure() < 0 || msg.getHydraulicPressure() > 500_000) return false;
        if (msg.getLubricantLevel() < 0 || msg.getLubricantLevel() > 10_000) return false;
        return true;
    }

    private static Set<Short> checkForErrors (TurbineMsgData msg, TurbineDetails turbineDetails) {
        Set<Short> errorCodes = new HashSet<>();
        String error = "";
        if (msg.getWindSpeed() != 0 && Math.abs(msg.getWindDirection() - msg.getYawAngle()) > 10 ) {
            errorCodes.add(TurbineOperationalErrorTypes.HIGH_YAW_MISALIGNMENT.getCode());
        }
        if (msg.getRotorSpeed() > turbineDetails.getMaxRPM()) {
            errorCodes.add(TurbineOperationalErrorTypes.MAX_ROTOR_SPEED_EXCEEDED.getCode());
        }
        if (msg.getGridFrequency() < turbineDetails.getGridFrequency() * (1-turbineDetails.getFrequencySafetyMargin()) || msg.getGridFrequency() > turbineDetails.getGridFrequency() * (1+turbineDetails.getFrequencySafetyMargin())) {
            errorCodes.add(TurbineOperationalErrorTypes.GRID_FREQUENCY_IMBALANCE.getCode());
        }
        if (turbineDetails.getMaxCurrent() < msg.getCurrent()) {
            errorCodes.add(TurbineOperationalErrorTypes.MAX_CURRENT_EXCEEDED.getCode());
        }
        if (turbineDetails.getMaxVoltage() < msg.getVoltage()) {
            errorCodes.add(TurbineOperationalErrorTypes.MAX_VOLTAGE_EXCEEDED.getCode());
        }
        if (turbineDetails.getMaxVibrationLevels() < msg.getVibrationLevels()) {
            errorCodes.add(TurbineOperationalErrorTypes.MAX_VIBRATION_EXCEEDED.getCode());
        }
        if (turbineDetails.getMaxHydraulicPressure() < msg.getHydraulicPressure()) {
            errorCodes.add(TurbineOperationalErrorTypes.MAX_HYDRAULIC_PRESSURE_EXCEEDED.getCode());
        }
        if (turbineDetails.getMinLubricantLevel() > msg.getLubricantLevel()) {
            errorCodes.add(TurbineOperationalErrorTypes.MIN_LUBRICANT_LEVEL_REACHED.getCode());
        }
        return errorCodes;
    }
}
