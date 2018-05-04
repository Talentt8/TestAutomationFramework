package apputils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.aventstack.extentreports.Status;

import AutomationFramework.BaseClass;

public class SeritiCommon extends BaseClass{

	static int rowIndex = 2;
	public static void login(String username, String password){
		IWanna.type("tbUsername", username);
		IWanna.type("tbPassword", password);
		IWanna.wait(1);
		IWanna.click("btnLogin");
		IWanna.wait(1);
	}
	
	public static void createTransaction(){
		testInfo.log(Status.INFO, "Create transaction");
		IWanna.selectFromDropdown("ddCustomerType", red.getCellData("TestData", "CustomerType", rowIndex));
		IWanna.type("tbLastName", red.getCellData("TestData", "LastName", rowIndex));
		IWanna.selectFromDropdown("ddGroup", red.getCellData("TestData", "Group", rowIndex));
		IWanna.selectFromDropdown("ddBranch", red.getCellData("TestData", "Branch", rowIndex));
		IWanna.type("tbFirstName", red.getCellData("TestData", "FirstName", rowIndex));
		IWanna.wait(2);
		IWanna.click("btnCreate");
		IWanna.hoverAndCLick("linkApplications", "linkFinanceApplications");
		IWanna.click("linkAVAF");
		IWanna.click("btnClientEdit");
	}
	
	public static void editClient(){
		testInfo.log(Status.INFO, "Edit Client");		
		IWanna.type("tbEditFirstName", red.getCellData("TestData", "FirstName", rowIndex));	
		String title = red.getCellData("TestData", "Title", rowIndex);
		
		//Select Title
		if (title.equalsIgnoreCase("Mr")){
			IWanna.click("rbMr");
		}
		else if (title.equalsIgnoreCase("Mrs")){
			IWanna.click("rbMrs");
		}
		else if (title.equalsIgnoreCase("Ms")){
			IWanna.click("rbMs");
		}
		else if (title.equalsIgnoreCase("Miss")){
			IWanna.click("rbMiss");
		}
		else if (title.equalsIgnoreCase("Dr")){
			IWanna.click("rbDr");
		}
		
		//Select ID type
		String idType = red.getCellData("TestData", "IdentificationType", rowIndex);
		if (idType.equalsIgnoreCase("RSA ID")){
			IWanna.click("rbRSA_ID");
		}
		else if (title.equalsIgnoreCase("PASSPORT")){
			IWanna.click("rbPassport");
		}
		else if (title.equalsIgnoreCase("Other ID")){
			IWanna.click("rbOtherId");
		}
		
		IWanna.type("tbIdNumber", red.getCellData("TestData", "IdentityNumber", rowIndex));
		IWanna.click("tbDateOfBirth");
		String foreignNational = red.getCellData("TestData", "ForeignNational", rowIndex);
		
		if (foreignNational.equalsIgnoreCase("False")){
			IWanna.click("rbForeignNo");
		}
		else if (foreignNational.equalsIgnoreCase("True")){
			IWanna.click("rbForeignYes");
		}
		
		IWanna.selectFromDropdown("ddCountry", 2);
		IWanna.type("tbEmailAddress", red.getCellData("TestData", "EmailAddress", rowIndex));
		IWanna.type("tbMobileNumber", red.getCellData("TestData", "MobileNumber", rowIndex));
		//IWanna.selectFromDropdown("ddBirthCountry", red.getCellData("TestData", "CountryOfBirth", rowIndex));
		//IWanna.selectFromDropdown("ddCountryOfResidence", red.getCellData("TestData", "ResdidenceCountry", rowIndex));
		IWanna.click("btnEditClientPhyAdd");
		IWanna.click("btnEditClientPhyAdd");
		IWanna.type("tbPhyAddLine1", "Line 1");
		IWanna.type("tbPhyAddSuburb", "Randburg");

	}
}
