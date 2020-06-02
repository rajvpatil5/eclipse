package com.testcases.nse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ObjectRepositries.Dataprovider;
import com.resourses.GetSheetData;

public class DataproviderTest {

	@Test(dataProvider = "getData", dataProviderClass = Dataprovider.class)
	public void test(Object u) {
		System.out.println(u.toString());
	}

	@Test(dataProvider = "getData", dataProviderClass = Dataprovider.class)
	public void test1(Object u) {
		System.out.println(u.toString());
	}

	@Test(dataProvider = "getData", dataProviderClass = Dataprovider.class)
	public void test2(Object u) {
		System.out.println(u.toString());
	}

	@Test(dataProvider = "getData", dataProviderClass = Dataprovider.class)
	public void test3(Object u) {
		System.out.println(u.toString());
	}

}
