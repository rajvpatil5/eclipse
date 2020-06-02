package com.testcases.nse;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.ObjectRepositries.WebdriverLaunch;

public class NaukriComWindow {
	WebDriver driver;

	@Test
	public void test() throws InterruptedException {
		driver = WebdriverLaunch.launchSetup(driver);
		driver.get("https://www.naukri.com/");
		Set<String> allwindow = driver.getWindowHandles();
		System.out.println(allwindow.size());
		Iterator<String> windowid = allwindow.iterator();
		while (windowid.hasNext()) {
			System.out.println(windowid.next());
			driver.switchTo().window(windowid.next());
			driver.close();
			Thread.sleep(5000);
		}
		driver.quit();
	}

}
