package utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.relevantcodes.extentreports.ExtentReports;



public class Wrapper  {
	
	private WebDriver driver;
	PropertyUtility util;
	static ExtentReports oReport;
	
		
	public Wrapper(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public void Initiate_Browser(){
		String sFile = "./Object_Repository/Environment_Variables.properties";
		util = new PropertyUtility(sFile);
		String sBroswer = util.getEnvironmentProperty("Browser").toUpperCase();
		
		switch (sBroswer) {
		case "FIREFOX":
			driver = new FirefoxDriver();
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver", "./Driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		default:
			System.out.println("Broeser Type is Wrong, So invoking default FireFox Browser ");
			driver = new FirefoxDriver();
			break;
		}
	}
	
	/*public String getscreenshot(String File_Name) throws Exception 
    {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
           // String sPath = "./Report_Output/Screenshot/"+File_Name+".jpg";
            String sPath = "./Screenshot/"+File_Name+".png";
            FileUtils.copyFile(scrFile, new File(sPath));
            return sPath;
    }*/
	
	public String getscreenshot(String File_Name) throws Exception 
    {
            Date d = new Date();
            Timestamp t = new Timestamp(d.getTime());
            String timeStamp = t.toString();
            timeStamp = timeStamp.replace(' ', '_');
            timeStamp = timeStamp.replace(':', '_');
            
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            // String sPath = "./Report_Output/Screenshot/"+File_Name+".jpg";
            
            String sPath = "D:\\Automation\\Automation_POM\\Screenshot\\"+File_Name+ "_" + timeStamp + ".png";
            FileUtils.copyFile(scrFile, new File(sPath));
            return sPath;
    }
	
	public   ArrayList<String> readExel(int colNo) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream("E:\\Selenium\\Testleaf Selenium Library\\selenium_projects\\selenium\\Excel\\patientCreation.xlsx");
	
	System.out.println("Deepak");


	XSSFWorkbook wb = new XSSFWorkbook(fis);

	XSSFSheet s=wb.getSheet("Deepak");

	Iterator<Row> rowiterator=s.iterator();

	rowiterator.next();

	ArrayList <String> list = new ArrayList <String>();

	while (rowiterator.hasNext()){
	/*	System.out.println(colNo);*/

	list.add(rowiterator.next().getCell(colNo).getStringCellValue());
	//System.out.println("test");
	}
	

	System.out.println(list.size());
	System.out.println("List ::::" +list);

	return list;
	}
	
	public void Lunch_Application(){
		String sURL = util.getEnvironmentProperty("Arivar_URL");
		driver.manage().window().maximize();
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}
	
	public String getPageTitle(){
		String sTitle = driver.getTitle().trim();
		return sTitle;
	}
	
	/*************************************************************************************
	*Method Name : waituntil invisible 
	*Method Description : Towait until the element is invisible and return webelement
	*input parameters : By locator, int timeout

	**************************************************************************************/
	public WebElement waituntilVisible(By location ,int timeout){
	WebDriverWait oWait = new WebDriverWait(driver, timeout);
	WebElement element = null;
	//reportLog.log("Wait until visible - Before"+locator,level.INFO);
	oWait.until(ExpectedConditions.presenceOfElementLocated(location));
	return element;
	}
	
	/******************************************************************

	*method name : verifyElement present

	*Method Description : to verify if any element present and return boolean value

	*input parameters : by locator

	******************************************************************/


	public boolean verifyElementpresent(By locator){
		boolean result= false;
		try{
		WebElement element = driver.findElement(locator);
		if(element !=null){
			result=true;
		}}catch(Exception e){
			result=false;
		}
		return result;
	}
	/******************************************************************

	*method name : Is Element displayed

	*Method Description : to verify if any element present and return boolean value

	*input parameters : by locator

	******************************************************************/


	public boolean Iselementdisplayed(By locator){
		boolean result= false;
		try{WebElement element = driver.findElement(locator);
		if(element.isDisplayed()){
			result=true;
		}}catch(Exception e){
			result=false;
		}
		return result;
	}
	

/*********************************************************************************************************************************************************************
*Method Name : waitUntilVisible 
*Method Description : To wait until the element is visible and return WebElement
*input parameters : By locator, int timeout

*********************************************************************************************************************************************************************/

public WebElement waitUntilVisible(By locator ,int timeout){
		WebElement element = null;
		//system.out.println(driver);
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		//ReportLog.log("Wait Until Visibl - Before"+locator,Level.INFO);
		element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		//ReportLog.log("Wait Until Visible-After"+locator,Level.INFO); 
		return element;
		}
	/************************************************************************************************************************************************************************
	*Method Name : typeValue 
	*Method Description : To enter text in the locator
	*input parameters : By locators , String text

	************************************************************************************************************************************************************************/


	public void typeValue(By locator,String text){

			WebElement element = waitUntilVisible(locator,30);
			element.click();
			element.clear();
			element.sendKeys(text);
			//system.out.println("Entered Text in ---> "+getLocatorName(locator)+"--->"+text);
			/////ReportLog.log("Entered Text in " + getLocatorName(locator),Leve.INFO);
			}
	
	/****************************************************************************************************************************************************************************
	*method name : click element
	 
	*Method Description : To click on an element once visible

	*input parameters : by locator

	****************************************************************************************************************************************************************************/


	public void clickElement(By locator){
			WebElement element = waitUntilVisible(locator,30);
			element.click();
			}
	
	/****************************************************************************************************************************************************************************
	*method name : Wait for Page Load Time
	 
	*Method Description :  Wait for Page Load Time

	*input parameters : by locator, string text

	****************************************************************************************************************************************************************************/


	public void waitforPageLoad(int time){
			driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
			}

	/****************************************************************************************************************************************************************************
	*method name : Get Text
	 
	*Method Description :  Get Text value by using Locator

	*input parameters : by locator

	****************************************************************************************************************************************************************************/


	public String getText(By locator){
		WebElement element = waitUntilVisible(locator,30);
		String text = element.getText().trim();
		return text;
			}
	
	/****************************************************************************************************************************************************************************
	*method name : SelectByIndex
	 
	*Method Description :  Select By Index by using Locator

	*input parameters : by locator

	****************************************************************************************************************************************************************************/


	public void selectByIndex(By locator){
		WebElement element = waitUntilVisible(locator,30);
		Select oSelect = new Select(element);
		oSelect.selectByIndex(0);
		}
	
	/****************************************************************************************************************************************************************************
	*method name : SelectByVisibleText
	 
	*Method Description :  Select By Visible Text by using Locator

	*input parameters : by locator and String Text

	****************************************************************************************************************************************************************************/


	public void SelectByVisibleText(By locator,String sValue){
		WebElement element = waitUntilVisible(locator,30);
		Select oSelect = new Select(element);
		oSelect.selectByVisibleText(sValue);
		}
	
	/****************************************************************************************************************************************************************************
	*method name : getListValue
	 
	*Method Description :  Get Listed Value by using Locator

	*input parameters : by locator 
	 * @return 

	****************************************************************************************************************************************************************************/


	public List<String> getListValue(By locator){
		WebElement element = waitUntilVisible(locator,30);
		List<WebElement> oList = new ArrayList<>();
		List<String> allList = new ArrayList<>();
		oList = element.findElements(locator);
		for(int  i=0;i<oList.size();i++){
			String otextEle = oList.get(i).getText();
			allList.add(otextEle);
		}
		return allList;
		}
	
	/****************************************************************************************************************************************************************************
	*method name : Mouse Over
	 
	*Method Description :  Mouse Over by using Locator

	*input parameters : by locator
	 * @throws InterruptedException 

	****************************************************************************************************************************************************************************/


	public void MouseOver(By locator) throws Exception {
		WebElement element = waitUntilVisible(locator,30);
		Actions oMouse = new Actions(driver);
		oMouse.moveToElement(element).build().perform();
		Thread.sleep(2000);
		
		
			}
	/****************************************************************************************************************************************************************************
	*method name : getListSize
	 
	*Method Description :  It will return the List Size for the locators

	*input parameters : by locator
	 

	****************************************************************************************************************************************************************************/


	public int getListSize(By locator) throws Exception {
		WebElement element = waitUntilVisible(locator,30);
		List<WebElement> oList = element.findElements(locator);
		return oList.size();
			
			}
	
	/****************************************************************************************************************************************************************************
	*method name : Wait for provied millisecond
	 
	*Method Description :  Wait for provied millisecond

	*input parameters : by Long Value
	 * @throws Exception 

	****************************************************************************************************************************************************************************/


	public void waitThread(long time) throws Exception{
			Thread.sleep(time);
			}
}
