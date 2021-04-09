package com.inextrix.astpp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CustomerImportPreview {
	WebDriver driver;

	public CustomerImportPreview(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id=\"password-select\"]")
	public WebElement password;

	public void selectPassword() {
		Select select = new Select(password);
		select.selectByVisibleText("Password");
	}

	@FindBy(xpath = "//*[@id=\"first_name-select\"]")
	public WebElement firstname;

	public void selectFirstname() {
		Select select = new Select(firstname);
		select.selectByVisibleText("First Name");
	}

	@FindBy(xpath = "//*[@id=\"last_name-select\"]")
	public WebElement lastname;

	public void selectLastname() {
		Select select = new Select(lastname);
		select.selectByVisibleText("Last Name");
	}

	@FindBy(xpath = "//*[@id=\"company-select\"]")
	public WebElement company;

	public void selectCompany() {
		Select select = new Select(company);
		select.selectByVisibleText("Company");
	}

	@FindBy(xpath = "//*[@id=\"telephone_1-select\"]")
	public WebElement phone;

	public void selectPhone() {
		Select select = new Select(phone);
		select.selectByVisibleText("Phone");
	}

	@FindBy(xpath = "//*[@id=\"telephone_2-select\"]")
	public WebElement mobile;

	public void selectMobile() {
		Select select = new Select(mobile);
		select.selectByVisibleText("Mobile");
	}

	@FindBy(xpath = "//*[@id=\"email-select\"]")
	public WebElement email;

	public void selectEmail() {
		Select select = new Select(email);
		select.selectByVisibleText("Email");
	}

	@FindBy(xpath = "//*[@id=\"address_1-select\"]")
	public WebElement address;

	public void selectAddress() {
		Select select = new Select(address);
		select.selectByVisibleText("Address");
	}

	@FindBy(xpath = "//*[@id=\"city-select\"]")
	public WebElement city;

	public void selectCity() {
		Select select = new Select(city);
		select.selectByVisibleText("City");
	}

	@FindBy(xpath = "//*[@id=\"province-select\"]")
	public WebElement provinceState;

	public void selectProvinceState() {
		Select select = new Select(provinceState);
		select.selectByVisibleText("Province/State");
	}

	@FindBy(xpath = "//*[@id=\"postal_code-select\"]")
	public WebElement zipPostalCode;

	public void selectZipPostalCode() {
		Select select = new Select(zipPostalCode);
		select.selectByVisibleText("Zip/Postal Code");
	}

	@FindBy(xpath = "//*[@id=\"balance-select\"]")
	public WebElement balance;

	public void selectBalance() {
		Select select = new Select(balance);
		select.selectByVisibleText("Balance");
	}

	@FindBy(xpath = "//*[@id=\"credit_limit-select\"]")
	public WebElement creditLimit;

	public void selectCreditLimit() {
		Select select = new Select(creditLimit);
		select.selectByVisibleText("Credit Limit");
	}

	@FindBy(xpath = "//*[@id=\"maxchannels-select\"]")
	public WebElement concurrentCalls;

	public void selectConcurrentCalls() {
		Select select = new Select(concurrentCalls);
		select.selectByVisibleText("Concurrent Calls");
	}

	@FindBy(xpath = "//*[@id=\"cps-select\"]")
	public WebElement cps;

	public void selectCPS() {
		Select select = new Select(cps);
		select.selectByVisibleText("CPS");
	}
}
