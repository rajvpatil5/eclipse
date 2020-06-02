package com.ObjectRepositries;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.resourses.GetSheetData;

public class Dataprovider {
	@DataProvider(parallel = true)
	public Object getData() throws IOException, GeneralSecurityException {
		String username = "Sheet1!A2:A14";
		String password = "Sheet1!B2:B14";
		List<List<Object>> userlist = GetSheetData.getData(username);
		List<List<Object>> psdlist = GetSheetData.getData(password);
		Object dateset[] = new Object[userlist.size()];

		for (int i = 0; i < userlist.size(); i++) {
			dateset[i] = userlist.get(i);
		}
		return dateset;
	}

}
