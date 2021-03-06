package apputils;

import java.io.IOException;

public class EobfGlobal extends EobfCommon{
	
	public static void application() throws Exception{
		System.out.println("The current executing method is : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		navigateToAboutYou();
		capturePersonalDetails();
		captureAddressInformation();
		captureContactInformation();
		captureNokInformation();
		captureYourFinances();
		captureVehicleDetails();
		capturePaymentDetails();
		getApplicationReference();
	}
	
	public static void podium() throws InterruptedException, IOException{
		getPodium();
	}
	
}