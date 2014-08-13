package com.sample.test1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sample.util.TestUtil;

public class pricefilter_unselection extends TestSuiteBase {
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
			App_Logs.debug("Testcase "+this.getClass().getSimpleName()+" is skipped");
			throw new SkipException("Testcase"+this.getClass().getSimpleName()+"is skipped");
		}
		else
		{
			runmode=TestUtil.getdata_for_runmode(test_1_suite, this.getClass().getSimpleName());
		}
	
	}
	
	@Test
	public void price_filter_unselection()
	{
		String shirt_count=getobject("count_of_shirts").getText();
		getobject("cross_mark").click();
		wait_visible("Loading_content");
		wait_invisible("Loading_content");
		String shirt_count1=getobject("count_of_shirts").getText();
		Assert.assertTrue(!shirt_count.equals(shirt_count1));
	}
	
//	@AfterMethod
//	public void setresultforData()
//	{
//		TestUtil.reportDataSetResult(test_1_suite, this.getClass().getSimpleName(),count+2, testdatastatus);
//		
//	}
//	@DataProvider
//	public Object[][] getTestData()
//	{
//		return TestUtil.getdata(test_1_suite, this.getClass().getSimpleName());
//		
//	}
	

}
