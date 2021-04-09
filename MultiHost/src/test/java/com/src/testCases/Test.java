package com.src.testCases;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class Test {
	//public static	WebDriver driver;
	  public static boolean acceptNextAlert = true;
	
	  public static void main(String[] args) throws Exception {
		   
		   //System.setProperty("webdriver.chrome.driver","E:/seleniumGrid/chromedriver.exe");
		   
			ChromeOptions optionsChrome = new ChromeOptions();

			optionsChrome.addArguments("--allow-file-access-from-files","--use-fake-ui-for-media-stream",
					"--allow-file-access",
					"--use-fake-device-for-media-stream",
					"--ignore-certificate-errors","--window-size=1366,768");
			DesiredCapabilities dr = new DesiredCapabilities();

			dr.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			dr.setCapability(ChromeOptions.CAPABILITY, optionsChrome);
			RemoteWebDriver driver =new RemoteWebDriver(new URL("http://54.145.168.103:5555/wd/hub"), dr);
		  // driver =new ChromeDriver();
		  By txt_Email = By.xpath("//input[@name='userName']");
			By txt_password = By.xpath("//input[@name='password']");
			By btn_signIn = By.xpath("//button[text()='Sign In']");

	    // Label: Test
	    // ERROR: Caught exception [ERROR: Unsupported command [resizeWindow | 1366,625 | ]]
	    driver.get("https://beta.loookit.com/#/");
	    driver.findElement(txt_Email).sendKeys("shahid.algur@springct.com");
		driver.findElement(txt_password).sendKeys("Spring123$");
		driver.findElement(btn_signIn).click();
		Thread.sleep(7000);
		Assert.assertEquals("https://beta.loookit.com/#/createSession", driver.getCurrentUrl());
	//	System.out.println("Successfully logged in with Agent : "+username);

//	    driver.findElement(By.cssSelector("button.customBtn.btnSignIn.whiteOnClick.btn.btn-primary")).click();
	    driver.findElement(By.cssSelector("input.formControlName.form-control")).clear();
	    driver.findElement(By.cssSelector("input.formControlName.form-control")).sendKeys("Test");
	    driver.findElement(By.id("proceedBtn")).click();
	    Thread.sleep(20000);
	    
	    driver.findElement(By.xpath("//img[@alt='upload_img']")).click();
	    Robot robot = null;
      
	    
	    String filepath = System.getProperty("user.dir")+"\\Images\\500.png";
	    StringSelection stringSelection = new StringSelection(filepath);
    //  Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//      clipboard.setContents(stringSelection, null);
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    

      try {
          robot = new Robot();
      } catch (AWTException e) {
          e.printStackTrace();
      }

      robot.delay(250);
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.delay(150);
      robot.keyRelease(KeyEvent.VK_ENTER);

	    
	    
      }

	}



