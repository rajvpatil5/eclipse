package Jy.Youtube;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Youtube {
	//@Test
	public void views() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\eclipse-workspace\\SmartOpen\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://youtu.be/luSQnB1kUcQ");
		//driver.findElement(By.xpath("//*[@id=\"movie_player\"]/div[26]/div[2]/div[1]/button")).click();
		Thread.sleep(5000);
		//driver.quit();
	}
//	@Test
//	public void views1() throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\eclipse-workspace\\SmartOpen\\drivers\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://youtu.be/luSQnB1kUcQ");
//		driver.findElement(By.xpath("//*[@id=\"movie_player\"]/div[26]/div[2]/div[1]/button")).click();
//		Thread.sleep(5000);
//		driver.quit();
//	}
//	@Test
//	public void views2() throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\eclipse-workspace\\SmartOpen\\drivers\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://youtu.be/luSQnB1kUcQ");
//		driver.findElement(By.xpath("//*[@id=\"movie_player\"]/div[26]/div[2]/div[1]/button")).click();
//		Thread.sleep(5000);
//		driver.quit();
//	}
//	@Test
//	public void views3() throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\eclipse-workspace\\SmartOpen\\drivers\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://youtu.be/luSQnB1kUcQ");
//		driver.findElement(By.xpath("//*[@id=\"movie_player\"]/div[26]/div[2]/div[1]/button")).click();
//		Thread.sleep(5000);
//		driver.quit();
//	}@Test
//	public void views4() throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\eclipse-workspace\\SmartOpen\\drivers\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://youtu.be/luSQnB1kUcQ");
//		driver.findElement(By.xpath("//*[@id=\"movie_player\"]/div[26]/div[2]/div[1]/button")).click();
//		Thread.sleep(5000);
//		driver.quit();
//	}@Test
//	public void views5() throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\eclipse-workspace\\SmartOpen\\drivers\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://youtu.be/luSQnB1kUcQ");
//		driver.findElement(By.xpath("//*[@id=\"movie_player\"]/div[26]/div[2]/div[1]/button")).click();
//		Thread.sleep(5000);
//		driver.quit();
//	}
//	@Test
//	public void views6() throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\eclipse-workspace\\SmartOpen\\drivers\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://youtu.be/luSQnB1kUcQ");
//		driver.findElement(By.xpath("//*[@id=\"movie_player\"]/div[26]/div[2]/div[1]/button")).click();
//		Thread.sleep(5000);
//		driver.quit();
//	}@Test
//	public void views7() throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\eclipse-workspace\\SmartOpen\\drivers\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://youtu.be/luSQnB1kUcQ");
//		driver.findElement(By.xpath("//*[@id=\"movie_player\"]/div[26]/div[2]/div[1]/button")).click();
//		Thread.sleep(5000);
//		driver.quit();
//	}@Test
//	public void views8() throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\eclipse-workspace\\SmartOpen\\drivers\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://youtu.be/luSQnB1kUcQ");
//		driver.findElement(By.xpath("//*[@id=\"movie_player\"]/div[26]/div[2]/div[1]/button")).click();
//		Thread.sleep(5000);
//		driver.quit();
//	}@Test
//	public void views9() throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\eclipse-workspace\\SmartOpen\\drivers\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://youtu.be/luSQnB1kUcQ");
//		driver.findElement(By.xpath("//*[@id=\"movie_player\"]/div[26]/div[2]/div[1]/button")).click();
//		Thread.sleep(5000);
//		driver.quit();
//	}
}
