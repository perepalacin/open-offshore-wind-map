package app.domain;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TurbineMsgData {

    private Long id;
    private Long turbineId;
    private OffsetDateTime createdAtLocal;
    private boolean isValid;

    private double windDirection;
    private double windSpeed;

    private double rotorSpeed;
    private double yawAngle;
    private double pitchAngle;
    private double powerOutput;
    private double reactivePower;
    private float gridFrequency;
    private double voltage;
    private double current;
    private double vibrationLevels;
    private double hydraulicPressure;
    private double lubricantLevel;
}

