package apputils;

import java.io.IOException;

public class SapCRM extends SapCommon{
	
	public static void approveApplication() throws Exception{
		login();
		navigateToFinancingQuotations();
		fixErrors();
		scoreApplication();
	}
	
	public static void addActions() throws InterruptedException, IOException{
		actions();
	}

}
