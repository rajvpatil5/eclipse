package Jy.Youtube;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultiWindow {

	@Test
	public void demoGridTest() throws MalformedURLException, InterruptedException {

//		If you want to use chromedriver in local machine then you have to write setup() Method, but for remote location like above line there is no need to write setup() method.
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.naukri.com");
		String parentwindowHandle = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println(windowHandles.size());
		String subWindow=null;
		Iterator<String> iterator = windowHandles.iterator();
		while (iterator.hasNext()) {
			subWindow = iterator.next();
//			System.out.println(iterator.next());
		}

		driver.switchTo().window(subWindow);
		System.out.println(driver.getTitle());
		driver.close();
		
		driver.switchTo().window(parentwindowHandle);
		
		Thread.sleep(5000);
		driver.quit();
	}
}
