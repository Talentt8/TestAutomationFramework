package apputils;

public class EobfInternet extends EobfCommon{
	
	public static void application() throws Exception{
		System.out.println("The current executing method is : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		navigateToAboutYou();
		capturePersonalDetails();
		captureAddressInformation();
		captureContactInformation();
		captureNokInformation();
	}
}