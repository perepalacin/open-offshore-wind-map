DROP SCHEMA IF EXISTS offshore_wind CASCADE;

CREATE SCHEMA offshore_wind;
SET search_path TO offshore_wind;

CREATE TABLE IF NOT EXISTS offshore_wind.platform_details (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    manufacturer VARCHAR(255),
    operator VARCHAR(255),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    location VARCHAR(255),
    weight DOUBLE PRECISION,
    hub_height DOUBLE PRECISION,
    installation_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS offshore_wind.turbine_details (
    -- All values are nominal!
    id BIGINT PRIMARY KEY,
    platform_id BIGINT,
    name VARCHAR(255),
    manufacturer VARCHAR(255),
    weight DOUBLE PRECISION,
    nominal_power BIGINT,
    blade_length BIGINT,
    blade_count SMALLINT,
    grid_frequency DOUBLE PRECISION,
    installation_date TIMESTAMP,
    frequency_safety_margin DOUBLE PRECISION,
    max_rpm DOUBLE PRECISION,
    max_voltage DOUBLE PRECISION,
    max_current DOUBLE PRECISION,
    max_hydraulic_pressure DOUBLE PRECISION,
    min_lubricant_level DOUBLE PRECISION,
    max_vibration_level DOUBLE PRECISION,
    CONSTRAINT fk_platform
    FOREIGN KEY (platform_id)
    REFERENCES offshore_wind.platform_details (id)
);

CREATE TABLE IF NOT EXISTS offshore_wind.turbine_status (
    turbine_id BIGINT,
    status VARCHAR(255),
    CONSTRAINT fk_turbine
    FOREIGN KEY (turbine_id)
    REFERENCES offshore_wind.turbine_details (id)
);

CREATE TABLE IF NOT EXISTS offshore_wind.turbine_msg_data (
    id BIGSERIAL PRIMARY KEY,
    turbine_id BIGINT NOT NULL,
    created_at_local TIMESTAMPTZ,
    wind_direction DOUBLE PRECISION,
    wind_speed DOUBLE PRECISION,
    rotor_speed DOUBLE PRECISION,
    yaw_angle DOUBLE PRECISION,
    pitch_angle DOUBLE PRECISION,
    power_output DOUBLE PRECISION,
    reactive_power DOUBLE PRECISION,
    grid_frequency FLOAT,
    voltage DOUBLE PRECISION,
    current DOUBLE PRECISION,
    vibration_levels DOUBLE PRECISION,
    hydraulic_pressure DOUBLE PRECISION,
    lubricant_level DOUBLE PRECISION,
    is_valid BOOLEAN,
    CONSTRAINT fk_turbine_id
    FOREIGN KEY (turbine_id)
    REFERENCES offshore_wind.turbine_details (id)
);

CREATE TABLE IF NOT EXISTS offshore_wind.turbine_operational_error_types (
    error_code SMALLINT PRIMARY KEY,
    error_name VARCHAR(50) UNIQUE NOT NULL,
    error_description VARCHAR(255)
);

COMMENT ON TABLE offshore_wind.turbine_operational_error_types IS 'Lookup table for specific turbine operational or sensor error types.';
COMMENT ON COLUMN offshore_wind.turbine_operational_error_types.error_code IS 'Numeric code representing the specific turbine error condition.';
COMMENT ON COLUMN offshore_wind.turbine_operational_error_types.error_name IS 'Short, unique name for the error type (e.g., HIGH_YAW_MISALIGNMENT).';
COMMENT ON COLUMN offshore_wind.turbine_operational_error_types.error_description IS 'Human-readable description of the turbine error condition.';

CREATE TABLE IF NOT EXISTS offshore_wind.turbine_msg_errors (
    id BIGSERIAL PRIMARY KEY,
    msg_id BIGINT,
    error_code INT,
    CONSTRAINT fk_turbine_msgs
        FOREIGN KEY (msg_id)
        REFERENCES offshore_wind.turbine_msg_data (id),
    CONSTRAINT fk_turbine_operational_error_types
        FOREIGN KEY (error_code)
        REFERENCES offshore_wind.turbine_operational_error_types (error_code)
);

CREATE TABLE IF NOT EXISTS offshore_wind.floater_msg_data (
    id BIGSERIAL PRIMARY KEY,
    platform_id BIGINT NOT NULL,
    pitch DOUBLE PRECISION,
    roll DOUBLE PRECISION,
    yaw DOUBLE PRECISION,
    surge DOUBLE PRECISION,
    sway DOUBLE PRECISION,
    heave DOUBLE PRECISION,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    is_valid BOOLEAN,
    CONSTRAINT fk_platform_floater
    FOREIGN KEY (platform_id)
    REFERENCES offshore_wind.platform_details (id)
);

CREATE TABLE IF NOT EXISTS offshore_wind.floater_msg_errors (
    id BIGSERIAL PRIMARY KEY,
    msg_id BIGINT,
    info VARCHAR(511),
    CONSTRAINT fk_floater_msgs
    FOREIGN KEY (msg_id)
    REFERENCES offshore_wind.floater_msg_data (id)
);

CREATE TABLE IF NOT EXISTS offshore_wind.environmental_msg_data (
    id BIGSERIAL PRIMARY KEY,
    platform_id BIGINT NOT NULL,
    timestamp TIMESTAMPTZ,
    wave_height DOUBLE PRECISION,
    wave_period DOUBLE PRECISION,
    water_temperature DOUBLE PRECISION,
    air_humidity DOUBLE PRECISION,
    wind_speed DOUBLE PRECISION,
    wind_direction DOUBLE PRECISION,
    sea_current_direction_surface DOUBLE PRECISION,
    sea_current_speed_surface DOUBLE PRECISION,
    sea_current_direction5m DOUBLE PRECISION,
    sea_current_speed5m DOUBLE PRECISION,
    sea_current_direction10m DOUBLE PRECISION,
    sea_current_speed10m DOUBLE PRECISION,
    sea_current_direction15m DOUBLE PRECISION,
    sea_current_speed15m DOUBLE PRECISION,
    water_gap DOUBLE PRECISION,
    atmospheric_pressure DOUBLE PRECISION,
    water_salinity DOUBLE PRECISION,
    ph_level FLOAT,
    is_valid BOOLEAN,
    CONSTRAINT fk_platform_environment
    FOREIGN KEY (platform_id)
    REFERENCES offshore_wind.platform_details (id)
);

CREATE TABLE IF NOT EXISTS offshore_wind.environment_msg_errors (
    id BIGSERIAL PRIMARY KEY,
    msg_id BIGINT,
    info VARCHAR(511),
    CONSTRAINT fk_environmental_msg_data
    FOREIGN KEY (msg_id)
    REFERENCES offshore_wind.environmental_msg_data (id)
);

--CREATE TABLE IF NOT EXISTS offshore_wind.platform_msg_data (
--    id BIGINT PRIMARY KEY,
--    farm_id BIGINT,
--    status VARCHAR(255),
--    total_power_output DOUBLE PRECISION,
--    total_energy_produced DOUBLE PRECISION,
--    average_wind_speed DOUBLE PRECISION,
--    availability DOUBLE PRECISION,
--    grid_frequency DOUBLE PRECISION,
--    voltage DOUBLE PRECISION,
--    power_factor DOUBLE PRECISION,
--    reactive_power DOUBLE PRECISION,
--    curtailment DOUBLE PRECISION
--);
