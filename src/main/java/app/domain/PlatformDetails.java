package app.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlatformDetails {
    private Long id; // platformId
    private String name;
    private Coordinates coordinates;
    private String location; // Name of the see
    private double weight;
    private double hubHeight; // in meters
    private String manufacturer;
    private String operator;
    private String installationDate;
    private TurbineDetails turbineDetails;
}
