package sap;

import org.testng.annotations.Test;
import AutomationFramework.BaseClass;
import apputils.SapCRM;


public class Sap extends BaseClass{	
		
	@Test
	public void individual() throws Exception{
		
		String screenShotPath;
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());		
		int firstRow = 2;
		red.sheet = red.workbook.getSheet("TestData");
		int numRows = red.sheet.getLastRowNum();
		int runTo = firstRow + numRows;
		System.out.println("Rows : " + numRows);
		driver.get("https://pcvapp2.absa.co.za:8210/sap/bc/bsp/sap/crm_ui_start/default.htm?sap-client=600&sap-sessioncmd=open");
		//Step 3: Clear errors on SAP and approve application
		SapCRM.login();
		String[] contractNumbers = {"503817051","503817052","503817052"};
		for (int i = firstRow; i < runTo ; i++){
			if (red.getCellData("TestData", "ExecutionFlag", i).equalsIgnoreCase("yes") && red.getCellData("TestData", "TestCaseId", i).equalsIgnoreCase(Thread.currentThread().getStackTrace()[1].getMethodName())){												
				int c=0;
				for (String cn : contractNumbers){
					System.out.println("Iteration : " + cn);
					driver.switchTo().defaultContent();
					SapCRM.navigateToFinancingContracts();
					SapCRM.searchContract(cn);
					SapCRM.changeProcess();
					SapCRM.scheduleCOA();
				}
				
			}
		}	
	}	
}