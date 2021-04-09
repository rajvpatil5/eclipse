package com.guru99.ebanking.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.guru99.ebanking.base.BaseClass;
import com.guru99.ebanking.dataprovider_package.MasterDataProvider;
import com.guru99.ebanking.pageObjects.LoginPage;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProviderClass = MasterDataProvider.class, dataProvider = "LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("user name provided");
		lp.setPassword(pwd);
		logger.info("password provided");
		lp.clickSubmit();

		Thread.sleep(3000);

		if (isAlertPresent() == true) {
			driver.switchTo().alert().accept();// close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");
		} else {
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();// close logout alert
			driver.switchTo().defaultContent();
		}
	}
}