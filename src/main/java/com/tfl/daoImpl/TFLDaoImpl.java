package com.tfl.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tfl.dao.TFLDao;
import com.tfl.model.TFL;
import com.tfl.util.UtilProperty;

@Repository
public class TFLDaoImpl implements TFLDao {
	private static Logger LOGGER = LoggerFactory.getLogger(TFLDaoImpl.class.getName());
	private ObjectMapper mapper = new ObjectMapper();
	private static final String db_url = UtilProperty.getValue("datasource.url");
	private static final String db_user_name = UtilProperty.getValue("datasource.username");
	private static final String db_password = UtilProperty.getValue("datasource.password");

	@Override
	public void saveTFLServices(List<TFL> tflResponse, String timeStamp) {
		LOGGER.info("TFLDaoImpl :: Entering saveTFLServices(...)");
		try (Connection conn = DriverManager.getConnection(db_url, db_user_name, db_password);) {
			for (TFL tfl : tflResponse) {
				Statement stmt = conn.createStatement();
				String sql = "insert into tfl (destination_name, direction, expected_arrival, line_id, line_name, mode_name, station_name, towards, vehicle_id, timestamp)"
						+ "values('" + tfl.getDestinationName() + "','" + tfl.getDirection() + "','"
						+ tfl.getExpectedArrival().toString() + "','" + tfl.getLineId() + "','" + tfl.getLineName()
						+ "','" + tfl.getModeName() + "','" + tfl.getStationName() + "','" + tfl.getTowards() + "','"
						+ tfl.getVehicleId() + "','" + timeStamp + "')";
				stmt = conn.createStatement();
				LOGGER.info("TFLDaoImpl :: Perpared insert query : " + sql);
				int i = stmt.executeUpdate(sql);
				if (i > 0) {
					LOGGER.info("TFLDaoImpl :: Record has been successfully inserted into TFL table");
				} else {
					LOGGER.info("TFLDaoImpl :: Record has been failed to insert into TFL table");
				}
			}
		} catch (SQLException e) {
			LOGGER.error("TFLDaoImpl :: Exception occured in saveTFLServices(...) due to " + e.getMessage());
		}
		LOGGER.info("TFLDaoImpl :: Exiting saveTFLServices(...)");
	}

	@Override
	public List<TFL> fetchHistory() {
		LOGGER.info("TFLDaoImpl :: Entering fetchHistory(...)");
		List<TFL> tfls = new ArrayList<TFL>();
		try (Connection conn = DriverManager.getConnection(db_url, db_user_name, db_password);) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM tfl group by timestamp, id;";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			LOGGER.info("TFLDaoImpl :: Perpared select query : " + sql);
			while (rs.next()) {
				TFL tfl = new TFL();
				tfl.setDestinationName(rs.getString(2));
				tfl.setDirection(rs.getString(3));
				tfl.setExpectedArrival(new SimpleDateFormat("E MMM dd hh:mm:ss Z yyyy").parse(rs.getString(4)));
				tfl.setLineId(rs.getString(5));
				tfl.setLineName(rs.getString(6));
				tfl.setModeName(rs.getString(7));
				tfl.setStationName(rs.getString(8));
				tfl.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(9)));
				tfl.setTowards(rs.getString(10));
				tfl.setVehicleId(rs.getString(11));
				tfls.add(tfl);
			}
			LOGGER.info("TFLDaoImpl :: Select query has executed successfully : " + mapper.writeValueAsString(tfls));
		} catch (SQLException e) {
			LOGGER.error("TFLDaoImpl :: SQLException occured in fetchHistory(...) due to " + e.getMessage());
		} catch (ParseException e) {
			LOGGER.error("TFLDaoImpl :: ParseException occured in fetchHistory(...) due to " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("TFLDaoImpl :: Exception occured in fetchHistory(...) due to " + e.getMessage());
		}
		LOGGER.info("TFLDaoImpl :: Exiting fetchHistory(...)");
		return tfls;
	}

	@Override
	public List<TFL> fetchTFLSearchHistory(TFL tflRequest) {
		LOGGER.info("TFLDaoImpl :: Entering fetchTFLSearchHistory(...)");
		List<TFL> tfls = new ArrayList<TFL>();
		List<String> clauses = new ArrayList<String>();
		List<Object> parameters = new ArrayList<Object>();

		try (Connection conn = DriverManager.getConnection(db_url, db_user_name, db_password);) {
			String sql = "select * from tfl";

			if (tflRequest.getVehicleId() != null && !tflRequest.getVehicleId().equals("")) {
				clauses.add("vehicle_id = ?");
				parameters.add(tflRequest.getVehicleId());
			}

			if (tflRequest.getStationName() != null && !tflRequest.getStationName().equals("")) {
				clauses.add("station_name = ?");
				parameters.add(tflRequest.getStationName());
			}

			if (tflRequest.getLineName() != null && !tflRequest.getLineName().equals("")) {
				clauses.add("line_name = ?");
				parameters.add(tflRequest.getLineName());
			}

			if (tflRequest.getDestinationName() != null && !tflRequest.getDestinationName().equals("")) {
				clauses.add("destination_name = ?");
				parameters.add(tflRequest.getDestinationName());
			}

			if (tflRequest.getTowards() != null && !tflRequest.getTowards().equals("")) {
				clauses.add("towards = ?");
				parameters.add(tflRequest.getTowards());
			}

			if (tflRequest.getExpectedArrival() != null && !tflRequest.getExpectedArrival().equals("")) {
				clauses.add("expected_arrival BETWEEN ? AND ?");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(tflRequest.getExpectedArrival());
				calendar.add(Calendar.SECOND, 59);
				parameters.add(tflRequest.getExpectedArrival().toString());
				parameters.add(calendar.getTime().toString());
			}

			if (tflRequest.getDirection() != null && !tflRequest.getDirection().equals("")) {
				clauses.add("direction = ?");
				parameters.add(tflRequest.getDirection());
			}

			if (!clauses.isEmpty()) {
				sql += " where " + StringUtils.join(clauses, " and ");
			}

			PreparedStatement ps = conn.prepareStatement(sql);
			for (int i = 0; i < parameters.size(); i++) {
				ps.setObject(i + 1, parameters.get(i));
			}
			LOGGER.info("TFLDaoImpl :: Perpared select query : " + ps.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TFL tfl = new TFL();
				tfl.setDestinationName(rs.getString(2));
				tfl.setDirection(rs.getString(3));
				tfl.setExpectedArrival(new SimpleDateFormat("E MMM dd hh:mm:ss Z yyyy").parse(rs.getString(4)));
				tfl.setLineId(rs.getString(5));
				tfl.setLineName(rs.getString(6));
				tfl.setModeName(rs.getString(7));
				tfl.setStationName(rs.getString(8));
				tfl.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(9)));
				tfl.setTowards(rs.getString(10));
				tfl.setVehicleId(rs.getString(11));
				tfls.add(tfl);
			}
			LOGGER.info("TFLDaoImpl :: Select query has executed successfully : " + mapper.writeValueAsString(tfls));
		} catch (SQLException e) {
			LOGGER.error("TFLDaoImpl :: SQLException occured in fetchTFLSearchHistory(...) due to " + e.getMessage());
		} catch (ParseException e) {
			LOGGER.error("TFLDaoImpl :: ParseException occured in fetchTFLSearchHistory(...) due to " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("TFLDaoImpl :: Exception occured in fetchTFLSearchHistory(...) due to " + e.getMessage());
		}
		LOGGER.info("TFLDaoImpl :: Exiting fetchTFLSearchHistory(...)");
		return tfls;
	}

}
