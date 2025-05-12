package app.domain.dto;

import java.time.OffsetDateTime;

public class TurbineDataDto {

    private Long id;
    private Long turbineId;
    private OffsetDateTime timestamp;

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

    public TurbineDataDto() {
    }

    public TurbineDataDto(Long id, Long turbineId) {
        this.id = id;
        this.turbineId = turbineId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTurbineId() {
        return turbineId;
    }

    public void setTurbineId(Long turbineId) {
        this.turbineId = turbineId;
    }

    @Override
    public String toString() {
        return "TurbineDataDto{" +
                "id=" + id +
                ", turbineId=" + turbineId +
                '}';
    }
}

