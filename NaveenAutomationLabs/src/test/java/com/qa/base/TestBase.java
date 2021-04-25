package com.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	public Properties prop;
	public FileInputStream in;
	public TestBase testbase;
	public String serviceURL = readConfigData("serviceURL");
	public String url = readConfigData("URL");
	public String getURL = url + serviceURL;

	public String readConfigData(String testdata) {
		if (prop == null) {
			try {
				prop = new Properties();
				in = new FileInputStream(System.getProperty("user.dir") + "\\config.properties");
				prop.load(in);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop.getProperty(testdata);
	}
}