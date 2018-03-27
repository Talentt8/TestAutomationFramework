package seritiutils;

import AutomationFramework.BaseClass;

public class CommonUtils extends BaseClass{

	public static void login(String username, String password){
		IWanna.type("tbUsername", username);
		IWanna.type("tbPassword", password);
		IWanna.click("btnLogin");
		IWanna.wait(1);
	}
}
