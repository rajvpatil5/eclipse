package com.springct.smartopen.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExistingMemberPage {
	WebDriver driver;

	public ExistingMemberPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='No']")
	WebElement noButton;

	public void clickNoButton() {
		noButton.click();
	}

	@FindBy(xpath = "//button[text()='Yes']")
	WebElement yesButton;

	public void clickYesButton() {
		yesButton.click();
	}
	@FindBy(xpath = "//button[text()='Continue']")
	WebElement continueButton;
	
	public void clickContinueButton() {
		continueButton.click();
	}

}
