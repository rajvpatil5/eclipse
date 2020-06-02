
package com.testcases.nse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.ObjectRepositries.Homepage;

import junit.framework.Assert;

public class CheckTitle {
	WebDriver driver;

	@Test
	public void pageTitle() {
		driver = new ChromeDriver();
		Homepage homepage = new Homepage(driver);
		homepage.launchWebpage();
		Assert.assertEquals("NSE - National Stock Exchange of India Ltd.", driver.getTitle());
		System.out.println(driver.getTitle());
		driver.quit();
	}

}
