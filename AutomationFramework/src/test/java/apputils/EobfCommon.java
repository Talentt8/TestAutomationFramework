package apputils;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import AutomationFramework.BaseClass;
import AutomationFramework.Executor;

public class EobfCommon extends BaseClass{
	
	static int rowIndex = 2;
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
		IWanna.click("linkTermsAndConditions");
		IWanna.wait(1);
		IWanna.click("btnDone"); 
		IWanna.click("cbAcceptTerms");
		IWanna.click("btnContinue");		
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
		IWanna.selectFromDropdown("ddClientType", red.getCellData("TestData", "ClientType", rowIndex));
		String vatIndicator = red.getCellData("TestData", "IndicatorVatVendor", rowIndex);		
		if (vatIndicator.equalsIgnoreCase("No")){	
			IWanna.click("cbVatVendorNo");
		}
		else if (vatIndicator.equalsIgnoreCase("Yes")){
			IWanna.click("cbVatVendorYes");
		}
		
		IWanna.type("tbVendorNumber", red.getCellData("TestData", "VATVendorNumber", rowIndex));
		String indicatorInsolvent = red.getCellData("TestData", "IndicatorInsolvent", rowIndex);	
		if (indicatorInsolvent.equalsIgnoreCase("No")){	
			IWanna.click("rbInsolventNo");
		}
		else if (indicatorInsolvent.equalsIgnoreCase("Yes")){
			IWanna.click("rbInsolventYes");
		}		
		IWanna.selectFromDropdown("ddTitle", red.getCellData("TestData", "Title", rowIndex));
		IWanna.type("tbFirstNames", red.getCellData("TestData", "FirstName", rowIndex));
		IWanna.type("tbSurname", red.getCellData("TestData", "Surname", rowIndex));
		
		String saResident = red.getCellData("TestData", "IndicatorSACitizen", rowIndex);	
		if (saResident.equalsIgnoreCase("No")){	
			IWanna.click("cbSAResNo");
			IWanna.type("tbIdNumber", red.getCellData("TestData", "IdNumber", rowIndex));
			IWanna.type("tbPassportValidFromDate", red.getCellData("TestData", "PassportValidFrom", rowIndex));
			IWanna.type("tbPassportExpiryDate", red.getCellData("TestData", "PassportExpiry", rowIndex));
			//capture temporary residence information
			if (red.getCellData("TestData", "TemporaryResident", rowIndex).equalsIgnoreCase("Yes")){
				IWanna.click("rbTempResYes");
				IWanna.type("tbPermitNumber", red.getCellData("TestData", "PermitNumber", rowIndex));
				IWanna.type("tbPermitExpiryDate", red.getCellData("TestData", "PermitExpiryDate", rowIndex));
			}
			else if (red.getCellData("TestData", "PassportExpiry", rowIndex).equalsIgnoreCase("No")){
				IWanna.click("rbTempResNo");
			}
		}
		else if (saResident.equalsIgnoreCase("Yes")){
			IWanna.click("cbSAResYes");
			IWanna.type("tbIdNumber", red.getCellData("TestData", "IdNumber", rowIndex));
		}			
		//select gender
		if (red.getCellData("TestData", "Gender", rowIndex).equalsIgnoreCase("Male")){
			IWanna.click("rbGenderMale");
		}
		else if(red.getCellData("TestData", "Gender", rowIndex).equalsIgnoreCase("female")){
			IWanna.click("rbGenderFemale");			
		}
		IWanna.selectFromDropdown("ddRaceIndicator", red.getCellData("TestData", "Race", rowIndex));
		IWanna.type("tbDateOfBirth", red.getCellData("TestData", "DOB", rowIndex));
		IWanna.selectFromDropdown("ddNationality", red.getCellData("TestData", "Nationality", rowIndex));
		IWanna.selectFromDropdown("ddCountryOfOrigin", red.getCellData("TestData", "CountryOfOrigin", rowIndex));
		IWanna.selectFromDropdown("ddMaritalStatus", red.getCellData("TestData", "MaritalStatus", rowIndex));
		IWanna.selectFromDropdown("ddLanguage", red.getCellData("TestData", "Language", rowIndex));
		
		//capture post matric qualification
		if (red.getCellData("TestData", "PostMatricQualification", rowIndex).equalsIgnoreCase("Yes")){
			IWanna.click("rbPostMatricQualificationYes");
			IWanna.selectFromDropdown("ddHighestQualification", red.getCellData("TestData", "Qualification", rowIndex));
		}
		else if(red.getCellData("TestData", "PostMatricQualification", rowIndex).equalsIgnoreCase("No")){
			IWanna.click("rbPostMatricQualificationNo");			
		}
		Assert.assertTrue(IWanna.getElementValue("tbDateOfBirth").equalsIgnoreCase("19880601"));
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
		IWanna.selectFromDropdown("ddStatus", red.getCellData("TestData", "ResStatus", rowIndex));
		IWanna.type("tbResidentialLine1", red.getCellData("TestData", "ResLine1", rowIndex));
		IWanna.type("tbResidentialLine2", red.getCellData("TestData", "ResLine2", rowIndex));
		IWanna.type("tbResidentialSuburb", red.getCellData("TestData", "ResSuburb", rowIndex));
		IWanna.click("btnSearchResidentialSuburb");
		IWanna.wait(1);
		IWanna.selectFromDropdown("ddResidentialSuburb", 1);	
		IWanna.type("tbResidentialCity", red.getCellData("TestData", "ResCity", rowIndex));
		IWanna.click("btnResidentialCitySearch");
		IWanna.selectFromDropdown("ddResidentialCity", 1);
		IWanna.type("tbYYatThisAddress", red.getCellData("TestData", "PeriodYY", rowIndex));
		IWanna.type("tbMMatThisAddress", red.getCellData("TestData", "PeriodMM", rowIndex));
		
		String sameAsPostal = red.getCellData("TestData", "SameAsPostal", rowIndex);
		if (sameAsPostal.equalsIgnoreCase("Yes")){
			IWanna.click("rbSameAsPostalYes");
		}
		else if(sameAsPostal.equalsIgnoreCase("No")){
			IWanna.type("tbPostalLine1", red.getCellData("TestData", "PostalLine1", rowIndex));
			IWanna.type("tbPostalLine2", red.getCellData("TestData", "PostalLine2", rowIndex));
			IWanna.type("tbPostalSuburb", red.getCellData("TestData", "PostalSuburb", rowIndex));
			IWanna.click("btnSearchPostalSuburb");
			IWanna.selectFromDropdown("ddPostalSuburb", 1);	
			IWanna.type("tbPostalCity", red.getCellData("TestData", "PostalCity", rowIndex));
			IWanna.click("btnSearchPostalCity");
			IWanna.selectFromDropdown("ddPostalCity", 1);
		}
	}
	
	public static void captureContactInformation(){
		testInfo.log(Status.INFO, "Capture Contact Information");
		IWanna.type("tbAreaCodeHome", red.getCellData("TestData", "AreaCodeHome", rowIndex));
		IWanna.type("tbTelNoHome", red.getCellData("TestData", "TelNumberHome", rowIndex));
		IWanna.type("tbAreaCodeWork", red.getCellData("TestData", "AreaCodeWork", rowIndex));
		IWanna.type("tbTelNoWork", red.getCellData("TestData", "TelNumberWork", rowIndex));
		IWanna.type("tbCellNo", red.getCellData("TestData", "Cell", rowIndex));
		IWanna.type("tbEmailAddress", red.getCellData("TestData", "Email", rowIndex));
		IWanna.selectFromDropdown("ddMethodOfComm", red.getCellData("TestData", "CommunicationMethod", rowIndex));
		
		//verify data capture
		/*try{
			Assert.assertEquals(IWanna.getElementValue("ddMethodOfComm"),red.getCellData("TestData", "CommunicationMethod", rowIndex));
		}
		catch(Throwable t){
			System.out.println("Failure message : " + t.getMessage());
			org.testng.Assert.fail("Assert failed");
		}*/
	}
	
	public static void captureNokInformation(){
		testInfo.log(Status.INFO, "Capture Next of KIN Information");
		IWanna.type("tbNokFirstName", red.getCellData("TestData", "NOKFName", rowIndex));
		IWanna.type("tbNokSurname", red.getCellData("TestData", "NOKSurname", rowIndex));
		IWanna.selectFromDropdown("ddRelationship", red.getCellData("TestData", "NOKRelation", rowIndex));
		/*IWanna.type("tbNokAreaCodeHome", red.getCellData("TestData", "AreaCodeHome", rowIndex));
		IWanna.type("tbNokTelNoHome", red.getCellData("TestData", "TelNumberHome", rowIndex));
		IWanna.type("tbNokAreaCodeWork", red.getCellData("TestData", "AreaCodeWork", rowIndex));*/
		IWanna.type("tbNokCellNo", red.getCellData("TestData", "NOKCell", rowIndex));
		IWanna.click("btnProcessContinue");
	}
	
	public static void captureYourFinances(){
		testInfo.log(Status.INFO, "Capture Your Finances");
		IWanna.selectFromDropdown("ddOccupationStatus", red.getCellData("TestData", "OccupationStatus", rowIndex));
		IWanna.selectFromDropdown("ddEmploymentSector", red.getCellData("TestData", "EmploymentSector", rowIndex));
		IWanna.selectFromDropdown("ddOccupationLevel", red.getCellData("TestData", "OccupationLevel", rowIndex));
		IWanna.selectFromDropdown("ddOccupation", red.getCellData("TestData", "Occupation", rowIndex));
		IWanna.type("tbPresentEmployer", red.getCellData("TestData", "PresentEmployer", rowIndex));
		IWanna.type("tbEmpAddressLine1", red.getCellData("TestData", "EmpAddrLine1", rowIndex));
		IWanna.type("tbEmpAddressLine2", red.getCellData("TestData", "EmpAddrLine2", rowIndex));
		IWanna.type("tbEmpSuburb", red.getCellData("TestData", "EmpSuburb", rowIndex));
		IWanna.click("btnSearchEmpSuburb");
		IWanna.wait(1);
		IWanna.selectFromDropdown("ddEmpSuburbList", Integer.parseInt(red.getCellData("TestData", "EmpSuburbList", rowIndex)));
		IWanna.type("tbEmpCity", red.getCellData("TestData", "EmpCity", rowIndex));
		IWanna.click("btnSearchEmpCity");
		IWanna.wait(1);
		IWanna.selectFromDropdown("ddEmpCityList", Integer.parseInt(red.getCellData("TestData", "EmpCityList", rowIndex)));
		IWanna.clearAndType("tbPeriodEmployedYY", red.getCellData("TestData", "PeriodEmployedYY", rowIndex));
		IWanna.clearAndType("tbPeriodEmployedMM", red.getCellData("TestData", "PeriodEmployedMM", rowIndex));
		
		IWanna.selectFromDropdown("ddSourceOfIncome", red.getCellData("TestData", "SourceOfIncome", rowIndex));
		IWanna.selectFromDropdown("ddSourceOfFunds1", red.getCellData("TestData", "SourceOFunds1", rowIndex));
		IWanna.selectFromDropdown("ddSalaryDay", red.getCellData("TestData", "SalaryDay", rowIndex));
		IWanna.clearAndType("tbGrossRemuneration", red.getCellData("TestData", "GrossRem", rowIndex));
		IWanna.clearAndType("tbNetPay", red.getCellData("TestData", "NetPay", rowIndex));
		IWanna.click("linkCalculate");
		IWanna.wait(1);
		IWanna.clearAndType("tbBondPayment", red.getCellData("TestData", "BondRepayment", rowIndex));
		IWanna.click("btnCalculate");
		IWanna.click("btnClose");
		IWanna.selectFromDropdown("ddCarAllowance", red.getCellData("TestData", "CarAllowance", rowIndex));
		IWanna.selectFromDropdown("ddSurety", red.getCellData("TestData", "Surety", rowIndex));
		IWanna.selectFromDropdown("ddGuarantor", red.getCellData("TestData", "Guarantor", rowIndex));
		IWanna.selectFromDropdown("ddCoDebtor", red.getCellData("TestData", "Debtor", rowIndex));
		
		String consent = red.getCellData("TestData", "Consent", rowIndex);
		String marketing = red.getCellData("TestData", "Marketing", rowIndex);
		String sms = red.getCellData("TestData", "SMS", rowIndex);
		String email = red.getCellData("TestData", "Emailc", rowIndex);
		String telephone = red.getCellData("TestData", "Telephone", rowIndex);
		String post = red.getCellData("TestData", "Post", rowIndex);
		String iVaf = red.getCellData("TestData", "IVAF", rowIndex);
		String acceptConsent = red.getCellData("TestData", "Accept", rowIndex);
		
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
		IWanna.selectFromDropdown("ddFinanceOption", red.getCellData("TestData", "FinanceType", rowIndex));
		String equipmentType = red.getCellData("TestData", "EquipmentType", rowIndex);
		IWanna.selectFromDropdown("ddEquipmentType", equipmentType);
		if (equipmentType.equalsIgnoreCase("other")){
			IWanna.selectFromDropdown("ddEquipmentOther", red.getCellData("TestData", "EquipmentTypeOther", rowIndex));
			IWanna.type("tbDescOfEquipment", red.getCellData("TestData", "EquipmentDesc", rowIndex));
		}
		IWanna.selectFromDropdown("ddYearOfFirstReg", red.getCellData("TestData", "RegYear", rowIndex));
		IWanna.selectFromDropdown("ddMake", red.getCellData("TestData", "Make", rowIndex));
		IWanna.selectFromDropdown("ddModel", red.getCellData("TestData", "Model", rowIndex));
		if (equipmentType.equalsIgnoreCase("Motorbikes")){
			IWanna.selectFromDropdown("ddEngineSize", red.getCellData("TestData", "EngineSize", rowIndex));
		}
		
		IWanna.type("tbPurchasePrice", red.getCellData("TestData", "PurcPrice", rowIndex));
		IWanna.selectFromDropdown("ddRepaymentPeriod", red.getCellData("TestData", "PeriodRep", rowIndex));
		IWanna.selectFromDropdown("ddShortTermInsurance", red.getCellData("TestData", "ShortTermInsurance", rowIndex));
		IWanna.selectFromDropdown("ddInsuranceQuotation", red.getCellData("TestData", "ShortTermQuotation", rowIndex));
		IWanna.click("btnProcessContinue");
		IWanna.wait(1);
	}
	
	public static void capturePaymentDetails(){
		testInfo.log(Status.INFO, "Capture Payment details");
		String debitOrder = red.getCellData("TestData", "DebitOrder", rowIndex);
		if (debitOrder.equalsIgnoreCase("yes")){
			IWanna.click("rbDebitOrderYes");
			String bank = red.getCellData("TestData", "DebitOrderBank", rowIndex);
			if (bank.equalsIgnoreCase("Absa")){
				IWanna.click("rbDOBankAbsa");
				IWanna.type("tbAbsaAccountNumber", red.getCellData("TestData", "AccountNoAbsa", rowIndex));
			}
			else{
				IWanna.click("rbDOBankOther");
				IWanna.selectFromDropdown("ddNameOfOtherBank", red.getCellData("TestData", "DebitOrderBank", rowIndex));
				IWanna.selectFromDropdown("ddAccountType", red.getCellData("TestData", "BankAccType", rowIndex));
				IWanna.type("tbAccountNumberOther", red.getCellData("TestData", "BankAccNo", rowIndex));
			}
		}
		
		String statementDelivery = red.getCellData("TestData", "StatementDelivery", rowIndex);
		if (statementDelivery.equalsIgnoreCase("email")){
			IWanna.click("rbStatementEmail");			
		}
		else{
			IWanna.click("rbStatementPost");
		}
		
		IWanna.click("btnProcessContinue");
		IWanna.wait(2);
	}
	
	public static void getApplicationReference() throws IOException{
		screenShotPath = Executor.capture();
		testInfo.log(Status.INFO, "Capture Application reference number");
		String appRefText = IWanna.getElementText("txtReferenceNumber");
		String appRef = appRefText.substring(25);
		System.out.println("App refe: " + appRef);
		red.setCellData("TestData", "ApplicationNumber", rowIndex, appRef);
		IWanna.waitForElement("txtApplicationResult",120);
		testInfo.log(Status.PASS, "Your reference number is: " + appRef + "    " +testInfo.addScreenCaptureFromPath(screenShotPath));
		
	}
	
	public static void setTestCase(String testCaseName, String id){
		
	}
}
