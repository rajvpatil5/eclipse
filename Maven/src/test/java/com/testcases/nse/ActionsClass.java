package com.testcases.nse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.ObjectRepositries.Homepage;
import com.ObjectRepositries.WebdriverLaunch;

public class ActionsClass {
	WebDriver driver;

	@Test
	public void test() {
		driver = WebdriverLaunch.launchSetup(driver);
		driver.get("https://www1.nseindia.com/");
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//a[text()='Live Market']"))).perform();
		driver.findElement(By.id("main_livewth_stkwth")).click();
		driver.quit();
	}

}
