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

public class pricefilter extends TestSuiteBase {
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
	public void price_filter()
	{
		int random_num=(int) (Math.floor(Math.random()*6));
		String price_range=driver.findElement(By.xpath(OR.getProperty("pricetag_start")+random_num+OR.getProperty("pricetag_end"))).getText();
		String[] price_element=price_range.split(" ");
		int rupees=Integer.parseInt(price_element[1]);
		driver.findElement(By.xpath(OR.getProperty("price_filter_start")+random_num+OR.getProperty("price_filter_end"))).click();
		wait_visible("Loading_content");
		wait_invisible("Loading_content");
		List<WebElement> price=getobjects("prices");
		for(int i=0;i<price.size();i++)
		{
			String value=price.get(i).getText();
			String[] rupees_only=value.split(" ");
			String rs=rupees_only[1];
			int price_value=Integer.parseInt(rs);
			if(random_num==1)
			{
				Assert.assertTrue(price_value <= rupees);
			}
			else
			{
				Assert.assertTrue(price_value >= rupees);
			}
		     
			//Assert.assertTrue(price.get(i)<rupees);
		}
		
		
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
