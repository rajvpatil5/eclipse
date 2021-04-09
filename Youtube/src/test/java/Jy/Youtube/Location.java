package Jy.Youtube;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Location {

	@Test
	public void demoLocation() throws MalformedURLException, InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");

	
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		Map<String, Object> coordinates = new HashMap<String, Object>() {
			{
				put("latitude", 50.2334);
				put("longitude", 70.2334);
				put("accuracy", 1);
			}
		};
		((ChromiumDriver) driver).executeCdpCommand("Emulation.setGeolocationOverride", coordinates);

		driver.manage().window().maximize();
		driver.get("http://127.0.0.1:5500/demoLocation.html");
		Thread.sleep(10000);
		driver.findElement(By.tagName("button")).click();
	    Thread.sleep(10000);
	    System.out.println("*******************************");
	    System.out.println(driver.findElement(By.id("demo")).getText());
	    System.out.println("*******************************");
	}
}




















