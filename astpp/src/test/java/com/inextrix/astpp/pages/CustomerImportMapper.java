package com.inextrix.astpp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerImportMapper {
	WebDriver driver;

	public CustomerImportMapper(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "#customer_import_mapper")
	public WebElement selectCSVfile;
	
	public WebElement selectCSVFile() {
		return selectCSVfile;
	}

	@FindBy(xpath = "//*[text()='Import']")
	public WebElement importButton;
	
	public void clickOnImport() {
		importButton.click();
	}
	
	
}
