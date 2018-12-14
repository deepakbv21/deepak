package com.ApplicationFn;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import com.Amazom.pages.HomePage;
import com.Amazom.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import utilites.PropertyUtility;


public class Amazon_Application_Fn {
	
	public static WebDriver driver;
	ExtentReports logger;
	PropertyUtility util;
	public HomePage oPage;
	public LoginPage oLogin;
	String sFile = "./Object_Repository/Environment_Variables.properties";
	public Amazon_Application_Fn(WebDriver driver){
		this.driver = driver;
		util = new PropertyUtility(sFile);
		logger = new ExtentReports();
	}
	
public void Initiate_Browser(){
		logger.init("./Report/Amazon_Application.html", true);
		logger.startTest("Browser Invoke", "Invoking Corresponding Browser");
		
		
		String sBroswer = "CHROME";
		
		switch (sBroswer) {
		case "FIREFOX":
			driver = new FirefoxDriver();
			logger.log(LogStatus.INFO,"FireFox Browser Invoked Successfully");
			
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver", "./Driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			logger.log(LogStatus.INFO,"IE Browser Invoked Successfully");
			
			break;
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
			driver = new ChromeDriver();
			logger.log(LogStatus.INFO,"Chrome Browser Invoked Successfully");
			
			break;
		default:
			System.out.println("Browser Type is Wrong, So invoking default FireFox Browser ");
			driver = new FirefoxDriver();
			logger.log(LogStatus.INFO,"FireFox Browser Invoked Successfully");
			
			break;
		}
		
		logger.endTest();	
	}

public void Lunch_Application(){
	logger.startTest("Navigate URL", "Navigate to the URL");
	String sURL = util.getEnvironmentProperty("Amazon");
	driver.manage().window().maximize();
	logger.log(LogStatus.INFO,"Maximize the Window");
	driver.get(sURL);
	driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	logger.log(LogStatus.INFO,"Application Lunched Successfully");
	logger.endTest();
}

public void validateHomePage() throws Exception{ 
	oPage = new HomePage(driver);
	oPage.Home_Page_Title(sFile);
	oPage.clickSignOn();
} 

public void validateLoginPage() throws Exception{ 
	oLogin = new LoginPage(driver);
	oLogin.loginValidation();
}

}
