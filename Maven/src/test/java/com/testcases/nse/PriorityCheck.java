package com.testcases.nse;

import org.testng.annotations.Test;

public class PriorityCheck {
	@Test
	public void test1() {
		System.out.println("test1");

	}

	@Test(priority = -100)
	public void atest2() {
		System.out.println("test2");

	}

}
