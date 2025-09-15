package com.ninza.crm.campaignstest;

import org.testng.annotations.Test;

import junit.framework.Assert;

public class demo {
	@Test(retryAnalyzer = com.ninzacrm.listenerutility.IRetryAnalyzerImplementation.class)
	public void add() {
		System.out.println("hi");
		Assert.assertEquals("hdfc", "hfdc");
	}

}
