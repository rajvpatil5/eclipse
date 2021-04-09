package com.springct.smartopen.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties properties;

	public ReadConfig() {
		File file = new File("./configuration\\config.properties");
		try {
		FileInputStream fis = new FileInputStream(file);
		properties = new Properties();
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getApplicationUrl() {
		return properties.getProperty("baseURL");
	}

	public String getUsername() {
		return properties.getProperty("username");
	}

	public String getPassword() {
		return properties.getProperty("password");
	}

	public String getChromePath() {
		return properties.getProperty("chromepath");
	}

	public String getIEPath() {
		return properties.getProperty("iepath");
	}

	public String getFirefoxPath() {
		return properties.getProperty("firefoxpath");
	}
}
