package com.sample.test2;

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
		App_Logs.debug("Checking Test2 suite for runnable");
		if(!TestUtil.isSuiteRunnable(suite, "Test_2"))
		{
			App_Logs.debug("Test 2 is skipped");
			throw new SkipException("Test2 is skipped");
		}
	}

}
