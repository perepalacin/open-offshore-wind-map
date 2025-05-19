package app.repostitories;

import app.domain.EnvironmentalMsgData;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class EnvironmentalRepository {

    private final JdbcTemplate jdbcTemplate;

    public long saveEnvironmentalMsg(EnvironmentalMsgData msgData) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("""
                    INSERT INTO offshore_wind.environmental_msg_data (
                        platform_id,
                        is_valid,
                        timestamp,
                        wave_height,
                        wave_period,
                        water_temperature,
                        air_humidity,
                        wind_speed,
                        wind_direction,
                        sea_current_direction_surface,
                        sea_current_speed_surface,
                        sea_current_direction5m,
                        sea_current_speed5m,
                        sea_current_direction10m,
                        sea_current_speed10m,
                        sea_current_direction15m,
                        sea_current_speed15m,
                        water_gap,
                        atmospheric_pressure,
                        water_salinity,
                        ph_level
                    )
                    VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
                    """, Statement.RETURN_GENERATED_KEYS);

            ps.setLong(1, msgData.getPlatformId());
            ps.setBoolean(2, msgData.isValid());
            ps.setObject(3, msgData.getTimestamp()); // Using OffsetDateTime
            ps.setDouble(4, msgData.getWaveHeight());
            ps.setDouble(5, msgData.getWavePeriod());
            ps.setDouble(6, msgData.getWaterTemperature());
            ps.setDouble(7, msgData.getAirHumidity());
            ps.setDouble(8, msgData.getWindSpeed());
            ps.setDouble(9, msgData.getWindDirection());
            ps.setDouble(10, msgData.getSeaCurrentDirectionSurface());
            ps.setDouble(11, msgData.getSeaCurrentSpeedSurface());
            ps.setDouble(12, msgData.getSeaCurrentDirection5m());
            ps.setDouble(13, msgData.getSeaCurrentSpeed5m());
            ps.setDouble(14, msgData.getSeaCurrentDirection10m());
            ps.setDouble(15, msgData.getSeaCurrentSpeed10m());
            ps.setDouble(16, msgData.getSeaCurrentDirection15m());
            ps.setDouble(17, msgData.getSeaCurrentSpeed15m());
            ps.setDouble(18, msgData.getWaterGap());
            ps.setDouble(19, msgData.getAtmosphericPressure());
            ps.setDouble(20, msgData.getWaterSalinity());
            ps.setFloat(21, msgData.getPhLevel());

            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null || !keys.containsKey("id")) {
            throw new RuntimeException("Failed to retrieve generated entry id for environmental data");
        }
        return ((Number) keys.get("id")).longValue();
    }
}