package com.testcases.nse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.ObjectRepositries.Homepage;
import com.ObjectRepositries.WebdriverLaunch;

public class DropdownCompanies {
	WebDriver driver;

	@Test
	public void test() throws InterruptedException {
		driver = WebdriverLaunch.launchSetup(driver);
		Homepage homepage = new Homepage(driver);
		homepage.launchWebpage();
		homepage.skipAd();
		Select type = new Select(homepage.typeDropdownElements());
		type.selectByValue("net-profit");
		Select category = new Select(homepage.categorydropdownElements());
		category.selectByValue("pharmaceuticals-drugs");
		homepage.goButton();
		Thread.sleep(5000);
		driver.quit();
	}

}
