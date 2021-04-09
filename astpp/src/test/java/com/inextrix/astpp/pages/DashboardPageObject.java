package com.inextrix.astpp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPageObject {

	WebDriver driver;

	public DashboardPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id=\"main_menu\"]/ul/li[1]/a")
	public WebElement accounts;

	public WebElement hoverOnAccounts() {
		return accounts;
	}

	@FindBy(xpath = "//*[@id=\"main_menu\"]/ul/li[1]/ul/li[1]/a/span")
	public WebElement customer;

	public WebElement hoverOncustomer() {
		return customer;
	}

	@FindBy(xpath = "//*[@id=\"main_menu\"]/ul/li[1]/ul/li[1]/ul/li[1]/a")
	public WebElement customerlist;
	
	public void clickOncustomerlist() {
		customerlist.click();
	}
}
