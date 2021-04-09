package com.inextrix.astpp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject {

	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "username")
	public WebElement userNameField;
	
	public WebElement enterUsername() {
		return userNameField;
	}
	@FindBy(id = "password")
	public WebElement passwordField;
	
	public WebElement enterPassword() {
		return passwordField;
	}
	@FindBy(id = "save_button")
	public WebElement submit;
	
	public void clickSigninButton() {
		submit.click();
	}
}
