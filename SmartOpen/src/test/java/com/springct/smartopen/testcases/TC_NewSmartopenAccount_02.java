package com.springct.smartopen.testcases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.springct.smartopen.base.BaseClass;
import com.springct.smartopen.pageObjects.AboutYou;
import com.springct.smartopen.pageObjects.AccountApplication;
import com.springct.smartopen.pageObjects.ExistingMemberPage;
import com.springct.smartopen.pageObjects.GettingStarted;
import com.springct.smartopen.pageObjects.LoginPage;

public class TC_NewSmartopenAccount_02 extends BaseClass {
	//@Test
	public void logintest() throws InterruptedException {
		logger.info("base url entered");
		LoginPage loginPage = new LoginPage(driver);

		loginPage.setUserName(username);
		logger.info("username entered");

		loginPage.setPassword(password);
		logger.info("password entered");

		loginPage.clickSubmit();
		logger.info("click on submit");
		Thread.sleep(5000);
		
		ExistingMemberPage existingMemberPage = new ExistingMemberPage(driver);
		existingMemberPage.clickNoButton();
		existingMemberPage.clickContinueButton();
		
		AccountApplication accountApplication = new AccountApplication(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		accountApplication.agreeTermsAndConditions();
		accountApplication.updatesSpecialOffers();
		jse.executeScript("window.scrollBy(0,250)");
		accountApplication.clickContinueButton();
		
		GettingStarted gettingStarted = new GettingStarted(driver);
		gettingStarted.clickJointAccountYesButton();
		gettingStarted.submitcoApplicantName("test");
		gettingStarted.submitcoApplicantemail("test@sp.com");
		gettingStarted.selectHomeBranchLocation("London");
		jse.executeScript("window.scrollBy(0,250)");
		gettingStarted.receiveAccountStatementEmail();
		gettingStarted.overdraftProtectionYesButton();
		jse.executeScript("window.scrollBy(0,250)");
		gettingStarted.clickContinueButton();
		
		
		AboutYou aboutYou = new AboutYou(driver);
		aboutYou.submitFirstName("test");
		aboutYou.submitMiddleName("testm");
		aboutYou.submitLastName("testl");
		aboutYou.submitDateOfBirth("19940418");
		aboutYou.submitAddress("test");
		aboutYou.submitAddressLine2("test");
		aboutYou.submitCity("test");
		aboutYou.selectProvince("Ontario");
		aboutYou.submitPostalCode("N4B 1B6");
		aboutYou.sameMailAddressNoButton();
		aboutYou.submitMailingAddressLine1("test");
		aboutYou.submitMailingAddressLine2("test");
		aboutYou.submitMailingCity("test");
		aboutYou.selectMailingProvince("Nunavut");
		aboutYou.submitMailingPostalCode("X0A 0H0");
		aboutYou.selectMailingCountry("Canada");
		aboutYou.clickContinueButton();
		
	}
}




























