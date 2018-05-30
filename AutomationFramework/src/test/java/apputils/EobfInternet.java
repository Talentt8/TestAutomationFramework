package apputils;

import org.testng.annotations.Test;

public class EobfInternet extends EobfCommon{
	
	@Test
	public static void application() throws Exception{
		System.out.println("The current executing method is : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		driver.get(red.getCellData("Credentials", "url", firstRow));
		navigateToAboutYou();
		capturePersonalDetails();
		/*captureAddressInformation();
		captureContactInformation();
		captureNokInformation();
		captureYourFinances();
		captureVehicleDetails();
		capturePaymentDetails();
		getApplicationReference();*/
	}
}