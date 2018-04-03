package apputils;

import AutomationFramework.BaseClass;

public class SeritiCommon extends BaseClass{

	public static void login(String username, String password){
		IWanna.type("tbUsername", username);
		IWanna.type("tbPassword", password);
		IWanna.wait(1);
		IWanna.click("btnLogin");
		IWanna.wait(1);
	}
}
