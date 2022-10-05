DROP DATABASE IF EXISTS tfl;
CREATE SCHEMA tfl;
DROP TABLE IF EXISTS tfl.tfl;

CREATE TABLE tfl.tfl (id int NOT NULL AUTO_INCREMENT, 
destination_name varchar(255) DEFAULT NULL, 
direction varchar(255) DEFAULT NULL,
expected_arrival varchar(255) DEFAULT NULL,
line_id varchar(255) DEFAULT NULL, 
line_name varchar(255) DEFAULT NULL, 
mode_name varchar(255) DEFAULT NULL,
station_name varchar(255) DEFAULT NULL, 
timestamp datetime(6) DEFAULT NULL, 
towards varchar(255) DEFAULT NULL,
vehicle_id varchar(255) DEFAULT NULL, PRIMARY KEY (id));