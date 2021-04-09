package Jy.Youtube;

import org.testng.annotations.Factory;

public class FactoryDatapass {
	@Factory
	public Object[] factoryMethod() {
		
		String [] arr = {"user1", "user2","user3", "user4"};
//		return arr;
		return new Object[] { new FactoryTestClass(arr) };
	}

}
