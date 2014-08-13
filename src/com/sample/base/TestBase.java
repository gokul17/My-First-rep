package com.sample.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sample.util.Xls_Reader;

public class TestBase {
	public static Logger App_Logs=null;
	public static Properties config=null;
	public static Properties OR=null;
	public static Xls_Reader suite=null;
	public static Xls_Reader test_1_suite=null;
	public static Xls_Reader test_2_suite=null;
	public static boolean isinitialised=false;
	public static boolean iswaitobjectinitialized=false;
	public static WebDriver web_driver;
	public static EventFiringWebDriver driver;
	public static WebDriverWait wait;
	
	public void initialize() throws IOException
	{
		if(!isinitialised)
		{
			//Log
			App_Logs=Logger.getLogger("devpinoyLogger");
			
			//Config
			App_Logs.debug("Loading config file");
			config=new Properties();
			FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"//src//com//sample//config//config.Properties");
			config.load(ip);
			config.getProperty("testsite");
			App_Logs.debug("Successfully loaded config file");
			
			//OR
			App_Logs.debug("Loading or properties");
			OR=new Properties();
			ip=new FileInputStream(System.getProperty("user.dir")+"//src//com//sample//config//OR.Properties");
			OR.load(ip);
			OR.getProperty("linkpath");
			App_Logs.debug("Successfully loaded OR properties file");
			
			//xls
			App_Logs.debug("Loading xls file");
			suite = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\sample\\xls\\Suite.xlsx");
			test_1_suite = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\sample\\xls\\Test_1.xlsx");
			test_2_suite = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\sample\\xls\\Test_2.xlsx");
			App_Logs.debug("Successfully Loaded xls file");
			isinitialised=true;
		}
		
		
	}
	
	public void openbrowser()
	{
		if(config.getProperty("browsername").equalsIgnoreCase("Mozilla"))
		{
			web_driver=new FirefoxDriver();
			driver=new EventFiringWebDriver(web_driver);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		}
		else if(config.getProperty("browsername").equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe");
			web_driver=new InternetExplorerDriver();
			driver=new EventFiringWebDriver(web_driver);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		else if(config.getProperty("browsername").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
			web_driver =new ChromeDriver();
			driver=new EventFiringWebDriver(web_driver);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
	}
	
	public void closebrowser()
	{
		driver.close();
		driver.quit();
	}
	
	
	public WebElement getobject(String xpathkey)
	{
		WebElement Element=driver.findElement(By.xpath(OR.getProperty(xpathkey)));
		return Element;	
	}
	
	public List<WebElement> getobjects(String xpathkey)
	{
		List<WebElement> Element=driver.findElements(By.xpath(OR.getProperty(xpathkey)));
		return Element;
	}
	
	public void wait_visible(String xpathkey)
	{
		if(!iswaitobjectinitialized)
		{
			wait=new WebDriverWait(driver,20);
			iswaitobjectinitialized=true;
		}
		wait.until(ExpectedConditions.visibilityOf(getobject(xpathkey)));
    }
	public void wait_invisible(String xpathkey)
	{
		if(!iswaitobjectinitialized)
		{
			wait=new WebDriverWait(driver,20);
			iswaitobjectinitialized=true;
		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(OR.getProperty(xpathkey))));
	}

	

}
