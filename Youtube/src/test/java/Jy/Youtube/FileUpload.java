package Jy.Youtube;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FileUpload {
	@Test
	public void demoFileUpload() throws InterruptedException, IOException {

		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless");

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://fujitsu-beta.loookit.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(CreateMeetingPage.engLang).click();
		driver.findElement(CreateMeetingPage.txt_Email).sendKeys("rajvpatil5@gmail.com");
		driver.findElement(CreateMeetingPage.txt_password).sendKeys("Test1234");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_signIn));
		System.out.println("Successfully logged in with Agent : " + "rajvpatil5@gmail.com");
		Thread.sleep(5000);
		

		js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.creatMeeting));
		Thread.sleep(2000);
		driver.findElement(CreateMeetingPage.setMeetingTab).click();
		Thread.sleep(2000);
		driver.findElement(CreateMeetingPage.txt_SessionName).sendKeys("demo");
		js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Proceed));
		System.out.println("Successfully created a session");
		Thread.sleep(60000);
		
		
		WebElement findElement = driver.findElement(CreateMeetingPage.img_UploadImage);
		findElement.sendKeys("C:\\Users\\Hp\\eclipse-workspace\\Youtube\\Images\\demoUpload.PNG");
		Thread.sleep(5000);
	}
}










































