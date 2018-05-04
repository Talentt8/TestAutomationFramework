package AutomationFramework;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import AutomationFramework.BaseClass;


/**
 * @author      Mzwandile Mdladla <mzwandile.mdladla@absa.co.za>
 * @version     1.0                
 * @since       1.0          
 */
public class Executor extends BaseClass{
	
	//Faker faker = new Faker();
	
	Set<String> s1;
	Iterator<String> i1;
	
	Set<String> if1;
	Iterator<String> f1;
	int iFrames;

	/**
	 * Click
	 * Clicks on the specified element on the screen. 
	 * <p>
	 * This method clicks on the specified element on the screen
	 * it handles an element not found exception if the element is not displayed 
	 *
	 * @param  name of the element which references the xpath from the properties file
	 * @return void     
	 * @see         
	 */
	public void click(String element){
		log.info("Clicking " + element + " element");
		try{
			driver.findElement(By.xpath(pro.getProperty(element))).click();
		}
		catch(Exception e){
			log.debug("Unable to locate " + element + " element in main window : " + e.getStackTrace());
			System.out.println(e.getStackTrace());									
			
			//Switch windows
			s1 = driver.getWindowHandles();		
		    i1 = s1.iterator();
		    	
		    if (s1.size() > 1){
		    	log.debug("Switching windows");
		    	while(i1.hasNext())			
		        {		
		            String childWindow=i1.next();		            		
		            if(!mainWindow.equalsIgnoreCase(childWindow))			
		            {    		                 
		                // Switching to Child window
		                driver.switchTo().window(childWindow);	
		                log.info("Clicking " + element + " element");
		                try{
		        			driver.findElement(By.xpath(pro.getProperty(element))).click();
		        		}
		        		catch(Exception e1){
		        			log.debug("Unable to locate " + element + " element in child window " + childWindow + " : " + e1.getStackTrace());	            
		        		}
		            }		
		        }
		    }
			
			
			// Switching to Parent window i.e Main Window.
	        driver.switchTo().window(mainWindow);
	        
	      //Switch iFrames
	        log.debug("Switching iFrames");
			iFrames = driver.findElements(By.tagName("iframe")).size();
			int frames = driver.findElements(By.tagName("frameset")).size();
			System.out.println("Frames : " + frames);
			System.out.println("iFrames : " + iFrames);
			for (int i=0; i<iFrames; i++){
				driver.switchTo().frame(i);				
                try{
                	log.info("Clicking " + element + " element");
        			driver.findElement(By.xpath(pro.getProperty(element))).click();
        			break;
        		}
        		catch(Exception e1){
        			log.debug("Unable to locate " + element + " element in iFrame " + e1.getStackTrace());	            
        		}
			}
			driver.switchTo().defaultContent();
	        	      
			// Closing the Child Window.
            // driver.close();	
		}
	}
	
	/**
	 * Clear and type. 
	 * <p>
	 * This method clears the specified element on screen and types in a text
	 * it handles an element not found exception if the element is not displayed 
	 *
	 * @param  element - name of the element which references the xpath from the properties file
	 * @param  text - text to be input in the text box
	 * @return void     
	 */
	public void clearAndType(String element, String text){
		
		try{
			log.info("Clearing " + element + " text box");
			driver.findElement(By.xpath(pro.getProperty(element))).clear();
			
			log.info("Typing " + text + " into " + element);
			driver.findElement(By.xpath(pro.getProperty(element))).sendKeys(text);
		}
		catch(Exception e){
			log.debug("Unable to locate " + element + " element : " + e.getStackTrace());
			System.out.println(e.getStackTrace());
			
			//Switch windows
			s1 = driver.getWindowHandles();		
		    i1 = s1.iterator();
		    
		    if ((s1.size() > 1)){
		    	log.debug("Switching windows");
		    	while(i1.hasNext())			
		        {		
		            String childWindow=i1.next();		            		
		            if(!mainWindow.equalsIgnoreCase(childWindow))			
		            {    		                 
		                // Switching to Child window
		                driver.switchTo().window(childWindow);	 
		                try{
		                	log.info("Clearing " + element + " text box");
		        			driver.findElement(By.xpath(pro.getProperty(element))).clear();
		        			
		        			log.info("Typing " + text + " into " + element);
		        			driver.findElement(By.xpath(pro.getProperty(element))).sendKeys(text);
		        		}
		        		catch(Exception e1){
		        			log.debug("Unable to locate " + element + " element in child window " + childWindow + " : " + e1.getStackTrace());	            
		        		}
		            }		
		        }
		    }
			
			// Switching to Parent window i.e Main Window.
	        driver.switchTo().window(mainWindow);
	        
	      //Switch iFrames
	        log.debug("Switching iFrames");
			iFrames = driver.findElements(By.tagName("iframe")).size();
			for (int i=0; i<iFrames; i++){
				driver.switchTo().frame(i);
				try{
                	log.info("Clearing " + element + " text box");
        			driver.findElement(By.xpath(pro.getProperty(element))).clear();
        			
        			log.info("Typing " + text + " into " + element);
        			driver.findElement(By.xpath(pro.getProperty(element))).sendKeys(text);
        			break;
        		}
        		catch(Exception e1){
        			log.debug("Unable to locate " + element + " element in iFrame " + e1.getStackTrace());	            
        		}
			}
			driver.switchTo().defaultContent();
		}
	}	
		
	/**
	 * Type. 
	 * <p>
	 * This method types text into the specified element on screen
	 * it handles an element not found exception if the element is not displayed 
	 *
	 * @param  element - name of the element which references the xpath from the properties file
	 * @param  text - text to be input in the text box
	 * @return void     
	 */
	public void type(String element, String text){		
		try{
			log.info("Typing " + text + " into " + element);
			driver.findElement(By.xpath(pro.getProperty(element))).sendKeys(text);
		}
		catch(Exception e){
			log.debug("Unable to locate " + element + " element : " + e.getStackTrace());
			System.out.println(e.getStackTrace());
			
			//Switch windows
			s1 = driver.getWindowHandles();		
		    i1 = s1.iterator();
		    		   		    		
		    if (s1.size() > 1){
		    	log.debug("Switching windows");
		    	while(i1.hasNext())			
		        {		
					log.debug("Switching windows");
		            String childWindow=i1.next();		            		
		            if(!mainWindow.equalsIgnoreCase(childWindow))			
		            {    		                 
		                // Switching to Child window
		                driver.switchTo().window(childWindow);	 
		                try{
		                	log.info("Typing " + text + " into " + element);
		                	driver.findElement(By.xpath(pro.getProperty(element))).sendKeys(text);
		        		}
		        		catch(Exception e1){
		        			log.debug("Unable to locate " + element + " element in child window " + childWindow + " : " + e1.getStackTrace());	            
		        		}
		            }		
		        }
		    	// Switching to Parent window i.e Main Window.
		        driver.switchTo().window(mainWindow);
		    }
			
			
	        
	      //Switch iFrames
	        log.debug("Switching iframes");
			iFrames = driver.findElements(By.tagName("iframe")).size();
			System.out.println("iFrames : " + iFrames);
			for (int i=0; i<iFrames; i++){				
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe")));
				try{    
					System.out.println(i);
        			log.info("Typing " + text + " into " + element);
        			driver.findElement(By.xpath(pro.getProperty(element))).sendKeys(text);
        			System.out.println("iFrame number : " + i);
        			driver.switchTo().defaultContent();
        			break;
        		}
        		catch(Exception e1){
        			log.debug("Unable to locate " + element + " element in iFrame " + e1.getStackTrace());	            
        		}
			}		
	        	      
		}		
	}
	
	/**
	 * selectFromDropdown
	 * Returns a boolean value true if the value is selected from the drop down list. 
	 * <p>
	 * This method always returns immediately, whether or not the 
	 * value exists on the drop down list. When the value does not exist a value 
	 * of false is returned. 
	 *
	 * @param  		element  the element to be looked for on the screen
	 * @param  		value  the value to be selected from the drop down list
	 * @return      boolean value
	 * @see         
	 */
	public boolean selectFromDropdown(String element, String value){
		try{
			Select dropdown = new Select(driver.findElement(By.xpath(pro.getProperty(element))));

			try{
				log.info("Selecting " + value + " from " + element + " dropdown list.");
				dropdown.selectByVisibleText(value);
				return true;
			}
			catch(Exception e){
				log.debug(value + " not found");
				log.debug(e.getStackTrace());
				return false;
			}		
		}
		catch(Exception e){
			log.debug(element + " not found in object repository");
			return false;
		}
	}
	
	/**
	 * selectFromDropdown
	 * Returns a boolean value true if the value is selected from the drop down list. 
	 * <p>
	 * This method always returns immediately, whether or not the 
	 * value exists on the drop down list. When the value does not exist a value 
	 * of false is returned. 
	 *
	 * @param  		element  the element to be looked for on the screen
	 * @param  		index  the index value to be selected from the drop down list
	 * @return      boolean value
	 * @see         
	 */
	public boolean selectFromDropdown(String element, int index){
		try{
			Select dropdown = new Select(driver.findElement(By.xpath(pro.getProperty(element))));

			try{
				log.info("Selecting index #" + index + " from " + element + " dropdown list.");
				dropdown.selectByIndex(index);
				return true;
			}
			catch(Exception e){
				log.debug("Index #" + index + " not found");
				log.debug(e.getStackTrace());
				return false;
			}		
		}
		catch(Exception e){
			log.debug(element + " not found in object repository");
			return false;
		}
	}
	
	/**
	 * handleOkAlert
	 * Returns a boolean value true if OK is clicked on the alert. 
	 * <p>
	 * This method always returns immediately, whether or not the alert exists.
	 * When there is no alert displayed a value of false is returned. 
	 *
	 * @return      boolean value
	 * @see         
	 */
	public boolean handleOkAlert(){
		try{
			Alert alert = driver.switchTo().alert();
			log.info("Clicking OK on alert");
			alert.accept();
			return true;
		}
		catch (Exception e){
			log.debug("No alert open");
			log.debug(e.getStackTrace());
			return false;
		}
	}
	
	/**
	 * isElementPresent
	 * Returns a boolean value true if the element is on the screen. 
	 * <p>
	 * This method always returns immediately, whether or not the 
	 * element exists. When the element is not on the screen a value 
	 * of false is returned. 
	 *
	 * @param  		element  the element to be looked for on the screen
	 * @return      boolean value
	 * @see         
	 */
	public boolean isElementPresent(String element) {
	  try {
		  driver.findElement(By.xpath(pro.getProperty(element)));
	    return true;
	  }
	catch (org.openqa.selenium.NoSuchElementException e) {
		log.debug(element + " not found");
		log.debug(e.getStackTrace());
	    return false;
	  }
	}
	
	/**
	 * isElementNotPresent
	 * Returns a boolean value true if the element is not on the screen. 
	 * <p>
	 * This method always returns immediately, whether or not the 
	 * element exists. When the element is not on the screen a value 
	 * of true is returned. 
	 *
	 * @param  		element  the element to be looked for on the screen
	 * @return      boolean value
	 * @see         
	 */
	public boolean isElementNotPresent(String element) {
	  try {
		  driver.findElement(By.xpath(pro.getProperty(element)));
	    return false;
	  }
	catch (org.openqa.selenium.NoSuchElementException e) {		
	    return true;
	  }
	}
	
	
	/**
	 * capture
	 * Returns the path of the screenshot captured. 
	 * <p>
	 *
	 * @param  		
	 * @return      String the destination of the screenshot
	 * @see         
	 */
	public static String capture() throws IOException
    {
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
        Date date = new Date();		
		
        TakesScreenshot ts = (TakesScreenshot)driver;
        log.info("Capturing screenshot");
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") +"\\Results\\Screenshots\\screenshot"+dateFormat.format(date)+".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);   
        log.info("The screenshot path is " + dest);
        return dest;
    }
	
	
	public void hoverAndCLick(String dropdown, String select){
		Actions action = new Actions(driver);		 
		action.moveToElement(driver.findElement(By.xpath(pro.getProperty(dropdown)))).moveToElement(driver.findElement(By.xpath(pro.getProperty(select)))).click().build().perform();
	}
			
	public String firstName(){
		return "";
	}
	
	/**
	 * getElementValue
	 * Returns the the value of the element on screen. 
	 * <p>
	 *
	 * @param  		String the element displayed on screen whose value you want to return.
	 * @return      String the value of the element.
	 * @see         
	 */
    public String getElementValue (String element) {
        return driver.findElement(By.xpath(pro.getProperty(element))).getAttribute("value");
    }
    
    public String getElementText (String element) {
        return driver.findElement(By.xpath(pro.getProperty(element))).getText();
    }
    
    /**
	 * wait
	 * Waits for the specified element to be displayed on screen. 
	 * <p>
	 *
	 * @param  		element to be displayed on screen.
	 * @return     
	 * @see         
	 */
    public void waitForElement(String element, int timeout){
    	WebDriverWait wait = new WebDriverWait(driver, timeout);
    	log.info("Waiting " + timeout + " seconds for " + element + " to be visible.");
    	try{
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty(element))));
    	}
    	catch(Exception e){
    		log.info(e.getMessage());
    	}
		
    }
    
    
    /**
	 * wait
	 * Waits for the specified number of seconds. 
	 * <p>
	 *
	 * @param  		int the number of seconds you want to wait.
	 * @return     
	 * @see         
	 */
    public void wait(int seconds){
    	if (seconds == 1){
    		log.info("Waiting " + seconds + " second.");
    	}
    	else{
    		log.info("Waiting " + seconds + " seconds.");
    		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    	}    	
    	
    }
}
