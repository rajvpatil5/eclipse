package com.springct.smartopen.testcases;

import org.testng.annotations.Test;

import com.springct.smartopen.base.BaseClass;
import com.springct.smartopen.pageObjects.LoginPage;

public class TC_LoginTestcase_01 extends BaseClass {

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
	}
	
}
