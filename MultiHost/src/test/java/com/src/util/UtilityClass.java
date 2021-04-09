package com.src.util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.src.pages.CreateMeetingPage;
import com.src.testCases.CreateMeeting;
import com.src.testCases.ExecuteTestRun;
import com.src.testCases.TestRunProperties;

public class UtilityClass {
	String mainWindow, newWindow;
	public static String CopiedURL = " ";
	public static WebDriver driver1;
	public static ArrayList<String> tabs = null;
	public static JavascriptExecutor js;
	public static int agentIndex = 0;
	public static int agentIndexPassword = 0;
	public static String sessionKey = null;
	public static String joiningURL = TestRunProperties.strBaseUrl+"join?sessionKey=";
	private static String Lock = "lock";

	public void userlogIn(WebDriver driver) {
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
	}

	public void createSession(WebDriver driver, String meetingName) {
		try {
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
				// driver.findElement(CreateMeetingPage.startPasscodeMeeting).click();
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

			String[] split = null;
			List<LogEntry> entries = driver.manage().logs().get("performance").getAll();
			for (LogEntry entry : entries) {
				if (entry.toString().contains("sessionKey")) {
					if (!entry.toString().contains("sessionKey%"))
//					System.out.println(entry.toString());
						split = entry.toString().split("sessionKey");
				}
			}
//			System.out.println("====================");
//			for (int i = 0; i < split.length; i++) {
//				System.out.println(split[i]);
//			}
//			System.out.println(split[1].substring(5,13));
			sessionKey = split[1].substring(5, 13);

		} catch (Exception e) {
			System.out.println("Exception in createSession method.." + e);
			e.printStackTrace();
		}
	}

	public void audioCall(WebDriver driver, ArrayList<String> tabs, String meetingName, String userName, String node) {
		try {
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.audio_icon));
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Confirm));
			Thread.sleep(3000);
			System.out.println("Audio call established Successfully");
			driver.switchTo().window(tabs.get(0));
			Thread.sleep(2000);
			TestRunProperties.sessionUrl
					.add(new String[] { meetingName, userName, node, UtilityClass.CopiedURL, "Pass" });
		} catch (Exception e) {
			TestRunProperties.sessionUrl.add(new String[] { meetingName, userName, node, "Null", "Fail" });
			System.out.println("Exception in audioCall call method.." + e);
			TestRunProperties.sessionUrl.add(new String[] { "Null", "Fail" });
		}

	}

	public ArrayList<String> addParticipant(WebDriver driver, String userName) {

		try {
//			Thread.sleep(3000);
//
//			assertTrue(driver.findElement(CreateMeetingPage.img_AddInvitee).isDisplayed(),
//					"Participant button is missing.");
//			TestRunProperties.explicitWait(driver, CreateMeetingPage.img_AddInvitee);
//			JavascriptExecutor js1 = (JavascriptExecutor) driver;
//			js1.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_AddInvitee));
//			Thread.sleep(4000);
//			assertTrue(driver.findElement(CreateMeetingPage.btn_CopySessionLink).isDisplayed(),
//					"Copy Session link button is missing.");
//			TestRunProperties.explicitWait(driver, CreateMeetingPage.btn_CopySessionLink);
//			driver.findElement(CreateMeetingPage.btn_CopySessionLink).click();
//			Thread.sleep(2000);
//			assertTrue(driver.findElement(CreateMeetingPage.btn_ManualEntry).isDisplayed(),
//					"Manual Entry modal is missing.");
//			TestRunProperties.explicitWait(driver, CreateMeetingPage.btn_ManualEntry);
//			driver.findElement(CreateMeetingPage.btn_ManualEntry).click();
//			Thread.sleep(1000);
//			assertTrue(driver.findElement(CreateMeetingPage.txt_CopyURL).isDisplayed(),
//					"Enter email input box is missing to paste copied URL.");
//			TestRunProperties.explicitWait(driver, CreateMeetingPage.txt_CopyURL);
//
//			if (TestRunProperties.machine.contentEquals("MAC")) {
//
//				driver.findElement(CreateMeetingPage.txt_CopyURL).sendKeys(Keys.SHIFT, Keys.INSERT);
//			} else {
//
//				driver.findElement(CreateMeetingPage.txt_CopyURL).sendKeys(Keys.chord(Keys.CONTROL, "v"));
//			}
//			CopiedURL = driver.findElement(CreateMeetingPage.txt_CopyURL).getAttribute("value");
//			System.out.println("Session Joining URL: " + CopiedURL);
//			Thread.sleep(3000);
//			assertTrue(driver.findElement(CreateMeetingPage.img_Close_manual).isDisplayed(),
//					"Close icon is missing for manual entry modal.");
//			TestRunProperties.explicitWait(driver, CreateMeetingPage.img_Close_manual);
//			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_Close_manual));
//			Thread.sleep(2000);
//			assertTrue(driver.findElement(CreateMeetingPage.img_Close_participant).isDisplayed(),
//					"Close icon is missing for Participant modal.");
//			TestRunProperties.explicitWait(driver, CreateMeetingPage.img_Close_participant);
//			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_Close_participant));
//			System.out.println("Successfully Copied the session Joining URL");

			// Join as user in new tab
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
			// Admit User
			driver1 = driver;
			UtilityClass.admitUser(driver);
			// TestRunProperties.sessionUrl.add(new
			// String[]{MeetingName,userName,Node,UtilityClass.CopiedURL,"Pass"});

		} catch (Exception e) {
			TestRunProperties.sessionUrl.add(new String[] { "Null", "Fail" });
			System.out.print("Exception in addparticipant method..");
			e.printStackTrace();
		}
		return tabs;
	}

	public static void admitUser(WebDriver driver) {
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
	}

	public void addParticipant(WebDriver driver, String meetingName, String userName, String node) {
		try {
//			Thread.sleep(3000);
//			assertTrue(driver.findElement(CreateMeetingPage.img_AddInvitee).isDisplayed(),
//					"Participant button is missing.");
//			TestRunProperties.explicitWait(driver, CreateMeetingPage.img_AddInvitee);
//			Thread.sleep(2000);
//			driver.findElement(CreateMeetingPage.img_AddInvitee).click();
//			Thread.sleep(4000);
//			assertTrue(driver.findElement(CreateMeetingPage.btn_CopySessionLink).isDisplayed(),
//					"Copy Session link button is missing.");
//			TestRunProperties.explicitWait(driver, CreateMeetingPage.btn_CopySessionLink);
//			driver.findElement(CreateMeetingPage.btn_CopySessionLink).click();
//			Thread.sleep(2000);
//			assertTrue(driver.findElement(CreateMeetingPage.btn_ManualEntry).isDisplayed(),
//					"Manual Entry modal is missing.");
//			TestRunProperties.explicitWait(driver, CreateMeetingPage.btn_ManualEntry);
//			driver.findElement(CreateMeetingPage.btn_ManualEntry).click();
//			Thread.sleep(1000);
//			assertTrue(driver.findElement(CreateMeetingPage.txt_CopyURL).isDisplayed(),
//					"Enter email input box is missing to paste copied URL.");
//			TestRunProperties.explicitWait(driver, CreateMeetingPage.txt_CopyURL);
//
//			if (TestRunProperties.machine.contentEquals("MAC")) {
//
//				driver.findElement(CreateMeetingPage.txt_CopyURL).sendKeys(Keys.SHIFT, Keys.INSERT);
//			} else {
//
//				driver.findElement(CreateMeetingPage.txt_CopyURL).sendKeys(Keys.chord(Keys.CONTROL, "v"));
//			}
//			CopiedURL = driver.findElement(CreateMeetingPage.txt_CopyURL).getAttribute("value");
//			System.out.println("Session Joining URL: " + CopiedURL);
//			Thread.sleep(3000);
//			assertTrue(driver.findElement(CreateMeetingPage.img_Close_manual).isDisplayed(),
//					"Close icon is missing for manual entry modal.");
//			TestRunProperties.explicitWait(driver, CreateMeetingPage.img_Close_manual);
//			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_Close_manual));
//			Thread.sleep(2000);
//			assertTrue(driver.findElement(CreateMeetingPage.img_Close_participant).isDisplayed(),
//					"Close icon is missing for Participant modal.");
//			TestRunProperties.explicitWait(driver, CreateMeetingPage.img_Close_participant);
//			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_Close_participant));
//			System.out.println("Successfully Copied the session Joining URL");
//			// TestRunProperties.sessionUrl.add(new
//			// String[]{UtilityClass.CopiedURL,"Pass"});
//			Thread.sleep(2000);
			driver1 = driver;
			// UtilityClass.admitUser(driver);
		} catch (Exception e) {
			System.out.println("Exception in addParticipant method..");
			TestRunProperties.sessionUrl.add(new String[] { meetingName, userName, node, "Null", "Fail" });
		}
	}

	public void videoCall(WebDriver driver, ArrayList<String> tabs, String meetingName, String userName, String node) {
		try {
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_Video));
			// Accept video Request at User side
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Confirm));
			Thread.sleep(3000);
			System.out.println("Video call established Successfully");
			driver.switchTo().window(tabs.get(0));
			Thread.sleep(2000);
			TestRunProperties.sessionUrl
					.add(new String[] { meetingName, userName, node, UtilityClass.CopiedURL, "Pass" });
		} catch (Exception e) {
			TestRunProperties.sessionUrl.add(new String[] { meetingName, userName, node, "Null", "Fail" });
			System.out.println("Exception in videoCall method.." + e);
		}
	}

	public void screenShareAudioVideoCall(WebDriver driver, ArrayList<String> tabs, By callType, String meetingName,
			String userName, String node) {
		try {
			Thread.sleep(10000);
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.screenShare_icon));
			Thread.sleep(3000);
			driver.findElement(CreateMeetingPage.screenshare_div);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.screenshare_div).isDisplayed(),
					"Screenshare is not visible..");
			js.executeScript("arguments[0].click();", driver.findElement(callType));
			// Accept Audio\video Request at User side
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Confirm));
			Thread.sleep(4000);
			driver.findElement(CreateMeetingPage.screenshare_div);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.screenshare_div).isDisplayed(),
					"Screenshare is not visible..");
			System.out.println("Audio call with Screenshare established Successfully");
			driver.switchTo().window(tabs.get(0));
			Thread.sleep(2000);
			TestRunProperties.sessionUrl
					.add(new String[] { meetingName, userName, node, UtilityClass.CopiedURL, "Pass" });
		} catch (Exception e) {
			TestRunProperties.sessionUrl.add(new String[] { meetingName, userName, node, "Null", "Fail" });
			System.out.println("Exception in screenShareAudioCall method.." + e);
		}
	}

	public void screenShare(WebDriver driver, ArrayList<String> tabs, String meetingName, String userName,
			String node) {
		try {
			Thread.sleep(000);
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.screenShare_icon));
			Thread.sleep(3000);
			driver.findElement(CreateMeetingPage.screenshare_div);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.screenshare_div).isDisplayed(),
					"Screenshare is not visible..");
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(3000);
			System.out.println("Screen Shared Successfully...");
			driver.switchTo().window(tabs.get(0));
			Thread.sleep(2000);
			TestRunProperties.sessionUrl
					.add(new String[] { meetingName, userName, node, UtilityClass.CopiedURL, "Pass" });
		} catch (Exception e) {
			TestRunProperties.sessionUrl.add(new String[] { meetingName, userName, node, "Null", "Fail" });
			System.out.println("Exception in screenShare method.." + e);
		}
	}

	public static void audioVideoAccept(WebDriver driver, String meetingName, String userName, String node) {
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
			System.out.println("Exception in audioVideoAccept method.." + e);
		}
	}

	public void canvasActivity(WebDriver driver, ArrayList<String> tabs, By callType, String meetingName,
			String userName, String node) {
		try {
			if (ExecuteTestRun.callType.equalsIgnoreCase("8") || ExecuteTestRun.callType.equalsIgnoreCase("9")) {
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.screenShare_icon));
				Thread.sleep(3000);
				driver.findElement(CreateMeetingPage.screenshare_div);
				Assert.assertTrue(driver.findElement(CreateMeetingPage.screenshare_div).isDisplayed(),
						"Screenshare is not visible..");
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", driver.findElement(callType));
				// Accept video Request at User side
				driver.switchTo().window(tabs.get(1));
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Confirm));
				Thread.sleep(3000);
				System.out.println("Video call established Successfully");
				driver.switchTo().window(tabs.get(0));
				System.out.println("Wating to load page after adding users");
			}
			Thread.sleep(3000);
			AddCanvas.addCanvas(driver);
			AddCanvas.AddAnnotations_Circle(driver);
//			AddCanvas.AddAnnotations_Rectangle(driver);
			AddCanvas.AddAnnotations_Line(driver);
			// AddCanvas.AddAnnotations_Draw(driver);
			System.out.println("Performing Canvas activity");
			AddCanvas.addCanvas(driver);
			// AddCanvas.AddAnnotations_Line(driver);
			// AddCanvas.AddAnnotations_Draw(driver);
			AddCanvas.textDecoration_canvas(driver);

			// String option=br.readLine();
			/*
			 * String filepath = System.getProperty("user.dir") + "\\Images\\500.png";
			 * System.out.println("filepath - " + filepath); // addFile.click();
			 * Thread.sleep(2000); TestRunProperties.explicitWait(driver,
			 * CreateMeetingPage.img_UploadImage);
			 * driver.findElement(CreateMeetingPage.img_UploadImage).click();
			 * System.out.println("Upload image on canvas: y/n\n"); Robot robot = null;
			 * 
			 * StringSelection stringSelection = new StringSelection(filepath);
			 * Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,
			 * null);
			 * 
			 * robot = new Robot();
			 * 
			 * // Thread.sleep(4000); robot.delay(450); robot.keyPress(KeyEvent.VK_ENTER);
			 * robot.keyRelease(KeyEvent.VK_ENTER); robot.delay(250);
			 * robot.keyPress(KeyEvent.VK_CONTROL); robot.keyPress(KeyEvent.VK_V);
			 * robot.keyRelease(KeyEvent.VK_V); robot.keyRelease(KeyEvent.VK_CONTROL);
			 * robot.delay(250); robot.keyPress(KeyEvent.VK_ENTER); robot.delay(250);
			 * robot.keyRelease(KeyEvent.VK_ENTER);
			 * System.out.println("Image uploaded on canvas successfully..\n");
			 * 
			 * System.out.println("Wating to load page after uploading image");
			 * Thread.sleep(30000); String filepathPdf = System.getProperty("user.dir") +
			 * "/Images/Loan.pdf"; System.out.println("filepathPdf - " + filepathPdf);
			 * StringSelection stringSelectionPdf = new StringSelection(filepathPdf);
			 * Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
			 * stringSelectionPdf, null);
			 * driver.findElement(CreateMeetingPage.img_UploadPdf).click(); //
			 * Thread.sleep(4000); robot.delay(550); robot.keyPress(KeyEvent.VK_ENTER);
			 * robot.keyRelease(KeyEvent.VK_ENTER); robot.delay(250);
			 * robot.keyPress(KeyEvent.VK_CONTROL); robot.keyPress(KeyEvent.VK_V);
			 * robot.keyRelease(KeyEvent.VK_V); robot.keyRelease(KeyEvent.VK_CONTROL);
			 * robot.delay(250); robot.keyPress(KeyEvent.VK_ENTER); robot.delay(250);
			 * robot.keyRelease(KeyEvent.VK_ENTER);
			 * System.out.println("PDF uploaded on canvas successfully..\n");
			 */
			TestRunProperties.sessionUrl
					.add(new String[] { meetingName, userName, node, UtilityClass.CopiedURL, "Pass" });

		} catch (Exception e) {
			TestRunProperties.sessionUrl.add(new String[] { meetingName, userName, node, "Null", "Fail" });
			System.out.println("Exception in canvasActivity() method... " + e);
			e.printStackTrace();
		}
	}

	public void shareLocation(WebDriver driver, ArrayList<String> tabs, String meetingName, String userName,
			String node) {
		try {
			CreateMeeting.sleep(10000);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.img_Location).isDisplayed(),
					"share location icon is missing");

			js.executeScript("arguments[0].click();", driver.findElement(By.id("LOCATION")));
			CreateMeeting.sleep(2000);
			driver.switchTo().window(tabs.get(1));
			CreateMeeting.sleep(3000);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.btn_Confirm).isDisplayed(),
					"Accept button for location request is not present");
			js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Confirm));
			CreateMeeting.sleep(5000);
			driver.switchTo().window(tabs.get(0));
			CreateMeeting.temp = true;
			CreateMeeting.sleep(7000);
			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.elementToBeClickable(CreateMeetingPage.btn_ShareLiveLoc)).click();
			TestRunProperties.explicitWait(driver, CreateMeetingPage.btn_ShareLiveLoc);
			Assert.assertTrue(driver.findElement(CreateMeetingPage.btn_ShareLiveLoc).isDisplayed(),
					"Screenshare live location button is missing");
			Thread.sleep(2000);
			TestRunProperties.sessionUrl
					.add(new String[] { meetingName, userName, node, UtilityClass.CopiedURL, "Pass" });
		} catch (Exception e) {
			TestRunProperties.sessionUrl.add(new String[] { meetingName, userName, node, "Null", "Fail" });
			System.out.println("Exception in shareLocation() method... " + e);
			e.printStackTrace();
		}
	}

	public void takeScreenShot(WebDriver driver, String meetingName, String userName) {
		try {
			System.out.print("\n Taking Screenshot...");
			Thread.sleep(2000);
			TakesScreenshot scrshot = ((TakesScreenshot) driver);
			File srcFile = scrshot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(
					System.getProperty("user.dir") + "//Screenshots//" + meetingName + "//" + userName + ".png");
			Thread.sleep(2000);
			FileUtils.copyFile(srcFile, DestFile);
			System.out.print("\nScreenshot taken Successfully...");
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("Exception in Screenshot method... " + e);
		}
	}

	public static void takeScreenShottemp(WebDriver driver) throws IOException {
		Date date = new Date();
		String Screenshot = "Screenshot_" + date.toString().replace(" ", "_").replace(":", "_") + ".png";
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "\\ScreenShotTemp\\" + Screenshot);
		FileUtils.copyFile(srcFile, destFile);
		System.out.println("Screenshot taken!!");
	}

	public static boolean isElementPresent(WebDriver driver, By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
