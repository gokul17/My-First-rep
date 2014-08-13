package com.sample.test1;

import java.util.ArrayList;
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

public class Mixedfilter extends TestSuiteBase {
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
	public void mixed_filter()
	{
		int random_number=(int) (Math.floor(Math.random()*6));
		if(random_number==0)
		{
			random_number=random_number+1;
		}
		String price_range=driver.findElement(By.xpath(OR.getProperty("pricetag_start")+random_number+OR.getProperty("pricetag_end"))).getText();
		String[] price_element=price_range.split(" ");
		int rupees=Integer.parseInt(price_element[1]);
		driver.findElement(By.xpath(OR.getProperty("price_filter_start")+random_number+OR.getProperty("price_filter_end"))).click();
		wait_visible("Loading_content");
		wait_invisible("Loading_content");
		List<WebElement> price=getobjects("prices");
		
		List<WebElement> brands=getobject("brand_ul_tag").findElements(By.tagName("li"));
		List<WebElement> brand_disabled=getobject("brand_ul_tag").findElements(By.xpath("//ul[@id='brand']/li/a/input[@disabled='disabled']"));
		int random_num=(int) (Math.floor(Math.random()*(brands.size()-brand_disabled.size())));
		String brand_names=driver.findElement(By.xpath(OR.getProperty("check_box_brandname_start")+random_num+OR.getProperty("check_box_brandname_end"))).getText();
		driver.findElement(By.xpath(OR.getProperty("check_box_brandfilter_start")+random_num+OR.getProperty("check_box_brandfilter_end"))).click();
		wait_visible("Loading_content");
		wait_invisible("Loading_content");
		List<WebElement> brand_names_filter_result=getobjects("brand_names_filter_result");
		for(int i=0;i<price.size();i++)
		{
			String value=price.get(i).getText();
			String[] rupees_only=value.split(" ");
			String rs=rupees_only[1];
			int price_value=Integer.parseInt(rs);
			String list_of_filtered_names=brand_names_filter_result.get(i).getText();
			if(random_num==1)
			{
				Assert.assertTrue(price_value <= rupees && list_of_filtered_names.contains(brand_names));
			}
			else
			{
				Assert.assertTrue(price_value >= rupees && list_of_filtered_names.contains(brand_names));
			}
		     
			//Assert.assertTrue(price.get(i)<rupees);
		}
		
//		for(int i=0;i<brand_names_filter_result.size();i++)
//		{
//			String list_of_filtered_names=brand_names_filter_result.get(i).getText();
//			Assert.assertTrue(list_of_filtered_names.contains(brand_names));
//			//Assert.assertTrue(price.get(i)<rupees);
//		}
		
		
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
