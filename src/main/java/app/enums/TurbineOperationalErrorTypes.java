package app.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum TurbineOperationalErrorTypes {

    HIGH_YAW_MISALIGNMENT((short) 101, "HIGH_YAW_MISALIGNMENT", "Significant difference between wind direction and yaw angle when wind speed is non-zero."),
    MAX_ROTOR_SPEED_EXCEEDED((short) 102, "MAX_ROTOR_SPEED_EXCEEDED", "Rotor speed is above the maximum allowed RPM."),
    GRID_FREQUENCY_IMBALANCE((short) 103, "GRID_FREQUENCY_IMBALANCE", "Grid frequency is outside the acceptable safety margin."),
    MAX_CURRENT_EXCEEDED((short) 104, "MAX_CURRENT_EXCEEDED", "Measured current is above the maximum limit."),
    MAX_VOLTAGE_EXCEEDED((short) 105, "MAX_VOLTAGE_EXCEEDED", "Measured voltage is above the maximum limit."),
    MAX_VIBRATION_EXCEEDED((short) 106, "MAX_VIBRATION_EXCEEDED", "Vibration levels are above the maximum limit."),
    MAX_HYDRAULIC_PRESSURE_EXCEEDED((short) 107, "MAX_HYDRAULIC_PRESSURE_EXCEEDED", "Hydraulic pressure is above the maximum limit."),
    MIN_LUBRICANT_LEVEL_REACHED((short) 108, "MIN_LUBRICANT_LEVEL_REACHED", "Lubricant level is at or below the minimum required level.");

    private final short code;
    private final String name;
    private final String description;
}