package com.src.testCases;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.src.pages.CreateMeetingPage;
import com.src.util.ExcelData;
import com.src.util.Meeting;
import com.src.util.UtilityClass;
import com.src.util.UtilityClass2;
import com.src.util.userInfo;

public class CreateMeeting extends UtilityClass2 {
	UtilityClass action = new UtilityClass();
	public static boolean temp;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static ArrayList<String> param = null;
	public static ArrayList<String> paramPassword = null;
	public static String sessionKey = null;
	ArrayList<String> tabs = null;
	private static String Lock = "lock";
	public static JavascriptExecutor js;

	public CreateMeeting(ArrayList<String> readAgentData, ArrayList<String> readAgentDataPassword) {
		param = readAgentData;
		paramPassword = readAgentDataPassword;
	}

	@Parameters({ "node", "meetingName", "FakeCame", "userName" })
	@BeforeMethod
	public void beforeMethod(String node, String meetingName, String FakeCame, String userName) throws Exception {
		setup(node, meetingName, FakeCame, userName);
		Meeting meetingDetails = new Meeting();
//		WebDriver driver = getDriver();
		if (userName.contains("Host") == true) {

			// TBC
			System.out.println("Value is true");
			meetingDetails.setMeetingName(meetingName);
			// TBC
			System.out.println("Meeting name is set" + meetingName);
			meetingDetails.setNodeIp(node);
			// TBC
			System.out.println("Meeting SETNodeIP  is set" + node);

			TestRunProperties.runningMeeting.put(meetingName, meetingDetails);
			// TBC
			System.out.println("Meeting detailsand name is " + meetingName + " Details " + meetingDetails);

		} else {

			if (ExecuteTestRun.callType.equalsIgnoreCase("1")
					&& TestRunProperties.users == TestRunProperties.noOfThreads) {
				UtilityClass.admitUser(UtilityClass.driver1);
			}

			if (ExecuteTestRun.callType.equalsIgnoreCase("2")) {
				userAudio(meetingName, userName, node, meetingDetails);
			} else if (ExecuteTestRun.callType.equalsIgnoreCase("3")) {
				userVideo(meetingName, userName, node, meetingDetails);
			} else if (ExecuteTestRun.callType.equalsIgnoreCase("4")) {
				userScreenShareAudio(meetingName, userName, node, meetingDetails);
			} else if (ExecuteTestRun.callType.equalsIgnoreCase("5")) {
				userScreenShareVideo(meetingName, userName, node, meetingDetails);
			} else if (ExecuteTestRun.callType.equalsIgnoreCase("6")) {
				userSharescreen(meetingName, userName, node, meetingDetails);
				// driver.findElement(CreateMeetingPage.screenshare_div);

			}
//			} else if (ExecuteTestRun.callType.equalsIgnoreCase("7")) {
//				Thread.sleep(2000);
//				// UtilityClass.audioVideoAccept(driver, meetingName, userName, node);
//			} else if (ExecuteTestRun.callType.equalsIgnoreCase("8")) {
//				Thread.sleep(2000);
//				UtilityClass.audioVideoAccept(driver, meetingName, userName, node);
//			} else if (ExecuteTestRun.callType.equalsIgnoreCase("9")) {
//				Thread.sleep(2000);
//				UtilityClass.audioVideoAccept(driver, meetingName, userName, node);
//			}
//			// TestRunProperties.sessionUrl.add(new
//			// String[]{UtilityClass.CopiedURL,"Pass"});
//			Thread.sleep(3000);
//			action.takeScreenShot(driver, meetingName, userName);
		}

	}

	@Parameters({ "node", "meetingName", "FakeCame", "userName" })
	@Test
	public void joinMeeting(String node, String meetingName, String FakeCame, String userName) throws Exception {

		/*
		 * Assert.assertTrue(false, "Inside JoinMeeting " + node + " " + meetingName +
		 * " " + userName);
		 */
		try {
			if (userName.contains("Host") == false) {
				System.out.println("Host is FALSE");
				System.out.println("Meeting name" + TestRunProperties.runningMeetings.containsKey(meetingName));
				Assert.assertTrue(TestRunProperties.runningMeetings.containsKey(meetingName),
						"Meeting not found. Unable to join " + userName);
			} else if (userName.contains("Host") == true) {
				System.out.println("Host is TRUE");
				System.out.println("Meeting name TRUE" + TestRunProperties.driverMap.get(node + userName));

				if (ExecuteTestRun.callType.equalsIgnoreCase("1")) {
					addParticipant(meetingName, userName, node);
					TestRunProperties.sessionUrl
							.add(new String[] { meetingName, userName, node, UtilityClass.CopiedURL, "Pass" });
				} else if (ExecuteTestRun.callType.equalsIgnoreCase("2")) {
					audioCall(meetingName, userName, node);
					TestRunProperties.sessionUrl.add(new String[] { UtilityClass.CopiedURL, "Pass" });
				} else if (ExecuteTestRun.callType.equalsIgnoreCase("3")) {
					videoCall(meetingName, userName, node);
				} else if (ExecuteTestRun.callType.equalsIgnoreCase("4")) {
					screenShareAudioVideoCall(CreateMeetingPage.audio_icon, meetingName, userName, node);
				} else if (ExecuteTestRun.callType.equalsIgnoreCase("5")) {
					screenShareAudioVideoCall(CreateMeetingPage.img_Video, meetingName, userName, node);
				} else if (ExecuteTestRun.callType.equalsIgnoreCase("6")) {
					shareScreen(node, meetingName, FakeCame, userName);
				}
//				
//				} else if (ExecuteTestRun.callType.equalsIgnoreCase("7")) {
//					action.canvasActivity(driver, action.addParticipant(driver, userName), CreateMeetingPage.img_Video,
//							meetingName, userName, node);
//
//				} else if (ExecuteTestRun.callType.equalsIgnoreCase("8")) {
//					action.canvasActivity(driver, action.addParticipant(driver, userName), CreateMeetingPage.img_Video,
//							meetingName, userName, node);
//
//				} else if (ExecuteTestRun.callType.equalsIgnoreCase("9")) {
//
//					action.canvasActivity(driver, action.addParticipant(driver, userName), CreateMeetingPage.audio_icon,
//							meetingName, userName, node);
//
//				}
//				else if (ExecuteTestRun.callType.equalsIgnoreCase("8")) {
//					action.shareLocation(driver, action.addParticipant(driver, userName), meetingName, userName, node);
//				}

				CreateMeeting.sleep(3000);
//				action.takeScreenShot(driver, meetingName, userName);
				if (userName.contains("Host") == true) {
					// ExecuteTestRun.totalNumberofRunningMeetings += 1;
					TestRunProperties.runningMeetings.put(meetingName, node);
				}
				int userCountPerMeeting = 0;
				if (TestRunProperties.userMeetingMap.containsKey(meetingName)) {
					userCountPerMeeting = TestRunProperties.userMeetingMap.get(meetingName).intValue();
				}
				userCountPerMeeting += 1;
				TestRunProperties.userMeetingMap.put(meetingName, userCountPerMeeting);

				synchronized (TestRunProperties.userNodeMap) {
					int userCount = 0;
					if (TestRunProperties.userNodeMap.containsKey(node.toString())) {
						userCount = TestRunProperties.userNodeMap.get(node.toString()).intValue();
						System.out.println(
								Thread.currentThread().getId() + "........" + node.toString() + " " + userCount);

					}
					userCount += 1;
					System.out.println(node.toString() + " " + userCount);
					TestRunProperties.userNodeMap.put(node.toString(), userCount);
				}

				if (TestRunProperties.users == 1 && !(ExecuteTestRun.callType.equalsIgnoreCase("1"))) {
					TestRunProperties.noOfThreads = 0;
					HashMap<String, RemoteWebDriver> tempList = TestRunProperties.driverMap;
//				System.out.println(tempList);
//				System.out.println("=========");
					TestRunProperties.elements = new HashMap<String, RemoteWebDriver>();// the second map
					if (TestRunProperties.elements != null) {
						TestRunProperties.elements.clear();
					}
					for (Entry<String, RemoteWebDriver> entry : tempList.entrySet()) {
						if (entry.getKey().contains("Host") == true)
							TestRunProperties.elements.put(entry.getKey(), entry.getValue());
					}
//				System.out.println(elements);
					if (TestRunProperties.entryofFirstThread) {
						CanvasActivityThread at = new CanvasActivityThread();
						Thread t = new Thread(at);
						t.start();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterSuite
	public void ExcelSheet() {
		// Write meeting details in excel sheet
		try {
			ExcelData.writeExcelData(TestRunProperties.sessionUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void sleep(long milliSec) {
		try {
			Thread.sleep(milliSec);
		} catch (Exception e) {
		}
	}
}