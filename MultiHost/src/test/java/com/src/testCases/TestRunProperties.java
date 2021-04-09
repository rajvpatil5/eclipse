package com.src.testCases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.src.util.Meeting;
import com.src.util.ReadFileData;
import com.src.util.SSHConnect;
import com.src.util.UtilityClass;

public class TestRunProperties {
	public static int noOfThreads = 0;
	static UtilityClass action = new UtilityClass();
	public static String strBaseUrl = ReadFileData.ReadFileConfig("url");
	public static String baseUrl = ReadFileData.ReadFileConfig("url");
	public static String uid = ReadFileData.ReadFileConfig("uid");
	public static String password = ReadFileData.ReadFileConfig("password");
	public static String sessionName = ReadFileData.ReadFileConfig("sessionName");
	public static String machine = ReadFileData.ReadFileConfig("Machine");
	public static String userName = ReadFileData.ReadFileConfig("userName");
	public static String fileLocation = ReadFileData.ReadFileConfig("File");
	public static String camera = ReadFileData.ReadFileConfig("FakeCamera");
	public static String meetingMenu = ReadFileData.ReadFileConfig("MeetingMenu");
	public static String meetingType = ReadFileData.ReadFileConfig("MeetingType");
	public static String meetingName = ReadFileData.ReadFileConfig("MeetingName");
	public static String meetings = ReadFileData.ReadFileConfig("NumberofMeetings");
	public static int meetings1 = Integer.parseInt(meetings);
	public static String users1 = ReadFileData.ReadFileConfig("NumbersofUsers");
	public static int users = Integer.parseInt(users1);
	public static String meetingOption = ReadFileData.ReadFileConfig("MeetingOption");
	public static String chromeVersion = ReadFileData.ReadFileConfig("ChromeVersion");
	public static String ExecutionMode = ReadFileData.ReadFileConfig("ExecutionMode");
	public static String ExecutionType = ReadFileData.ReadFileConfig("ExecutionType");
	public static String ChromePath = ReadFileData.ReadFileConfig("ChromePath");
	static List<String> nodeList = GetAvailableNodeList();
	public static HashMap<String, RemoteWebDriver> driverMap = new HashMap<String, RemoteWebDriver>();
	public static String maxInstance1 = ReadFileData.ReadFileConfig("MaximumInstance");
	public static int maxInstance = Integer.parseInt(maxInstance1);
	public static boolean fakCamera = Boolean.parseBoolean(camera);
	static HashMap<String, Integer> userNodeMap = new HashMap<String, Integer>();
	static HashMap<String, Integer> userMeetingMap = new HashMap<String, Integer>();
	static HashMap<String, String> runningMeetings = new HashMap<String, String>();
	static String meetingPrefix = "Meeting";
	public static HashMap<String, Meeting> runningMeeting = new HashMap<String, Meeting>();
	// static ArrayList<Object[]> data=new ArrayList<Object[]>();
	public static ArrayList<Object[]> sessionUrl = new ArrayList<Object[]>();
	public static HashMap<String, RemoteWebDriver> elements;
	public static boolean entryofFirstThread = true;
	public static int totalnoOfThreads = (users * meetings1) - meetings1;
	
	public static List<String> GetAvailableNodeList() {
		List<String> tempNodeList = new ArrayList<String>();
		try {
			String current = new java.io.File(".").getCanonicalPath();

			FileReader fileReader = new FileReader(new File(current + "/Nodelist.txt"));

			BufferedReader br = new BufferedReader(fileReader);

			String line = null;
			// if no more lines the readLine() returns null
			while ((line = br.readLine()) != null) {
				// reading lines until the end of the file
				if (line.trim().isEmpty() == false) {
					tempNodeList.add(line.trim());
					SSHConnect.connectToVM(line.trim());
				}
			}

			br.close();
			fileReader.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Error while fetching node list");

		}
		return tempNodeList;
	}

	public static void explicitWait(WebDriver driver, By xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 70);
		wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
	}
}
