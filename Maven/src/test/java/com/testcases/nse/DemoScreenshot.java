package com.testcases.nse;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepositries.CustomListener;
import com.ObjectRepositries.Homepage;
import com.ObjectRepositries.WebdriverLaunch;


public class DemoScreenshot {
	WebDriver driver;

	@Test
	public void demosnapshot() throws IOException {
		driver = WebdriverLaunch.launchSetup(driver);
		Homepage homepage = new Homepage(driver);
		homepage.launchWebpage();
		homepage.skipAd();
		Assert.assertEquals(false, true);
		driver.quit();
	}

}
