package AutomationFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


/**
 * @author      Mzwandile Mdladla <mzwandile.mdladla@absa.co.za>
 * @version     1.0                
 * @since       1.0          
 */
public abstract class BaseClass 
{   
	public static WebDriver driver;
	// Create Properties class object to read properties file
	public static final Properties pro = new Properties();
	public static final Logger log = Logger.getLogger(BaseClass.class);
	
	static String startTime;
	String endTime;
	public static Executor IWanna = new Executor();
	public static String mainWindow;
	public static ReadExcelData red;
	public static int currentRow = 2;
		
	//Reporter
	public static ExtentReports reports;
	public static ExtentTest testInfo;
	public static ExtentHtmlReporter htmlReporter;
	
	@BeforeTest
	public void setup() throws ParserConfigurationException, SAXException, IOException{
		
		//
		//Declare config.xml file path
		File xmlFile = new File(System.getProperty("user.dir") + "\\config\\config.xml");
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		
		//Get node list
		NodeList nlXml = doc.getChildNodes();
		Node nXml = nlXml.item(0);
		
		Element element = (Element)nXml;
		String env = element.getElementsByTagName("env").item(0).getTextContent();
		String appName = element.getElementsByTagName("appName").item(0).getTextContent();
		String appUrl = element.getElementsByTagName("appUrl").item(0).getTextContent();
		//
		
		htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir") + "/Resources/" + appName + "/Results/AutomationReport.html"));
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
		reports = new ExtentReports();		
		
		reports.setSystemInfo("Environment", env);
		reports.setSystemInfo("Application Name", appName);
		reports.setSystemInfo("Application Url", appUrl);
		reports.attachReporter(htmlReporter);		
	}
	
	@BeforeMethod
	public void register(Method method){
		String testName = method.getName();		
		testInfo = reports.createTest(testName);
	}
	
	@AfterMethod
	public void captureStatus(ITestResult result) throws IOException{
		String screenShotPath = Executor.capture();
		if (result.getStatus() == ITestResult.SUCCESS){			
			testInfo.log(Status.PASS, "The test method : " + result.getMethod().getMethodName() + " passed");
			testInfo.log(Status.INFO, "Screenshot : " + testInfo.addScreenCaptureFromPath(screenShotPath));
		}
		else if (result.getStatus() == ITestResult.FAILURE){
			testInfo.log(Status.FAIL, "Test failure : " + result.getThrowable());
			testInfo.log(Status.INFO, "Screenshot : " + testInfo.addScreenCaptureFromPath(screenShotPath));
		}
		else if (result.getStatus() == ITestResult.SKIP){
			testInfo.log(Status.SKIP, "The test method : " + result.getMethod().getMethodName() + " skipped");
		}	
	}
	
	@AfterTest
	public void cleanUp(){
		reports.flush();
	}
	//end reporter
	
	@BeforeClass
	public void setupApplication() throws Exception
	{	
		//Declare config.xml file path
		File xmlFile = new File(System.getProperty("user.dir") + "\\config\\config.xml");
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		
		//Get node list
		NodeList nlXml = doc.getChildNodes();
		Node nXml = nlXml.item(0);
		
		Element element = (Element)nXml;
		String appName = element.getElementsByTagName("appName").item(0).getTextContent();
		
		// Load test data sheet so we can use in our test(s)
		log.debug("Loading test data file");
		red = new ReadExcelData(".\\Resources\\" + appName + "\\TestData\\Driver.xlsx");
		RunTest.getRows();
		
		// Specify the object repository file location.
		File src = new File(".\\Resources\\" + appName + "\\ObjectRepository\\Object_Repo.properties");
	
		// Create  FileInputStream object
		FileInputStream fis = new FileInputStream(src);
	
		// Load file so we can use into our script
		log.debug("Loading " + src + " Object Repository file");
		pro.load(fis);
		
		log.debug("Starting Browser Session");
		String browser = red.getCellData("Credentials", "Browser", currentRow);
		log.debug("Starting " + browser + " browser session");
		
		startTime = getCurrentTimeStamp();
		launchBrowser(browser);
		driver.get(red.getCellData("Credentials", "url", currentRow));			
		Thread.sleep(2000);
		
		log.debug("Application Started");
		log.debug(driver.getTitle()+" page opened");
	}
	
	
	/** 
	 * Launch browser. 
	 * <p>
	 * This method launches the web browser specified in the test data sheet
	 *
	 * @return void     
	 */
	public void launchBrowser(String browser){
		if (browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", ".\\Resources\\Webdrivers\\Chrome\\32bit\\chromedriver.exe");
			driver = getDriver();
	        log.debug("Browser Session Started");
	        mainWindow = driver.getWindowHandle();
	        driver.manage().window().maximize();
		}
		else if(browser.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver", ".\\Resources\\Webdrivers\\IE\\32bit\\IEDriver.exe");
	        driver = new InternetExplorerDriver();
	        log.debug("Browser Session Started");
	        driver.manage().window().maximize();
		}
		else if(browser.equalsIgnoreCase("edge")){
			System.setProperty("webdriver.ie.driver", ".\\Resources\\Webdrivers\\Edge\\MicrosoftWebDriver.exe");
	        driver = new InternetExplorerDriver();
	        log.debug("Browser Session Started");
	        driver.manage().window().maximize();
		}
		else if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.ie.driver", ".\\Resources\\Webdrivers\\Firefox\\geckodriver.exe");
	        driver = new InternetExplorerDriver();
	        log.debug("Browser Session Started");
	        driver.manage().window().maximize();
		}
		else
		{
			System.setProperty("webdriver.chrome.driver", ".\\Resources\\Webdrivers\\Chrome\\32bit\\chromedriver.exe");
	        driver = new ChromeDriver();
	        log.debug("Browser Session Started");
	        driver.manage().window().maximize();
		}
	}	
	
	public void dict(String sheetName, String colName, int rowNum){
	}	
	
	/** 
	 * Get current time stamp. 
	 * <p>
	 * This method returns the current time stamp 
	 *
	 * @return strTimeStamp     
	 */
	public String getCurrentTimeStamp(){
		SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
	    Date now = new Date();
	    String strTimeStamp = sdfDate.format(now);
	    return strTimeStamp;
	}
		
	/** 
	 * Calculate execution time. 
	 * <p>
	 * This method returns the execution time of the test 
	 *
	 * @return strTimeStamp  the test execution time   
	 */
	public String getExecutionTime() throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat format1 = new SimpleDateFormat("mm:ss");
		Date time1 = format.parse(startTime);
		Date time2 = format.parse(endTime);
		long diff = time2.getTime() - time1.getTime();
		String time3 = format1.format(diff);
		return time3;
	}
	
	
	/** 
	 * Tear down. 
	 * <p>
	 * This method closes the browser session 
	 *
	 * @return      
	 */
	@AfterClass
	public void tearDown(){
		log.info("Ending Browser Session");
		//driver.quit();
		endTime = getCurrentTimeStamp();
		System.out.println("End time : " + endTime);
		try {
			System.out.println("Execution time is : " + getExecutionTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Browser Session Ended");
	}

	/** 
	 * getDriver. 
	 * <p>
	 * This method start a new browser session 
	 *
	 * @return      
	 */
	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		return new ChromeDriver();
	}		
}