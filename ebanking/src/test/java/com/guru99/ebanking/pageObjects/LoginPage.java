package com.guru99.ebanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "uid")
	WebElement txtUserName;

	public void setUserName(String uname) {
		txtUserName.sendKeys(uname);
	}

	@FindBy(name = "password")
	WebElement txtPassword;

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	@FindBy(name = "btnLogin")
	WebElement btnLogin;

	public void clickSubmit() {
		btnLogin.click();
	}

	@FindBy(xpath = "/html/body/div[3]/div/ul/li[15]/a")
	WebElement lnkLogout;

	public void clickLogout() {
		lnkLogout.click();
	}
}
