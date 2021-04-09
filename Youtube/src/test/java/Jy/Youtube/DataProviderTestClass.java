package Jy.Youtube;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTestClass {
	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[3][2];

		data[0][0] = "user1";
		data[0][1] = "abcdef";

		data[1][0] = "user2";
		data[1][1] = "xyz";

		data[2][0] = "user3";
		data[2][1] = "123456";

		return data;
	}

	@Test(dataProvider = "getData")
	public void test(String username, String password) {
		System.out.println("Running test method");
		System.out.println(username);
		System.out.println(password);
	}
}
