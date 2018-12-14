package com.TestCase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ApplicationFn.Amazon_Application_Fn;

public class LoginPage_TestCase {

	public static WebDriver driver;
	public Amazon_Application_Fn oFun;
	
	
	 
	@Test(enabled=true,priority=3)
	  public void loginPageValidation() throws Exception {
		
		  oFun = new Amazon_Application_Fn(driver);
		  oFun.validateLoginPage();
		  
		  //return driver;
	  }
	
}
