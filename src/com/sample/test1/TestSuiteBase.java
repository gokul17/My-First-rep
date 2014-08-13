package com.sample.test1;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.sample.base.TestBase;
import com.sample.util.TestUtil;

public class TestSuiteBase extends TestBase{
	
	@BeforeSuite
	public void checksuiteskip() throws IOException
	{
		initialize();
		App_Logs.debug("Checking Test1 suite for runnable");
		if(!TestUtil.isSuiteRunnable(suite, "Test_1"))
		{
			App_Logs.debug("Test 1 is skipped");
			throw new SkipException("Test1 is skipped");
		}
		else
		{
			openbrowser();
		}
		
	}
	
	


}
