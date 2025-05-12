package app.domain.dto;


public class FloaterDataDto {

    // Metadata
    private long id;
    private long platformId;

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
