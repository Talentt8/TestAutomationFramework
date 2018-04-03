package apputils;

//import seritiutils.CommonUtils;

public class SeritiInternet extends SeritiCommon{
	
	public static void application(){
		System.out.println("The current executing method is : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		login(red.getCellData("Credentials", "Username", 2),red.getCellData("Credentials", "Password", 2));
	}
}
