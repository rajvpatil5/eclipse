package com.testcases.nse;

import java.awt.AWTException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.ObjectRepositries.Homepage;
import com.ObjectRepositries.WebdriverLaunch;

public class SearchCompany {
	WebDriver driver;

	@Test
	public void searchCompany() throws InterruptedException, AWTException {
		driver = WebdriverLaunch.launchSetup(driver);
		Homepage homepage = new Homepage(driver);
		homepage.launchWebpage();
		homepage.skipAd();
		homepage.search().sendKeys("BAJAJFINSV" + Keys.ENTER);
		// homepage.submitButton();
		Thread.sleep(5000);
		driver.quit();
	}

}
