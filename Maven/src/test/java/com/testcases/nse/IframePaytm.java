package com.testcases.nse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.ObjectRepositries.WebdriverLaunch;

public class IframePaytm {
	WebDriver driver;

	@Test
	public void test() {
		driver = WebdriverLaunch.launchSetup(driver);
		driver.get("https://paytm.com/");
		driver.findElement(By.xpath("//div[text()='Log In/Sign Up']")).click();
		List<WebElement> list = driver.findElements(By.tagName("iframe"));
		System.out.println(list.size());
		driver.findElement(By.xpath("//span[text()='Login/Signup with mobile number and password']")).click();
		driver.quit();
	}

}
