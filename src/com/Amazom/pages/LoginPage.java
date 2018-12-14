package com.Amazom.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import utilites.Wrapper;

public class LoginPage {
	private WebDriver driver;
	static Wrapper oWrapper;
	ExtentReports logger;
	By emailLogin = By.xpath("//*[@name='email']");
	By password = By.xpath("//*[@name='password']");
	By login = By.xpath("//*[@id='signInSubmit']");
	By welcome_user = By.xpath("//a[@id='nav-link-yourAccount']/*[contains(@class,'nav-line-1')]");
	By Sign_Out = By.xpath("//a[@id='nav-item-signout']/*[contains(text(),'Sign Out')]");
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		oWrapper = new Wrapper(driver);
		logger = new ExtentReports();
		logger.init("./Report/Amazon_Application.html", false);
	}
	
	public void loginValidation() throws Exception{
		
		ArrayList<String> Account =readExel(0);

		ArrayList<String>FirstName=readExel(1);
	
		ArrayList<String> LastName=readExel(2);
		for(int i = 0;i<Account .size();i++){
			
		logger.startTest("Login Page UI Validation", "Validate the Element Presence in Login Page");
		oWrapper.waituntilVisible(emailLogin, 30);
		boolean email_Textbox = oWrapper.verifyElementpresent(emailLogin);
		if(email_Textbox==true){
			logger.log(LogStatus.PASS,"Email Textbox is presence in Amazon Home Page");
			oWrapper.typeValue(emailLogin, "mathan.chandrasekar@gmail.com");
		}else{
			logger.log(LogStatus.FAIL,"Email Textbox is not presence in Amazon Home Page");
		}
		oWrapper.waituntilVisible(password, 30);
		boolean password_Textbox = oWrapper.verifyElementpresent(password);
		if(password_Textbox==true){
			logger.log(LogStatus.PASS,"Password Textbox is presence in Amazon Home Page");
			oWrapper.typeValue(password, "Iloveindia");
		}else{
			logger.log(LogStatus.FAIL,"Password Textbox is not presence in Amazon Home Page");
		}
		
		oWrapper.waituntilVisible(login, 30);
		boolean login_Button = oWrapper.verifyElementpresent(login);
		if(login_Button==true){
			logger.log(LogStatus.PASS,"Login Button is presence in Amazon Home Page");
			oWrapper.clickElement(login);
		}else{
			logger.log(LogStatus.FAIL,"Login Button is not presence in Amazon Home Page");
		}
		//oWrapper.waitforPageLoad(30);
		
		String sText = oWrapper.getText(welcome_user);
		System.out.println("WelCome User : "+sText);
		if(sText.contains("Vijay")){
			logger.log(LogStatus.PASS,"User Logged In Successfully in Amazon");
		}else{
			logger.log(LogStatus.FAIL,"User Log-In Failed in Amazon");
			oWrapper.getscreenshot("Signin_Failure");
			logger.attachScreenshot("./Screenshot/Signin_Failure.png", "Signin Failed");
		
		}
		
		oWrapper.MouseOver(welcome_user);
		logger.log(LogStatus.INFO,"Mouse Over Your Order Menu");
		oWrapper.waituntilVisible(Sign_Out, 30);
		oWrapper.clickElement(Sign_Out);
		logger.log(LogStatus.INFO,"Clicked On Signout Button");
		oWrapper.waitforPageLoad(30);
		login_Button = oWrapper.verifyElementpresent(login);
		if(login_Button==true){
			logger.log(LogStatus.PASS,"User Logged Out Successfully from Amazon Home Page");
		}else{
			logger.log(LogStatus.FAIL,"User Not Logged Out Successfully from Amazon Home Page");
			oWrapper.getscreenshot("Signout_Failure");
			logger.attachScreenshot("./Screenshot/Signout_Failure.png", "Signout Failed");
		}
		
	}
	
}
}
