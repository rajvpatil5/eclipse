package com.linkdin;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LinkedinUrls {
	static List<Object> name = new ArrayList<Object>();
	static List<Object> position = new ArrayList<Object>();
	static List<Object> linkdinUrl = new ArrayList<Object>();

	public static void main(String[] args) throws AWTException, UnsupportedFlavorException, IOException {
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("debuggerAddress", "localhost:9014");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Set<String> win = driver.getWindowHandles();
		System.out.println(win.size());
		java.util.Iterator<String> i = win.iterator();
		while (i.hasNext()) {
			driver.switchTo().window(i.next());
			name.add(driver.findElement(By.cssSelector(".profile-topcard-person-entity__name")).getText());
			position.add(driver.findElement(By.xpath("//dd[1][@class='mt2']")).getText());
			driver.findElement(By.xpath("//li-icon[@type='ellipsis-horizontal-icon']")).click();
			driver.findElement(By.xpath("//div[@data-control-name='copy_linkedin']")).click();
			linkdinUrl.add(ClipboardData.clipboardPaste());
		}
		SimpleExcelWriterExample.setData(name, position, linkdinUrl);
	}
}
