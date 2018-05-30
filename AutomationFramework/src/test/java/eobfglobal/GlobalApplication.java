package eobfglobal;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import AutomationFramework.BaseClass;

public class GlobalApplication extends BaseClass{	
		
	static int rowIndex = 2;
	static String downloadPath = "C:\\seleniumdownloads";
	
	@Test	
	public void application() throws Exception{
		//apputils.EobfInternet.application();
		
		//Login
		IWanna.clearAndType("tbUsename", red.getCellData("Credentials", "Username", rowIndex));
		IWanna.clearAndType("tbPassword", red.getCellData("Credentials", "Password", rowIndex));
		IWanna.click("btnSubmit");
		
		
		//Search application
		driver.switchTo().frame("main");
		IWanna.waitForElement("tbAppRefNumber", 10);
		IWanna.clearAndType("tbAppRefNumber", red.getCellData("TestData", "ApplicationNumber", rowIndex));
		IWanna.click("btnSearchApp");
		
		//Application Enquiry
		driver.switchTo().defaultContent();
		driver.switchTo().frame("navigation");
		IWanna.click("linkApplicationEnquiry");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("main");
		IWanna.click("linkViewPodium");
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		Thread.sleep(1000);
		/*Actions oAction = new Actions(driver);
		oAction.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'VIEW PODIUM')]")));
		//oAction.contextClick(driver.findElement(By.xpath("//a[contains(text(),'VIEW PODIUM')]"))).build().perform();
		oAction.contextClick(driver.findElement(By.xpath("//a[contains(text(),'VIEW PODIUM')]"))).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
		
		/*
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		*/
		
		//red.setCellData("TestData", "ApplicationNum", rowIndex, "Pass");		
	}		
}