package app.repostitories;

import app.domain.FloaterMsgData;
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
public class FloaterRepository {

    private final JdbcTemplate jdbcTemplate;

    public long saveFloaterMsg(FloaterMsgData floaterMsgData) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("""
                    INSERT INTO offshore_wind.floater_msg_data (
                        platform_id,
                        is_valid,
                        pitch,
                        roll,
                        yaw,
                        surge,
                        sway,
                        heave,
                        latitude,
                        longitude
                    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """, Statement.RETURN_GENERATED_KEYS);

            ps.setLong(1, floaterMsgData.getPlatformId());
            ps.setBoolean(2, floaterMsgData.isValid());
            ps.setDouble(3, floaterMsgData.getPitch());
            ps.setDouble(4, floaterMsgData.getRoll());
            ps.setDouble(5, floaterMsgData.getYaw());
            ps.setDouble(6, floaterMsgData.getSurge());
            ps.setDouble(7, floaterMsgData.getSway());
            ps.setDouble(8, floaterMsgData.getHeave());
            ps.setDouble(9, floaterMsgData.getLatitude());
            ps.setDouble(10, floaterMsgData.getLongitude());

            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null || !keys.containsKey("id")) {
            throw new RuntimeException("Failed to retrieve generated ID for floater message");
        }
        return ((Number) keys.get("id")).longValue();
    }
}