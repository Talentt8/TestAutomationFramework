package eobf;

import org.testng.annotations.Test;

import AutomationFramework.BaseClass;

public class Application extends BaseClass{	
		
	@Test
	public void individual() throws Exception{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		int firstRow = 2;
		red.sheet = red.workbook.getSheet("TestData");
		int numRows = red.sheet.getLastRowNum();
		int runTo = firstRow + numRows;
		System.out.println("Rows : " + numRows);
		for (int i = firstRow; i < runTo ; i++){
			currentRow = i; 
			if (red.getCellData("TestData", "ExecutionFlag", i).equalsIgnoreCase("yes") && red.getCellData("TestData", "TestCaseId", i).equalsIgnoreCase(Thread.currentThread().getStackTrace()[1].getMethodName())){				
				apputils.EobfInternet.application();
			}
		}
	}	
	
	@Test
	public void soleTraderFarmer() throws Exception{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		int firstRow = 2;
		red.sheet = red.workbook.getSheet("TestData");
		int numRows = red.sheet.getLastRowNum();
		int runTo = firstRow + numRows;
		System.out.println("Rows : " + numRows);
		for (int i = firstRow; i < runTo ; i++){
			currentRow = i; 
			if (red.getCellData("TestData", "ExecutionFlag", i).equalsIgnoreCase("yes") && red.getCellData("TestData", "TestCaseId", i).equalsIgnoreCase(Thread.currentThread().getStackTrace()[1].getMethodName())){				
				apputils.EobfInternet.application();
			}
		}
	}
	
	@Test
	public void soleTraderSoleProprietor() throws Exception{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		int firstRow = 2;
		red.sheet = red.workbook.getSheet("TestData");
		int numRows = red.sheet.getLastRowNum();
		int runTo = firstRow + numRows;
		System.out.println("Rows : " + numRows);
		for (int i = firstRow; i < runTo ; i++){
			currentRow = i; 
			if (red.getCellData("TestData", "ExecutionFlag", i).equalsIgnoreCase("yes") && red.getCellData("TestData", "TestCaseId", i).equalsIgnoreCase(Thread.currentThread().getStackTrace()[1].getMethodName())){				
				apputils.EobfInternet.application();
			}
		}
	}
}