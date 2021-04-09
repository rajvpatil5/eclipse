package Jy.Youtube;

import org.testng.TestNG;

public class FactoryDemo {

	public static void main(String[] args) {
		TestNG testSuite = new TestNG();
		testSuite.setTestClasses(new Class[] { FactoryDatapass.class });
//		testSuite.setDefaultSuiteName("My Test Suite");
//		testSuite.setDefaultTestName("My Test");
		testSuite.run();
		testSuite.run();
	}
}
