package apputils;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import AutomationFramework.BaseClass;
import AutomationFramework.Executor;

public class EobfCommon extends BaseClass{
	
	//static int currentRow = 2;
	static String screenShotPath;
	//= Executor.capture();
	
	/**
	 * navigateToAboutYou
	 * This method navigates to the About You tab on the online application. 
	 * <p>
	 *
	 * @param  		
	 * @return     
	 * @see
	 * */
	public static void navigateToAboutYou() throws Exception{	
		testInfo.log(Status.INFO, "Navigate to About You page");
		IWanna.waitForElement("linkTermsAndConditions", 10);
		IWanna.click("linkTermsAndConditions");
		IWanna.wait(1);
		IWanna.click("btnDone"); 
		IWanna.click("cbAcceptTerms");
		IWanna.click("btnContinue");
		IWanna.waitForElement("aboutYouHeader", 5);
		screenShotPath = Executor.capture();
		if (IWanna.isElementPresent("aboutYouHeader")){			
			testInfo.log(Status.PASS, "Navigation to About You page was successful. " + testInfo.addScreenCaptureFromPath(screenShotPath));
			driver.switchTo().defaultContent();
		}
		else{
			testInfo.log(Status.FAIL, "Navigation to About You page failed. " + testInfo.addScreenCaptureFromPath(screenShotPath));
		}	
		Assert.assertTrue(driver.findElement(By.xpath(pro.getProperty("aboutYouHeader"))).isDisplayed());						
	}
	
	/**
	 * capturePersonalDetails
	 * This method captures the Personal Details on an online application. 
	 * <p>
	 *
	 * @param  		
	 * @return     
	 * @see
	 * */
	public static void capturePersonalDetails(){
		testInfo.log(Status.INFO, "Capture Personal Details");
		IWanna.selectFromDropdown("ddClientType", red.getCellData("TestData", "ClientType", currentRow));
		String vatIndicator = red.getCellData("TestData", "IndicatorVatVendor", currentRow);		
		if (vatIndicator.equalsIgnoreCase("No")){	
			IWanna.click("cbVatVendorNo");
		}
		else if (vatIndicator.equalsIgnoreCase("Yes")){
			IWanna.click("cbVatVendorYes");
		}
		
		IWanna.type("tbVendorNumber", red.getCellData("TestData", "VATVendorNumber", currentRow));
		String indicatorInsolvent = red.getCellData("TestData", "IndicatorInsolvent", currentRow);	
		if (indicatorInsolvent.equalsIgnoreCase("No")){	
			IWanna.click("rbInsolventNo");
		}
		else if (indicatorInsolvent.equalsIgnoreCase("Yes")){
			IWanna.click("rbInsolventYes");
		}		
		IWanna.selectFromDropdown("ddTitle", red.getCellData("TestData", "Title", currentRow));
		IWanna.type("tbFirstNames", red.getCellData("TestData", "FirstName", currentRow));
		IWanna.type("tbSurname", red.getCellData("TestData", "Surname", currentRow));
		
		String saResident = red.getCellData("TestData", "IndicatorSACitizen", currentRow);	
		if (saResident.equalsIgnoreCase("No")){	
			IWanna.click("cbSAResNo");
			IWanna.type("tbIdNumber", red.getCellData("TestData", "IdNumber", currentRow));
			IWanna.type("tbPassportValidFromDate", red.getCellData("TestData", "PassportValidFrom", currentRow));
			IWanna.type("tbPassportExpiryDate", red.getCellData("TestData", "PassportExpiry", currentRow));
			//capture temporary residence information
			if (red.getCellData("TestData", "TemporaryResident", currentRow).equalsIgnoreCase("Yes")){
				IWanna.click("rbTempResYes");
				IWanna.type("tbPermitNumber", red.getCellData("TestData", "PermitNumber", currentRow));
				IWanna.type("tbPermitExpiryDate", red.getCellData("TestData", "PermitExpiryDate", currentRow));
			}
			else if (red.getCellData("TestData", "PassportExpiry", currentRow).equalsIgnoreCase("No")){
				IWanna.click("rbTempResNo");
			}
		}
		else if (saResident.equalsIgnoreCase("Yes")){
			IWanna.click("cbSAResYes");
			IWanna.type("tbIdNumber", red.getCellData("TestData", "IdNumber", currentRow));
		}			
		//select gender
		if (red.getCellData("TestData", "Gender", currentRow).equalsIgnoreCase("Male")){
			IWanna.click("rbGenderMale");
		}
		else if(red.getCellData("TestData", "Gender", currentRow).equalsIgnoreCase("female")){
			IWanna.click("rbGenderFemale");			
		}
		IWanna.selectFromDropdown("ddRaceIndicator", red.getCellData("TestData", "Race", currentRow));
		IWanna.type("tbDateOfBirth", red.getCellData("TestData", "DOB", currentRow));
		IWanna.selectFromDropdown("ddNationality", red.getCellData("TestData", "Nationality", currentRow));
		IWanna.selectFromDropdown("ddCountryOfOrigin", red.getCellData("TestData", "CountryOfOrigin", currentRow));
		IWanna.selectFromDropdown("ddMaritalStatus", red.getCellData("TestData", "MaritalStatus", currentRow));
		IWanna.selectFromDropdown("ddLanguage", red.getCellData("TestData", "Language", currentRow));
		
		//capture post matric qualification
		if (red.getCellData("TestData", "PostMatricQualification", currentRow).equalsIgnoreCase("Yes")){
			IWanna.click("rbPostMatricQualificationYes");
			IWanna.selectFromDropdown("ddHighestQualification", red.getCellData("TestData", "Qualification", currentRow));
		}
		else if(red.getCellData("TestData", "PostMatricQualification", currentRow).equalsIgnoreCase("No")){
			IWanna.click("rbPostMatricQualificationNo");			
		}
		Assert.assertTrue(IWanna.getElementValue("tbDateOfBirth").equalsIgnoreCase(red.getCellData("TestData", "DOB", currentRow)));
	}
	
	
	/**
	 * captureAdressInformation
	 * This method captures the Adress Information on an online application. 
	 * <p>
	 *
	 * @param  		
	 * @return     
	 * @see
	 * */
	public static void captureAddressInformation(){
		testInfo.log(Status.INFO, "Capture Address Information");
		IWanna.selectFromDropdown("ddStatus", red.getCellData("TestData", "ResStatus", currentRow));
		IWanna.type("tbResidentialLine1", red.getCellData("TestData", "ResLine1", currentRow));
		IWanna.type("tbResidentialLine2", red.getCellData("TestData", "ResLine2", currentRow));
		IWanna.type("tbResidentialSuburb", red.getCellData("TestData", "ResSuburb", currentRow));
		IWanna.click("btnSearchResidentialSuburb");
		IWanna.wait(1);
		IWanna.selectFromDropdown("ddResidentialSuburb", 1);	
		IWanna.type("tbResidentialCity", red.getCellData("TestData", "ResCity", currentRow));
		IWanna.click("btnResidentialCitySearch");
		IWanna.selectFromDropdown("ddResidentialCity", 1);
		IWanna.type("tbYYatThisAddress", red.getCellData("TestData", "PeriodYY", currentRow));
		IWanna.type("tbMMatThisAddress", red.getCellData("TestData", "PeriodMM", currentRow));
		
		String sameAsPostal = red.getCellData("TestData", "SameAsPostal", currentRow);
		if (sameAsPostal.equalsIgnoreCase("Yes")){
			IWanna.click("rbSameAsPostalYes");
		}
		else if(sameAsPostal.equalsIgnoreCase("No")){
			IWanna.type("tbPostalLine1", red.getCellData("TestData", "PostalLine1", currentRow));
			IWanna.type("tbPostalLine2", red.getCellData("TestData", "PostalLine2", currentRow));
			IWanna.type("tbPostalSuburb", red.getCellData("TestData", "PostalSuburb", currentRow));
			IWanna.click("btnSearchPostalSuburb");
			IWanna.selectFromDropdown("ddPostalSuburb", 1);	
			IWanna.type("tbPostalCity", red.getCellData("TestData", "PostalCity", currentRow));
			IWanna.click("btnSearchPostalCity");
			IWanna.selectFromDropdown("ddPostalCity", 1);
		}
	}
	
	public static void captureContactInformation(){
		testInfo.log(Status.INFO, "Capture Contact Information");
		IWanna.type("tbAreaCodeHome", red.getCellData("TestData", "AreaCodeHome", currentRow));
		IWanna.type("tbTelNoHome", red.getCellData("TestData", "TelNumberHome", currentRow));
		IWanna.type("tbAreaCodeWork", red.getCellData("TestData", "AreaCodeWork", currentRow));
		IWanna.type("tbTelNoWork", red.getCellData("TestData", "TelNumberWork", currentRow));
		IWanna.type("tbCellNo", red.getCellData("TestData", "Cell", currentRow));
		IWanna.type("tbEmailAddress", red.getCellData("TestData", "Email", currentRow));
		IWanna.selectFromDropdown("ddMethodOfComm", red.getCellData("TestData", "CommunicationMethod", currentRow));
		
		//verify data capture
		/*try{
			Assert.assertEquals(IWanna.getElementValue("ddMethodOfComm"),red.getCellData("TestData", "CommunicationMethod", currentRow));
		}
		catch(Throwable t){
			System.out.println("Failure message : " + t.getMessage());
			org.testng.Assert.fail("Assert failed");
		}*/
	}
	
	public static void captureNokInformation(){
		testInfo.log(Status.INFO, "Capture Next of KIN Information");
		IWanna.type("tbNokFirstName", red.getCellData("TestData", "NOKFName", currentRow));
		IWanna.type("tbNokSurname", red.getCellData("TestData", "NOKSurname", currentRow));
		IWanna.selectFromDropdown("ddRelationship", red.getCellData("TestData", "NOKRelation", currentRow));
		/*IWanna.type("tbNokAreaCodeHome", red.getCellData("TestData", "AreaCodeHome", currentRow));
		IWanna.type("tbNokTelNoHome", red.getCellData("TestData", "TelNumberHome", currentRow));
		IWanna.type("tbNokAreaCodeWork", red.getCellData("TestData", "AreaCodeWork", currentRow));*/
		IWanna.type("tbNokCellNo", red.getCellData("TestData", "NOKCell", currentRow));
		IWanna.click("btnProcessContinue");
	}
	
	public static void captureYourFinances(){
		testInfo.log(Status.INFO, "Capture Your Finances");
		IWanna.selectFromDropdown("ddOccupationStatus", red.getCellData("TestData", "OccupationStatus", currentRow));
		IWanna.selectFromDropdown("ddEmploymentSector", red.getCellData("TestData", "EmploymentSector", currentRow));
		IWanna.selectFromDropdown("ddOccupationLevel", red.getCellData("TestData", "OccupationLevel", currentRow));
		IWanna.selectFromDropdown("ddOccupation", red.getCellData("TestData", "Occupation", currentRow));
		IWanna.type("tbPresentEmployer", red.getCellData("TestData", "PresentEmployer", currentRow));
		IWanna.type("tbEmpAddressLine1", red.getCellData("TestData", "EmpAddrLine1", currentRow));
		IWanna.type("tbEmpAddressLine2", red.getCellData("TestData", "EmpAddrLine2", currentRow));
		IWanna.type("tbEmpSuburb", red.getCellData("TestData", "EmpSuburb", currentRow));
		IWanna.click("btnSearchEmpSuburb");
		IWanna.wait(1);
		IWanna.selectFromDropdown("ddEmpSuburbList", Integer.parseInt(red.getCellData("TestData", "EmpSuburbList", currentRow)));
		IWanna.type("tbEmpCity", red.getCellData("TestData", "EmpCity", currentRow));
		IWanna.click("btnSearchEmpCity");
		IWanna.wait(1);
		IWanna.selectFromDropdown("ddEmpCityList", Integer.parseInt(red.getCellData("TestData", "EmpCityList", currentRow)));
		IWanna.clearAndType("tbPeriodEmployedYY", red.getCellData("TestData", "PeriodEmployedYY", currentRow));
		IWanna.clearAndType("tbPeriodEmployedMM", red.getCellData("TestData", "PeriodEmployedMM", currentRow));
		
		IWanna.selectFromDropdown("ddSourceOfIncome", red.getCellData("TestData", "SourceOfIncome", currentRow));
		IWanna.selectFromDropdown("ddSourceOfFunds1", red.getCellData("TestData", "SourceOFunds1", currentRow));
		IWanna.selectFromDropdown("ddSalaryDay", red.getCellData("TestData", "SalaryDay", currentRow));
		IWanna.clearAndType("tbGrossRemuneration", red.getCellData("TestData", "GrossRem", currentRow));
		IWanna.clearAndType("tbNetPay", red.getCellData("TestData", "NetPay", currentRow));
		IWanna.click("linkCalculate");
		IWanna.wait(1);
		IWanna.clearAndType("tbBondPayment", red.getCellData("TestData", "BondRepayment", currentRow));
		IWanna.click("btnCalculate");
		IWanna.click("btnClose");
		IWanna.selectFromDropdown("ddCarAllowance", red.getCellData("TestData", "CarAllowance", currentRow));
		IWanna.selectFromDropdown("ddSurety", red.getCellData("TestData", "Surety", currentRow));
		IWanna.selectFromDropdown("ddGuarantor", red.getCellData("TestData", "Guarantor", currentRow));
		IWanna.selectFromDropdown("ddCoDebtor", red.getCellData("TestData", "Debtor", currentRow));
		
		String consent = red.getCellData("TestData", "Consent", currentRow);
		String marketing = red.getCellData("TestData", "Marketing", currentRow);
		String sms = red.getCellData("TestData", "SMS", currentRow);
		String email = red.getCellData("TestData", "Emailc", currentRow);
		String telephone = red.getCellData("TestData", "Telephone", currentRow);
		String post = red.getCellData("TestData", "Post", currentRow);
		String iVaf = red.getCellData("TestData", "IVAF", currentRow);
		String acceptConsent = red.getCellData("TestData", "Accept", currentRow);
		
		if (consent.equalsIgnoreCase("Yes")){
			IWanna.click("rbConsentYes");
		}
		else{
			IWanna.click("rbConsentNo");
		}
		
		if (marketing.equalsIgnoreCase("Yes")){
			IWanna.click("rbMarketingtYes");
		}
		else{
			IWanna.click("rbMarketingtNo");
		}
		
		if (sms.equalsIgnoreCase("Yes")){
			IWanna.click("cbCommSMS");
		}
		
		if (email.equalsIgnoreCase("Yes")){
			IWanna.click("cbCommEmail");
		}
		
		if (telephone.equalsIgnoreCase("Yes")){
			IWanna.click("cbCommTelephone");
		}
		
		if (post.equalsIgnoreCase("Yes")){
			IWanna.click("cbCommPost");
		}
		
		if (iVaf.equalsIgnoreCase("Yes")){
			IWanna.click("rbIVafYes");
		}
		else{
			IWanna.click("rbIVafNo");
		}
		
		if (acceptConsent.equalsIgnoreCase("Yes")){
			IWanna.click("cbAcceptConsent");
		}
		
		IWanna.click("btnProcessContinue");
		IWanna.wait(1);
	}
	
	public static void captureVehicleDetails(){
		testInfo.log(Status.INFO, "Capture Vehicle/Asset details");
		IWanna.selectFromDropdown("ddFinanceOption", red.getCellData("TestData", "FinanceType", currentRow));
		String equipmentType = red.getCellData("TestData", "EquipmentType", currentRow);
		IWanna.selectFromDropdown("ddEquipmentType", equipmentType);
		if (equipmentType.equalsIgnoreCase("other")){
			IWanna.selectFromDropdown("ddEquipmentOther", red.getCellData("TestData", "EquipmentTypeOther", currentRow));
			IWanna.type("tbDescOfEquipment", red.getCellData("TestData", "EquipmentDesc", currentRow));
		}
		IWanna.selectFromDropdown("ddYearOfFirstReg", red.getCellData("TestData", "RegYear", currentRow));
		IWanna.selectFromDropdown("ddMake", red.getCellData("TestData", "Make", currentRow));
		IWanna.selectFromDropdown("ddModel", red.getCellData("TestData", "Model", currentRow));
		if (equipmentType.equalsIgnoreCase("Motorbikes")){
			IWanna.selectFromDropdown("ddEngineSize", red.getCellData("TestData", "EngineSize", currentRow));
		}
		
		IWanna.type("tbPurchasePrice", red.getCellData("TestData", "PurcPrice", currentRow));
		IWanna.selectFromDropdown("ddRepaymentPeriod", red.getCellData("TestData", "PeriodRep", currentRow));
		IWanna.selectFromDropdown("ddShortTermInsurance", red.getCellData("TestData", "ShortTermInsurance", currentRow));
		IWanna.selectFromDropdown("ddInsuranceQuotation", red.getCellData("TestData", "ShortTermQuotation", currentRow));
		IWanna.click("btnProcessContinue");
		IWanna.wait(1);
	}
	
	public static void capturePaymentDetails(){
		testInfo.log(Status.INFO, "Capture Payment details");
		String debitOrder = red.getCellData("TestData", "DebitOrder", currentRow);
		if (debitOrder.equalsIgnoreCase("yes")){
			IWanna.click("rbDebitOrderYes");
			String bank = red.getCellData("TestData", "DebitOrderBank", currentRow);
			if (bank.equalsIgnoreCase("Absa")){
				IWanna.click("rbDOBankAbsa");
				IWanna.type("tbAbsaAccountNumber", red.getCellData("TestData", "AccountNoAbsa", currentRow));
			}
			else{
				IWanna.click("rbDOBankOther");
				IWanna.selectFromDropdown("ddNameOfOtherBank", red.getCellData("TestData", "DebitOrderBank", currentRow));
				IWanna.selectFromDropdown("ddAccountType", red.getCellData("TestData", "BankAccType", currentRow));
				IWanna.type("tbAccountNumberOther", red.getCellData("TestData", "BankAccNo", currentRow));
			}
		}
		
		String statementDelivery = red.getCellData("TestData", "StatementDelivery", currentRow);
		if (statementDelivery.equalsIgnoreCase("email")){
			IWanna.click("rbStatementEmail");			
		}
		else{
			IWanna.click("rbStatementPost");
		}
		
		IWanna.click("btnProcessContinue");
		IWanna.wait(2);
	}
	
	public static void getApplicationReference() throws IOException, InterruptedException{		
		testInfo.log(Status.INFO, "Capture Application reference number");
		String appRefText = IWanna.getElementText("txtReferenceNumber");
		String appRef = appRefText.substring(25);
		//System.out.println("App ref: " + appRef);
		red.setCellData("TestData", "ApplicationNumber", currentRow, appRef);
		IWanna.waitForElement("txtApplicationResult",120);
		screenShotPath = Executor.capture();
		if (IWanna.isElementPresent("txtApplicationResult")){
			testInfo.log(Status.PASS, "Your reference number is: " + appRef + "    " +testInfo.addScreenCaptureFromPath(screenShotPath));
		}
		else
			testInfo.log(Status.FAIL, "Application reault was not returned for " + appRef + "    " +testInfo.addScreenCaptureFromPath(screenShotPath));		
	}
	
	public static void getPodium() throws InterruptedException, IOException{
		String user = red.getCellData("Credentials", "Username", currentRow);
		String password = red.getCellData("Credentials", "Password", currentRow);
		login(user,password);		
		searchApplication();
		appEnquiry();
		
		switchToNavigation();
		IWanna.click("linkApplicationEnquiry");
		switchToMain();
		IWanna.click("linkViewPodium");
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		Thread.sleep(1000);
	}
	
	public static void getPodium(String username, String password) throws InterruptedException{
		login(username,password);
		IWanna.clearAndType("tbAppRefNumber", red.getCellData("TestData", "ApplicationNumber", currentRow));
		IWanna.click("btnSearchApp");
		
		//Application Enquiry
		switchToNavigation();
		IWanna.click("linkApplicationEnquiry");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("main");
		IWanna.click("linkViewPodium");
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		Thread.sleep(1000);
	}
	
	public static void getBRNumber() throws InterruptedException, IOException{
		String user = red.getCellData("Credentials", "Username", currentRow);
		String password = red.getCellData("Credentials", "Password", currentRow);
		login(user,password);
		//driver.navigate().refresh();
		searchApplication();
		appEnquiry();
		switchToMain();
		String BRNumber = IWanna.getElementText("txtBRNumber").trim();
		String SapBrSearch = BRNumber.substring(10);
		System.out.println("SapBrSearch : " + SapBrSearch);
		red.setCellData("TestData", "BRNumber", currentRow, BRNumber);
		red.setCellData("TestData", "SapBrSearch", currentRow, SapBrSearch);
	}
	
	private static void login(String username, String password){
		//Login
		IWanna.clearAndType("tbUsenameGB", username);
		IWanna.clearAndType("tbPasswordGB", password);
		IWanna.click("btnSubmit");
		
		//Verify login
		switchToMain();
		IWanna.waitForElement("tbAppRefNumber", 5);
		if (IWanna.isElementPresent("tbAppRefNumber")){
			switchToNavigation();
			testInfo.log(Status.PASS, IWanna.getElementText("txtLoggedInAs"));
			driver.switchTo().defaultContent();
		}
		else{
			testInfo.log(Status.FAIL, "Login failed for user: " + username);
			driver.quit();
		}
	}
	
	private static void searchApplication() throws InterruptedException{	
		String appRef = red.getCellData("TestData", "ApplicationNumber", currentRow);
		Thread.sleep(2000);
		driver.navigate().refresh();
		//Search application
		switchToMain();	
		IWanna.clearAndType("tbAppRefNumber",appRef);
		IWanna.click("btnSearchApp");
		switchToNavigation();
		while (!IWanna.isElementPresent("linkApplicationEnquiry")){
			IWanna.click("linkSearchApplication");
			switchToMain();
			IWanna.clearAndType("tbAppRefNumber",appRef);
			IWanna.click("btnSearchApp");
			Thread.sleep(1000);
			switchToNavigation();
		}
	}
	
	private static void appEnquiry() throws InterruptedException, IOException{	
		String appRef = red.getCellData("TestData", "ApplicationNumber", currentRow);
		switchToNavigation();
		IWanna.click("linkApplicationEnquiry");
		Thread.sleep(1000);
		switchToMain();
		while (IWanna.isElementPresent("txtContractDetailsNA")){
			System.out.println("Contract details unavailable");
			switchToNavigation();
			IWanna.click("linkSearchApplication");
			switchToMain();
			IWanna.clearAndType("tbAppRefNumber",appRef);
			IWanna.click("btnSearchApp");
			switchToNavigation();
			IWanna.click("linkApplicationEnquiry");
			Thread.sleep(1000);
		}
		
		switchToMain();
		screenShotPath = Executor.capture();
		if (IWanna.isElementPresent("headerApplicationHistory")){			
			testInfo.log(Status.PASS, "Application history was found" + testInfo.addScreenCaptureFromPath(screenShotPath));
			driver.switchTo().defaultContent();
		}
		else{
			testInfo.log(Status.FAIL, "Application history was not found" + testInfo.addScreenCaptureFromPath(screenShotPath));
		}			
	}
	
	private static void switchToNavigation(){
		driver.switchTo().defaultContent();
		try{
			driver.switchTo().frame("navigation");
		}
		catch(Exception e){
			log.debug(e.getMessage());
		}
	}
	
	private static void switchToMain(){
		driver.switchTo().defaultContent();
		try{
			driver.switchTo().frame("main");
		}
		catch(Exception e){
			log.debug(e.getMessage());
		}
	}
	
	public static void setTestCase(String testCaseName, String id){
		
	}
}
