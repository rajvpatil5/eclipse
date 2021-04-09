package com.src.testCases;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.apache.bcel.classfile.Utility;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.src.util.UtilityClass;
import com.src.util.UtilityClass2;

public class BaseSetupClass extends UtilityClass{

	protected static ThreadLocal<RemoteWebDriver> drivers = new ThreadLocal<RemoteWebDriver>();
	
	public RemoteWebDriver getDriver() {
		return drivers.get();
	}
	
	public void setup(String node, String meetingName, String FakeCame, String userName) throws UnknownHostException{
		long id = Thread.currentThread().getId();
		int trycount = 0;
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress();
		System.out.println("************Ip: " + ip);

		do {
			System.out.println(
					"Before test-joinMeeting try count:" + trycount + " " + meetingName + ". Thread id is: " + id);

			try {
				// data.add(new String[]{meetingName,userName,node});
				System.out.println("Joining class");
				DesiredCapabilities capa = DesiredCapabilities.chrome();

				capa.setBrowserName("chrome");
				capa.setPlatform(Platform.ANY);
				ChromeOptions options = new ChromeOptions();
				if (TestRunProperties.ExecutionType.equalsIgnoreCase("Headless")) {
					options.addArguments("--disable-extensions");
					options.addArguments("--no-startup-window");
					options.addArguments("--headless");
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-dev-shm-usage");
					options.addArguments("--utility-disable-mdns");
					options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
					options.addArguments("--ignore-certificate-errors");
					options.addArguments("--allow-insecure-localhost");
				}
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);

				LoggingPreferences logPrefs = new LoggingPreferences();
				logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
				options.setCapability("goog:loggingPrefs", logPrefs);

				options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
				options.setPageLoadStrategy(PageLoadStrategy.NONE);
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				if (FakeCame.equals("true")) {
					// TBC
					System.out.println("Fake Camera is true for " + node);

					options.addArguments("--allow-file-access-from-files", "--allow-file-access", "--window-size=1366,768",
							"--allow-file-access-from-files", "--use-fake-ui-for-media-stream",
							"--use-file-for-fake-audio-capture=" + System.getProperty("user.dir") + "/Images/Example.wav",
							"--use-fake-device-for-media-stream", "--user-data-dir=/tmp/" + userName,
							"--ignore-certificate-errors");
				} else {
					// TBC
					System.out.println("Fake Camera is false for " + node);

					options.addArguments("--allow-file-access-from-files", "--allow-file-access", "--window-size=1366,768",
							"--allow-file-access-from-files", "--use-fake-ui-for-media-stream", "--allow-file-access",
							"--user-data-dir=/tmp/" + userName, "--ignore-certificate-errors");
				}

				capa.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

				capa.setCapability(ChromeOptions.CAPABILITY, options);
				// TBC
				String Url = "http://" + node + ":5555/wd/hub";
				System.out.println("Node value fetched in test run prop " + node);
				Thread.sleep(2000);
				
				if (TestRunProperties.ExecutionMode.equalsIgnoreCase("Grid")) {
					try {
						System.out.println(" Remote Webdriver ");

						drivers.set(new RemoteWebDriver(new URL(Url), capa));
					} catch (Exception e) {
						System.out.print("Exception in Remote Webdriver " + e);
					}
				} else

					try {
						System.setProperty("webdriver.chrome.driver", TestRunProperties.ChromePath);
//						WebDriverManager.chromedriver().setup();

						drivers.set(new ChromeDriver(capa));
					} catch (Exception e) {
						System.out.print("Exception in  Webdriver " + e);
					}
				trycount += 2;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				if (trycount >= 2) {
					Assert.assertTrue(false,
							MessageFormat.format(
									"Error while Driver setup for {0} Meeting name={1} User Name={2} Exception={3}",
									node, meetingName, userName, e.getMessage()));
				}
				trycount++;

			}

		} while (trycount <= 1);
		TestRunProperties.driverMap.put(node + userName, getDriver());
	}
}
