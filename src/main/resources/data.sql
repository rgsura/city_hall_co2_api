INSERT INTO CO2_LEVELS (CITY, DISTRICT, CO2READING_VALUE, READING_TS) VALUES
('VIENNA', 'Penzing' , 20.5, CURRENT_TIMESTAMP());
INSERT INTO CO2_LEVELS (CITY, DISTRICT, CO2READING_VALUE, READING_TS) VALUES
('VIENNA', 'Penzing' , 20.45, TIMESTAMPADD(MINUTE, 5, CURRENT_TIMESTAMP()));
INSERT INTO CO2_LEVELS (CITY, DISTRICT, CO2READING_VALUE, READING_TS) VALUES
    ('VIENNA', 'Penzing' , 20.45, TIMESTAMPADD(DAY, -40, CURRENT_TIMESTAMP()));