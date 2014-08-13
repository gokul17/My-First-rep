package com.sample.test1;

import java.util.List;

import junit.framework.Assert;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sample.util.TestUtil;

public class Test1_1 extends TestSuiteBase {
	public static String runmode[]=null;
	public int count=-1;
	public static String testcasestatus=null;
	public static String testdatastatus=null;
	
	@BeforeMethod
	public void checktestcaserunnable()
	{
		if(!TestUtil.isTestCaseRunnable(test_1_suite, this.getClass().getSimpleName()))
		{
			testcasestatus="skip";
			App_Logs.debug("Testcase"+this.getClass().getSimpleName()+"is skipped");
			throw new SkipException("Testcase "+this.getClass().getSimpleName()+" is skipped");
		}
		else
		{
			runmode=TestUtil.getdata_for_runmode(test_1_suite, this.getClass().getSimpleName());
		}
		
	
	}
	
	@Test
	public void test1()
	{
			driver.get(config.getProperty("testsite"));
			driver.manage().window().maximize();
			Actions builder=new Actions(driver);
			builder.moveToElement(getobject("Mens_collection")).build().perform();
			wait_visible("Formal_shirt");
			getobject("Formal_shirt").click();
			String title=driver.getTitle();
			Assert.assertEquals("Formal Shirts - Buy Mens Formal Shirts Online at Best Prices in India", title);
	}
	
//	@AfterMethod
//	public void setresultforData()
//	{
//		TestUtil.reportDataSetResult(test_1_suite, this.getClass().getSimpleName(),count+2, testdatastatus);
//		testdatastatus=null;
//	}
//	@DataProvider
//	public Object[][] getTestData()
//	{
//		return TestUtil.getdata(test_1_suite, this.getClass().getSimpleName());
//		
//	}
	

}
