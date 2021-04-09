package Jy.Youtube;

import java.net.MalformedURLException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Canvas {
	@Test
	public void demoCanvas() throws MalformedURLException, InterruptedException {
//		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\Drivers\\chromedriver.exe");
//		ChromeOptions chromeOptions = new ChromeOptions();
//		chromeOptions.setExperimentalOption("debuggerAddress", "localhost:9014");
//		WebDriver driver = new ChromeDriver(chromeOptions);

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://127.0.0.1:5500/demo.html");
		Random random = new Random();
		Actions act = new Actions(driver);
		WebElement element = driver.findElement(By.id("can"));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int a = random.nextInt(200);
				int b = random.nextInt(200);
				act.clickAndHold(element).moveToElement(element, a, b).build().perform();
				act.moveToElement(element, (-a), (-b)).clickAndHold(element).build().perform();

				act.clickAndHold(element).moveToElement(element, a, (-b)).build().perform();
				act.moveToElement(element, a, (-b)).clickAndHold(element).build().perform();

				act.clickAndHold(element).moveToElement(element, (-a), b).build().perform();
				act.moveToElement(element, (-a), b).clickAndHold(element).release().build().perform();

			}
		}
	}
}
