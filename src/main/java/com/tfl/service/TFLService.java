package com.tfl.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tfl.model.TFL;

public interface TFLService {

	void saveTFLServices(List<TFL> tflResponse, String format);

	Map<Date, List<TFL>> fetchHistory();

	Map<Date, List<TFL>> fetchTFLSearchHistory(TFL tfl);

}
