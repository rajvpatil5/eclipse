package com.inextrix.astpp.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerListPageObject {
	WebDriver driver;

	public CustomerListPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[text()='Import Customers']")
	public WebElement importCustomer;
	
	public void clickOnImportCustomer() {
		importCustomer.click();
	}
	
	public List<String> getApplicationCustomerList() {
		List<String> customer = new ArrayList<String>();
		customer.add(driver.findElement(By.xpath("//*[@id=\"flex1\"]/tbody/tr[1]/td[2]/div/a[1]/span[1]")).getText()+".0");
		for(int i=3;i<6;i++) {
		customer.add(driver.findElement(By.xpath("//*[@id=\"flex1\"]/tbody/tr[1]/td["+i+"]")).getText());
		}
		return customer;
	}
	
}
