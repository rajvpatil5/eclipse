package com.src.testCases;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi.Attribute;
import com.diogonunes.jcdp.color.api.Ansi.BColor;
import com.diogonunes.jcdp.color.api.Ansi.FColor;
import com.src.pages.CreateMeetingPage;
import com.src.util.AddCanvas;
import com.src.util.Meeting;
import com.src.util.UtilityClass;
import com.src.util.userInfo;

public class ExecuteTestRun {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int totalNumberofRunningMeetings = 0;
	static int numberOfUserPerMeeting;
	HashMap<String, Integer> tempUserNodeMap = new HashMap<String, Integer>();
	static ColoredPrinter cp;
	public static String callType = "";
	public static int numberofuser;

	public void writeNode() throws UnknownHostException {
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress();
			System.out.println("**************" + ip);
			String current = new java.io.File(".").getCanonicalPath();

			String url = current + "/Nodelist.txt";

			File file = new File(url);
			FileWriter fileWriter;
			fileWriter = new FileWriter(file);
			fileWriter.write(String.format(ip));

			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		cp = new ColoredPrinter.Builder(1, false).foreground(FColor.RED).background(BColor.BLUE) // setting
																									// format
				.build();
		ExecuteTestRun et = new ExecuteTestRun();
		if (TestRunProperties.ExecutionMode.equalsIgnoreCase("NonGrid")) {
			et.writeNode();
		}
		// printing according to that format
		// cp.setAttribute(Attribute.REVERSE);
		cp.clear();
		cp.print("Autoscale set Load test", Attribute.BOLD, FColor.YELLOW, BColor.GREEN);

		cp.clear();
		cp.print(cp.getDateFormatted(), Attribute.NONE, FColor.CYAN, BColor.BLACK);
		cp.debugPrintln(" Started");
		cp.clear();
		/*
		 * cp.print("\tMADE ", Attribute.BOLD, FColor.YELLOW, BColor.GREEN);
		 * cp.print("IN PORTUGAL\t\n", Attribute.BOLD, FColor.YELLOW, BColor.RED);
		 * cp.println("I hope you find it useful ;)");
		 */

		TestRunProperties.userNodeMap = new HashMap<>();
		String url = TestRunProperties.strBaseUrl;
		String cmd;

		boolean executeloop = true;
		ExecuteTestRun ET = new ExecuteTestRun();
		while (true) {
			setCommandPrinter();
			// System.out.print("\nPlease Enter Command to Run...\n Enter -h for help.\n");
			try {
				setInputPrinter();
				// cmd = br.readLine();

				cmd = TestRunProperties.meetingMenu;
				switch (cmd) {
				case "-h":
					setResponsePrinter();
					System.out.print("\nHelp");
					System.out.print("\n------------------------------------------------------");
					System.out.print("\n1 Start Meeting");
					System.out.print("\n2 Add user to Meeting");
					System.out.print("\n3 Update Node List");
					System.out.print("\n4 Show Current Status");
					System.out.print("\n------------------------------------------------------");
					System.out.print("\n5 Update Maximum Instance Limit");
					System.out.print("\n6 Update Host Url");
					System.out.print("\n7 Show Node List");
					System.out.print("\n8 Show Currently Running Meeting List");
					System.out.print("\n9 Show Currently Running Meeting With user count");
					System.out.print("\n------------------------------------------------------");
					break;
				case "1":
					setResponsePrinter();
					System.out.print("\nStart Meeting...\n");
					startMeeting();
					setCommandPrinter();
					System.out.print("\nPlease enter path to save Test reports\n");
					setInputPrinter();
					// cmd = br.readLine();
					try {
						String current = new java.io.File(".").getCanonicalPath();

						// cmd.trim().isEmpty() == true) {
						cmd = current;
						System.out.println("Current" + cmd);

						setResponsePrinter();
						System.out.print("\nSaving reports....");
						File source = new File(current + "/test-output");
						File target = new File(cmd + "/TestRun(" + TestRunProperties.meetingPrefix + ")");
						ET.copyDirectory(source, target);
						System.out.print("\nReports saved to path: " + cmd + "/TestRun("
								+ TestRunProperties.meetingPrefix + ")");

					} catch (Exception e) {
						// TODO: handle exception
						setErrorBoxPrinter();
						e.printStackTrace();
					}
					// executeloop=false;
					break;
				case "2":
					setResponsePrinter();
					System.out.print("\nAdd user to Meeting");
					AddUserToExistingMeetings();
					break;
				case "3":
					setResponsePrinter();
					System.out.print("\nUpdating Node List...");
					TestRunProperties.nodeList = TestRunProperties.GetAvailableNodeList();
					System.out.print("\nUpdated Node List...");
					break;
				case "4":
					setBoxPrinter();
					System.out.print("\nCurrent Status");
					showCurrentStatus();
					break;
				case "5":
					setCommandPrinter();
					System.out.print("\nPlease Enter Maximum Instance Limit. Press enter to use default("
							+ TestRunProperties.maxInstance + ")\n");
					setInputPrinter();
					cmd = br.readLine();
					if (cmd.trim().isEmpty() == false) {
						TestRunProperties.maxInstance = Integer.parseInt(cmd);
					}
					break;
				case "6":
					setCommandPrinter();
					System.out.print("\nPlease Enter Host Url. Press enter to use default("
							+ TestRunProperties.strBaseUrl + ")\n");
					setInputPrinter();
					cmd = br.readLine();
					if (cmd.trim().isEmpty() == false) {
						TestRunProperties.strBaseUrl = cmd;
					}
					break;
				case "7":
					setBoxPrinter();
					System.out.print("\n\nNodes:");
					for (String entry : TestRunProperties.nodeList) {
						System.out.print("\n" + entry);
					}
					break;
				case "8":
					setResponsePrinter();
					System.out.print("\n\n Running Meetings:");
					showRunningMeetings();
					break;
				case "9":
					setBoxPrinter();
					ShowMeetingsWithUsers();
					break;

//				case "5":
//					setResponsePrinter();
//					System.out.print("\nStarting Recording for all running Meetings...");
//					startRecording(true);
//					break;
//				case "6":
//					setResponsePrinter();
//					System.out.print("\nStoping Recording for all running Meetings...");
//					startRecording(false);
//					break;
//				case "7":
//					setCommandPrinter();
//					System.out.print("\nPlease enter meeting name to Start Recording\n");
//					setInputPrinter();
//					cmd = br.readLine();
//					setResponsePrinter();
//					System.out.print("\nStarting Recording for " + cmd + "....");
//					startRecording(true, cmd);
//					break;
//				case "8":
//					setCommandPrinter();
//					System.out.print("\nPlease enter meeting name to Stop Recording\n");
//					setInputPrinter();
//					cmd = br.readLine();
//					setResponsePrinter();
//					System.out.print("\nStoping Recording for " + cmd + "...");
//					startRecording(false, cmd);
//					break;
//				case "9":
//					setCommandPrinter();
//					System.out.print("\nPlease Enter Maximum Instance Limit. Press enter to use default("
//							+ TestRunProperties.maxInstance + ")\n");
//					setInputPrinter();
//					cmd = br.readLine();
//					if (cmd.trim().isEmpty() == false) {
//						TestRunProperties.maxInstance = Integer.parseInt(cmd);
//					}
//					break;
//				case "10":
//					setCommandPrinter();
//					System.out.print("\nPlease Enter Host Url. Press enter to use default("
//							+ TestRunProperties.strBaseUrl + ")\n");
//					setInputPrinter();
//					cmd = br.readLine();
//					if (cmd.trim().isEmpty() == false) {
//						TestRunProperties.strBaseUrl = cmd;
//					}
//					break;
//				case "11":
//					setBoxPrinter();
//					System.out.print("\n\nNodes:");
//					for (String entry : TestRunProperties.nodeList) {
//						System.out.print("\n" + entry);
//					}
//					break;
//				case "12":
//					setResponsePrinter();
//					System.out.print("\n\n Running Meetings:");
//					showRunningMeetings();
//					break;
//				case "13":
//					setBoxPrinter();
//					ShowMeetingsWithUsers();
//					break;
//				case "14":
//					setCommandPrinter();
//					SSHConnect.connectToVM();
//					break;
//				case "15":
//					setResponsePrinter();
//					checkMediaFlowAndFix();
//					break;
//				case "16":
//					setResponsePrinter();
//					checkMediaFlowAndFixSpecific();
//					break;
//				case "01":
//					setResponsePrinter();
//					System.out.print("\nLeaving meeting and exit...");
//					leaveMeeting1();
//					// leaveSpecificMeeting();
//					break;
//				case "0":
//					setResponsePrinter();
//					System.out.print("\nLeaving meeting and exit...");
//					leaveMeeting();

				default:
					break;

				}
			}

			catch (IOException e) {
				// TODO Auto-generated catch block
				setErrorBoxPrinter();
				e.printStackTrace();
			}
			executeloop = false;
			if (executeloop == false) {
				break;
			}

		}

		cp.clear(); // don't forget to clear the terminal's format before
		// exiting

	}

	private static void checkMediaFlowAndFixSpecific() {
		Meeting meeting;
		StringBuilder result = new StringBuilder();
		List<userInfo> failMediaUser = new ArrayList<>();
		boolean isMediaOk = true;
		String cmd = "";
		try {
			setCommandPrinter();
			System.out.println("\n Enter Meeting name for media flow checking");
			setInputPrinter();
			cmd = br.readLine();
			if (TestRunProperties.runningMeeting.containsKey(cmd)) {
				meeting = TestRunProperties.runningMeeting.get(cmd);
				StringBuilder failresult = new StringBuilder();
				result.append("\nDate: " + ExecuteTestRun.cp.getDateFormatted());
				System.out.println("\n Checking Media flow...");
				try {
					failresult = new StringBuilder();
					userInfo hostUser = meeting.getHostUser();
					if (hostUser != null) {
						result.append("\n------------------------------------------------------");
						failresult.append("\n------------------------------------------------------");
						String meetingName = meeting.getMeetingName();
						System.out.println("\n Please wait. Checking Media flow for " + meetingName + "...");
						result.append("\nMedia status for " + meetingName);
						failresult.append("\nMedia status for " + meetingName);
						result.append("\nParticipant List with media status ");
						failresult.append("\nParticipant List with media status ");
						WebDriver driver = hostUser.getDriver();
						List<WebElement> videoList = driver.findElements(By.tagName("video"));
						for (WebElement webElement : videoList) {
							userInfo userDetail = null;
							String participaintName = webElement.getAttribute("id").replace("video-", "");
							JavascriptExecutor js = (JavascriptExecutor) driver;
							Object isPaused = js.executeScript("return document.getElementById(\""
									+ webElement.getAttribute("id") + "\").paused;");
							Object currentTime = js.executeScript("return document.getElementById(\""
									+ webElement.getAttribute("id") + "\").currentTime;");
							String strCurrentTime = "";
							if (currentTime != null) {
								try {
									strCurrentTime = currentTime.toString();
								} catch (Exception e) {
									// TODO: handle exception
									strCurrentTime = "Unable to calculate";
								}
							}
							if (isPaused == null) {
								result.append("\n" + participaintName + "  Media Element not found");
								failresult.append("\n" + participaintName + "  Media Element not found");
							} else {
								if ((boolean) isPaused == true) {
									result.append("\n" + participaintName + "  Media flow: False Current Time: "
											+ strCurrentTime);
									failresult.append("\n" + participaintName + "  Media flow: False Current Time: "
											+ strCurrentTime);
									isMediaOk = false;
									userDetail = new userInfo();
									userDetail = meeting.getUserByName(participaintName);
								} else {
									result.append("\n" + participaintName + "  Media flow: True Current Time: "
											+ strCurrentTime);
									failresult.append("\n" + participaintName + "  Media flow: True Current Time: "
											+ strCurrentTime);
								}
							}
							// userDetail =
							// entry.getValue().getUserByName(participaintName);
							if (userDetail != null && userDetail.getUserName().contains("Host") == false)
								failMediaUser.add(userDetail);
						}
						result.append("\n------------------------------------------------------");
						failresult.append("\n------------------------------------------------------");
						if (isMediaOk == false) {
							System.out.println(failresult.toString());
							isMediaOk = true;
						} else {
							System.out.println("Found ok...");
						}

					}

					ExecuteTestRun.setCommandPrinter();
					System.out.println("\nExport result to file? Y/N");
					ExecuteTestRun.setInputPrinter();
					cmd = br.readLine();

					if (cmd.equalsIgnoreCase("y") == true) {
						try {
							String current = new File(".").getCanonicalPath();
							setResponsePrinter();
							System.out.print("\nSaving result....");
							File target = new File(current + "/checkMediaFlowResult/");
							if (!target.exists()) {
								target.mkdir();
							}
							FileWriter fw = new FileWriter(current + "/checkMediaFlowResult/"
									+ TestRunProperties.meetingPrefix + "MediaOutput.txt", true);
							fw.write(result.toString());
							fw.close();
							System.out.print("\nResult saved to path: " + current + "/checkMediaFlowResult/"
									+ TestRunProperties.meetingPrefix + "MediaOutput.txt");

						} catch (Exception e) {

							ExecuteTestRun.setErrorBoxPrinter();
							e.printStackTrace();
						}
					}
					if (failMediaUser.size() > 0) {
						ExecuteTestRun.setCommandPrinter();
						System.out.println("\nFix User media flow issues by re-joining meeting? Y/N");
						ExecuteTestRun.setInputPrinter();
						cmd = br.readLine();
						if (cmd.equalsIgnoreCase("y") == true) {
							leaveAndRejoinMeeting(failMediaUser);
						}
					}
				} catch (Exception e) {

					ExecuteTestRun.setErrorBoxPrinter();
					e.printStackTrace();
				}

			} else {
				setResponsePrinter();
				System.out.println("\n No Such meeting found.");
			}
		} catch (Exception e) {

			ExecuteTestRun.setErrorBoxPrinter();
			e.printStackTrace();
		}

	}

	private static void checkMediaFlowAndFix() {
		HashMap<String, Meeting> tempList = TestRunProperties.runningMeeting;
		StringBuilder result = new StringBuilder();
		List<userInfo> failMediaUser = new ArrayList<>();
		boolean isMediaOk = true;
		StringBuilder failresult = new StringBuilder();
		result.append("\nDate: " + ExecuteTestRun.cp.getDateFormatted());
		System.out.println("\n Checking Media flow...");
		try {
			for (Entry<String, Meeting> entry : tempList.entrySet()) {
				failresult = new StringBuilder();
				userInfo hostUser = entry.getValue().getHostUser();
				if (hostUser != null) {
					result.append("\n------------------------------------------------------");
					failresult.append("\n------------------------------------------------------");
					String meetingName = entry.getKey().replace("Host", "").trim();
					System.out.println("\n Please wait. Checking Media flow for " + meetingName + "...");
					result.append("\nMedia status for " + meetingName);
					failresult.append("\nMedia status for " + meetingName);
					result.append("\nParticipant List with media status ");
					failresult.append("\nParticipant List with media status ");
					WebDriver driver = hostUser.getDriver();
					List<WebElement> videoList = driver.findElements(By.tagName("video"));
					for (WebElement webElement : videoList) {
						userInfo userDetail = null;
						String participaintName = webElement.getAttribute("id").replace("video-", "");
						JavascriptExecutor js = (JavascriptExecutor) driver;
						Object isPaused = js.executeScript(
								"return document.getElementById(\"" + webElement.getAttribute("id") + "\").paused;");
						Object currentTime = js.executeScript("return document.getElementById(\""
								+ webElement.getAttribute("id") + "\").currentTime;");
						String strCurrentTime = "";
						if (currentTime != null) {
							try {
								strCurrentTime = currentTime.toString();
							} catch (Exception e) {
								// TODO: handle exception
								strCurrentTime = "Unable to calculate";
							}
						}
						if (isPaused == null) {
							result.append("\n" + participaintName + "  Media Element not found");
							failresult.append("\n" + participaintName + "  Media Element not found");
						} else {
							if ((boolean) isPaused == true) {
								result.append("\n" + participaintName + "  Media flow: False Current Time: "
										+ strCurrentTime);
								failresult.append("\n" + participaintName + "  Media flow: False Current Time: "
										+ strCurrentTime);
								isMediaOk = false;
								userDetail = new userInfo();
								userDetail = entry.getValue().getUserByName(participaintName);
							} else {
								result.append(
										"\n" + participaintName + "  Media flow: True Current Time: " + strCurrentTime);
								failresult.append(
										"\n" + participaintName + "  Media flow: True Current Time: " + strCurrentTime);
							}
						}
						// userDetail =
						// entry.getValue().getUserByName(participaintName);
						if (userDetail != null && userDetail.isHost() == false)
							failMediaUser.add(userDetail);
					}
					result.append("\n------------------------------------------------------");
					failresult.append("\n------------------------------------------------------");
					if (isMediaOk == false) {
						System.out.println(failresult.toString());
						isMediaOk = true;
					} else {
						System.out.println("Found ok...");
					}

				}
			}
			ExecuteTestRun.setCommandPrinter();
			System.out.println("\nExport result to file? Y/N");
			ExecuteTestRun.setInputPrinter();
			String cmd = br.readLine();

			if (cmd.equalsIgnoreCase("y") == true) {
				try {
					String current = new File(".").getCanonicalPath();
					setResponsePrinter();
					System.out.print("\nSaving result....");
					File target = new File(current + "/checkMediaFlowResult/");
					if (!target.exists()) {
						target.mkdir();
					}
					FileWriter fw = new FileWriter(
							current + "/checkMediaFlowResult/" + TestRunProperties.meetingPrefix + "MediaOutput.txt",
							true);
					fw.write(result.toString());
					fw.close();
					System.out.print("\nResult saved to path: " + current + "/checkMediaFlowResult/"
							+ TestRunProperties.meetingPrefix + "MediaOutput.txt");

				} catch (Exception e) {

					ExecuteTestRun.setErrorBoxPrinter();
					e.printStackTrace();
				}
			}
			if (failMediaUser.size() > 0) {
				ExecuteTestRun.setCommandPrinter();
				System.out.println("\nFix User media flow issues by re-joining meeting? Y/N");
				ExecuteTestRun.setInputPrinter();
				cmd = br.readLine();
				if (cmd.equalsIgnoreCase("y") == true) {
					leaveAndRejoinMeeting(failMediaUser);
				}
			} else {
				System.out.println("\n Done....");
			}
		} catch (Exception e) {

			ExecuteTestRun.setErrorBoxPrinter();
			e.printStackTrace();
		}

	}

	private static void leaveAndRejoinMeeting(List<userInfo> failMediaUser) {
		ExecuteTestRun.setResponsePrinter();

		StringBuilder result = new StringBuilder();
		StringBuilder finalresult = new StringBuilder();
		finalresult.append("\nDate: " + ExecuteTestRun.cp.getDateFormatted());

		for (userInfo userInfo : failMediaUser) {
			result = new StringBuilder();
			result.append("\n--------------------------------------------------");
			try {
				WebDriver driver = userInfo.getDriver();
				result.append(
						"\nUser: " + userInfo.getUserName() + " is leaving meeting: " + userInfo.getMeetingName());
				driver.findElement(By.id("button-leave")).click();

				result.append("\n" + "Leave button clicked");
				driver.navigate().refresh();
				Thread.sleep(2000);
				driver.navigate().refresh();
				Thread.sleep(2000);
				WebElement name1 = (new WebDriverWait(driver, 120))
						.until(ExpectedConditions.presenceOfElementLocated(By.name("name")));
				if (name1.isDisplayed() == true) {
					result.append(
							"\nUser: " + userInfo.getUserName() + " is joining meeting: " + userInfo.getMeetingName());
					name1.clear();
					name1.sendKeys(userInfo.getUserName());
					WebElement rm = driver.findElement(By.name("room"));
					rm.sendKeys(userInfo.getMeetingName());

					driver.findElement(By.cssSelector(".submit>input")).click();

					result.append("\n" + "Join Meeting button pressed");
					Thread.sleep(1000);
					WebElement leaveButton = (new WebDriverWait(driver, 120))
							.until(ExpectedConditions.presenceOfElementLocated(By.id("button-leave")));
					if (leaveButton.isDisplayed() == true) {
						result.append("\n" + userInfo.getUserName() + " joined meeting " + userInfo.getMeetingName());

					} else {
						result.append("\n" + "User have not joined the meeting ");
					}
				} else {
					result.append("\n" + "Unable to load the page ");
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
			result.append("\n--------------------------------------------------");
			System.out.println(result.toString());
			finalresult.append(result.toString());

		}
		try {

			String current = new File(".").getCanonicalPath();
			setResponsePrinter();
			System.out.print("\nSaving result....");
			File target = new File(current + "/LeaveAndRejoinResult/");
			if (!target.exists()) {
				target.mkdir();
			}
			FileWriter fw = new FileWriter(current + "/LeaveAndRejoinResult/" + "LeaveAndRejoinResultOutput.txt", true);
			fw.write(finalresult.toString());
			fw.close();
			System.out.print(
					"\nResult saved to path: " + current + "/checkMediaFlowResult/" + "LeaveAndRejoinResultOutput.txt");
		} catch (Exception e) {
			// TODO: handle exception
		}
		finalresult = null;
		result = null;
	}

	private static void ShowMeetingsWithUsers() {
		try {
			for (Entry<String, Integer> entry : TestRunProperties.userMeetingMap.entrySet()) {
				System.out.print("\n" + entry.getKey() + " Total user count: " + entry.getValue());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void setCommandPrinter() {
		cp.print("", Attribute.BOLD, FColor.RED, BColor.BLACK);
	}

	public static void setInputPrinter() {
		cp.print("", Attribute.BOLD, FColor.GREEN, BColor.BLACK);
	}

	public static void setResponsePrinter() {
		cp.print("", Attribute.BOLD, FColor.YELLOW, BColor.BLACK);
	}

	public static void setBoxPrinter() {
		cp.print("", Attribute.BOLD, FColor.WHITE, BColor.YELLOW);
	}

	public static void setErrorBoxPrinter() {
		cp.print("", Attribute.BOLD, FColor.WHITE, BColor.RED);
	}

	private static void showRunningMeetings() {
		try {
			for (Entry<String, String> entry : TestRunProperties.runningMeetings.entrySet()) {
				System.out.print("\n" + entry.getKey() + " running on " + entry.getValue());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void startRecording(boolean start, String meetingName) {
		try {

			if (TestRunProperties.runningMeetings.containsKey(meetingName)) {
				if (TestRunProperties.driverMap
						.containsKey(TestRunProperties.runningMeetings.get(meetingName) + "Host" + meetingName)) {
					WebDriver driver = TestRunProperties.driverMap
							.get(TestRunProperties.runningMeetings.get(meetingName) + "Host" + meetingName);
					startRecording(start, driver, "Host" + meetingName);
				} else {
					setErrorBoxPrinter();
					System.out.print("\n Driver Not Found for " + "Host" + meetingName);
					setResponsePrinter();
				}
			} else {
				setErrorBoxPrinter();
				System.out.print("\n No such meeting Exist....");
				setResponsePrinter();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private static void startRecording(boolean start, WebDriver driver, String userName) {
		// TODO Auto-generated method stub
		try {
			if (start == true) {
				driver.findElement(By.id("button-startRecording")).click();
				// System.out.println("Start Recording button
				// pressed. Recording is started.");

			} else {
				driver.findElement(By.id("button-stopRecording")).click();
				// System.out.println("Stop Recording button
				// pressed. Recroding is stopped.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			if (start == true) {
				setErrorBoxPrinter();
				System.out.print("\nUnable to Start recording for " + userName + e.getMessage());
			} else {
				setErrorBoxPrinter();
				System.out.print("\nUnable to stop recording for " + userName + e.getMessage());
			}
			setResponsePrinter();
		}
	}

	private static void showCurrentStatus() {

		System.out.print("\n=================================================================");
		System.out.print("\nHost Address=" + TestRunProperties.strBaseUrl);
		System.out.print("\nTotal Number of running Meetings=" + TestRunProperties.runningMeetings.size());
		System.out.print("\nTotal Number of Users=" + TestRunProperties.driverMap.size());
		System.out.print("\nTotal Number of running Nodes=" + TestRunProperties.nodeList.size());
		System.out.print("\n\nLoad on each Nodes:");
		for (Entry<String, Integer> entry : TestRunProperties.userNodeMap.entrySet()) {
			System.out.print("\n" + entry.getKey() + ": " + entry.getValue());
		}
		System.out.print("\n=================================================================");
	}

	private static void startRecording(boolean start) {
		// TODO Auto-generated method stub
		HashMap<String, RemoteWebDriver> tempList = TestRunProperties.driverMap;
		try {
			for (Entry<String, RemoteWebDriver> entry : tempList.entrySet()) {
				if (entry.getKey().contains("Host") == true) {
					WebDriver driver = entry.getValue();
					startRecording(start, driver, entry.getKey());

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private static void leaveMeeting1() {
		// TODO Auto-generated method stub
		try {
			String meetingName = "";
			ArrayList<String> tabs = null;
			setCommandPrinter();
			System.out.print("\n Please enter Meeting Name \n");
			meetingName = (br.readLine());
			HashMap<String, RemoteWebDriver> tempList = TestRunProperties.driverMap;

			// for all user
			for (Entry<String, RemoteWebDriver> entry : tempList.entrySet()) {
				if (entry.getKey().contains(meetingName)) {

					try {
						RemoteWebDriver driver = entry.getValue();
						// String driverWindow=driver.getWindowHandle();
						// driver.switchTo().window(driverWindow);
						tabs = new ArrayList<String>(driver.getWindowHandles());
						driver.switchTo().window(tabs.get(0));
						int count = 0;
						if (entry.getKey().contains("Host")) {
							Thread.sleep(3000);
							if (UtilityClass.isElementPresent(driver, CreateMeetingPage.endCall_icon)) {
								driver.findElement(CreateMeetingPage.endCall_icon).click();
								Thread.sleep(2000);
								TestRunProperties.explicitWait(driver, CreateMeetingPage.btn_Yes);
								WebElement modalContainer = driver.findElement(CreateMeetingPage.accept_modal);
								WebElement modalContent = modalContainer.findElement(CreateMeetingPage.btn_Yes);
								modalContent.click();
								count++;
							}
							if (UtilityClass.isElementPresent(driver, CreateMeetingPage.endSS_icon)) {
								driver.findElement(CreateMeetingPage.endSS_icon).click();
								Thread.sleep(2000);
								TestRunProperties.explicitWait(driver, CreateMeetingPage.btn_Yes);
								WebElement modalContainer = driver.findElement(CreateMeetingPage.accept_modal);
								WebElement modalContent = modalContainer.findElement(CreateMeetingPage.btn_Yes);
								modalContent.click();
								// count++;
							}
							driver.close();
							if (count == 1) {
								driver.switchTo().window(tabs.get(1)).close();
							}
							count = 0;
							// System.out.print("Leave button pressed.");
						} else {
							driver.close();
						}
					} catch (Exception e) {
						// TODO: handle exception
						setErrorBoxPrinter();
						System.out.print("\nUnable to leave meeting for " + entry.getKey() + "\n" + e);
						setResponsePrinter();
					}
				} else {
					// entry.getValue().quit();
				}
			}
			System.out.print("\nLeave Meeting Process Completed.... ");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void performActivity(HashMap<String, RemoteWebDriver> elements) {
		System.out.println("Current Thread id is " + Thread.currentThread().getId());
		for (int i = 0; i < 1; i++) {
//			HashMap<String, RemoteWebDriver> tempList = TestRunProperties.driverMap;
//			System.out.println(tempList);
//			System.out.println("=================================");

			for (Entry<String, RemoteWebDriver> entry : elements.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
				if (entry.getKey().contains("Host") == true) {
					try {
						RemoteWebDriver driver = entry.getValue();
						AddCanvas.addCanvas(driver);
						Thread.sleep(5000);
						Actions action = new Actions(driver);
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_Color));
						js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.btn_Purple));
						Thread.sleep(1000);

						WebElement wb = driver.findElement(CreateMeetingPage.img_Annotations);
						if (wb.isSelected() == false) {
							js.executeScript("arguments[0].click();",
									driver.findElement(CreateMeetingPage.img_Annotations));
						}
						WebElement Circle = driver.findElement(CreateMeetingPage.img_Circle);
						js.executeScript("arguments[0].click();", Circle);
						Thread.sleep(1000);
						WebElement Canvas = driver.findElement(CreateMeetingPage.LOC_Canvas);
						action.moveToElement(Canvas, 200, 80);

						org.openqa.selenium.Point point = Canvas.getLocation();

						int xcord = point.getX() - 100;
						int ycord = point.getY() - 50;
						System.out.println("X coordinates:" + xcord);
						System.out.println("Y coordinates:" + ycord);
						int x = xcord - 20;
						int y = ycord + 45;

						Thread.sleep(3000);
						Circle.click();

						js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].click();", Circle);

						Action series = action.moveToElement(Canvas, xcord, ycord).clickAndHold().moveByOffset(x, y)
								.build();
						series.perform();
						action.release().perform();
						Thread.sleep(3000);
						js.executeScript("arguments[0].click();",
								driver.findElement(CreateMeetingPage.img_Annotations));
						js.executeScript("arguments[0].click();", driver.findElement(CreateMeetingPage.img_Circle));
						js.executeScript("arguments[0].click();",
								driver.findElement(CreateMeetingPage.img_Annotations));
						Thread.sleep(2000);
					} catch (Exception e) {
						System.out.print("\nUnable to perform activity" + entry.getKey());
					}
				}
			}
		}
	}

	private static void leaveMeeting() {
		// TODO Auto-generated method stub
		HashMap<String, RemoteWebDriver> tempList = TestRunProperties.driverMap;
		try {
			// for all user
			for (Entry<String, RemoteWebDriver> entry : tempList.entrySet()) {
				if (entry.getKey().contains("Host") == false) {
					try {
						RemoteWebDriver driver = entry.getValue();
						/*
						 * driver.findElement(CreateMeetingPage.leaveSessionBtn) .click();
						 * Thread.sleep(2000); driver.findElement(CreateMeetingPage.
						 * leave_Session_Accept).click();
						 */
						driver.quit();
						// System.out.print("Leave button pressed.");

					} catch (Exception e) {
						// TODO: handle exception
						setErrorBoxPrinter();
						System.out.print("\nUnable to leave meeting for " + entry.getKey());
						setResponsePrinter();
					}
				} else {
					entry.getValue().quit();
				}
			}
			// for all host
			for (Entry<String, RemoteWebDriver> entry : tempList.entrySet()) {
				if (entry.getKey().contains("Host") == true) {
					try {
						RemoteWebDriver driver = entry.getValue();
						/*
						 * driver.findElement(CreateMeetingPage.leaveSessionBtn) .click();
						 * Thread.sleep(2000); driver.findElement(CreateMeetingPage.
						 * leave_Session_Accept).click();
						 */
						driver.quit();
						// System.out.print("Leave button pressed.");

					} catch (Exception e) {
						// TODO: handle exception
						setErrorBoxPrinter();
						System.out.print("\nUnable to leave meeting for " + entry.getKey());
						setResponsePrinter();
					}
				}
			}
			System.out.print("\nLeave Meeting Process Completed.... ");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void AddUserToExistingMeetings() {
		int numberofuser;
		String option = "";
		String meetingName = "";

		try {
			setCommandPrinter();
			System.out.print("\n Please enter Number of users to be added \n");
			setInputPrinter();
			numberofuser = Integer.parseInt(br.readLine());
			setCommandPrinter();
			System.out.print("\n Add to all existing meetings ? y/n \n");
			setInputPrinter();
			option = br.readLine();
			setResponsePrinter();
			ExecuteTestRun Et = new ExecuteTestRun();
			Et.tempUserNodeMap = ExecuteTestRun.deepClone(TestRunProperties.userNodeMap);
			HashMap<String, String> meetingToHost = new HashMap<String, String>();

			if (option.equalsIgnoreCase("n")) {
				setCommandPrinter();
				System.out.print("\n Please enter Meeting Name \n");
				meetingName = (br.readLine());
				setResponsePrinter();
				if (TestRunProperties.userMeetingMap.containsKey(meetingName)) {

					meetingToHost.put(meetingName, "");
					List<XmlSuite> suites = new ArrayList<XmlSuite>();
					XmlSuite xmlSuite = Et.getAllUserMeetingXmlSuite(meetingToHost, numberofuser + 1);
					suites.add(xmlSuite);
					executeTest(suites);

				} else {
					setErrorBoxPrinter();
					System.out.print("\n No such meeting Exist....");
					setResponsePrinter();
				}
			} else {
				setResponsePrinter();
				System.out.print("\n Adding user to Existing Meetings \n");
				meetingToHost = TestRunProperties.runningMeetings;
				List<XmlSuite> suites = new ArrayList<XmlSuite>();
				XmlSuite xmlSuite = Et.getAllUserMeetingXmlSuite(meetingToHost, numberofuser + 1);
				suites.add(xmlSuite);
				executeTest(suites);
			}
		} catch (Exception e) {
			System.out.print(
					"\n Exception in AddUserToExistingMeetings() of ExecuteTestRun class which add users in on going meeting :  \n"
							+ e);
		}

	}

	private static void startMeeting() {
		int numberofMeeeting;

		try {
			setCommandPrinter();
//			System.out.println("\nChoose meeting type...");
//			System.out.print("\n1 Start Session only and Add users in Session");
//			System.out.print("\n2 Start Audio Meeting");
//			System.out.print("\n3 Start Video Meeting");
//			System.out.print("\n4 Start Audio meeting with Screenshare");
//			System.out.print("\n5 Start Video meeting with Screenshare");
//			System.out.print("\n6 Start Session with Screenshare only");
//			System.out.print("\n7 Start Session with Canvas");
//			System.out.print("\n8 Start Session with Canvas and VideoCall");
//			System.out.print("\n9 Start Session with Canvas and AudioCall");

			setInputPrinter();
			callType = TestRunProperties.meetingType;
			try {
				int calltyp = Integer.parseInt(callType);
				if (calltyp >= 1 && calltyp <= 9) {
					// System.out.print("\nPlease Enter Meeting Prefix.\n");

					setInputPrinter();
					String cmd = TestRunProperties.meetingName;
					if (cmd.trim().isEmpty() == false) {
						TestRunProperties.meetingPrefix = cmd;
					} else {
						TestRunProperties.meetingPrefix = "Meeting" + (int) (Math.random() * 50 + 1);
					}
					setCommandPrinter();
					// System.out.print("\n Please enter Number of meetings to start\n");
					setInputPrinter();
					numberofMeeeting = Integer.parseInt(TestRunProperties.meetings);
					setCommandPrinter();
					// System.out.print("\n Please enter Number of user in each meeting\n");
					setInputPrinter();
					numberofuser = Integer.parseInt(TestRunProperties.users1);
					numberOfUserPerMeeting = numberofMeeeting;
//					if (numberofuser > 1 && !(calltyp == 1 || calltyp == 7)) {
//					numberofuser = numberofuser - 1;
//				}
					if (calltyp == 1) {
						numberofuser = numberofuser + 1;
					}
					int totalUsers = numberofuser * numberofMeeeting;
					int noOfNodes = TestRunProperties.GetAvailableNodeList().size();
					int actualInstances = totalUsers / noOfNodes;
					if (actualInstances > TestRunProperties.maxInstance) {
						System.out.print(
								"Your number of browser instance per node is more then defined in config.properties fille, Please try again with less number of meetings");
						System.out.print("\n--------------------------------------------------------------------");
						startMeeting();
					} else {

						setCommandPrinter();
						// System.out.print("\n Please set Test execution mode. Press enter to use
						// default(2)\n");
						setResponsePrinter();
//						System.out.print("\n 1 Start all host sequentialy and then join all users in parallel mode.");
//						System.out.print("\n 2 Start meetings one by one with their users in parallel mode");
						setInputPrinter();
						cmd = "1";
						setResponsePrinter();
						ExecuteTestRun Et = new ExecuteTestRun();
						// Et.tempUserNodeMap = TestRunProperties.userNodeMap;
						Et.tempUserNodeMap = ExecuteTestRun.deepClone(TestRunProperties.userNodeMap);
						// Et.hostMeeting(numberofMeeeting, numberofuser);
						HashMap<String, String> meetingToHost = Et.getMeetingsNameWithHostNode(numberofMeeeting);
						List<XmlSuite> suites = new ArrayList<XmlSuite>();
						if (cmd.equals("1")) {
							XmlSuite XmlSuite1 = Et.getAllhostMeetingXmlSuite(meetingToHost);
							XmlSuite XmlSuite2 = Et.getAllUserMeetingXmlSuite(meetingToHost, numberofuser);

							suites.add(XmlSuite1);
							// System.out.print(XmlSuite1.toXml());
							suites.add(XmlSuite2);
							// System.out.print(XmlSuite2.toXml());
						} else {
							for (HashMap.Entry<String, String> entry : meetingToHost.entrySet()) {

								XmlSuite XmlSuite1 = Et.getSinglehostMeetingXmlSuite(entry.getKey(), entry.getValue());
								XmlSuite XmlSuite2 = Et.getUserSingleMeetingXmlSuite(entry.getKey(), numberofuser);

								suites.add(XmlSuite1);
								// System.out.print(XmlSuite1.toXml());
								suites.add(XmlSuite2);
								// System.out.print(XmlSuite2.toXml());

							}
						}
						executeTest(suites);
					}
				} else {
					System.out.println("Please enter a valid input");
				}
			} catch (Exception e) {
				System.out.println("Please enter a valid input");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	private static void executeTest(List<XmlSuite> suites) {
		TestNG testNG = new TestNG();
		testNG.setXmlSuites(suites);
		for(int i=0;i<suites.size();i++) {
			System.out.println(suites.get(i).toXml());
			System.out.println("==================");
		}
		testNG.run();
	}

	private HashMap<String, String> getMeetingsNameWithHostNode(int numberofMeeeting) {
		HashMap<String, String> meetingsWithHost = new HashMap<String, String>();

		int nodeIndex = 0;
		int capacityFullInstance = 0;
		for (int i = 1; i <= numberofMeeeting; i++) {
			int userCount = 0;
			String nodeIp = "";
			do {
				if (nodeIndex == TestRunProperties.nodeList.size()
						|| nodeIndex > TestRunProperties.nodeList.size() - 1) {
					nodeIndex = 0;
				}
				nodeIp = TestRunProperties.nodeList.get(nodeIndex);
				userCount = 0;
				if (tempUserNodeMap.containsKey(nodeIp) == true) {
					userCount = tempUserNodeMap.get(nodeIp.toString());
				}

				if (userCount <= TestRunProperties.maxInstance) {
					nodeIndex++;
					capacityFullInstance = 0;

					break;
				} else {
					capacityFullInstance += 1;
				}
				nodeIndex++;
			} while (capacityFullInstance != TestRunProperties.nodeList.size());

			if (capacityFullInstance != TestRunProperties.nodeList.size()) {
				if (tempUserNodeMap.containsKey(nodeIp) == true) {
					userCount = tempUserNodeMap.get(nodeIp.toString());
				}

				if (userCount <= TestRunProperties.maxInstance) {

					meetingsWithHost.put(TestRunProperties.meetingPrefix + (totalNumberofRunningMeetings + i), nodeIp);

					userCount += 1;
					tempUserNodeMap.put(nodeIp.toString(), userCount);
				}
			} else {
				setErrorBoxPrinter();
				System.out.print("\n No more meeting can be hosted as max limit reached");
				setResponsePrinter();
				break;
			}

		}
		totalNumberofRunningMeetings += numberofMeeeting;
		return meetingsWithHost;
	}

	private XmlSuite getSinglehostMeetingXmlSuite(String meetingName, String node) {

		XmlSuite suite = createSuite("StartMeetings", ParallelMode.FALSE, 1);

		XmlTest test = getJoinUserXmlTest("Host" + meetingName, node, meetingName);

		test.setXmlSuite(suite);
		suite.addTest(test);

		// System.out.print("d......."+suite.toXml());
		return suite;
	}

	private XmlSuite getAllhostMeetingXmlSuite(HashMap<String, String> meetingToHost) {

		XmlSuite suite = createSuite("StartMeetings", ParallelMode.TESTS, 3);
		for (HashMap.Entry<String, String> entry : meetingToHost.entrySet()) {

			XmlTest test = getJoinUserXmlTest("Host" + entry.getKey(), entry.getValue(), entry.getKey());

			test.setXmlSuite(suite);
			suite.addTest(test);
		}

		// System.out.print("d......."+suite.toXml());
		return suite;
	}

	private XmlSuite getUserSingleMeetingXmlSuite(String meetingName, int numberofuser) {
		// XmlSuite for Join Meeting
		XmlSuite suiteUser = createSuite("Join Meetings", ParallelMode.TESTS, numberofuser);

		int userNodeIndex = 0;
		// Loop for total number of user
		String userNodeIp = "";
		int userCount = 0;
		int capacityFullInstance = 0;
		int LastUserIndex = 0;
		if (TestRunProperties.userMeetingMap.containsKey(meetingName)) {
			LastUserIndex = TestRunProperties.userMeetingMap.get(meetingName).intValue();
		}
		System.out.println(meetingName + " " + LastUserIndex);
		for (int userindex = 1 + LastUserIndex; userindex <= (numberofuser + LastUserIndex) - 1; userindex++) {

			do {
				if (userNodeIndex == TestRunProperties.nodeList.size()
						|| userNodeIndex > TestRunProperties.nodeList.size() - 1) {
					userNodeIndex = 0;
				}
				userNodeIp = TestRunProperties.nodeList.get(userNodeIndex);
				userCount = 0;
				if (tempUserNodeMap.containsKey(userNodeIp) == true) {
					userCount = tempUserNodeMap.get(userNodeIp.toString());

				}

				if (userCount <= TestRunProperties.maxInstance) {
					userNodeIndex++;
					capacityFullInstance = 0;
					break;
				} else {
					capacityFullInstance += 1;
				}
				userNodeIndex++;
			} while (capacityFullInstance != TestRunProperties.nodeList.size());

			if (capacityFullInstance != TestRunProperties.nodeList.size()) {

				if (tempUserNodeMap.containsKey(userNodeIp) == true) {
					userCount = tempUserNodeMap.get(userNodeIp.toString());

				}

				if (userCount <= TestRunProperties.maxInstance) {

					XmlTest testUser = getJoinUserXmlTest("User" + userindex + meetingName, userNodeIp, meetingName);
					testUser.setXmlSuite(suiteUser);
					suiteUser.addTest(testUser);
					userCount += 1;
					tempUserNodeMap.put(userNodeIp.toString(), userCount);
				}
			} else {
				setErrorBoxPrinter();
				System.out.print("\n No more user can join meeting as max limit reached");
				setResponsePrinter();
				break;
			}

		}

		return suiteUser;

	}

	private XmlSuite getAllUserMeetingXmlSuite(HashMap<String, String> meetingToHost, int numberofuser) {
		// XmlSuite for Join Meeting
		XmlSuite suiteUser = createSuite("Join Meetings", ParallelMode.TESTS, numberofuser * meetingToHost.size());

		for (HashMap.Entry<String, String> entry : meetingToHost.entrySet()) {

			int userNodeIndex = 0;
			// Loop for total number of user
			String userNodeIp = "";
			int userCount = 0;
			int capacityFullInstance = 0;
			int LastUserIndex = 0;
			if (TestRunProperties.userMeetingMap.containsKey(entry.getKey())) {
				LastUserIndex = TestRunProperties.userMeetingMap.get(entry.getKey()).intValue();
			}
			System.out.println(entry.getKey() + " " + LastUserIndex);
			for (int userindex = 1 + LastUserIndex; userindex <= (numberofuser + LastUserIndex) - 1; userindex++) {

				do {
					if (userNodeIndex == (TestRunProperties.nodeList.size())
							|| userNodeIndex > (TestRunProperties.nodeList.size() - 1)) {
						userNodeIndex = 0;
					}
					userNodeIp = TestRunProperties.nodeList.get(userNodeIndex);
					userCount = 0;
					if (tempUserNodeMap.containsKey(userNodeIp) == true) {
						userCount = tempUserNodeMap.get(userNodeIp.toString());

					}

					if (userCount <= TestRunProperties.maxInstance) {
						userNodeIndex++;
						capacityFullInstance = 0;
						break;
					} else {
						capacityFullInstance += 1;
					}
					userNodeIndex++;
				} while (capacityFullInstance != TestRunProperties.nodeList.size());

				if (capacityFullInstance != TestRunProperties.nodeList.size()) {

					if (tempUserNodeMap.containsKey(userNodeIp) == true) {
						userCount = tempUserNodeMap.get(userNodeIp.toString());

					}

					if (userCount <= TestRunProperties.maxInstance) {

						XmlTest testUser = getJoinUserXmlTest("User" + userindex + entry.getKey(), userNodeIp,
								entry.getKey());
						testUser.setXmlSuite(suiteUser);
						suiteUser.addTest(testUser);
						userCount += 1;
						tempUserNodeMap.put(userNodeIp.toString(), userCount);
					}
				} else {
					setErrorBoxPrinter();
					System.out.print("\n No more user can join meeting as max limit reached");
					setResponsePrinter();
					break;
				}

			}

		}

		return suiteUser;

	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T deepClone(T o) {
		try {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(byteOut);
			out.writeObject(o);
			out.flush();
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(byteOut.toByteArray()));
			return (T) o.getClass().cast(in.readObject());

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	@SuppressWarnings("unused")
	private void hostMeeting(int numberofMeeeting, int numberofuser) {
		// TODO Auto-generated method stub
		// XmlSuite for Start Meeting
		HashMap<String, Integer> tempUserNodeMap = new HashMap<>();
		tempUserNodeMap = ExecuteTestRun.deepClone(TestRunProperties.userNodeMap);

		XmlSuite suite = createSuite("StartMeetings", ParallelMode.FALSE, 1);

		// XmlSuite for Join Meeting
		XmlSuite suiteUser = createSuite("Join Meetings", ParallelMode.TESTS, numberofuser * numberofMeeeting);

		int nodeIndex = 0;

		// for Hosting meeting
		for (int i = 1; i <= numberofMeeeting; i++) {
			int userCount = 0;

			if (nodeIndex == TestRunProperties.nodeList.size() || nodeIndex > TestRunProperties.nodeList.size() - 1) {
				nodeIndex = 0;
			}
			String nodeIp = TestRunProperties.nodeList.get(nodeIndex);

			if (tempUserNodeMap.containsKey(nodeIp) == true) {
				userCount = tempUserNodeMap.get(nodeIp.toString());
			}

			if (userCount <= TestRunProperties.maxInstance) {

				XmlTest test = getJoinUserXmlTest(
						"Host" + TestRunProperties.meetingPrefix + (totalNumberofRunningMeetings + i), nodeIp,
						TestRunProperties.meetingPrefix + (totalNumberofRunningMeetings + i));

				test.setXmlSuite(suite);
				suite.addTest(test);
				userCount += 1;
				tempUserNodeMap.put(nodeIp.toString(), userCount);

				int userNodeIndex = 0;
				// Loop for total number of user
				for (int userindex = 1; userindex <= numberofuser - 1; userindex++) {

					if (userNodeIndex == TestRunProperties.nodeList.size() - 1
							|| userNodeIndex > TestRunProperties.nodeList.size() - 1) {
						userNodeIndex = 0;
					}
					String userNodeIp = TestRunProperties.nodeList.get(userNodeIndex);
					userCount = 0;
					if (tempUserNodeMap.containsKey(userNodeIp) == true) {
						userCount = tempUserNodeMap.get(userNodeIp.toString());

					}

					if (userCount <= TestRunProperties.maxInstance) {

						XmlTest testUser = getJoinUserXmlTest(
								"User" + userindex + TestRunProperties.meetingPrefix
										+ (totalNumberofRunningMeetings + i),
								nodeIp, TestRunProperties.meetingPrefix + (totalNumberofRunningMeetings + i));
						testUser.setXmlSuite(suiteUser);
						suiteUser.addTest(testUser);
						userCount += 1;
						tempUserNodeMap.put(userNodeIp.toString(), userCount);
					}
					userNodeIndex++;
				}
				nodeIndex++;
			}
		}

		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		suites.add(suiteUser);
		System.out.print(suiteUser.toXml());
		TestNG testNG = new TestNG();
		testNG.setXmlSuites(suites);
		testNG.run();
	}

	private XmlTest getJoinUserXmlTest(String userName, String nodeIp, String meetingName) {
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass("com.src.testCases.CreateMeeting"));
		classes.add(new XmlClass("com.src.util.FactoryDataprovider"));
		XmlTest test = new XmlTest();
		test.setName(userName);
		test.setGroupByInstances(true);
		test.setXmlClasses(classes);
		test.addParameter("node", nodeIp);
		test.addParameter("meetingName", meetingName);
		test.addParameter("FakeCame", "true");
		test.addParameter("userName", userName);
		return test;
	}

	private XmlSuite createSuite(String suiteName, ParallelMode parallelMode, int threadCount) {
		XmlSuite suite = new XmlSuite();
		suite.setName(suiteName);
		suite.setParallel(parallelMode);
		suite.setThreadCount(threadCount);
		return suite;
	}

	public void copy(File sourceLocation, File targetLocation) throws IOException {
		if (sourceLocation.isDirectory()) {
			copyDirectory(sourceLocation, targetLocation);
		} else {
			copyFile(sourceLocation, targetLocation);
		}
	}

	private void copyDirectory(File source, File target) throws IOException {
		if (!target.exists()) {
			target.mkdir();
		}

		for (String f : source.list()) {
			copy(new File(source, f), new File(target, f));
		}
	}

	private void copyFile(File source, File target) throws IOException {
		try (InputStream in = new FileInputStream(source); OutputStream out = new FileOutputStream(target)) {
			byte[] buf = new byte[1024];
			int length;
			while ((length = in.read(buf)) > 0) {
				out.write(buf, 0, length);
			}
		} catch (Exception e) {
			// TODO: handle exception
			setErrorBoxPrinter();
			e.printStackTrace();
			setResponsePrinter();
		}
	}

	public void createmeeting() {
		String xmlFileName = "testng.xml";
		List<XmlSuite> suite;
		TestNG testNG = new TestNG();
		try {
			suite = (List<XmlSuite>) (new Parser(xmlFileName).parse());

			testNG.setXmlSuites(suite);
			testNG.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
