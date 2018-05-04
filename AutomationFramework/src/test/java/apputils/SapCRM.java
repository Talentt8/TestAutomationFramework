package apputils;

public class SapCRM extends SapCommon{
	
	public static void approveApplication() throws Exception{
		login();
		navigateToFinancingQuotations();
		fixErrors();
		scoreApplication();
	}

}
