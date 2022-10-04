package com.tfl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan
@SpringBootApplication
public class TFLSystemApplication {

	static final String DB_URL = "jdbc:mysql://localhost:3306/tfl";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {
		SpringApplication.run(TFLSystemApplication.class, args);
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();) {
			String sql = "DROP TABLE IF EXISTS tfl";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE tfl (" + "id int NOT NULL AUTO_INCREMENT," + "destination_name varchar(255) DEFAULT NULL,"
					+ "direction varchar(255) DEFAULT NULL," + "expected_arrival varchar(255) DEFAULT NULL,"
					+ "line_id varchar(255) DEFAULT NULL," + "line_name varchar(255) DEFAULT NULL,"
					+ "mode_name varchar(255) DEFAULT NULL," + "station_name varchar(255) DEFAULT NULL,"
					+ "timestamp datetime(6) DEFAULT NULL,"
					+ "towards varchar(255) DEFAULT NULL," + "vehicle_id varchar(255) DEFAULT NULL,"
					+ "PRIMARY KEY (id))";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
