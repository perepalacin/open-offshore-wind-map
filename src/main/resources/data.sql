-- Inserting data into offshore_wind.platform_details

INSERT INTO offshore_wind.platform_details (id, name, manufacturer, operator, latitude, longitude, location, weight, hub_height, installation_date) VALUES
(201, 'Poseidon-Alpha', 'OWFloater AS', 'Equinor', 56.2, 3.1, 'North Sea - Norway', 1800.7, 110.5, '2024-09-10 12:00:00'),
(202, 'Poseidon-Beta', 'BW Ideol', 'Iberdrola', 56.3, 3.2, 'North Sea - Norway', 1950.3, 115.8, '2024-10-25 15:30:00'),
(203, 'Aeolus-Gamma', 'Principle Power', 'Orsted', 55.8, 2.5, 'North Sea - Denmark', 1750.9, 108.2, '2025-01-15 09:45:00'),
(204, 'Aeolus-Delta', 'SBM Offshore', 'SSE Renewables', 55.9, 2.6, 'North Sea - Denmark', 1880.1, 112.0, '2025-03-05 17:00:00'),
(205, 'Triton-Epsilon', 'Navantia-Windar', 'EDP Renewables', 40.5, -7.8, 'Atlantic Ocean - Portugal', 2000.0, 120.0, '2023-12-01 11:15:00'),
(206, 'Triton-Zeta', 'Ideol-JGC', 'Repsol', 40.6, -7.9, 'Atlantic Ocean - Portugal', 1920.5, 118.3, '2024-02-10 08:30:00'),
(207, 'Oceanus-Eta', 'Technip Energies', 'TotalEnergies', 48.1, -5.2, 'Atlantic Ocean - France', 1850.2, 111.5, '2024-07-20 14:20:00'),
(208, 'Oceanus-Theta', 'Saipem', 'ENGIE', 48.2, -5.3, 'Atlantic Ocean - France', 1980.8, 117.0, '2024-11-01 18:45:00'),
(209, 'Boreas-Iota', 'Stiesdal Offshore', 'Vattenfall', 54.7, 4.5, 'North Sea - Netherlands', 1780.6, 109.8, '2025-02-28 13:10:00'),
(210, 'Boreas-Kappa', 'X1 Wind', 'Eneco', 54.8, 4.6, 'North Sea - Netherlands', 1900.4, 114.2, '2025-04-15 07:45:00'),
(211, 'Levante-Lambda', 'Energinet', 'Naturgy', 38.5, 0.3, 'Mediterranean Sea - Spain', 2050.3, 122.5, '2024-05-10 16:00:00'),
(212, 'Levante-Mu', 'BlueFloat Energy', 'Acciona Energia', 38.6, 0.4, 'Mediterranean Sea - Spain', 1970.1, 119.0, '2024-08-01 10:45:00'),
(213, 'Scirocco-Nu', 'Floating Power Plant', 'ERM', 37.2, 15.1, 'Mediterranean Sea - Italy', 1890.7, 113.3, '2025-05-20 19:15:00'),
(214, 'Scirocco-Xi', 'Hexicon', 'Eni', 37.3, 15.2, 'Mediterranean Sea - Italy', 1930.5, 116.8, '2025-06-05 12:30:00'),
(215, 'Mistral-Omicron', 'Eolink', 'EDF Renouvelables', 49.5, -3.0, 'Atlantic Ocean - France', 1810.9, 110.5, '2024-12-20 09:15:00'),
(216, 'Mistral-Pi', 'Gazelle Wind Power', 'Green Power Investment', 49.6, -3.1, 'Atlantic Ocean - France', 1960.2, 117.7, '2025-03-20 14:30:00'),
(217, 'Tramontana-Rho', 'Saitec Offshore', 'Ocean Winds', 42.1, 2.8, 'Mediterranean Sea - Spain', 2020.8, 121.0, '2024-11-15 17:45:00'),
(218, 'Tramontana-Sigma', 'EnerOcean', 'Capital Energy', 42.2, 2.9, 'Mediterranean Sea - Spain', 1990.6, 119.5, '2025-04-01 08:00:00'),
(219, 'Grecale-Tau', 'OO-Star', 'Corio Generation', 39.8, 18.5, 'Mediterranean Sea - Greece', 1870.4, 112.8, '2025-01-05 12:15:00'),
(220, 'Grecale-Upsilon', 'Atlantis Operations', 'Mytilineos', 39.9, 18.6, 'Mediterranean Sea - Greece', 1910.1, 115.1, '2025-07-01 18:00:00');

-- Inserting data into offshore_wind.turbine_details

INSERT INTO offshore_wind.turbine_details (id, platform_id, name, manufacturer, weight, nominal_power, blade_length, blade_count, grid_frequency, installation_date) VALUES
(2011, 201, 'V236-15.0 MW', 'Vestas', 800.5, 15000, 115, 3, 50.0, '2024-09-10 13:00:00'),
(2021, 202, 'SG 14-236 DD', 'Siemens Gamesa', 850.2, 14000, 118, 3, 50.0, '2024-10-25 16:30:00'),
(2031, 203, 'Haliade-X 14 MW', 'GE Renewable Energy', 780.8, 14000, 107, 3, 50.0, '2025-01-15 10:45:00'),
(2041, 204, 'V236-15.0 MW', 'Vestas', 810.1, 15000, 115, 3, 50.0, '2025-03-05 18:00:00'),
(2051, 205, 'SG 14-236 DD', 'Siemens Gamesa', 860.0, 14000, 118, 3, 50.0, '2023-12-01 12:15:00'),
(2061, 206, 'Haliade-X 13 MW', 'GE Renewable Energy', 790.5, 13000, 107, 3, 50.0, '2024-02-10 09:30:00'),
(2071, 207, 'V236-15.0 MW', 'Vestas', 820.2, 15000, 115, 3, 50.0, '2024-07-20 15:20:00'),
(2081, 208, 'SG 14-236 DD', 'Siemens Gamesa', 870.9, 14000, 118, 3, 50.0, '2024-11-01 19:45:00'),
(2091, 209, 'Haliade-X 12 MW', 'GE Renewable Energy', 770.6, 12000, 107, 3, 50.0, '2025-02-28 14:10:00'),
(2101, 210, 'V236-15.0 MW', 'Vestas', 830.4, 15000, 115, 3, 50.0, '2025-04-15 08:45:00'),
(2111, 211, 'SG 11-200 DD', 'Siemens Gamesa', 750.3, 11000, 100, 3, 50.0, '2024-05-10 17:00:00'),
(2121, 212, 'Acciona AW1500', 'Acciona', 700.1, 6000, 80, 3, 50.0, '2024-08-01 11:45:00'),
(2131, 213, 'Enercon E-160 EP5', 'Enercon', 720.7, 5500, 78, 3, 50.0, '2025-05-20 20:15:00'),
(2141, 214, 'Vestas V164-10.0 MW', 'Vestas', 790.5, 10000, 82, 3, 50.0, '2025-06-05 13:30:00'),
(2151, 215, 'Haliade-X 12 MW', 'GE Renewable Energy', 760.9, 12000, 107, 3, 50.0, '2024-12-20 10:15:00'),
(2161, 216, 'SG 11-200 DD', 'Siemens Gamesa', 740.2, 11000, 100, 3, 50.0, '2025-03-20 15:30:00'),
(2171, 217, 'Adwen AD 5-135', 'Adwen', 680.8, 5000, 67, 3, 50.0, '2024-11-15 18:45:00'),
(2181, 218, 'Gamesa G132-5.0 MW', 'Siemens Gamesa', 710.6, 5000, 66, 3, 50.0, '2025-04-01 09:00:00'),
(2191, 219, 'Enercon E-147 EP5', 'Enercon', 730.4, 4200, 73.5, 3, 50.0, '2025-01-05 13:15:00'),
(2201, 220, 'Vestas V150-4.2 MW', 'Vestas', 700.1, 4200, 74, 3, 50.0, '2025-07-01 19:00:00');

-- Inserting data into offshore_wind.turbine_status

INSERT INTO offshore_wind.turbine_status (turbine_id, status) VALUES
(2011, 'Operational'),
(2021, 'Maintenance'),
(2031, 'Operational'),
(2041, 'Operational'),
(2051, 'Fault Detected'),
(2061, 'Operational'),
(2071, 'Operational'),
(2081, 'Maintenance'),
(2091, 'Operational'),
(2101, 'Operational'),
(2111, 'Operational'),
(2121, 'Fault Detected'),
(2131, 'Operational'),
(2141, 'Maintenance'),
(2151, 'Operational'),
(2161, 'Operational'),
(2171, 'Operational'),
(2181, 'Operational'),
(2191, 'Maintenance'),
(2201, 'Operational');

INSERT INTO offshore_wind.turbine_operational_error_types (error_code, error_name, error_description) VALUES
(101, 'HIGH_YAW_MISALIGNMENT', 'Significant difference between wind direction and yaw angle when wind speed is non-zero.'),
(102, 'MAX_ROTOR_SPEED_EXCEEDED', 'Rotor speed is above the maximum allowed RPM.'),
(103, 'GRID_FREQUENCY_IMBALANCE', 'Grid frequency is outside the acceptable safety margin.'),
(104, 'MAX_CURRENT_EXCEEDED', 'Measured current is above the maximum limit.'),
(105, 'MAX_VOLTAGE_EXCEEDED', 'Measured voltage is above the maximum limit.'),
(106, 'MAX_VIBRATION_EXCEEDED', 'Vibration levels are above the maximum limit.'),
(107, 'MAX_HYDRAULIC_PRESSURE_EXCEEDED', 'Hydraulic pressure is above the maximum limit.'),
(108, 'MIN_LUBRICANT_LEVEL_REACHED', 'Lubricant level is at or below the minimum required level.')
ON CONFLICT (error_code) DO NOTHING; -- Or ON CONFLICT (error_name) DO NOTHING;