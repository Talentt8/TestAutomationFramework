package seriti;

import org.testng.annotations.Test;
import AutomationFramework.BaseClass;

public class Application extends BaseClass{	
		
	@Test	
	public void application() throws Exception{
		seritiutils.Internet.application();	
	}		
}