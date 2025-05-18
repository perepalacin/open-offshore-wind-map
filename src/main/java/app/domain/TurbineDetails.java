package app.domain;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurbineDetails {
    private long id;
    private long platformId;
    private String name;
    private String manufacturer;
    private double nominalPower; // in W
    private double bladeLength;
    private double bladeCount;
    private double weight;
    private String installationDate;
    private double gridFrequency;
    private double maxRPM;
    private double frequencySafetyMargin;
    private double maxCurrent;
    private double maxVoltage;
    private double maxVibrationLevels;
    private double maxHydraulicPressure;
    private double minLubricantLevel;
}
