package eobf;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import AutomationFramework.BaseClass;

public class TestDownload extends BaseClass{	
		
	@Test	
	public void application() throws Exception{
		/*
		//Step 1: Online application on EOBF
		apputils.EobfInternet.application();
		System.out.println("App ref: " + red.getCellData("TestData", "ApplicationNumber", 2));
		//Step 2: GEt BR Number form EOBF Global
		Thread.sleep(5000);
		driver.get("https://eu.absa.co.za/vehiclefin/GlobalLogin.do");
		apputils.EobfGlobal.getBRNumber();
		driver.get("https://qcvdb.absa.co.za:8001/sap/bc/bsp/sap/crm_ui_start/default.htm?sap-sessioncmd=open");
		//Step 3: Clear errors on SAP and approve application
		apputils.SapCRM.approveApplication();*/		
		
		//download properties
		String downloadFilepath = "/path/to/download";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		WebDriver driver = new ChromeDriver(cap);
		
		//end of download properties
		
		
		driver.get("https://eu.absa.co.za/vehiclefin/GlobalLogin.do");
		//Step 4: Get Podium on EOBF Global
		apputils.EobfGlobal.getPodium();
	}		
}