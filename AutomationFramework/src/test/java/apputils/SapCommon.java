package apputils;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
		String pass = red.getCellData("Credentials", "Password", rowIndex);
		/*byte[] encodedPwdBytes = Base64.encodeBase64(pass.getBytes());
		byte[] decodedPwdBytes = Base64.decodeBase64(encodedPwdBytes);
		String decodedPwd= new String(decodedPwdBytes);
		System.out.println(decodedPwd);*/
		IWanna.clearAndType("tbPassword", pass);
		IWanna.click("btnLogon");
		Thread.sleep(1000);
		if (IWanna.isElementPresent("cbCancellExistingLogons")){
			IWanna.click("cbCancellExistingLogons");
		}
		
		Thread.sleep(1000);
		IWanna.wait(3);	
		if (IWanna.isElementPresent("btnSapLogonContinue")){
			IWanna.click("btnSapLogonContinue");
		}
		
		if (IWanna.isElementPresent("linkZSupport")){
			IWanna.click("linkZSupport");
		}
		//driver.navigate().refresh();
		
		/*
		IWanna.wait(3);	
		if (IWanna.isElementPresent("btnSapLogonContinue")){
			IWanna.click("btnSapLogonContinue");
		}
		/*Assert.assertTrue(driver.findElement(By.xpath(pro.getProperty("aboutYouHeader"))).isDisplayed());*/						
	}
	
	public static void navigateToFinancingQuotations(){
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='CRMApplicationFrame']")));
		driver.switchTo().frame("WorkAreaFrame1");
		IWanna.click("linkLeasingDocuments");
		IWanna.click("linkFinancingQuotations");
		//IWanna.waitForElement("tbBrNumberSearch", 10);
	}
	
	public static void navigateToFinancingContracts() throws InterruptedException{
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='CRMApplicationFrame']")));
		driver.switchTo().frame("WorkAreaFrame1");
		IWanna.click("linkLeasingDocuments");
		IWanna.click("linkFinancingContracts");
		Thread.sleep(1000);
		IWanna.waitForElement("tbContractNumberSearch", 10);
	}
	
	public static void fixErrors() throws InterruptedException{
		IWanna.type("tbBrNumberSearch", red.getCellData("TestData", "SapBrSearch", rowIndex));
		IWanna.click("btnSearch");
		IWanna.click("linkLeasingResult");
		IWanna.waitForElement("btnPartners", 5);		
		
		IWanna.click("btnPartners");
		IWanna.waitForElement("btnEditPartners", 5);
		IWanna.click("btnEditPartners");
		if(!IWanna.waitForElement("btnInsert", 5)){
			IWanna.click("btnEditPartners");
			IWanna.waitForElement("btnInsert", 5);
		}
		IWanna.clearAndType("tbAvafMarketer", red.getCellData("TestData", "AvafMarketer", rowIndex));
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
		IWanna.waitForElement("imgSuccess", 10);
	}
	
	private static void clickViewDetails(){
		IWanna.click("btnViewDetails");
		IWanna.waitForElement("btnRefer", 3);
		if (!IWanna .isElementPresent("btnRefer")){
			IWanna.click("btnViewDetails");
			IWanna.waitForElement("btnRefer", 3);
		}
	}
	
	public static void scoreApplication() throws InterruptedException{
		IWanna.click("linkScoring");
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='CRMApplicationFrame']")));
		driver.switchTo().frame("WorkAreaFrame2");
		System.out.println("Switching to myActionIframe");
		driver.switchTo().frame("myActionIframe");
		System.out.println("Switching to ITSFRAME1");
		driver.switchTo().frame("ITSFRAME1");
		IWanna.clearAndType("tbQuoteNumber", red.getCellData("TestData", "SapBrSearch", rowIndex));
		IWanna.clearAndType("tbFiscalYear", red.getCellData("TestData", "FiscalYear", rowIndex));
		IWanna.click("btnScoringContinue");
		//String BRNumber = IWanna.getElementText("txtBRNumber");
		//red.setCellData("TestData", "BRNumber", rowIndex, BRNumber);
		IWanna.click("btnFirstProduct");
		clickViewDetails();
		IWanna.click("btnRefer");
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("URLSPW-0");
		IWanna.click("btnScoreUpdatedContinue");
		
		//navigate to scoring
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='CRMApplicationFrame']")));
		driver.switchTo().frame("WorkAreaFrame1");
		IWanna.waitForElement("linkScoring", 10);
		IWanna.click("linkScoring");
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='CRMApplicationFrame']")));
		driver.switchTo().frame("WorkAreaFrame2");
		System.out.println("Switching to myActionIframe");
		driver.switchTo().frame("myActionIframe");
		System.out.println("Switching to ITSFRAME1");
		driver.switchTo().frame("ITSFRAME1");
		IWanna.clearAndType("tbQuoteNumber", red.getCellData("TestData", "SapBrSearch", rowIndex));
		IWanna.clearAndType("tbFiscalYear", red.getCellData("TestData", "FiscalYear", rowIndex));
		IWanna.click("btnScoringContinue");
		//String BRNumber = IWanna.getElementText("txtBRNumber");
		//red.setCellData("TestData", "BRNumber", rowIndex, BRNumber);
		IWanna.click("btnFirstProduct");
		clickViewDetails();		
		IWanna.type("tbOverrideCode", "P");
		IWanna.click("btnApprove");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("URLSPW-0");
		IWanna.click("goodExistingABSA");
		IWanna.click("btnChoose");		
		//IWanna.click("rbEmail");
		IWanna.click("btnDealerCommChoose");
		IWanna.waitForElement("txtScoreUpdated", 90);
		IWanna.click("btnScoreUpdatedContinue");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='CRMApplicationFrame']")));
		driver.switchTo().frame("WorkAreaFrame1");
		IWanna.click("linkRecentItem1");
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
	}
	
	protected static void actions() throws InterruptedException, IOException{
		//Actions
		String winHandleBefore = driver.getWindowHandle();
		switchToWorkFrame1();
		//IWanna.click("linkRecentItem1");
		IWanna.click("btnActions");
		//Validations Performed Action
		IWanna.click("btnEditList");
		IWanna.waitForElement("btnScheduleNewActions", 2);
		if (!IWanna.isElementPresent("btnScheduleNewActions")){
			IWanna.click("btnEditList");
			IWanna.waitForElement("btnScheduleNewActions", 5);
		}
		IWanna.click("btnScheduleNewActions");
		Thread.sleep(1000);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		    //System.out.println(driver.getTitle());
		}
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='WorkAreaFrame1popup']")));
		IWanna.waitForElement("btnValidationPerformed", 5);
		IWanna.click("btnValidationPerformed");
		Thread.sleep(1000);
		IWanna.click("btnSchedule");
		driver.switchTo().window(winHandleBefore);
		switchToDefaultContent();
		switchToWorkFrame1();
		Thread.sleep(3000);
		IWanna.click("btnSave");
		IWanna.waitForElement("imgSuccess", 20);
		//End of Validations Performed Action
		
		//Request for Contract Action		
		IWanna.click("btnEditList");		
		IWanna.waitForElement("btnScheduleNewActions", 2);
		if (!IWanna.isElementPresent("btnScheduleNewActions")){
			IWanna.click("btnEditList");
			IWanna.waitForElement("btnScheduleNewActions", 5);
		}
		IWanna.click("btnScheduleNewActions");
		Thread.sleep(1000);	
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='WorkAreaFrame1popup']")));
		IWanna.waitForElement("btnRequestForContract", 5);
		IWanna.click("btnRequestForContract");
		Thread.sleep(1000);
		IWanna.click("btnSchedule");
		Thread.sleep(3000);
		driver.switchTo().window(winHandleBefore);
		switchToDefaultContent();
		switchToWorkFrame1();
		IWanna.waitForElement("imgWarning", 60);
		IWanna.click("btnSave");
		IWanna.waitForElement("imgSuccess", 60);		
		Thread.sleep(1000);
		switchToDefaultContent();
		switchToWorkFrame1();
		Thread.sleep(3000);
		IWanna.click("btnSave");
		IWanna.waitForElement("imgSuccess", 20);
		//End of Request for Contract Action
		
		//Generate COA Action		
		IWanna.click("btnEditList");		
		IWanna.waitForElement("btnScheduleNewActions", 2);
		if (!IWanna.isElementPresent("btnScheduleNewActions")){
			IWanna.click("btnEditList");
			IWanna.waitForElement("btnScheduleNewActions", 5);
		}
		IWanna.click("btnScheduleNewActions");
		Thread.sleep(1000);	
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='WorkAreaFrame1popup']")));
		IWanna.waitForElement("btnGenerateCOA", 5);
		IWanna.click("btnGenerateCOA");
		Thread.sleep(1000);
		IWanna.click("btnSchedule");
		Thread.sleep(3000);
		driver.switchTo().window(winHandleBefore);
		switchToDefaultContent();
		switchToWorkFrame1();
		//IWanna.waitForElement("imgWarning", 60);
		IWanna.click("btnSave");
		IWanna.waitForElement("imgSuccess", 60);
		IWanna.click("btnActions");
		Thread.sleep(1000);
		screenShotPath = Executor.capture();
		if (IWanna.isElementPresent("imgSuccess")){
			testInfo.log(Status.PASS, "Actions added successfully" + testInfo.addScreenCaptureFromPath(screenShotPath));
		}
		else{
			testInfo.log(Status.FAIL, "Actions added successfully" + testInfo.addScreenCaptureFromPath(screenShotPath));
		}
		String extRef = IWanna.getElementText("txtExternalRef");
		red.setCellData("TestData", "ExternalReference", 2, extRef);
		//End of Generate COA Action
	}
	
	public static void searchContract(String contractNumber) throws IOException{
		//Search Contract 
		IWanna.clearAndType("tbContractNumberSearch", contractNumber);
		IWanna.click("btnSearch");
		IWanna.waitForElement("linkLeasingResult", 10);
		screenShotPath = Executor.capture();
		if (IWanna.isElementPresent("linkLeasingResult")){
			testInfo.log(Status.PASS, "Contract found "+testInfo.addScreenCaptureFromPath(screenShotPath));
		}
		else{
			testInfo.log(Status.FAIL, "Contract not found "+testInfo.addScreenCaptureFromPath(screenShotPath));
		}
	}
	
	public static void changeProcess() throws InterruptedException, IOException{
		//Change process
		IWanna.click("linkLeasingResult");
		IWanna.waitForElement("btnChangeProcesses", 10);
		IWanna.click("btnChangeProcesses");
		IWanna.waitForElement("btnItemChangeProcess", 10);
		IWanna.click("btnItemChangeProcess");
		IWanna.waitForElement("tbChangeProcess", 10);
		IWanna.type("tbChangeProcess", "ZCON");
		Thread.sleep(1000);
		IWanna.type("tbChangeProcess", Keys.ENTER);
		Thread.sleep(1000);
		IWanna.click("tbEffectiveDate");
		IWanna.type("tbEffectiveDate", "05.04.2018");
		Thread.sleep(2000);
		IWanna.click("btnExecute");
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='CRMApplicationFrame']")));
		driver.switchTo().frame("WorkAreaFrame1");
		IWanna.click("spanBack");
		IWanna.click("btnSave");
		IWanna.waitForElement("imgSuccess", 30);
		screenShotPath = Executor.capture();
		if (IWanna.isElementPresent("imgSuccess")){
			testInfo.log(Status.PASS, "Change process successful "+testInfo.addScreenCaptureFromPath(screenShotPath));
		}
		else{
			testInfo.log(Status.FAIL, "Change process was not successful "+testInfo.addScreenCaptureFromPath(screenShotPath));
		}
	}
	
	public static void scheduleCOA() throws InterruptedException, IOException{
		//
		String extRef = IWanna.getElementText("txtExternalRef");
		driver.switchTo().defaultContent();
		SapCRM.navigateToFinancingQuotations();
		IWanna.type("tbExtReferenceSearch", extRef);
		Thread.sleep(1000);
		IWanna.click("btnSearch");
		IWanna.waitForElement("linkQuotationID", 10);
		screenShotPath = Executor.capture();
		if (IWanna.isElementPresent("linkQuotationID")){
			IWanna.click("linkQuotationID");					
		}
		else{
			testInfo.log(Status.FAIL, "Quotation not found "+testInfo.addScreenCaptureFromPath(screenShotPath));
		}
		
		//Schedule Action
		String winHandleBefore = driver.getWindowHandle();
		IWanna.click("btnActions");
		IWanna.click("btnEditList");
		IWanna.waitForElement("btnScheduleNewActions", 2);
		if (!IWanna.isElementPresent("btnScheduleNewActions")){
			IWanna.click("btnEditList");
			IWanna.waitForElement("btnScheduleNewActions", 5);
		}
		IWanna.click("btnScheduleNewActions");
		Thread.sleep(1000);	
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='WorkAreaFrame1popup']")));
		IWanna.waitForElement("btnGenerateCOA", 5);
		IWanna.click("btnGenerateCOA");
		Thread.sleep(1000);
		IWanna.click("btnSchedule");
		Thread.sleep(3000);
		driver.switchTo().window(winHandleBefore);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='CRMApplicationFrame']")));
		driver.switchTo().frame("WorkAreaFrame1");	
		//driver.close();
		//driver.quit();
		//IWanna.waitForElement("imgWarning", 60);
		IWanna.click("btnSave");
		IWanna.waitForElement("imgSuccess", 60);
		IWanna.click("btnActions");
		Thread.sleep(1000);
		screenShotPath = Executor.capture();
		if (IWanna.isElementPresent("imgSuccess")){
			testInfo.log(Status.PASS, "Actions added successfully" + testInfo.addScreenCaptureFromPath(screenShotPath));
		}
		else{
			testInfo.log(Status.FAIL, "Actions added successfully" + testInfo.addScreenCaptureFromPath(screenShotPath));
		}
		//String extRef = IWanna.getElementText("txtExternalRef");
		//red.setCellData("TestData", "ExternalReference", 2, extRef);
		//End of Generate COA Action
	}
	
	public static boolean isContractOpen(){
		IWanna.click("linkLeasingResult");
		IWanna.waitForElement("labelStatus", 10);
		if (IWanna.isElementPresent("txtStatusOpen")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static void reverseContract() throws InterruptedException, IOException{
		//Reverse contract
		IWanna.click("btnActions");
		IWanna.waitForElement("btnEditList", 5);
		String winHandleBefore = driver.getWindowHandle();
		IWanna.click("btnEditList");
		IWanna.waitForElement("btnScheduleNewActions", 10);
		if (!IWanna.isElementPresent("btnScheduleNewActions")){
			IWanna.click("btnEditList");
			IWanna.waitForElement("btnScheduleNewActions", 10);
		}
		IWanna.click("btnScheduleNewActions");
		Thread.sleep(1000);	
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		try{
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='WorkAreaFrame1popup']")));
			
			IWanna.waitForElement("btnReverseContract", 5);
			
			IWanna.click("btnReverseContract");
			Thread.sleep(1000);
			IWanna.click("btnSchedule");
			Thread.sleep(3000);
			driver.switchTo().window(winHandleBefore);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='CRMApplicationFrame']")));
			driver.switchTo().frame("WorkAreaFrame1");
			IWanna.waitForElement("btnExecute", 5);
			IWanna.click("btnExecute");
			IWanna.waitForElement("imgWarning", 600);
			IWanna.click("btnBack");
			Thread.sleep(1000);
			IWanna.waitForElement("btnActions", 10);
			IWanna.click("btnActions");
			Thread.sleep(1000);
			IWanna.click("btnSave");
			Thread.sleep(1000);
			IWanna.waitForElement("imgSuccess", 30);
			String contractNumber = IWanna.getElementText("txtContractNumber").substring(0, 18);

			screenShotPath = Executor.capture();
			if (IWanna.isElementPresent("txtStatusCompleted")){
				testInfo.log(Status.PASS, contractNumber + " reversal completed "+testInfo.addScreenCaptureFromPath(screenShotPath));
			}
			else{
				testInfo.log(Status.FAIL, contractNumber + " reversal failed "+testInfo.addScreenCaptureFromPath(screenShotPath));
			}
		}
		catch(Exception e){
			IWanna.click("btnActions");
			Thread.sleep(1000);
			log.debug("Unable to schedule action for contract");
			log.debug(e.getMessage());
		}
	}
	
	private static void switchToWorkFrame1(){
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='CRMApplicationFrame']")));
		driver.switchTo().frame("WorkAreaFrame1");
	}
	
	private static void switchToDefaultContent(){
		driver.switchTo().defaultContent();
	}

}
