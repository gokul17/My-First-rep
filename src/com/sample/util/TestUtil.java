package com.sample.util;

import com.sample.util.Xls_Reader;

public class TestUtil {
	
	
	public static boolean isSuiteRunnable(Xls_Reader xls,String Suitename)
	{
		boolean isexecutable = false;
		int rownumber=xls.getRowCount("Suite");
		for(int rownum=2;rownum<=rownumber;rownum++)
		{
			String Suite=xls.getCellData("Suite", "TSID", rownum);
			if(Suite.equalsIgnoreCase(Suitename))
			{
				String runmode=xls.getCellData("Suite", "Runmode", rownum);
				if(runmode.equalsIgnoreCase("Y"))
				{
					isexecutable=true;
				}
				else
				{
					isexecutable=false;
				}
			}
		}
		return isexecutable;
		
	}
	public static boolean  isTestCaseRunnable(Xls_Reader xls,String testcase)
	{
		boolean isexecutable = false;
		for(int rownum=2;rownum<=xls.getRowCount("Testcase");rownum++)
		{
			if(testcase.equalsIgnoreCase(xls.getCellData("Testcase", "TSID", rownum)))
					{
				           if(xls.getCellData("Testcase", "Runmode", rownum).equalsIgnoreCase("Y"))
				           {
				        	   isexecutable=true;
				           }
				           else
				           {
				        	   isexecutable=false;
				           }
					}
		}
		return isexecutable;
	}
	public static Object[][] getdata(Xls_Reader xls,String sheetname)
	{
		if(!xls.isSheetExist(sheetname))
		{
			return new Object[1][0];
		}
		else
		{
			int totalrows=xls.getRowCount(sheetname);
		    int totalcolumns=xls.getColumnCount(sheetname);
		    Object[][] data=new Object[totalrows-1][totalcolumns-3];
			for(int rownum=2;rownum<=totalrows;rownum++)
			{
				for(int colnum=0;colnum<totalcolumns-3;colnum++)
				{
					data[rownum-2][colnum]=xls.getCellData(sheetname, colnum, rownum);
					
				}
			}
			return data;
		}
	}
	public static String[] getdata_for_runmode(Xls_Reader xls,String sheetname)
    {
    	String runmode[]=null;
    	if(!xls.isSheetExist(sheetname))
    	{
    		xls=null;
			sheetname=null;
			runmode=new String[1];
			runmode[0]="Y";
    	}
    	else
    	{
    		int rows=xls.getRowCount(sheetname);
			runmode=new String[rows-1];
			for(int i=2;i<=xls.getRowCount(sheetname);i++)
			{
				runmode[i-2]=xls.getCellData(sheetname, "Runmode", i);
			}
    		
    	}
    	return runmode;
    	
    }
	public static void reportDataSetResult(Xls_Reader xls,String testcase,int Rownum,String Result)
	{
		xls.setCellData(testcase, "Results", Rownum, Result);
	}
 

}