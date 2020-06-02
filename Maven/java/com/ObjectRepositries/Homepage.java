package com.ObjectRepositries;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	WebDriver driver;
	String url = "https://www.moneycontrol.com/";

	public Homepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void launchWebpage() {
		driver.get(url);
	}

	@FindBy(id = "search_str")
	public WebElement searchBox;

	public WebElement search() {
		return searchBox;
	}

	@FindBy(className = "btn_black btn_search FR")
	public WebElement sumbit;

	public void submitButton() {
		sumbit.click();
	}

	@FindBy(partialLinkText = "moneycontrol.com")
	public WebElement link;

	public void skipAd() {
		link.click();
	}

	@FindBy(xpath = "//*")
	public WebElement element;

	public WebElement allWebElement() {
		return element;
	}

	@FindBy(xpath = "//a")
	public List<WebElement> linkElement;

	public List<WebElement> allWebLink() {
		return linkElement;
	}

	@FindBy(xpath = "//ul[@class='headbotmmenus clearfix ']/li")
	WebElement titlebarlinkarea;

	public List<WebElement> allTitleAreaLinks() {

		return titlebarlinkarea.findElements(By.tagName("a"));
	}

	@FindBy(id = "screen_crit")
	WebElement dropdownList;

	public WebElement typeDropdownElements() {

		return dropdownList;
	}

	@FindBy(id = "sel_code")
	WebElement categoryDropdownList;

	public WebElement categorydropdownElements() {

		return categoryDropdownList;
	}

	@FindBy(xpath = "//div[@class='FL ML10']/input")
	WebElement gobutton;

	public void goButton() {
		gobutton.click();
	}

}



























