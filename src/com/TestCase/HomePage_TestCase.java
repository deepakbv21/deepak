package com.TestCase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ApplicationFn.Amazon_Application_Fn;


public class HomePage_TestCase {
	
	public static WebDriver driver;
	public Amazon_Application_Fn oFun;
	
	@BeforeTest
	public void invokeBrowser(){
		oFun = new Amazon_Application_Fn(driver);
		oFun.Initiate_Browser();
	}
	
	
	@Test(enabled=true,priority=1)
	  public void homePageValidation() throws Exception {
			  
		  oFun.Lunch_Application();
		  oFun.validateHomePage();
		  oFun.validateLoginPage();
	
		  
	  }
	

}
