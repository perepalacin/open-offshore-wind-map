package app.repostitories;

import app.domain.Coordinates;
import app.domain.PlatformDetails;
import app.domain.TurbineDetails;
import app.domain.TurbineMsgData;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class TurbineRepository {

    private final JdbcTemplate jdbcTemplate;

    public long saveTurbineMsg (TurbineMsgData turbineMsgData) {
        // Timestamp is missing!
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("""
                    INSERT INTO offshore_wind.turbine_msg_data (
                        turbine_id,
                        is_valid,
                        wind_direction,
                        wind_speed,
                        rotor_speed,
                        yaw_angle,
                        pitch_angle,
                        power_output,
                        reactive_power,
                        grid_frequency,
                        voltage,
                        current,
                        vibration_levels,
                        hydraulic_pressure,
                        lubricant_level,
                        created_at_local
                    ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
                    """,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, turbineMsgData.getTurbineId());
            ps.setBoolean(2, turbineMsgData.isValid());
            ps.setDouble(3, turbineMsgData.getWindDirection());
            ps.setDouble(4, turbineMsgData.getWindSpeed());
            ps.setDouble(5, turbineMsgData.getRotorSpeed());
            ps.setDouble(6, turbineMsgData.getYawAngle());
            ps.setDouble(7, turbineMsgData.getPitchAngle());
            ps.setDouble(8, turbineMsgData.getPowerOutput());
            ps.setDouble(9, turbineMsgData.getReactivePower());
            ps.setDouble(10, turbineMsgData.getGridFrequency());
            ps.setDouble(11, turbineMsgData.getVoltage());
            ps.setDouble(12, turbineMsgData.getCurrent());
            ps.setDouble(13, turbineMsgData.getVibrationLevels());
            ps.setDouble(14, turbineMsgData.getHydraulicPressure());
            ps.setDouble(15, turbineMsgData.getLubricantLevel());
            ps.setObject(16, turbineMsgData.getCreatedAtLocal());
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null || !keys.containsKey("id")) {
            throw new RuntimeException("Failed to retrieve generated entry Id");
        }
        return (((Number) keys.get("id")).longValue());
    }

    public TurbineDetails getById (long id) {
        String sqlStatement = """
                SELECT
                    id,
                    nominal_power,
                    grid_frequency,
                    frequency_safety_margin,
                    max_rpm,
                    max_voltage,
                    max_current,
                    max_hydraulic_pressure,
                    min_lubricant_level,
                    max_vibration_level
                FROM
                    offshore_wind.turbine_details
                WHERE
                    id = ?
                LIMIT
                    1;
                """;

        List<TurbineDetails> turbines = jdbcTemplate.query(sqlStatement, (rs, rowNum) -> {

            return TurbineDetails.builder()
                    .id(rs.getLong("id"))
                    .nominalPower(rs.getDouble("nominal_power"))
                    .gridFrequency(rs.getDouble("grid_frequency"))
                    .frequencySafetyMargin(rs.getDouble("frequency_safety_margin"))
                    .maxRPM(rs.getDouble("max_rpm"))
                    .maxVoltage(rs.getDouble("max_voltage"))
                    .maxCurrent(rs.getDouble("max_current"))
                    .maxHydraulicPressure(rs.getDouble("max_hydraulic_pressure"))
                    .minLubricantLevel(rs.getDouble("min_lubricant_level"))
                    .maxVibrationLevels(rs.getDouble("max_vibration_level"))
                    .build();
            }, id);

        return  turbines.isEmpty() ? null : turbines.get(0);
    }

    public void saveTurbineErrors (Set<Short> errors, long msgId) {
        String updateSetsSQL = """
            INSERT INTO offshore_wind.turbine_msg_errors (
                msg_id,
                error_code)
            VALUES (?, ?)
        """;

        jdbcTemplate.batchUpdate(updateSetsSQL, errors, errors.size(),
                (ps, error) -> {
                    ps.setLong(1, msgId);
                    ps.setShort(2, error);
        });
    }

}
