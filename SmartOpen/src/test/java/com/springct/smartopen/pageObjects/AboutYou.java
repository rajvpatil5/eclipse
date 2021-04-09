package com.springct.smartopen.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AboutYou {

	WebDriver driver;

	public AboutYou(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "firstName")
	WebElement firstName;

	public void submitFirstName(String name) {
		firstName.sendKeys(name);
	}

	@FindBy(name = "middleName")
	WebElement middleName;

	public void submitMiddleName(String midName) {
		middleName.sendKeys(midName);
	}

	@FindBy(name = "lastName")
	WebElement lastName;

	public void submitLastName(String lName) {
		lastName.sendKeys(lName);
	}

	@FindBy(name = "dateOfBirth")
	WebElement dateOfBirth;

	public void submitDateOfBirth(String dob) {
		dateOfBirth.sendKeys(dob);
	}

	@FindBy(name = "address")
	WebElement address;

	public void submitAddress(String add) {
		address.sendKeys(add);
	}

	@FindBy(name = "addressLine2")
	WebElement addressLine2;

	public void submitAddressLine2(String add2) {
		addressLine2.sendKeys(add2);
	}

	@FindBy(name = "city")
	WebElement city;

	public void submitCity(String cityName) {
		city.sendKeys(cityName);
	}

	@FindBy(name = "province")
	WebElement province;

	public void selectProvince(String provinceName) {
		Select select = new Select(province);
		select.selectByVisibleText(provinceName);
	}

	@FindBy(name = "postalCode")
	WebElement postalCode;

	public void submitPostalCode(String pCode) {
		postalCode.sendKeys(pCode);
	}

	@FindBy(xpath = "//*[text()='No']")
	WebElement sameMailAddressNo;

	public void sameMailAddressNoButton() {
		sameMailAddressNo.click();
	}

	@FindBy(xpath = "//*[text()='Yes']")
	WebElement sameMailAddressYes;

	public void sameMailAddressYesButton() {
		sameMailAddressYes.click();
	}

	@FindBy(name = "mailingAddressLine1")
	WebElement mailingAddressLine1;

	public void submitMailingAddressLine1(String add1) {
		mailingAddressLine1.sendKeys(add1);
	}

	@FindBy(name = "mailingAddressLine2")
	WebElement mailingAddressLine2;

	public void submitMailingAddressLine2(String add2) {
		mailingAddressLine2.sendKeys(add2);
	}

	@FindBy(name = "mailingCity")
	WebElement mailingCity;

	public void submitMailingCity(String city) {
		mailingCity.sendKeys(city);
	}

	@FindBy(name = "mailingProvince")
	WebElement mailingProvince;

	public void selectMailingProvince(String provinceName) {
		Select select = new Select(mailingProvince);
		select.selectByVisibleText(provinceName);
	}

	@FindBy(name = "mailingPostalCode")
	WebElement mailingPostalCode;

	public void submitMailingPostalCode(String pCode) {
		mailingPostalCode.sendKeys(pCode);
	}

	@FindBy(name = "mailingCountry")
	WebElement mailingCountry;

	public void selectMailingCountry(String country) {
		Select select = new Select(mailingCountry);
		select.selectByVisibleText(country);
	}

	@FindBy(xpath = "//*[text()='Continue']")
	WebElement continueButton;

	public void clickContinueButton() {
		continueButton.click();
	}
}
