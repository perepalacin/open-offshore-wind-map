package app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FloaterMsgData {

    // Metadata
    private long id;
    private long platformId;
    private boolean isValid;

    // Rotation
    private double pitch;
    private double roll;
    private double yaw;

    // Translation
    private double surge;
    private double sway;
    private double heave;
    // GPS Tracking
    private double latitude;
    private double longitude;
}
