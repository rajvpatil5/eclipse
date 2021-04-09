package com.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	public Properties prop;
	public FileInputStream in;

	public TestBase() {
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
}