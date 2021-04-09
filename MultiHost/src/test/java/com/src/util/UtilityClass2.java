package com.src.util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.src.pages.CreateMeetingPage;
import com.src.testCases.CreateMeeting;
import com.src.testCases.ExecuteTestRun;
import com.src.testCases.TestRunProperties;

public class UtilityClass2 {
	String mainWindow, newWindow;
	public static String CopiedURL = " ";
	public static WebDriver driver1;
	public static ArrayList<String> tabs = null;
	public static int agentIndex = 0;
	public static int agentIndexPassword = 0;
	public static String sessionKey = null;
	public static String joiningURL = TestRunProperties.strBaseUrl+"join?sessionKey=";
	private static String Lock = "lock";
	protected static ThreadLocal<RemoteWebDriver> drivers = new ThreadLocal<RemoteWebDriver>();
	public static Map<String, String> sessionKeyWithMeetings = new HashMap<String, String>();

	public RemoteWebDriver getDriver() {
		return drivers.get();
	}

	public void setup(String node, String meetingName, String FakeCame, String userName) throws UnknownHostException {
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

					options.addArguments("--allow-file-access-from-files", "--allow-file-access",
							"--window-size=1366,768", "--allow-file-access-from-files",
							"--use-fake-ui-for-media-stream",
							"--use-file-for-fake-audio-capture=" + System.getProperty("user.dir")
									+ "/Images/Example.wav",
							"--use-fake-device-for-media-stream", "--user-data-dir=/tmp/" + userName,
							"--ignore-certificate-errors");
				} else {
					// TBC
					System.out.println("Fake Camera is false for " + node);

					options.addArguments("--allow-file-access-from-files", "--allow-file-access",
							"--window-size=1366,768", "--allow-file-access-from-files",
							"--use-fake-ui-for-media-stream", "--allow-file-access", "--user-data-dir=/tmp/" + userName,
							"--ignore-certificate-errors");
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

	public void shareScreen(String node, String meetingName, String FakeCame, String userName)
			throws InterruptedException {

		WebDriver driver = drivers.get();
		if (!TestRunProperties.ExecutionType.equalsIgnoreCase("Headless")) {
			driver.manage().window().maximize();
		}
		System.out.println(driver);
		System.out.println("====================");
		JavascriptExecutor js = (JavascriptExecutor) driver;

		synchronized (Lock) {
			try {
				agentIndex = agentIndex + 1;
				agentIndexPassword = agentIndexPassword + 1;
				if (CreateMeeting.param.size() - 1 < agentIndex) {
					agentIndex = 1;
					agentIndexPassword = 1;
				}
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.get(TestRunProperties.baseUrl);
				assertEquals("Loookit", driver.getTitle());
				Assert.assertTrue(driver.findElement(CreateMeetingPage.engLang).isDisplayed(),
						"English language radio button is missing on landing page.");
				driver.findElement(CreateMeetingPage.engLang).click();
				driver.findElement(CreateMeetingPage.txt_Email).sendKeys(CreateMeeting.param.get(agentIndex));
				driver.findElement(CreateMeetingPage.txt_password)
						.sendKeys(CreateMeeting.paramPassword.get(agentIndexPassword));
				System.out.println(CreateMeeting.paramPassword.get(agentIndexPassword));
				js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_signIn));
				System.out.println("Successfully logged in with Agent : " + CreateMeeting.param.get(agentIndex));
			} catch (Exception e) {
				System.out.println("Exception in UserLogIn method.." + e);
			}
		}

		Thread.sleep(4000);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.creatMeeting).isDisplayed(),
				"Create Meeting option is missing");
		TestRunProperties.explicitWait(driver, CreateMeetingPage.creatMeeting);
		driver.findElement(CreateMeetingPage.creatMeeting).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.setMeetingTab).isDisplayed(),
				"Set up a new meeting tab is missing..");
		String meetingType = TestRunProperties.meetingOption;
		if (meetingType.equalsIgnoreCase("Passcode")) {
			TestRunProperties.explicitWait(driver, CreateMeetingPage.setMeetingTab);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.btn_Proceed).isDisplayed(),
					"'Send Invite and Start' button is missing for create session.");
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Proceed));
		} else {
			TestRunProperties.explicitWait(driver, CreateMeetingPage.setMeetingTab);
			driver.findElement(CreateMeetingPage.setMeetingTab).click();
			Thread.sleep(2000);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.txt_SessionName).isDisplayed(),
					"Enter session name input box is missing");
			TestRunProperties.explicitWait(driver, CreateMeetingPage.txt_SessionName);
			driver.findElement(CreateMeetingPage.txt_SessionName).sendKeys(meetingName);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.btn_Proceed).isDisplayed(),
					"'Send Invite and Start' button is missing for create session.");
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Proceed));
		}
		System.out.println("Successfully created a session");
		CreateMeeting.sleep(4000);

		try {
			synchronized (Lock) {
				System.out.println(Thread.currentThread().getId() + "is inn++++++++");
				String[] split = null;
				List<LogEntry> entries = driver.manage().logs().get("performance").getAll();
				for (LogEntry entry : entries) {
					if (entry.toString().contains("sessionKey")) {
						if (!entry.toString().contains("sessionKey%"))
							System.out.println(entry.toString());
						split = entry.toString().split("sessionKey");
					}
				}
				System.out.println("====================");
				for (int i = 0; i < split.length; i++) {
					System.out.println(split[i]);
				}
				System.out.println(split[1].substring(5, 13));
				sessionKey = split[1].substring(5, 13);
				sessionKeyWithMeetings.put(sessionKey, meetingName);
				System.out.println("%%%%%%%%%%%%%%%%%%%%%");
				System.out.println(sessionKeyWithMeetings);

				Thread.sleep(2000);
				js.executeScript("window.open('about:blank','_blank');");
				tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				driver.get(joiningURL + sessionKey);
				TestRunProperties.explicitWait(driver, CreateMeetingPage.txt_UserName);
				driver.findElement(CreateMeetingPage.txt_UserName).sendKeys(userName);
				TestRunProperties.explicitWait(driver, CreateMeetingPage.btn_ProceedUser);
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_ProceedUser));
				Thread.sleep(4000);
				// Start Audio/Video call at Agent side
				driver.switchTo().window(tabs.get(0));
				System.out.println(Thread.currentThread().getId() + "is out------");
			}
			// Admit User
			System.out.println("In admin User");
			try {
				TestRunProperties.explicitWait(driver, CreateMeetingPage.img_AddInvitee);
				assertTrue(driver.findElement(CreateMeetingPage.img_AddInvitee).isDisplayed(),
						"Participant button is missing.");
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_AddInvitee));
				System.out.println("Click on img_AddInvitee button");

				TestRunProperties.explicitWait(driver, CreateMeetingPage.waitingUsers);
				driver.findElement(CreateMeetingPage.waitingUsers).isDisplayed();
				System.out.println("User is waiting....");

				Actions action = new Actions(driver);
				action.moveToElement(driver.findElement(CreateMeetingPage.waitingUsers)).perform();

				TestRunProperties.explicitWait(driver, CreateMeetingPage.admit_btn);
				assertTrue(driver.findElement(CreateMeetingPage.admit_btn).isDisplayed(),
						"Admit button is missing for User.");
				System.out.println("Adding users from waiting area");

				TestRunProperties.explicitWait(driver, CreateMeetingPage.waitingUsers);
				List<WebElement> li = driver.findElements(CreateMeetingPage.waitingUsers);
				int i = li.size();
				while (i > 0) {
					System.out.println(i);
					TestRunProperties.explicitWait(driver, CreateMeetingPage.admit_btn);
					driver.findElement(CreateMeetingPage.admit_btn).click();
					i--;
				}
				Thread.sleep(1000);
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_Close_participant));
				System.out.println("Users added - waiting area modal close");
			} catch (Exception e) {
				TestRunProperties.sessionUrl.add(new String[] { "Null", "Fail" });
				System.out.print("Exception in addparticipant method..");
				e.printStackTrace();
			}
			UtilityClass.driver1 = driver;

			// TestRunProperties.sessionUrl.add(new
			// String[]{MeetingName,userName,Node,UtilityClass.CopiedURL,"Pass"});

		} catch (Exception e) {
			TestRunProperties.sessionUrl.add(new String[] { "Null", "Fail" });
			System.out.print("Exception in addparticipant method..");
			e.printStackTrace();
		}
		try {
			Thread.sleep(000);
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.screenShare_icon));
			Thread.sleep(3000);
			driver.findElement(CreateMeetingPage.screenshare_div);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.screenshare_div).isDisplayed(),
					"Screenshare is not visible..");
			synchronized (Lock) {
				tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				Thread.sleep(3000);
				System.out.println("Screen Shared Successfully...");
				driver.switchTo().window(tabs.get(0));
			}
			Thread.sleep(2000);
			TestRunProperties.sessionUrl
					.add(new String[] { meetingName, userName, node, UtilityClass.CopiedURL, "Pass" });
		} catch (Exception e) {
			TestRunProperties.sessionUrl.add(new String[] { meetingName, userName, node, "Null", "Fail" });
			System.out.println("Exception in screenShare method.." + e);
			e.printStackTrace();
		}
	}

	public void screenShareAudioVideoCall(By callType, String meetingName, String userName, String node)
			throws InterruptedException {

		WebDriver driver = drivers.get();
		if (!TestRunProperties.ExecutionType.equalsIgnoreCase("Headless")) {
			driver.manage().window().maximize();
		}
		System.out.println(driver);
		System.out.println("====================");
		JavascriptExecutor js = (JavascriptExecutor) driver;

		synchronized (Lock) {
			try {
				agentIndex = agentIndex + 1;
				agentIndexPassword = agentIndexPassword + 1;
				if (CreateMeeting.param.size() - 1 < agentIndex) {
					agentIndex = 1;
					agentIndexPassword = 1;
				}
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.get(TestRunProperties.baseUrl);
				assertEquals("Loookit", driver.getTitle());
				Assert.assertTrue(driver.findElement(CreateMeetingPage.engLang).isDisplayed(),
						"English language radio button is missing on landing page.");
				driver.findElement(CreateMeetingPage.engLang).click();
				driver.findElement(CreateMeetingPage.txt_Email).sendKeys(CreateMeeting.param.get(agentIndex));
				driver.findElement(CreateMeetingPage.txt_password)
						.sendKeys(CreateMeeting.paramPassword.get(agentIndexPassword));
				System.out.println(CreateMeeting.paramPassword.get(agentIndexPassword));
				js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_signIn));
				System.out.println("Successfully logged in with Agent : " + CreateMeeting.param.get(agentIndex));
			} catch (Exception e) {
				System.out.println("Exception in UserLogIn method.." + e);
			}
		}

		Thread.sleep(4000);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.creatMeeting).isDisplayed(),
				"Create Meeting option is missing");
		TestRunProperties.explicitWait(driver, CreateMeetingPage.creatMeeting);
		driver.findElement(CreateMeetingPage.creatMeeting).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.setMeetingTab).isDisplayed(),
				"Set up a new meeting tab is missing..");
		String meetingType = TestRunProperties.meetingOption;
		if (meetingType.equalsIgnoreCase("Passcode")) {
			TestRunProperties.explicitWait(driver, CreateMeetingPage.setMeetingTab);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.btn_Proceed).isDisplayed(),
					"'Send Invite and Start' button is missing for create session.");
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Proceed));
		} else {
			TestRunProperties.explicitWait(driver, CreateMeetingPage.setMeetingTab);
			driver.findElement(CreateMeetingPage.setMeetingTab).click();
			Thread.sleep(2000);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.txt_SessionName).isDisplayed(),
					"Enter session name input box is missing");
			TestRunProperties.explicitWait(driver, CreateMeetingPage.txt_SessionName);
			driver.findElement(CreateMeetingPage.txt_SessionName).sendKeys(meetingName);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.btn_Proceed).isDisplayed(),
					"'Send Invite and Start' button is missing for create session.");
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Proceed));
		}
		System.out.println("Successfully created a session");
		CreateMeeting.sleep(4000);

		try {
			synchronized (Lock) {
				System.out.println(Thread.currentThread().getId() + "is inn++++++++");
				String[] split = null;
				List<LogEntry> entries = driver.manage().logs().get("performance").getAll();
				for (LogEntry entry : entries) {
					if (entry.toString().contains("sessionKey")) {
						if (!entry.toString().contains("sessionKey%"))
//							System.out.println(entry.toString());
							split = entry.toString().split("sessionKey");
					}
				}
//				System.out.println("====================");
//				for (int i = 0; i < split.length; i++) {
//					System.out.println(split[i]);
//				}
//				System.out.println(split[1].substring(5, 13));
				sessionKey = split[1].substring(5, 13);
				sessionKeyWithMeetings.put(sessionKey, meetingName);
				Thread.sleep(2000);
				js.executeScript("window.open('about:blank','_blank');");
				tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				driver.get(joiningURL + sessionKey);
				TestRunProperties.explicitWait(driver, CreateMeetingPage.txt_UserName);
				driver.findElement(CreateMeetingPage.txt_UserName).sendKeys(userName);
				TestRunProperties.explicitWait(driver, CreateMeetingPage.btn_ProceedUser);
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_ProceedUser));
				Thread.sleep(4000);
				// Start Audio/Video call at Agent side
				driver.switchTo().window(tabs.get(0));
				System.out.println(Thread.currentThread().getId() + "is out------");
			}
			// Admit User
			System.out.println("In admin User");
			try {
				TestRunProperties.explicitWait(driver, CreateMeetingPage.img_AddInvitee);
				assertTrue(driver.findElement(CreateMeetingPage.img_AddInvitee).isDisplayed(),
						"Participant button is missing.");
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_AddInvitee));
				System.out.println("Click on img_AddInvitee button");

				TestRunProperties.explicitWait(driver, CreateMeetingPage.waitingUsers);
				driver.findElement(CreateMeetingPage.waitingUsers).isDisplayed();
				System.out.println("User is waiting....");

				Actions action = new Actions(driver);
				action.moveToElement(driver.findElement(CreateMeetingPage.waitingUsers)).perform();

				TestRunProperties.explicitWait(driver, CreateMeetingPage.admit_btn);
				assertTrue(driver.findElement(CreateMeetingPage.admit_btn).isDisplayed(),
						"Admit button is missing for User.");
				System.out.println("Adding users from waiting area");

				TestRunProperties.explicitWait(driver, CreateMeetingPage.waitingUsers);
				List<WebElement> li = driver.findElements(CreateMeetingPage.waitingUsers);
				int i = li.size();
				while (i > 0) {
					System.out.println(i);
					TestRunProperties.explicitWait(driver, CreateMeetingPage.admit_btn);
					driver.findElement(CreateMeetingPage.admit_btn).click();
					i--;
				}
				Thread.sleep(1000);
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_Close_participant));
				System.out.println("Users added - waiting area modal close");
			} catch (Exception e) {
				TestRunProperties.sessionUrl.add(new String[] { "Null", "Fail" });
				System.out.print("Exception in addparticipant method..");
				e.printStackTrace();
			}
			UtilityClass.driver1 = driver;

			// TestRunProperties.sessionUrl.add(new
			// String[]{MeetingName,userName,Node,UtilityClass.CopiedURL,"Pass"});

		} catch (Exception e) {
			TestRunProperties.sessionUrl.add(new String[] { "Null", "Fail" });
			System.out.print("Exception in addparticipant method..");
			e.printStackTrace();
		}
		try {
			Thread.sleep(10000);
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.screenShare_icon));
			Thread.sleep(3000);
			driver.findElement(CreateMeetingPage.screenshare_div);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.screenshare_div).isDisplayed(),
					"Screenshare is not visible..");
			js.executeScript("arguments[0].click();", driver.findElement(callType));
			// Accept Audio\video Request at User side
			synchronized (Lock) {
				tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Confirm));
				Thread.sleep(4000);
				driver.findElement(CreateMeetingPage.screenshare_div);
				Assert.assertTrue(driver.findElement(CreateMeetingPage.screenshare_div).isDisplayed(),
						"Screenshare is not visible..");
				System.out.println("Audio/Video call with Screenshare established Successfully");
				driver.switchTo().window(tabs.get(0));
			}
			Thread.sleep(2000);
			TestRunProperties.sessionUrl
					.add(new String[] { meetingName, userName, node, UtilityClass.CopiedURL, "Pass" });
		} catch (Exception e) {
			TestRunProperties.sessionUrl.add(new String[] { meetingName, userName, node, "Null", "Fail" });
			System.out.println("Exception in screenShareAudioCall method.." + e);
		}
	}

	public void videoCall(String meetingName, String userName, String node) throws InterruptedException {

		WebDriver driver = drivers.get();
		if (!TestRunProperties.ExecutionType.equalsIgnoreCase("Headless")) {
			driver.manage().window().maximize();
		}
		System.out.println(driver);
		System.out.println("====================");
		JavascriptExecutor js = (JavascriptExecutor) driver;

		synchronized (Lock) {
			try {
				agentIndex = agentIndex + 1;
				agentIndexPassword = agentIndexPassword + 1;
				if (CreateMeeting.param.size() - 1 < agentIndex) {
					agentIndex = 1;
					agentIndexPassword = 1;
				}
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.get(TestRunProperties.baseUrl);
				assertEquals("Loookit", driver.getTitle());
				Assert.assertTrue(driver.findElement(CreateMeetingPage.engLang).isDisplayed(),
						"English language radio button is missing on landing page.");
				driver.findElement(CreateMeetingPage.engLang).click();
				driver.findElement(CreateMeetingPage.txt_Email).sendKeys(CreateMeeting.param.get(agentIndex));
				driver.findElement(CreateMeetingPage.txt_password)
						.sendKeys(CreateMeeting.paramPassword.get(agentIndexPassword));
				System.out.println(CreateMeeting.paramPassword.get(agentIndexPassword));
				js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_signIn));
				System.out.println("Successfully logged in with Agent : " + CreateMeeting.param.get(agentIndex));
			} catch (Exception e) {
				System.out.println("Exception in UserLogIn method.." + e);
			}
		}

		Thread.sleep(4000);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.creatMeeting).isDisplayed(),
				"Create Meeting option is missing");
		TestRunProperties.explicitWait(driver, CreateMeetingPage.creatMeeting);
		driver.findElement(CreateMeetingPage.creatMeeting).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.setMeetingTab).isDisplayed(),
				"Set up a new meeting tab is missing..");
		String meetingType = TestRunProperties.meetingOption;
		if (meetingType.equalsIgnoreCase("Passcode")) {
			TestRunProperties.explicitWait(driver, CreateMeetingPage.setMeetingTab);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.btn_Proceed).isDisplayed(),
					"'Send Invite and Start' button is missing for create session.");
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Proceed));
		} else {
			TestRunProperties.explicitWait(driver, CreateMeetingPage.setMeetingTab);
			driver.findElement(CreateMeetingPage.setMeetingTab).click();
			Thread.sleep(2000);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.txt_SessionName).isDisplayed(),
					"Enter session name input box is missing");
			TestRunProperties.explicitWait(driver, CreateMeetingPage.txt_SessionName);
			driver.findElement(CreateMeetingPage.txt_SessionName).sendKeys(meetingName);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.btn_Proceed).isDisplayed(),
					"'Send Invite and Start' button is missing for create session.");
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Proceed));
		}
		System.out.println("Successfully created a session");
		CreateMeeting.sleep(4000);

		try {
			synchronized (Lock) {
				System.out.println(Thread.currentThread().getId() + "is inn++++++++");
				String[] split = null;
				List<LogEntry> entries = driver.manage().logs().get("performance").getAll();
				for (LogEntry entry : entries) {
					if (entry.toString().contains("sessionKey")) {
						if (!entry.toString().contains("sessionKey%"))
//							System.out.println(entry.toString());
							split = entry.toString().split("sessionKey");
					}
				}
//				System.out.println("====================");
//				for (int i = 0; i < split.length; i++) {
//					System.out.println(split[i]);
//				}
//				System.out.println(split[1].substring(5, 13));
				sessionKey = split[1].substring(5, 13);
				sessionKeyWithMeetings.put(sessionKey, meetingName);
				Thread.sleep(2000);
				js.executeScript("window.open('about:blank','_blank');");
				tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				driver.get(joiningURL + sessionKey);
				TestRunProperties.explicitWait(driver, CreateMeetingPage.txt_UserName);
				driver.findElement(CreateMeetingPage.txt_UserName).sendKeys(userName);
				TestRunProperties.explicitWait(driver, CreateMeetingPage.btn_ProceedUser);
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_ProceedUser));
				Thread.sleep(4000);
				// Start Audio/Video call at Agent side
				driver.switchTo().window(tabs.get(0));
				System.out.println(Thread.currentThread().getId() + "is out------");
			}
			// Admit User
			System.out.println("In admin User");
			try {
				TestRunProperties.explicitWait(driver, CreateMeetingPage.img_AddInvitee);
				assertTrue(driver.findElement(CreateMeetingPage.img_AddInvitee).isDisplayed(),
						"Participant button is missing.");
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_AddInvitee));
				System.out.println("Click on img_AddInvitee button");

				TestRunProperties.explicitWait(driver, CreateMeetingPage.waitingUsers);
				driver.findElement(CreateMeetingPage.waitingUsers).isDisplayed();
				System.out.println("User is waiting....");

				Actions action = new Actions(driver);
				action.moveToElement(driver.findElement(CreateMeetingPage.waitingUsers)).perform();

				TestRunProperties.explicitWait(driver, CreateMeetingPage.admit_btn);
				assertTrue(driver.findElement(CreateMeetingPage.admit_btn).isDisplayed(),
						"Admit button is missing for User.");
				System.out.println("Adding users from waiting area");

				TestRunProperties.explicitWait(driver, CreateMeetingPage.waitingUsers);
				List<WebElement> li = driver.findElements(CreateMeetingPage.waitingUsers);
				int i = li.size();
				while (i > 0) {
					System.out.println(i);
					TestRunProperties.explicitWait(driver, CreateMeetingPage.admit_btn);
					driver.findElement(CreateMeetingPage.admit_btn).click();
					i--;
				}
				Thread.sleep(1000);
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_Close_participant));
				System.out.println("Users added - waiting area modal close");
			} catch (Exception e) {
				TestRunProperties.sessionUrl.add(new String[] { "Null", "Fail" });
				System.out.print("Exception in addparticipant method..");
				e.printStackTrace();
			}
			UtilityClass.driver1 = driver;

			// TestRunProperties.sessionUrl.add(new
			// String[]{MeetingName,userName,Node,UtilityClass.CopiedURL,"Pass"});

		} catch (Exception e) {
			TestRunProperties.sessionUrl.add(new String[] { "Null", "Fail" });
			System.out.print("Exception in addparticipant method..");
			e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_Video));
			synchronized (Lock) {
				ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
				// Accept video Request at User side
				driver.switchTo().window(tabs1.get(1));
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Confirm));
				Thread.sleep(3000);
				System.out.println("Video call established Successfully");
				driver.switchTo().window(tabs1.get(0));
			}
			Thread.sleep(2000);
			TestRunProperties.sessionUrl
					.add(new String[] { meetingName, userName, node, UtilityClass.CopiedURL, "Pass" });
		} catch (Exception e) {
			TestRunProperties.sessionUrl.add(new String[] { meetingName, userName, node, "Null", "Fail" });
			System.out.println("Exception in videoCall method.." + e);
		}
	}

	public void audioCall(String meetingName, String userName, String node) throws InterruptedException {

		WebDriver driver = drivers.get();
		if (!TestRunProperties.ExecutionType.equalsIgnoreCase("Headless")) {
			driver.manage().window().maximize();
		}
		System.out.println(driver);
		System.out.println("====================");
		JavascriptExecutor js = (JavascriptExecutor) driver;

		synchronized (Lock) {
			try {
				agentIndex = agentIndex + 1;
				agentIndexPassword = agentIndexPassword + 1;
				if (CreateMeeting.param.size() - 1 < agentIndex) {
					agentIndex = 1;
					agentIndexPassword = 1;
				}
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.get(TestRunProperties.baseUrl);
				assertEquals("Loookit", driver.getTitle());
				Assert.assertTrue(driver.findElement(CreateMeetingPage.engLang).isDisplayed(),
						"English language radio button is missing on landing page.");
				driver.findElement(CreateMeetingPage.engLang).click();
				driver.findElement(CreateMeetingPage.txt_Email).sendKeys(CreateMeeting.param.get(agentIndex));
				driver.findElement(CreateMeetingPage.txt_password)
						.sendKeys(CreateMeeting.paramPassword.get(agentIndexPassword));
				System.out.println(CreateMeeting.paramPassword.get(agentIndexPassword));
				js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_signIn));
				System.out.println("Successfully logged in with Agent : " + CreateMeeting.param.get(agentIndex));
			} catch (Exception e) {
				System.out.println("Exception in UserLogIn method.." + e);
			}
		}

		Thread.sleep(4000);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.creatMeeting).isDisplayed(),
				"Create Meeting option is missing");
		TestRunProperties.explicitWait(driver, CreateMeetingPage.creatMeeting);
		driver.findElement(CreateMeetingPage.creatMeeting).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.setMeetingTab).isDisplayed(),
				"Set up a new meeting tab is missing..");
		String meetingType = TestRunProperties.meetingOption;
		if (meetingType.equalsIgnoreCase("Passcode")) {
			TestRunProperties.explicitWait(driver, CreateMeetingPage.setMeetingTab);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.btn_Proceed).isDisplayed(),
					"'Send Invite and Start' button is missing for create session.");
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Proceed));
		} else {
			TestRunProperties.explicitWait(driver, CreateMeetingPage.setMeetingTab);
			driver.findElement(CreateMeetingPage.setMeetingTab).click();
			Thread.sleep(2000);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.txt_SessionName).isDisplayed(),
					"Enter session name input box is missing");
			TestRunProperties.explicitWait(driver, CreateMeetingPage.txt_SessionName);
			driver.findElement(CreateMeetingPage.txt_SessionName).sendKeys(meetingName);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.btn_Proceed).isDisplayed(),
					"'Send Invite and Start' button is missing for create session.");
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Proceed));
		}
		System.out.println("Successfully created a session");
		CreateMeeting.sleep(4000);

		try {
			synchronized (Lock) {
				System.out.println(Thread.currentThread().getId() + "is inn++++++++");
				String[] split = null;
				List<LogEntry> entries = driver.manage().logs().get("performance").getAll();
				for (LogEntry entry : entries) {
					if (entry.toString().contains("sessionKey")) {
						if (!entry.toString().contains("sessionKey%"))
//							System.out.println(entry.toString());
							split = entry.toString().split("sessionKey");
					}
				}
//				System.out.println("====================");
//				for (int i = 0; i < split.length; i++) {
//					System.out.println(split[i]);
//				}
//				System.out.println(split[1].substring(5, 13));
				sessionKey = split[1].substring(5, 13);
				sessionKeyWithMeetings.put(sessionKey, meetingName);
				System.out.println("%%%%%%%%%%%%%%%%%%%%%");
				System.out.println(sessionKeyWithMeetings);
				Thread.sleep(2000);
				js.executeScript("window.open('about:blank','_blank');");
				tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				driver.get(joiningURL + sessionKey);
				TestRunProperties.explicitWait(driver, CreateMeetingPage.txt_UserName);
				driver.findElement(CreateMeetingPage.txt_UserName).sendKeys(userName);
				TestRunProperties.explicitWait(driver, CreateMeetingPage.btn_ProceedUser);
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_ProceedUser));
				Thread.sleep(4000);
				// Start Audio/Video call at Agent side
				driver.switchTo().window(tabs.get(0));
				System.out.println(Thread.currentThread().getId() + "is out------");
			}
			// Admit User
			System.out.println("In admin User");
			try {
				TestRunProperties.explicitWait(driver, CreateMeetingPage.img_AddInvitee);
				assertTrue(driver.findElement(CreateMeetingPage.img_AddInvitee).isDisplayed(),
						"Participant button is missing.");
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_AddInvitee));
				System.out.println("Click on img_AddInvitee button");

				TestRunProperties.explicitWait(driver, CreateMeetingPage.waitingUsers);
				driver.findElement(CreateMeetingPage.waitingUsers).isDisplayed();
				System.out.println("User is waiting....");

				Actions action = new Actions(driver);
				action.moveToElement(driver.findElement(CreateMeetingPage.waitingUsers)).perform();

				TestRunProperties.explicitWait(driver, CreateMeetingPage.admit_btn);
				assertTrue(driver.findElement(CreateMeetingPage.admit_btn).isDisplayed(),
						"Admit button is missing for User.");
				System.out.println("Adding users from waiting area");

				TestRunProperties.explicitWait(driver, CreateMeetingPage.waitingUsers);
				List<WebElement> li = driver.findElements(CreateMeetingPage.waitingUsers);
				int i = li.size();
				while (i > 0) {
					System.out.println(i);
					TestRunProperties.explicitWait(driver, CreateMeetingPage.admit_btn);
					driver.findElement(CreateMeetingPage.admit_btn).click();
					i--;
				}
				Thread.sleep(1000);
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_Close_participant));
				System.out.println("Users added - waiting area modal close");
			} catch (Exception e) {
				TestRunProperties.sessionUrl.add(new String[] { "Null", "Fail" });
				System.out.print("Exception in addparticipant method..");
				e.printStackTrace();
			}
			UtilityClass.driver1 = driver;

			// TestRunProperties.sessionUrl.add(new
			// String[]{MeetingName,userName,Node,UtilityClass.CopiedURL,"Pass"});

		} catch (Exception e) {
			TestRunProperties.sessionUrl.add(new String[] { "Null", "Fail" });
			System.out.print("Exception in addparticipant method..");
			e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.audio_icon));
			synchronized (Lock) {
				ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());

				driver.switchTo().window(tabs1.get(1));
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Confirm));
				Thread.sleep(3000);
				System.out.println("Audio call established Successfully");
				driver.switchTo().window(tabs1.get(0));
			}
			Thread.sleep(2000);
			TestRunProperties.sessionUrl
					.add(new String[] { meetingName, userName, node, UtilityClass.CopiedURL, "Pass" });
		} catch (Exception e) {
			TestRunProperties.sessionUrl.add(new String[] { meetingName, userName, node, "Null", "Fail" });
			System.out.println("Exception in audioCall call method.." + e);
			TestRunProperties.sessionUrl.add(new String[] { "Null", "Fail" });
		}
	}

	public void addParticipant(String meetingName, String userName, String node) throws InterruptedException {
		WebDriver driver = drivers.get();
		if (!TestRunProperties.ExecutionType.equalsIgnoreCase("Headless")) {
			driver.manage().window().maximize();
		}
		System.out.println(driver);
		System.out.println("====================");
		JavascriptExecutor js = (JavascriptExecutor) driver;

		synchronized (Lock) {
			try {
				agentIndex = agentIndex + 1;
				agentIndexPassword = agentIndexPassword + 1;
				if (CreateMeeting.param.size() - 1 < agentIndex) {
					agentIndex = 1;
					agentIndexPassword = 1;
				}
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.get(TestRunProperties.baseUrl);
				assertEquals("Loookit", driver.getTitle());
				Assert.assertTrue(driver.findElement(CreateMeetingPage.engLang).isDisplayed(),
						"English language radio button is missing on landing page.");
				driver.findElement(CreateMeetingPage.engLang).click();
				driver.findElement(CreateMeetingPage.txt_Email).sendKeys(CreateMeeting.param.get(agentIndex));
				driver.findElement(CreateMeetingPage.txt_password)
						.sendKeys(CreateMeeting.paramPassword.get(agentIndexPassword));
				System.out.println(CreateMeeting.paramPassword.get(agentIndexPassword));
				js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_signIn));
				System.out.println("Successfully logged in with Agent : " + CreateMeeting.param.get(agentIndex));
			} catch (Exception e) {
				System.out.println("Exception in UserLogIn method.." + e);
			}
		}

		Thread.sleep(4000);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.creatMeeting).isDisplayed(),
				"Create Meeting option is missing");
		TestRunProperties.explicitWait(driver, CreateMeetingPage.creatMeeting);
		driver.findElement(CreateMeetingPage.creatMeeting).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.setMeetingTab).isDisplayed(),
				"Set up a new meeting tab is missing..");
		String meetingType = TestRunProperties.meetingOption;
		if (meetingType.equalsIgnoreCase("Passcode")) {
			TestRunProperties.explicitWait(driver, CreateMeetingPage.setMeetingTab);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.btn_Proceed).isDisplayed(),
					"'Send Invite and Start' button is missing for create session.");
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Proceed));
		} else {
			TestRunProperties.explicitWait(driver, CreateMeetingPage.setMeetingTab);
			driver.findElement(CreateMeetingPage.setMeetingTab).click();
			Thread.sleep(2000);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.txt_SessionName).isDisplayed(),
					"Enter session name input box is missing");
			TestRunProperties.explicitWait(driver, CreateMeetingPage.txt_SessionName);
			driver.findElement(CreateMeetingPage.txt_SessionName).sendKeys(meetingName);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.btn_Proceed).isDisplayed(),
					"'Send Invite and Start' button is missing for create session.");
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Proceed));
		}
		System.out.println("Successfully created a session");
		CreateMeeting.sleep(4000);

		try {
			synchronized (Lock) {
				System.out.println(Thread.currentThread().getId() + "is inn++++++++");
				String[] split = null;
				List<LogEntry> entries = driver.manage().logs().get("performance").getAll();
				for (LogEntry entry : entries) {
					if (entry.toString().contains("sessionKey")) {
						if (!entry.toString().contains("sessionKey%"))
//							System.out.println(entry.toString());
							split = entry.toString().split("sessionKey");
					}
				}
//				System.out.println("====================");
//				for (int i = 0; i < split.length; i++) {
//					System.out.println(split[i]);
//				}
//				System.out.println(split[1].substring(5, 13));
				sessionKey = split[1].substring(5, 13);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			driver1 = driver;
		} catch (Exception e) {
			System.out.println("Exception in addParticipant method..");
			TestRunProperties.sessionUrl.add(new String[] { meetingName, userName, node, "Null", "Fail" });
		}
	}

	public void userSharescreen(String meetingName, String userName, String node, Meeting meetingDetails)
			throws InterruptedException {
		WebDriver driver = getDriver();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		synchronized (Lock) {
			String entrySessionKey = null;
			for (Entry<String, String> entry : sessionKeyWithMeetings.entrySet()) {
				if (meetingName.equals(entry.getValue())) {
					System.out.print(entry.getKey() + " : " + entry.getValue());
					entrySessionKey = entry.getKey();
				}
			}
			driver.get(UtilityClass.joiningURL + entrySessionKey);
		}
		// System.out.print(url);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.engLang).isDisplayed(),
				"English language radio button is missing on landing page.");
		driver.findElement(CreateMeetingPage.engLang).click();
		Thread.sleep(3000);
		TestRunProperties.explicitWait(driver, CreateMeetingPage.txt_UserName);
		driver.findElement(CreateMeetingPage.txt_UserName).sendKeys(userName);
		js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_ProceedUser));
		Thread.sleep(3000);
		System.out.println("Number of users - " + TestRunProperties.users);
		synchronized (driver) {
			TestRunProperties.noOfThreads = TestRunProperties.noOfThreads + 1;
			System.out.println("Number of thread - " + TestRunProperties.noOfThreads);
		}
		if (TestRunProperties.totalnoOfThreads == TestRunProperties.noOfThreads) {
			HashMap<String, RemoteWebDriver> tempList = TestRunProperties.driverMap;
			for (Entry<String, RemoteWebDriver> entry : tempList.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
				if (entry.getKey().contains("Host") == true) {
					try {
						driver = entry.getValue();
						JavascriptExecutor js1 = ((JavascriptExecutor) driver);
						System.out.println("In admin User");
						TestRunProperties.explicitWait(driver, CreateMeetingPage.img_AddInvitee);
						assertTrue(driver.findElement(CreateMeetingPage.img_AddInvitee).isDisplayed(),
								"Participant button is missing.");
						js1.executeScript("arguments[0].click();",
								driver.findElement(CreateMeetingPage.img_AddInvitee));
						System.out.println("Click on img_AddInvitee button");

						TestRunProperties.explicitWait(driver, CreateMeetingPage.waitingUsers);
						driver.findElement(CreateMeetingPage.waitingUsers).isDisplayed();
						System.out.println("User is waiting....");

						Actions action = new Actions(driver);
						action.moveToElement(driver.findElement(CreateMeetingPage.waitingUsers)).perform();

						TestRunProperties.explicitWait(driver, CreateMeetingPage.admit_btn);
						assertTrue(driver.findElement(CreateMeetingPage.admit_btn).isDisplayed(),
								"Admit button is missing for User.");
						System.out.println("Adding users from waiting area");

						TestRunProperties.explicitWait(driver, CreateMeetingPage.waitingUsers);
						List<WebElement> li = driver.findElements(CreateMeetingPage.waitingUsers);
						int i = li.size();
						while (i > 0) {
							System.out.println(i);
							TestRunProperties.explicitWait(driver, CreateMeetingPage.admit_btn);
							js1.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.admit_btn));
							i--;
						}
						Thread.sleep(1000);
						js1.executeScript("arguments[0].click();",
								driver.findElement(CreateMeetingPage.img_Close_participant));
						System.out.println("Users added - waiting area modal close");

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
//			UtilityClass.admitUser(UtilityClass.driver1);
		}
		Thread.sleep(4000);
		TestRunProperties.explicitWait(driver, CreateMeetingPage.screenshare_div);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.screenshare_div).isDisplayed(),
				"Screenshare is not visible..");
		System.out.print("Screenshare is visible for user: " + userName);

		if (TestRunProperties.runningMeeting.containsKey(meetingName)) {
			meetingDetails = TestRunProperties.runningMeeting.get(meetingName);
			userInfo userInfo = new userInfo();
			userInfo.setMeetingName(meetingName);
			userInfo.setDriver(driver);
			userInfo.setNodeIp(node);
			userInfo.setUserName(userName);
			if (userName.contains("Host") == true) {
				userInfo.setHost(true);
			}
			meetingDetails.addUser(userInfo);
			TestRunProperties.runningMeeting.put(meetingName, meetingDetails);
		}
	}

	public void userAudio(String meetingName, String userName, String node, Meeting meetingDetails)
			throws InterruptedException {
		WebDriver driver = getDriver();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		synchronized (Lock) {
			String entrySessionKey = null;
			for (Entry<String, String> entry : sessionKeyWithMeetings.entrySet()) {
				if (meetingName.equals(entry.getValue())) {
					System.out.print(entry.getKey() + " : " + entry.getValue());
					entrySessionKey = entry.getKey();
				}
			}
			driver.get(UtilityClass.joiningURL + entrySessionKey);
		}
		// System.out.print(url);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.engLang).isDisplayed(),
				"English language radio button is missing on landing page.");
		driver.findElement(CreateMeetingPage.engLang).click();
		Thread.sleep(3000);
		TestRunProperties.explicitWait(driver, CreateMeetingPage.txt_UserName);
		driver.findElement(CreateMeetingPage.txt_UserName).sendKeys(userName);
		js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_ProceedUser));
		Thread.sleep(3000);
		System.out.println("Number of users - " + TestRunProperties.users);
		synchronized (driver) {
			TestRunProperties.noOfThreads = TestRunProperties.noOfThreads + 1;
			System.out.println("Number of thread - " + TestRunProperties.noOfThreads);
		}
		if (TestRunProperties.totalnoOfThreads == TestRunProperties.noOfThreads) {
			HashMap<String, RemoteWebDriver> tempList = TestRunProperties.driverMap;
			for (Entry<String, RemoteWebDriver> entry : tempList.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
				if (entry.getKey().contains("Host") == true) {
					try {
						RemoteWebDriver driver2 = entry.getValue();
						JavascriptExecutor js1 = ((JavascriptExecutor) driver2);
						System.out.println("In admin User");
						TestRunProperties.explicitWait(driver2, CreateMeetingPage.img_AddInvitee);
						assertTrue(driver2.findElement(CreateMeetingPage.img_AddInvitee).isDisplayed(),
								"Participant button is missing.");
						js1.executeScript("arguments[0].click();",
								driver2.findElement(CreateMeetingPage.img_AddInvitee));
						System.out.println("Click on img_AddInvitee button");

						TestRunProperties.explicitWait(driver2, CreateMeetingPage.waitingUsers);
						driver2.findElement(CreateMeetingPage.waitingUsers).isDisplayed();
						System.out.println("User is waiting....");

						Actions action = new Actions(driver2);
						action.moveToElement(driver2.findElement(CreateMeetingPage.waitingUsers)).perform();

						TestRunProperties.explicitWait(driver2, CreateMeetingPage.admit_btn);
						assertTrue(driver2.findElement(CreateMeetingPage.admit_btn).isDisplayed(),
								"Admit button is missing for User.");
						System.out.println("Adding users from waiting area");

						TestRunProperties.explicitWait(driver2, CreateMeetingPage.waitingUsers);
						List<WebElement> li = driver2.findElements(CreateMeetingPage.waitingUsers);
						int i = li.size();
						while (i > 0) {
							System.out.println(i);
							TestRunProperties.explicitWait(driver2, CreateMeetingPage.admit_btn);
							js1.executeScript("arguments[0].click();",
									driver2.findElement(CreateMeetingPage.admit_btn));
							i--;
						}
						Thread.sleep(1000);
						js1.executeScript("arguments[0].click();",
								driver2.findElement(CreateMeetingPage.img_Close_participant));
						System.out.println("Users added - waiting area modal close");

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
//			UtilityClass.admitUser(UtilityClass.driver1);
		}
		try {
			Thread.sleep(2000);
			TestRunProperties.explicitWait(driver, CreateMeetingPage.btn_Confirm);
			WebElement modalContainer = driver.findElement(CreateMeetingPage.accept_modal);
			WebElement modalContent = modalContainer.findElement(CreateMeetingPage.btn_Confirm);
			modalContent.click();
			Thread.sleep(1000);
			TestRunProperties.sessionUrl
					.add(new String[] { meetingName, userName, node, UtilityClass.CopiedURL, "Pass" });
		} catch (Exception e) {
			System.out.println("Exception in userAudio method.." + e);
		}

		if (TestRunProperties.runningMeeting.containsKey(meetingName)) {
			meetingDetails = TestRunProperties.runningMeeting.get(meetingName);
			userInfo userInfo = new userInfo();
			userInfo.setMeetingName(meetingName);
			userInfo.setDriver(driver);
			userInfo.setNodeIp(node);
			userInfo.setUserName(userName);
			if (userName.contains("Host") == true) {
				userInfo.setHost(true);
			}
			meetingDetails.addUser(userInfo);
			TestRunProperties.runningMeeting.put(meetingName, meetingDetails);
		}
	}
	
	
	public void userVideo(String meetingName, String userName, String node, Meeting meetingDetails)
			throws InterruptedException {
		WebDriver driver = getDriver();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		synchronized (Lock) {
			String entrySessionKey = null;
			for (Entry<String, String> entry : sessionKeyWithMeetings.entrySet()) {
				if (meetingName.equals(entry.getValue())) {
					System.out.print(entry.getKey() + " : " + entry.getValue());
					entrySessionKey = entry.getKey();
				}
			}
			driver.get(UtilityClass.joiningURL + entrySessionKey);
		}
		// System.out.print(url);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.engLang).isDisplayed(),
				"English language radio button is missing on landing page.");
		driver.findElement(CreateMeetingPage.engLang).click();
		Thread.sleep(3000);
		TestRunProperties.explicitWait(driver, CreateMeetingPage.txt_UserName);
		driver.findElement(CreateMeetingPage.txt_UserName).sendKeys(userName);
		js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_ProceedUser));
		Thread.sleep(3000);
		System.out.println("Number of users - " + TestRunProperties.users);
		synchronized (driver) {
			TestRunProperties.noOfThreads = TestRunProperties.noOfThreads + 1;
			System.out.println("Number of thread - " + TestRunProperties.noOfThreads);
		}
		if (TestRunProperties.totalnoOfThreads == TestRunProperties.noOfThreads) {
			HashMap<String, RemoteWebDriver> tempList = TestRunProperties.driverMap;
			for (Entry<String, RemoteWebDriver> entry : tempList.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
				if (entry.getKey().contains("Host") == true) {
					try {
						RemoteWebDriver driver2 = entry.getValue();
						JavascriptExecutor js1 = ((JavascriptExecutor) driver2);
						System.out.println("In admin User");
						TestRunProperties.explicitWait(driver2, CreateMeetingPage.img_AddInvitee);
						assertTrue(driver2.findElement(CreateMeetingPage.img_AddInvitee).isDisplayed(),
								"Participant button is missing.");
						js1.executeScript("arguments[0].click();",
								driver2.findElement(CreateMeetingPage.img_AddInvitee));
						System.out.println("Click on img_AddInvitee button");

						TestRunProperties.explicitWait(driver2, CreateMeetingPage.waitingUsers);
						driver2.findElement(CreateMeetingPage.waitingUsers).isDisplayed();
						System.out.println("User is waiting....");

						Actions action = new Actions(driver2);
						action.moveToElement(driver2.findElement(CreateMeetingPage.waitingUsers)).perform();

						TestRunProperties.explicitWait(driver2, CreateMeetingPage.admit_btn);
						assertTrue(driver2.findElement(CreateMeetingPage.admit_btn).isDisplayed(),
								"Admit button is missing for User.");
						System.out.println("Adding users from waiting area");

						TestRunProperties.explicitWait(driver2, CreateMeetingPage.waitingUsers);
						List<WebElement> li = driver2.findElements(CreateMeetingPage.waitingUsers);
						int i = li.size();
						while (i > 0) {
							System.out.println(i);
							TestRunProperties.explicitWait(driver2, CreateMeetingPage.admit_btn);
							js1.executeScript("arguments[0].click();",
									driver2.findElement(CreateMeetingPage.admit_btn));
							i--;
						}
						Thread.sleep(1000);
						js1.executeScript("arguments[0].click();",
								driver2.findElement(CreateMeetingPage.img_Close_participant));
						System.out.println("Users added - waiting area modal close");

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
//			UtilityClass.admitUser(UtilityClass.driver1);
		}
		try {
			Thread.sleep(2000);
			TestRunProperties.explicitWait(driver, CreateMeetingPage.btn_Confirm);
			WebElement modalContainer = driver.findElement(CreateMeetingPage.accept_modal);
			WebElement modalContent = modalContainer.findElement(CreateMeetingPage.btn_Confirm);
			modalContent.click();
			Thread.sleep(1000);
			TestRunProperties.sessionUrl
					.add(new String[] { meetingName, userName, node, UtilityClass.CopiedURL, "Pass" });
		} catch (Exception e) {
			System.out.println("Exception in userVideo method.." + e);
		}

		if (TestRunProperties.runningMeeting.containsKey(meetingName)) {
			meetingDetails = TestRunProperties.runningMeeting.get(meetingName);
			userInfo userInfo = new userInfo();
			userInfo.setMeetingName(meetingName);
			userInfo.setDriver(driver);
			userInfo.setNodeIp(node);
			userInfo.setUserName(userName);
			if (userName.contains("Host") == true) {
				userInfo.setHost(true);
			}
			meetingDetails.addUser(userInfo);
			TestRunProperties.runningMeeting.put(meetingName, meetingDetails);
		}
	}
	
	public void userScreenShareAudio(String meetingName, String userName, String node, Meeting meetingDetails)
			throws InterruptedException {
		WebDriver driver = getDriver();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		synchronized (Lock) {
			String entrySessionKey = null;
			for (Entry<String, String> entry : sessionKeyWithMeetings.entrySet()) {
				if (meetingName.equals(entry.getValue())) {
					System.out.print(entry.getKey() + " : " + entry.getValue());
					entrySessionKey = entry.getKey();
				}
			}
			driver.get(UtilityClass.joiningURL + entrySessionKey);
		}
		// System.out.print(url);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.engLang).isDisplayed(),
				"English language radio button is missing on landing page.");
		driver.findElement(CreateMeetingPage.engLang).click();
		Thread.sleep(3000);
		TestRunProperties.explicitWait(driver, CreateMeetingPage.txt_UserName);
		driver.findElement(CreateMeetingPage.txt_UserName).sendKeys(userName);
		js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_ProceedUser));
		Thread.sleep(3000);
		System.out.println("Number of users - " + TestRunProperties.users);
		synchronized (driver) {
			TestRunProperties.noOfThreads = TestRunProperties.noOfThreads + 1;
			System.out.println("Number of thread - " + TestRunProperties.noOfThreads);
		}
		if (TestRunProperties.totalnoOfThreads == TestRunProperties.noOfThreads) {
			HashMap<String, RemoteWebDriver> tempList = TestRunProperties.driverMap;
			for (Entry<String, RemoteWebDriver> entry : tempList.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
				if (entry.getKey().contains("Host") == true) {
					try {
						RemoteWebDriver driver2 = entry.getValue();
						JavascriptExecutor js1 = ((JavascriptExecutor) driver2);
						System.out.println("In admin User");
						TestRunProperties.explicitWait(driver2, CreateMeetingPage.img_AddInvitee);
						assertTrue(driver2.findElement(CreateMeetingPage.img_AddInvitee).isDisplayed(),
								"Participant button is missing.");
						js1.executeScript("arguments[0].click();",
								driver2.findElement(CreateMeetingPage.img_AddInvitee));
						System.out.println("Click on img_AddInvitee button");

						TestRunProperties.explicitWait(driver2, CreateMeetingPage.waitingUsers);
						driver2.findElement(CreateMeetingPage.waitingUsers).isDisplayed();
						System.out.println("User is waiting....");

						Actions action = new Actions(driver2);
						action.moveToElement(driver2.findElement(CreateMeetingPage.waitingUsers)).perform();

						TestRunProperties.explicitWait(driver2, CreateMeetingPage.admit_btn);
						assertTrue(driver2.findElement(CreateMeetingPage.admit_btn).isDisplayed(),
								"Admit button is missing for User.");
						System.out.println("Adding users from waiting area");

						TestRunProperties.explicitWait(driver2, CreateMeetingPage.waitingUsers);
						List<WebElement> li = driver2.findElements(CreateMeetingPage.waitingUsers);
						int i = li.size();
						while (i > 0) {
							System.out.println(i);
							TestRunProperties.explicitWait(driver2, CreateMeetingPage.admit_btn);
							js1.executeScript("arguments[0].click();",
									driver2.findElement(CreateMeetingPage.admit_btn));
							i--;
						}
						Thread.sleep(1000);
						js1.executeScript("arguments[0].click();",
								driver2.findElement(CreateMeetingPage.img_Close_participant));
						System.out.println("Users added - waiting area modal close");

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
//			UtilityClass.admitUser(UtilityClass.driver1);
		}
		try {
			Thread.sleep(2000);
			TestRunProperties.explicitWait(driver, CreateMeetingPage.btn_Confirm);
			WebElement modalContainer = driver.findElement(CreateMeetingPage.accept_modal);
			WebElement modalContent = modalContainer.findElement(CreateMeetingPage.btn_Confirm);
			modalContent.click();
			Thread.sleep(1000);
			TestRunProperties.sessionUrl
					.add(new String[] { meetingName, userName, node, UtilityClass.CopiedURL, "Pass" });
		} catch (Exception e) {
			System.out.println("Exception in userVideo method.." + e);
		}
		TestRunProperties.explicitWait(driver, CreateMeetingPage.screenshare_div);
		Thread.sleep(4000);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.screenshare_div).isDisplayed(),
				"Screenshare is not visible..");

		if (TestRunProperties.runningMeeting.containsKey(meetingName)) {
			meetingDetails = TestRunProperties.runningMeeting.get(meetingName);
			userInfo userInfo = new userInfo();
			userInfo.setMeetingName(meetingName);
			userInfo.setDriver(driver);
			userInfo.setNodeIp(node);
			userInfo.setUserName(userName);
			if (userName.contains("Host") == true) {
				userInfo.setHost(true);
			}
			meetingDetails.addUser(userInfo);
			TestRunProperties.runningMeeting.put(meetingName, meetingDetails);
		}
	}
	
	
	public void userScreenShareVideo(String meetingName, String userName, String node, Meeting meetingDetails)
			throws InterruptedException {
		WebDriver driver = getDriver();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		synchronized (Lock) {
			String entrySessionKey = null;
			for (Entry<String, String> entry : sessionKeyWithMeetings.entrySet()) {
				if (meetingName.equals(entry.getValue())) {
					System.out.print(entry.getKey() + " : " + entry.getValue());
					entrySessionKey = entry.getKey();
				}
			}
			driver.get(UtilityClass.joiningURL + entrySessionKey);
		}
		// System.out.print(url);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.engLang).isDisplayed(),
				"English language radio button is missing on landing page.");
		driver.findElement(CreateMeetingPage.engLang).click();
		Thread.sleep(3000);
		TestRunProperties.explicitWait(driver, CreateMeetingPage.txt_UserName);
		driver.findElement(CreateMeetingPage.txt_UserName).sendKeys(userName);
		js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_ProceedUser));
		Thread.sleep(3000);
		System.out.println("Number of users - " + TestRunProperties.users);
		synchronized (driver) {
			TestRunProperties.noOfThreads = TestRunProperties.noOfThreads + 1;
			System.out.println("Number of thread - " + TestRunProperties.noOfThreads);
		}
		if (TestRunProperties.totalnoOfThreads == TestRunProperties.noOfThreads) {
			HashMap<String, RemoteWebDriver> tempList = TestRunProperties.driverMap;
			for (Entry<String, RemoteWebDriver> entry : tempList.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
				if (entry.getKey().contains("Host") == true) {
					try {
						RemoteWebDriver driver2 = entry.getValue();
						JavascriptExecutor js1 = ((JavascriptExecutor) driver2);
						System.out.println("In admin User");
						TestRunProperties.explicitWait(driver2, CreateMeetingPage.img_AddInvitee);
						assertTrue(driver2.findElement(CreateMeetingPage.img_AddInvitee).isDisplayed(),
								"Participant button is missing.");
						js1.executeScript("arguments[0].click();",
								driver2.findElement(CreateMeetingPage.img_AddInvitee));
						System.out.println("Click on img_AddInvitee button");

						TestRunProperties.explicitWait(driver2, CreateMeetingPage.waitingUsers);
						driver2.findElement(CreateMeetingPage.waitingUsers).isDisplayed();
						System.out.println("User is waiting....");

						Actions action = new Actions(driver2);
						action.moveToElement(driver2.findElement(CreateMeetingPage.waitingUsers)).perform();

						TestRunProperties.explicitWait(driver2, CreateMeetingPage.admit_btn);
						assertTrue(driver2.findElement(CreateMeetingPage.admit_btn).isDisplayed(),
								"Admit button is missing for User.");
						System.out.println("Adding users from waiting area");

						TestRunProperties.explicitWait(driver2, CreateMeetingPage.waitingUsers);
						List<WebElement> li = driver2.findElements(CreateMeetingPage.waitingUsers);
						int i = li.size();
						while (i > 0) {
							System.out.println(i);
							TestRunProperties.explicitWait(driver2, CreateMeetingPage.admit_btn);
							js1.executeScript("arguments[0].click();",
									driver2.findElement(CreateMeetingPage.admit_btn));
							i--;
						}
						Thread.sleep(1000);
						js1.executeScript("arguments[0].click();",
								driver2.findElement(CreateMeetingPage.img_Close_participant));
						System.out.println("Users added - waiting area modal close");

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
//			UtilityClass.admitUser(UtilityClass.driver1);
		}
		try {
			Thread.sleep(2000);
			TestRunProperties.explicitWait(driver, CreateMeetingPage.btn_Confirm);
			WebElement modalContainer = driver.findElement(CreateMeetingPage.accept_modal);
			WebElement modalContent = modalContainer.findElement(CreateMeetingPage.btn_Confirm);
			modalContent.click();
			Thread.sleep(1000);
			TestRunProperties.sessionUrl
					.add(new String[] { meetingName, userName, node, UtilityClass.CopiedURL, "Pass" });
		} catch (Exception e) {
			System.out.println("Exception in userVideo method.." + e);
		}
		TestRunProperties.explicitWait(driver, CreateMeetingPage.screenshare_div);
		Thread.sleep(4000);
		Assert.assertTrue(driver.findElement(CreateMeetingPage.screenshare_div).isDisplayed(),
				"Screenshare is not visible..");

		if (TestRunProperties.runningMeeting.containsKey(meetingName)) {
			meetingDetails = TestRunProperties.runningMeeting.get(meetingName);
			userInfo userInfo = new userInfo();
			userInfo.setMeetingName(meetingName);
			userInfo.setDriver(driver);
			userInfo.setNodeIp(node);
			userInfo.setUserName(userName);
			if (userName.contains("Host") == true) {
				userInfo.setHost(true);
			}
			meetingDetails.addUser(userInfo);
			TestRunProperties.runningMeeting.put(meetingName, meetingDetails);
		}
	}
}



























