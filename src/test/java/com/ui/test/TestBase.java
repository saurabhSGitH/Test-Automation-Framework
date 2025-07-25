package com.ui.test;

import static com.constants.Browser.CHROME;
import static com.constants.Browser.EDGE;


import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {

	protected HomePage homePage;

	Logger logger = LoggerUtility.getLogger(this.getClass());
	private boolean isLambdaTest;

	@Parameters({"browser", "isLambdaTest", "isHeadless"})
	
	
	@BeforeMethod(description = "Load the homepage of the website")
	public void setup(
			@Optional ("chrome") String browser, //If testNg uses @parameters and you dont provide value, testng xml will throw error hence use optional
			@Optional ("false") boolean isLambdaTest, 
			@Optional ("false") boolean isHeadless, ITestResult result) {
		
		this.isLambdaTest = isLambdaTest;
		
		
		WebDriver lambdaDriver;
		if(isLambdaTest) {
			
			lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());

			homePage = new HomePage(lambdaDriver);
			
		}
		else {
			//Running test on local machine
		logger.info("Load the homepage of the website");
		homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
	}
	}
	public BrowserUtility getInstance() {
		return homePage;
	}
	@AfterMethod(description ="Tear Down the browser")
	
	public void tearDown() {
		if(isLambdaTest) {
			LambdaTestUtility.quitSession(); //close browser session on lambda test
	}else {
		homePage.getDriver().quit();//close local browser session
	}
	}
}
