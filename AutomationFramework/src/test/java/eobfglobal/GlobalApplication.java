package eobfglobal;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import AutomationFramework.BaseClass;

public class GlobalApplication extends BaseClass{	
		
	static int rowIndex = 2;
	@Test	
	public void application() throws Exception{
		//apputils.EobfInternet.application();
		
		//Login
		IWanna.clearAndType("tbUsename", red.getCellData("Credentials", "Username", rowIndex));
		IWanna.clearAndType("tbPassword", red.getCellData("Credentials", "Password", rowIndex));
		IWanna.click("btnSubmit");
		
		
		//Search application
		driver.switchTo().frame("main");
		IWanna.waitForElement("tbAppRefNumber", 10);
		IWanna.clearAndType("tbAppRefNumber", red.getCellData("TestData", "ApplicationNumber", rowIndex));
		IWanna.click("btnSearchApp");
		
		//Application Enquiry
		driver.switchTo().defaultContent();
		driver.switchTo().frame("navigation");
		IWanna.click("linkApplicationEnquiry");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("main");
		IWanna.click("linkViewPodium");
		red.setCellData("TestData", "ApplicationNum", rowIndex, "Pass");		
	}		
}