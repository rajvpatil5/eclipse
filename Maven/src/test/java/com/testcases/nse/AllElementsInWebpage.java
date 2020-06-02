package com.testcases.nse;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.ObjectRepositries.Homepage;
import com.ObjectRepositries.WebdriverLaunch;

public class AllElementsInWebpage {
	WebDriver driver;

	@Test
	public void allElement() {
		driver = WebdriverLaunch.launchSetup(driver);
		Homepage homepage = new Homepage(driver);
		homepage.launchWebpage();
		homepage.skipAd();
		System.out.println(homepage.allWebElement().getSize());
		driver.quit();
	}
}
