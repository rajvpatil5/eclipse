package com.testcases.nse;

import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.ObjectRepositries.Homepage;
import com.ObjectRepositries.WebdriverLaunch;

public class LinkName {
	WebDriver driver;

	@Test
	public void name() {
		driver = WebdriverLaunch.launchSetup(driver);
		Homepage homepage = new Homepage(driver);
		homepage.launchWebpage();
		homepage.skipAd();
		List<WebElement> webLinkList = homepage.allWebLink();
		for (WebElement link : webLinkList) {
			try {
				System.out.println(link.getText() + " - " + link.isDisplayed());
			} catch (StaleElementReferenceException se) {
				System.out.println("Expeption Occures");
				continue;
			}
		}
		driver.quit();
	}

}
