package eobf;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import AutomationFramework.BaseClass;
import AutomationFramework.Executor;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TC_1 extends BaseClass{	
	
	public static ExtentReports reports;
	public static ExtentTest testInfo;
	public static ExtentHtmlReporter htmlReporter;
	
	@BeforeTest
	public void setup(){
		htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir") + "/AutomationReport.html"));
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
		reports = new ExtentReports();
		reports.setSystemInfo("Environment", "UAT");
		reports.setSystemInfo("Application Name", "EOBF Internet");
		reports.setSystemInfo("Application Url", "eu.absa.co.za/vehiclefin/VAFOnline.do");
		reports.attachReporter(htmlReporter);		
	}
	
	@BeforeMethod
	public void register(Method method){
		String testName = method.getName();		
		testInfo = reports.createTest(testName);
	}
	
	@AfterMethod
	public void captureStatus(ITestResult result) throws IOException{
		if (result.getStatus() == ITestResult.SUCCESS){
			String screenShotPath = Executor.capture();
			testInfo.log(Status.PASS, "The test method : " + result.getMethod().getMethodName() + " passed");
			testInfo.log(Status.INFO, "Screenshot : " + testInfo.addScreenCaptureFromPath(screenShotPath));
		}
		else if (result.getStatus() == ITestResult.FAILURE){
			testInfo.log(Status.FAIL, "Test failure : " + result.getThrowable());
		}
		else if (result.getStatus() == ITestResult.SKIP){
			testInfo.log(Status.SKIP, "The test method : " + result.getMethod().getMethodName() + " skipped");
		}	
	}
	
	@AfterTest
	public void cleanUp(){
		reports.flush();
	}
	
	@Test	
	public void eobfApplication() throws Exception{	
		IWanna.click("linkTermsAndConditions");
		IWanna.click("btnDone");        
		IWanna.click("cbAcceptTerms");
		IWanna.click("btnContinue");
		IWanna.type("tbVendorNumber", red.getCellData("TestData", "VendorNumber", 2));
		IWanna.selectFromDropdown("ddClientType", "Individual");
		Assert.assertTrue(driver.findElement(By.xpath(pro.getProperty("aboutYouHeader"))).isDisplayed());
		testInfo.log(Status.INFO, "Navigation to About You");
		IWanna.click("btnProcessContinue");
		IWanna.handleOkAlert();
	}
}