package eobfutils;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import AutomationFramework.BaseClass;

public class CommonUtils extends BaseClass{
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
		IWanna.selectFromDropdown("ddClientType", red.getCellData("TestData", "ClientType", 2));
		String vatIndicator = red.getCellData("TestData", "IndicatorVatVendor", 2);		
		if (vatIndicator.equalsIgnoreCase("No")){	
			IWanna.click("cbVatVendorNo");
		}
		else if (vatIndicator.equalsIgnoreCase("Yes")){
			IWanna.click("cbVatVendorYes");
		}
		
		IWanna.type("tbVendorNumber", red.getCellData("TestData", "VATVendorNumber", 2));
		String indicatorInsolvent = red.getCellData("TestData", "IndicatorInsolvent", 2);	
		if (indicatorInsolvent.equalsIgnoreCase("No")){	
			IWanna.click("rbInsolventNo");
		}
		else if (indicatorInsolvent.equalsIgnoreCase("Yes")){
			IWanna.click("rbInsolventYes");
		}		
		IWanna.selectFromDropdown("ddTitle", red.getCellData("TestData", "Title", 2));
		IWanna.type("tbFirstNames", red.getCellData("TestData", "FirstName", 2));
		IWanna.type("tbSurname", red.getCellData("TestData", "Surname", 2));
		
		String saResident = red.getCellData("TestData", "IndicatorSACitizen", 2);	
		if (saResident.equalsIgnoreCase("No")){	
			IWanna.click("cbSAResNo");
			IWanna.type("tbIdNumber", red.getCellData("TestData", "IdNumber", 2));
			IWanna.type("tbPassportValidFromDate", red.getCellData("TestData", "PassportValidFrom", 2));
			IWanna.type("tbPassportExpiryDate", red.getCellData("TestData", "PassportExpiry", 2));
			//capture temporary residence information
			if (red.getCellData("TestData", "TemporaryResident", 2).equalsIgnoreCase("Yes")){
				IWanna.click("rbTempResYes");
				IWanna.type("tbPermitNumber", red.getCellData("TestData", "PermitNumber", 2));
				IWanna.type("tbPermitExpiryDate", red.getCellData("TestData", "PermitExpiryDate", 2));
			}
			else if (red.getCellData("TestData", "PassportExpiry", 2).equalsIgnoreCase("No")){
				IWanna.click("rbTempResNo");
			}
		}
		else if (saResident.equalsIgnoreCase("Yes")){
			IWanna.click("cbSAResYes");
			IWanna.type("tbIdNumber", red.getCellData("TestData", "IdNumber", 2));
		}			
		//select gender
		if (red.getCellData("TestData", "Gender", 2).equalsIgnoreCase("Male")){
			IWanna.click("rbGenderMale");
		}
		else if(red.getCellData("TestData", "Gender", 2).equalsIgnoreCase("female")){
			IWanna.click("rbGenderFemale");			
		}
		IWanna.selectFromDropdown("ddRaceIndicator", red.getCellData("TestData", "Race", 2));
		IWanna.type("tbDateOfBirth", red.getCellData("TestData", "DOB", 2));
		IWanna.selectFromDropdown("ddNationality", red.getCellData("TestData", "Nationality", 2));
		IWanna.selectFromDropdown("ddCountryOfOrigin", red.getCellData("TestData", "CountryOfOrigin", 2));
		IWanna.selectFromDropdown("ddMaritalStatus", red.getCellData("TestData", "MaritalStatus", 2));
		IWanna.selectFromDropdown("ddLanguage", red.getCellData("TestData", "Language", 2));
		
		//capture post matric qualification
		if (red.getCellData("TestData", "PostMatricQualification", 2).equalsIgnoreCase("Yes")){
			IWanna.click("rbPostMatricQualificationYes");
			IWanna.selectFromDropdown("ddHighestQualification", red.getCellData("TestData", "Qualification", 2));
		}
		else if(red.getCellData("TestData", "PostMatricQualification", 2).equalsIgnoreCase("No")){
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
		IWanna.selectFromDropdown("ddStatus", red.getCellData("TestData", "ResStatus", 2));
		IWanna.type("tbResidentialLine1", red.getCellData("TestData", "ResLine1", 2));
		IWanna.type("tbResidentialLine2", red.getCellData("TestData", "ResLine2", 2));
		IWanna.type("tbResidentialSuburb", red.getCellData("TestData", "ResSuburb", 2));
		IWanna.click("btnSearchResidentialSuburb");
		IWanna.wait(1);
		IWanna.selectFromDropdown("ddResidentialSuburb", 1);	
		IWanna.type("tbResidentialCity", red.getCellData("TestData", "ResCity", 2));
		IWanna.click("btnResidentialCitySearch");
		IWanna.selectFromDropdown("ddResidentialCity", 1);
		IWanna.type("tbYYatThisAddress", red.getCellData("TestData", "PeriodYY", 2));
		IWanna.type("tbMMatThisAddress", red.getCellData("TestData", "PeriodMM", 2));
		
		String sameAsPostal = red.getCellData("TestData", "SameAsPostal", 2);
		if (sameAsPostal.equalsIgnoreCase("Yes")){
			IWanna.click("rbSameAsPostalYes");
		}
		else if(sameAsPostal.equalsIgnoreCase("No")){
			IWanna.type("tbPostalLine1", red.getCellData("TestData", "PostalLine1", 2));
			IWanna.type("tbPostalLine2", red.getCellData("TestData", "PostalLine2", 2));
			IWanna.type("tbPostalSuburb", red.getCellData("TestData", "PostalSuburb", 2));
			IWanna.click("btnSearchPostalSuburb");
			IWanna.selectFromDropdown("ddPostalSuburb", 1);	
			IWanna.type("tbPostalCity", red.getCellData("TestData", "PostalCity", 2));
			IWanna.click("btnSearchPostalCity");
			IWanna.selectFromDropdown("ddPostalCity", 1);
		}
	}
	
	public static void captureContactInformation(){
		IWanna.type("tbAreaCodeHome", red.getCellData("TestData", "AreaCodeHome", 2));
		IWanna.type("tbTelNoHome", red.getCellData("TestData", "TelNumberHome", 2));
		IWanna.type("tbAreaCodeWork", red.getCellData("TestData", "AreaCodeWork", 2));
		IWanna.type("tbTelNoWork", red.getCellData("TestData", "TelNumberWork", 2));
		IWanna.type("tbCellNo", red.getCellData("TestData", "Cell", 2));
		IWanna.type("tbEmailAddress", red.getCellData("TestData", "Email", 2));
		IWanna.selectFromDropdown("ddMethodOfComm", red.getCellData("TestData", "CommunicationMethod", 2));
		
		//verify data capture
		/*try{
			Assert.assertEquals(IWanna.getElementValue("ddMethodOfComm"),red.getCellData("TestData", "CommunicationMethod", 2));
		}
		catch(Throwable t){
			System.out.println("Failure message : " + t.getMessage());
			org.testng.Assert.fail("Assert failed");
		}*/
	}
	
	public static void captureNokInformation(){
		IWanna.type("tbNokFirstName", red.getCellData("TestData", "NOKFName", 2));
		IWanna.type("tbNokSurname", red.getCellData("TestData", "NOKSurname", 2));
		IWanna.selectFromDropdown("ddRelationship", red.getCellData("TestData", "NOKRelation", 2));
		/*IWanna.type("tbNokAreaCodeHome", red.getCellData("TestData", "AreaCodeHome", 2));
		IWanna.type("tbNokTelNoHome", red.getCellData("TestData", "TelNumberHome", 2));
		IWanna.type("tbNokAreaCodeWork", red.getCellData("TestData", "AreaCodeWork", 2));*/
		IWanna.type("tbNokCellNo", red.getCellData("TestData", "NOKCell", 2));
		IWanna.click("btnProcessContinue");
	}
	
	public static void setTestCase(String testCaseName, String id){
		
	}
}
