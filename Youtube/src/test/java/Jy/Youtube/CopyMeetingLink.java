package Jy.Youtube;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CopyMeetingLink {
	public static JavascriptExecutor js;

	public static void explicitWait(WebDriver driver, By xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
	}

	@Test
	public void m1() {
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless");
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();

		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.addArguments("--headless");
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver(firefoxOptions);
		driver.get("https://fujitsu-staging.loookit.com/");
		driver.manage().window().maximize();

		try {

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(CreateMeetingPage.engLang).click();
			driver.findElement(CreateMeetingPage.txt_Email).sendKeys("agent1@dummy.com");
			driver.findElement(CreateMeetingPage.txt_password).sendKeys("Test123");
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_signIn));
			System.out.println("Successfully logged in with Agent : " + "agent1@dummy.com");
		} catch (Exception e) {
			System.out.println("Exception in UserLogIn method.." + e);
		}

		try {
			Thread.sleep(4000);
			driver.findElement(CreateMeetingPage.creatMeeting).click();
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Proceed));
			System.out.println("Successfully created a session");
			Thread.sleep(4000);
		} catch (Exception e) {
			System.out.println("Exception in createSession method.." + e);
			e.printStackTrace();
		}

		try {
			Thread.sleep(3000);
			explicitWait(driver, CreateMeetingPage.img_AddInvitee);
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_AddInvitee));
			Thread.sleep(4000);
			explicitWait(driver, CreateMeetingPage.btn_CopySessionLink);
			driver.findElement(CreateMeetingPage.btn_CopySessionLink).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("Exception in copy method.." + e);
			e.printStackTrace();
		}
		try {
		String myText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); // extracting the text that was copied to the clipboard
		System.out.println("Copied URL is***********"+myText);
		} catch (Exception e) {
			System.out.println("Exception in copy linkcnd.." + e);
			e.printStackTrace();
		}
		driver.quit();
	}
}
