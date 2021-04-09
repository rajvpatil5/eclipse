package com.springct.smartopen.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class GettingStarted {

	WebDriver driver;

	public GettingStarted(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='root']/div/div/div/div/div[3]/div/div[1]/div/div[3]/div/div[2]/div/div[1]/button")
	WebElement jointAccountYesButton;

	public void clickJointAccountYesButton() {
		jointAccountYesButton.click();
	}

	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/div[3]/div/div[1]/div/div[3]/div/div[2]/div/div[2]/button")
	WebElement jointAccountNoButton;

	public void clickJointAccountNoButton() {
		jointAccountNoButton.click();
	}

	@FindBy(name = "applicantname")
	WebElement coApplicantName;

	public void submitcoApplicantName(String name) {
		coApplicantName.sendKeys(name);
	}

	@FindBy(name = "applicantemail")
	WebElement coApplicantEmail;

	public void submitcoApplicantemail(String email) {
		coApplicantEmail.sendKeys(email);
	}

	@FindBy(xpath = "//select")
	WebElement homeBranchLocation;
	
	public void selectHomeBranchLocation(String location) {
		Select select = new Select(homeBranchLocation);
		select.selectByVisibleText(location);
	}

	@FindBy(xpath = "//*[text()='Email']")
	WebElement accountStatement;

	public void receiveAccountStatementEmail() {
		accountStatement.click();
	}

	@FindBy(xpath = "//*[text()='Paper']")
	WebElement accountStatementPaper;

	public void receiveAccountStatementPaper() {
		accountStatementPaper.click();
	}

	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/div[3]/div/div[1]/div/div[9]/div/div[2]/div/div[1]/button")
	WebElement overdraftProtectionYes;

	public void overdraftProtectionYesButton() {
		overdraftProtectionYes.click();
	}

	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/div[3]/div/div[1]/div/div[9]/div/div[2]/div/div[2]/button")
	WebElement overdraftProtectionNo;

	public void overdraftProtectionNoButton() {
		overdraftProtectionNo.click();
	}

	@FindBy(xpath = "//*[text()='Continue']")
	WebElement continueButton;

	public void clickContinueButton() {
		continueButton.click();
	}

}

































