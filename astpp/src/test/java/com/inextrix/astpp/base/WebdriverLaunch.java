package com.inextrix.astpp.base;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.inextrix.astpp.utilities.ApplicationUtiles;

public class WebdriverLaunch {
	static WebDriver driver1;

	public static WebDriver launchSetup(WebDriver driver) throws IOException {
		Properties properties = ApplicationUtiles.getConfigProperties();
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + properties.getProperty("driverPath"));
		driver = new ChromeDriver();
		driver1 = driver;
		return driver;
	}

	public void takeScreenShot() throws IOException {
		Date date = new Date();
		String Screenshot = "Screenshot_" + date.toString().replace(" ", "_").replace(":", "_") + ".png";
		File srcFile = ((TakesScreenshot) driver1).getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")+"\\ScreenShot\\" + Screenshot);
		FileUtils.copyFile(srcFile, destFile);
		System.out.println("Screenshot taken!!");
	}

}
