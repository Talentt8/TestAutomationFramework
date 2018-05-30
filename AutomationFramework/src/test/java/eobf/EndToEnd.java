package eobf;

import org.testng.annotations.Test;

import AutomationFramework.BaseClass;

public class EndToEnd extends BaseClass{	
		
	@Test
	public void individual() throws Exception{
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());		
		int firstRow = 2;
		red.sheet = red.workbook.getSheet("TestData");
		int numRows = red.sheet.getLastRowNum();
		int runTo = firstRow + numRows;
		System.out.println("Rows : " + numRows);
		for (int i = firstRow; i < runTo ; i++){
			if (red.getCellData("TestData", "ExecutionFlag", i).equalsIgnoreCase("yes") && red.getCellData("TestData", "TestCaseId", i).equalsIgnoreCase(Thread.currentThread().getStackTrace()[1].getMethodName())){				
				//Step 1: Online application on EOBF
				apputils.EobfInternet.application();
				/*
				//Step 2: GEt BR Number form EOBF Global
				Thread.sleep(5000);
				driver.get("https://eu.absa.co.za/vehiclefin/GlobalLogin.do");
				apputils.EobfGlobal.getBRNumber();
				driver.get("https://qcvdb.absa.co.za:8001/sap/bc/bsp/sap/crm_ui_start/default.htm?sap-sessioncmd=open");
				//Step 3: Clear errors on SAP and approve application
				apputils.SapCRM.approveApplication();
				//Step 4: Add actions
				apputils.SapCRM.addActions();
				driver.get("https://eu.absa.co.za/vehiclefin/GlobalLogin.do");
				//Step 5: Get Podium on EOBF Global
				apputils.EobfGlobal.getPodium();
				*/
			}
		}
	}	
}