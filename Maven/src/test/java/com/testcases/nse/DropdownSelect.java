package com.testcases.nse;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.ObjectRepositries.Homepage;
import com.ObjectRepositries.WebdriverLaunch;

public class DropdownSelect {
	WebDriver driver;

	@Test
	public void dropdownElement() {
		driver = WebdriverLaunch.launchSetup(driver);
		Homepage homepage = new Homepage(driver);
		homepage.launchWebpage();
		homepage.skipAd();
		Select select = new Select(homepage.typeDropdownElements());
		List<WebElement> list = select.getOptions();
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getText());
		}
		Select categorySelect = new Select(homepage.categorydropdownElements());
		List<WebElement> categoryList = categorySelect.getOptions();
		System.out.println(categoryList.size());
		for (WebElement allList : categoryList) {
			System.out.println(allList.getText());
		}
		driver.quit();
	}

}
