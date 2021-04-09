package com.springct.smartopen.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountApplication {
	WebDriver driver;

	public AccountApplication(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "TC-GF-01")
	WebElement agreeTandC;

	public void agreeTermsAndConditions() {
		agreeTandC.click();
	}
	@FindBy(id = "TC-GF-02")
	WebElement updateOffers;
	
	public void updatesSpecialOffers() {
		updateOffers.click();
	}
	
	@FindBy(xpath = "//button[text()='Continue']")
	WebElement continueButton;
	
	public void clickContinueButton() {
		continueButton.click();
	}
}
