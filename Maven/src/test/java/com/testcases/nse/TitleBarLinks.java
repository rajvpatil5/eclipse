package com.testcases.nse;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.ObjectRepositries.Homepage;
import com.ObjectRepositries.WebdriverLaunch;

public class TitleBarLinks {
	WebDriver driver;

	@Test
	public void clickAllLinks() {
		driver = WebdriverLaunch.launchSetup(driver);
		Homepage homepage = new Homepage(driver);
		homepage.launchWebpage();
		homepage.skipAd();
		List<WebElement> list = homepage.allTitleAreaLinks();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).click();
			System.out.println(driver.getTitle());
			homepage.launchWebpage();
			list = homepage.allTitleAreaLinks();
		}
		driver.quit();
	}

}
