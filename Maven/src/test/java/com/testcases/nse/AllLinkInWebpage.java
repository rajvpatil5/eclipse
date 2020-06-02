package com.testcases.nse;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.ObjectRepositries.Homepage;
import com.ObjectRepositries.WebdriverLaunch;

public class AllLinkInWebpage {
	WebDriver driver;

	@Test
	public void link() {
		driver = WebdriverLaunch.launchSetup(driver);
		Homepage homepage = new Homepage(driver);
		homepage.launchWebpage();
		homepage.skipAd();
		List<WebElement> list = homepage.allWebLink();
		System.out.println(list.size());
		driver.quit();
	}
}
