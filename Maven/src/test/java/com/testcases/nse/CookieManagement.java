package com.testcases.nse;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.ObjectRepositries.WebdriverLaunch;

public class CookieManagement {
	WebDriver driver;

	@Test
	public void test() {
		driver = WebdriverLaunch.launchSetup(driver);
		driver.get("https://in.yahoo.com/?p=us");
		Set<Cookie> set = driver.manage().getCookies();
		System.out.println(set.size());
		for (Cookie set1 : set) {
			System.out.println(set1.getName() + " - " + set1.getValue());
		}
		driver.manage().deleteAllCookies();
		driver.quit();
	}

}
