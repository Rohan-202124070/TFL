package com.tfl.dao;

import java.util.List;

import com.tfl.model.TFL;

public interface TFLDao {

	void saveTFLServices(List<TFL> tflResponse, String timeStamp);

	List<TFL> fetchHistory();

	List<TFL> fetchTFLSearchHistory(TFL tfl);

}
