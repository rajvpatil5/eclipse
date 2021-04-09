package com.inextrix.astpp.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class ApplicationUtiles {

	public static Properties getConfigProperties() throws IOException {
		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\com\\inextrix\\astpp\\config\\config.properties");
		properties.load(fileInputStream);
		return properties;
	}
	
	public static void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	public static void waitImplicitily(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public static void launchApplicatioUrl(WebDriver driver) throws IOException {
		Properties properties = ApplicationUtiles.getConfigProperties();
		driver.get(properties.getProperty("applicationUrl"));
	}
}
