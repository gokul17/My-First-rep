package com.sample.test2;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sample.util.TestUtil;

public class Test2_1 extends TestSuiteBase {
	public static String runmode[]=null;
	public int count=-1;
	public static String testcasestatus=null;
	public static String testdatastatus=null;
	
	@BeforeMethod
	public void checktestcaserunnable()
	{
		if(!TestUtil.isTestCaseRunnable(test_2_suite, this.getClass().getSimpleName()))
		{
			testcasestatus="skip";
			App_Logs.debug("Testcase"+this.getClass().getSimpleName()+"is skipped");
			throw new SkipException("Testcase"+this.getClass().getSimpleName()+"is skipped");
		}
		else
		{
			runmode=TestUtil.getdata_for_runmode(test_2_suite, this.getClass().getSimpleName());
		}
	
	}
	
	@Test(dataProvider="getTestData")
	public void test1in2(
			String username,
			String password,
			String email)
	{
		count++;
		if(!runmode[count].equalsIgnoreCase("Y"))
		{
			testdatastatus="skip";
			throw new SkipException(this.getClass().getSimpleName()+" Data "+count+" is skipped");
		}
		else
		{
			
		}
		
		
	}
	
	@AfterMethod
	public void setresultforData()
	{
		TestUtil.reportDataSetResult(test_2_suite, this.getClass().getSimpleName(),count+2, testdatastatus);
		
	}
	@DataProvider
	public Object[][] getTestData()
	{
		return TestUtil.getdata(test_2_suite, this.getClass().getSimpleName());
		
	}
	

}
