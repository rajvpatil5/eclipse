package com.ObjectRepositries;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebdriverLaunch {
	static WebDriver driver1;

	public static WebDriver launchSetup(WebDriver driver) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver1 = driver;
		return driver;
	}

	public void takeScreenShot() throws IOException {
		Date date = new Date();
		String Screenshot = "Screenshot_" + date.toString().replace(" ", "_").replace(":", "_") + ".png";
		File srcFile = ((TakesScreenshot) driver1).getScreenshotAs(OutputType.FILE);
		File destFile = new File("C:\\Users\\Hp\\eclipse-workspace\\Maven\\ScreenShot\\" + Screenshot);
		FileUtils.copyFile(srcFile, destFile);
		System.out.println("Screenshot taken!!");
	}

}
