package eobf;

import org.testng.annotations.Test;
import AutomationFramework.BaseClass;

public class Application extends BaseClass{	
		
	@Test	
	public void application() throws Exception{
		apputils.EobfInternet.application();
	}		
}