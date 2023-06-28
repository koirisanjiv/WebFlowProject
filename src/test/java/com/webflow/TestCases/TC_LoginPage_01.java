package com.webflow.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.webflow.PageObject.LoginPage;

public class TC_LoginPage_01 extends BaseClass {

	
	@Test(priority =1)
	public void test_LoginPage()
	{
		
		LoginPage lp = new LoginPage(driver);
	
		lp.setEmailAddress(email);
		logger.info("email entered");
		
		lp.setPassword(password);
		logger.info("password entered");
		
		lp.clickLogin();
		logger.info("clicked on login button");
		
		if(driver.getPageSource().contains("Start building in Webflow"))
		{
			logger.info("login successfull");
			Assert.assertTrue(true);
			
		}
		else
		{
			logger.info("login failed");
			Assert.assertTrue(false);
			
		}
	}
	
	@Test(priority =2, dependsOnMethods = {"test_LoginPage"} )
	public void test_SkippedReporting()
	{
		logger.info("Skipped Reporting");
	}
	
}
