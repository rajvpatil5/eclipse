package com.src.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadFileData {

	public static String ReadFileConfig(String TestData) {
		File file = new File(System.getProperty("user.dir") + "/config.property");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		TestData = prop.getProperty(TestData);
		return TestData;
	}
}