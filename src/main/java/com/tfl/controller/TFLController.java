package com.tfl.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tfl.model.TFL;
import com.tfl.service.TFLService;
import com.tfl.util.UtilProperty;

@Controller
public class TFLController {

	private static Logger LOGGER = LoggerFactory.getLogger(TFLController.class.getName());

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private TFLService tflService;

	@Autowired
	public RestTemplate restTemplate;

	@GetMapping("/tfl")
	public String getTFL(Model model) {
		LOGGER.info("TFLController :: Entering getTFL(...)");
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			List<TFL> tflResponse = Arrays
					.asList(restTemplate.getForObject(UtilProperty.getValue("tfl.service.url"), TFL[].class));
			LOGGER.info("TFLController :: Response from TFL Service API : " + mapper.writeValueAsString(tflResponse));
			tflService.saveTFLServices(tflResponse, dateFormat.format(date));
			LOGGER.info("TFLController :: Response from getTFL API : " + mapper.writeValueAsString(tflResponse));
			model.addAttribute("tflResponse", tflResponse);
			LOGGER.info("TFLController :: Exiting getTFL(...)");
		} catch (Exception e) {
			LOGGER.error("TFLController :: Exception occured in getTFL(...) due to " + e.getMessage());
		}
		return "tflResponse";
	}

	@GetMapping("/history")
	public String fetchtflHistory(Model model) {
		LOGGER.info("TFLController :: Entering fetchtflHistory(...)");
		try {
			Map<Date, List<TFL>> fetchHistoryResponse = tflService.fetchHistory();
			LOGGER.info("TFLController :: Response from fetchHistory API : "
					+ mapper.writeValueAsString(fetchHistoryResponse));

			model.addAttribute("history", fetchHistoryResponse);
		} catch (Exception e) {
			LOGGER.error("TFLController :: Exception occured in fetchtflHistory(...) due to " + e.getMessage());
		}
		LOGGER.info("TFLController :: Exiting fetchtflHistory(...)");
		return "history";
	}

	@GetMapping("/history-search")
	public String fetchTFLSearchHistory(@ModelAttribute("tfl") TFL tfl, Model model) {
		LOGGER.info("TFLController :: Entering FetchTFLSearchHistory(...)");
		try {
			Map<Date, List<TFL>> fetchTFLSearchHistoryResponse = tflService.fetchTFLSearchHistory(tfl);
			LOGGER.info("TFLController :: Response from fetchTFLSearchHistory API : "
					+ mapper.writeValueAsString(fetchTFLSearchHistoryResponse));
			model.addAttribute("history", fetchTFLSearchHistoryResponse);
		} catch (Exception e) {
			LOGGER.error("TFLController :: Exception occured in FetchTFLSearchHistory(...) due to " + e.getMessage());
		}
		LOGGER.info("TFLController :: Exiting FetchTFLSearchHistory(...)");
		return "history";
	}
}
