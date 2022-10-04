package com.tfl.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UtilProperty {

	private static Properties prop = null;
	private static final String propFileName = "application.properties";

	private static void loadPropValues() throws IOException {
		prop = new Properties();
		InputStream inputStream = UtilProperty.class.getClassLoader().getResourceAsStream(propFileName);
		prop.load(inputStream);
		if (inputStream == null) {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
	}

	public static String getValue(String key) {
		if (prop == null) {
			try {
				loadPropValues();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop.getProperty(key);
	}

	public static String getValue(String key, String defaultvalue) throws IOException {
		if (prop == null) {
			loadPropValues();
		}
		return prop.getProperty(key, defaultvalue);
	}
}
