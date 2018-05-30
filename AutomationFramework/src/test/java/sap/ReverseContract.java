package sap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.testng.annotations.Test;

import AutomationFramework.BaseClass;
import apputils.SapCRM;


public class ReverseContract extends BaseClass{	
		
	@Test
	public void individual() throws Exception{
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());		
		int firstRow = 2;
		red.sheet = red.workbook.getSheet("TestData");
		int numRows = red.sheet.getLastRowNum();
		int runTo = firstRow + numRows;
		//System.out.println("Rows : " + numRows);
		driver.get("https://pcvapp2.absa.co.za:8210/sap/bc/bsp/sap/crm_ui_start/default.htm?sap-client=600&sap-sessioncmd=open");
		//Step 3: Clear errors on SAP and approve application
		SapCRM.login();
		//String[] contractNumbers = {"504732339","504572363","504732792","504732681","504732771","504732296","504732314"};
		ArrayList<String> contractNumbers = createArralist();
		for (int i = firstRow; i < runTo ; i++){
			System.out.println("Executing iteration number : " + i);
			if (red.getCellData("TestData", "ExecutionFlag", i).equalsIgnoreCase("yes") && red.getCellData("TestData", "TestCaseId", i).equalsIgnoreCase(Thread.currentThread().getStackTrace()[1].getMethodName())){												
				for (String cn : contractNumbers){
					System.out.println("Iteration : " + cn);
					driver.switchTo().defaultContent();
					SapCRM.navigateToFinancingContracts();
					SapCRM.searchContract(cn);
					if (SapCRM.isContractOpen()){
						SapCRM.reverseContract();
					}
				}
			}
		}	
	}	
	
	public static ArrayList<String> createArralist() throws FileNotFoundException{
		Scanner s = new Scanner(new File("c://contractnumbers.txt"));
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNext()){
		    list.add(s.next());
		}
		s.close();
		return list;
	}
}