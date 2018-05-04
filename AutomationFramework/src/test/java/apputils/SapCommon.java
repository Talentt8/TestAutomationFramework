package apputils;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import AutomationFramework.BaseClass;
import AutomationFramework.Executor;

public class SapCommon extends BaseClass{
	
	static int rowIndex = 2;
	static String screenShotPath;
	
	/**
	 * navigateToAboutYou
	 * This method navigates to the About You tab on the online application. 
	 * <p>
	 *
	 * @param  		
	 * @return     
	 * @see
	 * */
	public static void login() throws Exception{	
		testInfo.log(Status.INFO, "Login to SAP CRM");
		IWanna.clearAndType("tbUsername", red.getCellData("Credentials", "Username", rowIndex));
		IWanna.clearAndType("tbPassword", red.getCellData("Credentials", "Password", rowIndex));
		IWanna.click("btnLogon");
		IWanna.wait(3);	
		if (IWanna.isElementPresent("btnContinue")){
			IWanna.click("btnContinue");
		}
		/*Assert.assertTrue(driver.findElement(By.xpath(pro.getProperty("aboutYouHeader"))).isDisplayed());*/						
	}
	
	public static void navigateToFinancingQuotations(){
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='CRMApplicationFrame']")));
		driver.switchTo().frame("WorkAreaFrame1");
		IWanna.click("linkLeasingDocuments");
		IWanna.click("linkFinancingQuotations");
	}
	
	public static void fixErrors(){
		IWanna.type("tbBrNumberSearch", "100059548");
		IWanna.click("btnSearch");
		IWanna.click("linkLeasingResult");
		IWanna.waitForElement("btnPartners", 5);		
		/*
		IWanna.click("btnPartners");
		IWanna.click("btnEditPartners");
		IWanna.waitForElement("tbAvafMarketer", 5);
		IWanna.clearAndType("tbAvafMarketer", "1000008797");
		driver.findElement(By.xpath("//*[contains(text(),'AVAF Marketer')]//following::input[2]")).sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		IWanna.click("btnPartners");
		Thread.sleep(2000);		
		
		IWanna.click("btnEnatisInformation");
		Thread.sleep(2000);
		IWanna.click("btnEditEnatis");
		IWanna.waitForElement("ddLicenceVerification", 5);
		IWanna.click("ddLicenceVerification");
		IWanna.click("linkPass");
		IWanna.click("btnEnatisInformation");
		Thread.sleep(2000);
		IWanna.click("btnSave");
		IWanna.waitForElement("imgSuccess", 10);*/
	}
	
	public static void scoreApplication() throws InterruptedException{
		IWanna.click("linkScoring");
		Thread.sleep(10000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='CRMApplicationFrame']")));
		driver.switchTo().frame("WorkAreaFrame2");
		IWanna.clearAndType("tbQuoteNumber", "100059548");
		//System.out.println("iFrames : " + driver.findElements(By.tagName("frame")).size());
		//driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='WorkAreaFrame2']")));
		//driver.switchTo().frame("WorkAreaFrame2");
		//IWanna.waitForElement("tbQuoteNumber", 25);
		//IWanna.click("btnBack");
		/*driver.switchTo().activeElement();
		System.out.println("iFrames : " + driver.findElements(By.tagName("iframe")).size());
		IWanna.clearAndType("tbQuoteNumber", "100059548");
		IWanna.clearAndType("tbFiscalYear", "2018");
		IWanna.click("btnScoringContinue");
		IWanna.waitForElement("btnFinancedProduct", 10);*/
	}

}
