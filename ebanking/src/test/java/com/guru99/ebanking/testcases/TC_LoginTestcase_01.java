package com.guru99.ebanking.testcases;

import org.testng.annotations.Test;

import com.guru99.ebanking.base.BaseClass;
import com.guru99.ebanking.pageObjects.LoginPage;

import junit.framework.Assert;

public class TC_LoginTestcase_01 extends BaseClass {

	@Test
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
	@Test
	public void logintest1() {
		Assert.assertEquals(true, false);
	}
}
