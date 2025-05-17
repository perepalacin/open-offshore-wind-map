package app.domain;

import java.time.OffsetDateTime;

public class TurbineMsgData {

    private Long id;
    private Long platformId;
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

    public TurbineMsgData() {
    }

    public TurbineMsgData(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TurbineDataDto{" +
                "id=" + id +
                '}';
    }
}

