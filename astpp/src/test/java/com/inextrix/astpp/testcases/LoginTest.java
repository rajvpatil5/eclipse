package com.inextrix.astpp.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.inextrix.astpp.ReadExcel;
import com.inextrix.astpp.base.WebdriverLaunch;
import com.inextrix.astpp.pages.CustomerImportMapper;
import com.inextrix.astpp.pages.CustomerImportPreview;
import com.inextrix.astpp.pages.CustomerListPageObject;
import com.inextrix.astpp.pages.DashboardPageObject;
import com.inextrix.astpp.pages.LoginPageObject;
import com.inextrix.astpp.utilities.ApplicationUtiles;
import com.inextrix.astpp.utilities.UploadFile;

public class LoginTest {

	WebDriver driver;
	
	
	
	@BeforeMethod
	public void loginToApplication() throws IOException, InterruptedException {

		driver = WebdriverLaunch.launchSetup(driver);
		ApplicationUtiles.maximizeWindow(driver);
		ApplicationUtiles.waitImplicitily(driver);
		ApplicationUtiles.launchApplicatioUrl(driver);
		Properties properties = ApplicationUtiles.getConfigProperties();

		LoginPageObject loginPageObject = new LoginPageObject(driver);
		loginPageObject.enterUsername().sendKeys(properties.getProperty("username"));
		loginPageObject.enterPassword().sendKeys(properties.getProperty("password"));
		loginPageObject.clickSigninButton();
	}

	@Test
	public void homepage() throws IOException, InterruptedException, AWTException {
		DashboardPageObject dashboardPageObject=new DashboardPageObject(driver);
		CustomerListPageObject customerListPageObject = new CustomerListPageObject(driver);
		CustomerImportMapper customerImportMapper = new CustomerImportMapper(driver);
		CustomerImportPreview customerImportPreview = new CustomerImportPreview(driver);
		
		Actions actions = new Actions(driver);	
		actions.moveToElement(dashboardPageObject.hoverOnAccounts()).perform();
		actions.moveToElement(dashboardPageObject.hoverOncustomer()).perform();
		dashboardPageObject.clickOncustomerlist();
		
		customerListPageObject.clickOnImportCustomer();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		actions.moveToElement(customerImportMapper.selectCSVFile()).click().perform();
		
		UploadFile.uploadCustomer();
		Thread.sleep(10000);
		
		customerImportMapper.clickOnImport();
		
		customerImportPreview.selectPassword();
		customerImportPreview.selectFirstname();
		customerImportPreview.selectLastname();
		customerImportPreview.selectCompany();
		customerImportPreview.selectPhone();
		customerImportPreview.selectMobile();
		customerImportPreview.selectEmail();
		customerImportPreview.selectAddress();
		customerImportPreview.selectCity();
		customerImportPreview.selectProvinceState();
		customerImportPreview.selectZipPostalCode();
		customerImportPreview.selectBalance();
		customerImportPreview.selectCreditLimit();
		customerImportPreview.selectConcurrentCalls();
		customerImportPreview.selectCPS();
		js.executeScript("window.scrollBy(0,1000)");
		driver.findElement(By.id("Process")).click();
	}


	//@Test
	public void VerifyCustomer() throws InterruptedException {
		DashboardPageObject dashboardPageObject=new DashboardPageObject(driver);
		CustomerListPageObject customerListPageObject = new CustomerListPageObject(driver);
		Actions actions = new Actions(driver);	
		actions.moveToElement(dashboardPageObject.hoverOnAccounts()).perform();
		actions.moveToElement(dashboardPageObject.hoverOncustomer()).perform();
		dashboardPageObject.clickOncustomerlist();
		
		//Reading data from Webapplication
		List<String> applicationCustomerList = customerListPageObject.getApplicationCustomerList();
		
		//Reading data from Excelsheet
		List<String> excelcustomer = ReadExcel.read();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(applicationCustomerList.get(0).toString(), excelcustomer.get(0).toString());
		softAssert.assertAll();
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
