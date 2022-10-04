package com.tfl.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfl.dao.TFLDao;
import com.tfl.model.TFL;
import com.tfl.service.TFLService;

@Service
public class TFLServiceImpl implements TFLService {

	private static Logger LOGGER = LoggerFactory.getLogger(TFLServiceImpl.class.getName());

	@Autowired
	TFLDao tflDao;

	public void saveTFLServices(List<TFL> tflResponse, String timeStamp) {
		LOGGER.info("TFLServiceImpl :: Entering saveTFLServices(...)");
		try {
			tflDao.saveTFLServices(tflResponse, timeStamp);
		} catch (Exception e) {
			LOGGER.error("TFLServiceImpl :: Exception occured in saveTFLServices(...) due to " + e.getMessage());
		}
		LOGGER.info("TFLServiceImpl :: Exiting saveTFLServices(...)");
	}

	@Override
	public Map<Date, List<TFL>> fetchHistory() {
		Map<Date, List<TFL>> fetchHistoryResponse = null;
		LOGGER.info("TFLServiceImpl :: Entering fetchHistory(...)");
		try {
			List<TFL> tfls = tflDao.fetchHistory();
			fetchHistoryResponse = tfls.stream().collect(Collectors.groupingBy(TFL::getTimestamp));
		} catch (Exception e) {
			LOGGER.error("TFLServiceImpl :: Exception occured in fetchHistory(...) due to " + e.getMessage());
		}
		LOGGER.info("TFLServiceImpl :: Exiting fetchHistory(...)");
		return fetchHistoryResponse;
	}

	@Override
	public Map<Date, List<TFL>> fetchTFLSearchHistory(TFL tfl) {
		Map<Date, List<TFL>> fetchHistoryResponse = null;
		LOGGER.info("TFLServiceImpl :: Entering fetchTFLSearchHistory(...)");
		try {
			List<TFL> tfls = tflDao.fetchTFLSearchHistory(tfl);
			fetchHistoryResponse = tfls.stream().collect(Collectors.groupingBy(TFL::getTimestamp));
		} catch (Exception e) {
			LOGGER.error("TFLServiceImpl :: Exception occured in fetchTFLSearchHistory(...) due to " + e.getMessage());
		}
		LOGGER.info("TFLServiceImpl :: Exiting fetchHistoryResponse(...)");
		return fetchHistoryResponse;
	}

}
