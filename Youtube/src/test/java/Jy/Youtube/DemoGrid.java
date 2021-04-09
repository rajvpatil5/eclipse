package Jy.Youtube;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class DemoGrid {

	@Test
	public void demoGridTest() throws MalformedURLException, InterruptedException {

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName("chrome");
		cap.setPlatform(Platform.WINDOWS);

		ChromeOptions options = new ChromeOptions();
		options.merge(cap);

		String url = "http://192.168.43.131:4444/wd/hub";
		// System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\eclipse-workspace\\SmartOpen\\drivers\\chromedriver.exe");
		WebDriver driver = new RemoteWebDriver(new URL(url), options);

//		If you want to use chromedriver in local machine then you have to write setup() Method, but for remote location like above line there is no need to write setup() method.
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();

		driver.get("https://www.selenium.dev/downloads/");

		Thread.sleep(5000);
		driver.quit();
	}
}
