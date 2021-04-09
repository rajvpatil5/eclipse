package Jy.Youtube;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AllowGeoLocationPopup {
  public static final String AUTOMATE_USERNAME = "rajatpatil2";
  public static final String AUTOMATE_KEY = "Yp4EBrTEmPC66Py4HV4j";
  public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

  public static void main(String[] args) throws Exception {
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("browser", "Chrome");
    caps.setCapability("browser_version", "75.0");
    caps.setCapability("os", "Windows");
    caps.setCapability("os_version", "10");

    // INIT CHROME OPTIONS
    ChromeOptions options = new ChromeOptions();
    Map < String, Object > prefs = new HashMap < String, Object > ();
    Map < String, Object > profile = new HashMap < String, Object > ();
    Map < String, Object > contentSettings = new HashMap < String, Object > ();

    // SET CHROME OPTIONS
    // 0 - Default, 1 - Allow, 2 - Block
    contentSettings.put("geolocation", 1);
    profile.put("managed_default_content_settings", contentSettings);
    prefs.put("profile", profile);
    options.setExperimentalOption("prefs", prefs);

    // SET CAPABILITY
    caps.setCapability(ChromeOptions.CAPABILITY, options);
    WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver(options);
	
    driver.get("https://the-internet.herokuapp.com/geolocation");
    driver.findElement(By.xpath("//*[@id='content']/div/button")).click();
    Thread.sleep(5000);
    driver.quit();
  }
}
