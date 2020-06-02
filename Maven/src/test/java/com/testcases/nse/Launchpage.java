package com.testcases.nse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.ObjectRepositries.Homepage;

public class Launchpage {
	WebDriver driver;

	@Test
	public void launchChrome() throws InterruptedException {
		driver = new ChromeDriver();
		Homepage hp = new Homepage(driver);		
		hp.launchWebpage();
		driver.quit();
	}

}
