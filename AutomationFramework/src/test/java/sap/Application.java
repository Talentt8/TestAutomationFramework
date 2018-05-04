package sap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import AutomationFramework.BaseClass;

public class Application extends BaseClass{	
		
	@Test	
	public void application() throws Exception{
		apputils.SapCRM.approveApplication();		
	}		
}