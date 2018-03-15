package eobf;

import org.testng.annotations.Test;
import AutomationFramework.BaseClass;
import utils.EOBFUtils;

public class IndividualApplication extends BaseClass{	
		
	@Test	
	public void individualApplication() throws Exception{
		//First line
		EOBFUtils.setTestCase("Individual Application", "123");
		EOBFUtils.navigateToAboutYou();
		EOBFUtils.capturePersonalDetails();
		EOBFUtils.captureAdressInformation();
	}		
}