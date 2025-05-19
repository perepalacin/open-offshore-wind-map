package app.domain;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class EnvironmentalMsgData {

    // Metadata
    private long id;
    private long platformId;
    private OffsetDateTime timestamp;
    private boolean isValid;

    // Environmental Conditions
    private double waveHeight;
    private double wavePeriod;
    private double waterTemperature;
    private double airHumidity;
    private double windSpeed;
    private double windDirection;
    private double seaCurrentDirectionSurface;
    private double seaCurrentSpeedSurface;
    private double seaCurrentDirection5m;
    private double seaCurrentSpeed5m;
    private double seaCurrentDirection10m;
    private double seaCurrentSpeed10m;
    private double seaCurrentDirection15m;
    private double seaCurrentSpeed15m;
    private double waterGap;
    private double atmosphericPressure;
    private double waterSalinity;
    private float  phLevel;
}
