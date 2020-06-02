package com.testng.selenium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Sample3 {
	@Test
	public void m1() {
		Assert.assertTrue(false);
	}

	@Test
	public void m2() {
		Assert.assertTrue(true);
	}

	@Test
	public void m3() {
		Assert.assertTrue(false);
	}

}
