package Jy.Youtube;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.devtools.DevTools;

public class GetLocation {
	public static void takeScreenShot(WebDriver driver) throws IOException {
		Date date = new Date();
		String Screenshot = "Screenshot_" + date.toString().replace(" ", "_").replace(":", "_") + ".png";
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "\\ScreenShot\\" + Screenshot);
		FileUtils.copyFile(srcFile, destFile);
		System.out.println("Screenshot taken!!");
	}


	@SuppressWarnings("unchecked")
	@Test
	public void demoLocation() throws InterruptedException, IOException {

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
		
		driver.get("https://www.openstreetmap.org/");

		driver.findElement(By.xpath("//span[@class='icon geolocate']")).click();
		Thread.sleep(5000);
		takeScreenShot(driver);
		driver.quit();
	}
}
