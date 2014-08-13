package com.sample.test1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sample.util.TestUtil;

public class brandsearch extends TestSuiteBase {
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
	
	@Test(dataProvider="getTestData")
	public void test2in1(
			String searchcriteria
			)
	{
		count++;
		if(!runmode[count].equalsIgnoreCase("Y"))
		{
			testdatastatus="skip";
			throw new SkipException(this.getClass().getSimpleName()+" Data "+count+" is skipped");
		}
		else
		{
			getobject("brand_search").clear();
			getobject("brand_search").sendKeys(searchcriteria);
			getobject("brand_search").sendKeys(Keys.ENTER);
			if(getobjects("search_result").size()>0)
			{
				int search=getobjects("search_result").size();
				for(int i=1;i<=search;i++)
				{
					String search_result=driver.findElement(By.xpath(OR.getProperty("search_result_start")+i+OR.getProperty("search_result_end"))).getText();
					Assert.assertTrue(search_result.contains(searchcriteria));
				}
			}
			
		}
		
		
	}
	
	@AfterMethod
	public void setresultforData()
	{
		TestUtil.reportDataSetResult(test_1_suite, this.getClass().getSimpleName(),count+2, testdatastatus);
		
	}
	@DataProvider
	public Object[][] getTestData()
	{
		return TestUtil.getdata(test_1_suite, this.getClass().getSimpleName());
		
	}
	

}
