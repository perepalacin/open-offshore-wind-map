package app.domain;

public class PlatformMsgData {

    private long id;
    private long farmId;

    private String status;
    private double totalPowerOutput;
    private double totalEnergyProduced;
    private double averageWindSpeed;
    private double availability;
    private double gridFrequency; // Actual frequency
    private double voltage;
    private double powerFactor;
    private double reactivePower;
    private double curtailment;
}
