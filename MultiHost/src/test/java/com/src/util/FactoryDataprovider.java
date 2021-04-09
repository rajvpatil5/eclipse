package com.src.util;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import com.src.testCases.CreateMeeting;

public class FactoryDataprovider {

//	@DataProvider
//	public Object[] getAgentData() throws IOException {
//		ArrayList<String> data = ExcelData.readExcelData();
//		Object[] arrayNoOfAgents = { "Rajat","Rutuja","Rozzaa" };
//		return arrayNoOfAgents;
//	}

	@Factory
	public Object[] factoryMethod() {
		ArrayList<String> readAgentData = null;
		try {
			readAgentData = ExcelData.readExcelData();
		} catch (IOException e) {
			System.out.println("Exception in @Factory - FactoryDataprovider");
			e.printStackTrace();
		}
		
		ArrayList<String> readAgentDataPassword = null;
		try {
			readAgentDataPassword = ExcelData.readExcelDataPassword();
		} catch (IOException e) {
			System.out.println("Exception in @Factory - FactoryDataprovider");
			e.printStackTrace();
		}
		return new Object[] { new CreateMeeting(readAgentData, readAgentDataPassword) };
	}
	
}
