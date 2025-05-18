package app.repostitories;

import app.domain.Coordinates;
import app.domain.PlatformDetails;
import app.domain.TurbineDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlatformRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<PlatformDetails> getAll () {

        String sqlStatement = """
                SELECT
                    id,
                    name,
                    latitude,
                    longitude,
                    location,
                    manufacturer,
                    operator
                FROM
                    offshore_wind.platform_details
                ORDER BY
                    id;
                """;

        return jdbcTemplate.query(sqlStatement, (rs, rowNum) -> {

            Coordinates coordinates = new Coordinates(rs.getLong("latitude"), rs.getLong("longitude"));

            PlatformDetails plaform = PlatformDetails.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .coordinates(coordinates)
                    .location(rs.getString("location"))
                    .manufacturer(rs.getString("manufacturer"))
                    .operator(rs.getString("operator"))
                    .build();
            return plaform;

        });
    }

    public PlatformDetails getById (long platformId) {

        String sqlStatement = """
                SELECT
                    p.id,
                    t.id AS turb_id,
                    p.name,
                    p.latitude,
                    p.longitude,
                    p.location,
                    p.manufacturer,
                    p.operator,
                    p.weight,
                    p.hub_height,
                    t.name AS turb_name,
                    t.manufacturer AS turb_manu,
                    t.nominal_power,
                    t.blade_length,
                    t.weight AS turb_weight,
                    t.blade_count,
                    t.installation_date AS turb_date,
                    p.installation_date,
                    t.grid_frequency
                FROM
                    offshore_wind.platform_details AS p
                LEFT JOIN offshore_wind.turbine_details as t
                    ON t.platform_id = p.id
                WHERE
                    p.id = ?
                LIMIT
                    1;
                """;

        List<PlatformDetails> platforms = jdbcTemplate.query(sqlStatement, (rs, rowNum) -> {

            TurbineDetails turbineDetails = TurbineDetails.builder()
                    .id(rs.getLong("turb_id"))
                    .platformId(rs.getLong("id"))
                    .name(rs.getString("turb_name"))
                    .manufacturer(rs.getString("turb_manu"))
                    .nominalPower(rs.getDouble("nominal_power"))
                    .bladeLength(rs.getDouble("blade_length"))
                    .bladeCount(rs.getDouble("blade_count"))
                    .gridFrequency(rs.getDouble("grid_frequency"))
                    .weight(rs.getDouble("turb_weight"))
                    .installationDate(rs.getString("turb_date"))
                    .build();

            Coordinates coordinates = new Coordinates(rs.getLong("latitude"), rs.getLong("longitude"));

            PlatformDetails plaform = PlatformDetails.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .coordinates(coordinates)
                    .location(rs.getString("location"))
                    .manufacturer(rs.getString("manufacturer"))
                    .operator(rs.getString("operator"))
                    .weight(rs.getDouble("weight"))
                    .hubHeight(rs.getDouble("hub_height"))
                    .installationDate(rs.getString("installation_date"))
                    .turbineDetails(turbineDetails)
                    .build();
            return plaform;

        }, platformId);

        return  platforms.isEmpty() ? null : platforms.get(0);
    }

}
