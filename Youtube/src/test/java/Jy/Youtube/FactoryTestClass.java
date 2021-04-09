package Jy.Youtube;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FactoryTestClass {

	private String[] param = {""};

	public FactoryTestClass(String[] arr) {
		this.param = arr;
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before SimpleTest class executed.");
	}

	@Test
	
	public void testMethod() {
		System.out.println("testMethod parameter value is: " + param[0]);
	}
}
