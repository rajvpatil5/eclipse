package com.resourses;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class Demo {

	public static void main(String[] args) throws IOException, GeneralSecurityException {
		
		String username="Sheet1!A2:A14";
		String password="Sheet1!B2:B14";

		List list = GetSheetData.getData(username);
		for (Object element : list) {
			System.out.println(element);
		}
		
	}

}
