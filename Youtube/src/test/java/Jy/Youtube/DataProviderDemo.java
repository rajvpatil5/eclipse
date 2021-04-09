package Jy.Youtube;

import org.testng.TestNG;
import org.testng.annotations.Test;

public class DataProviderDemo {

	public static void main(String[] args) {
		TestNG testSuite = new TestNG();
		testSuite.setTestClasses(new Class[] { DataProviderTestClass.class });
		testSuite.setDefaultSuiteName("My Test Suite");
		testSuite.setDefaultTestName("My Test");
		testSuite.run();

	}

}
