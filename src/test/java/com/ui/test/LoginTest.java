package com.ui.test;

import static com.constants.Browser.*;



import static org.testng.Assert.*;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;


@Listeners(com.ui.listeners.TestListner.class)
public class LoginTest extends TestBase {

	

	Logger logger = LoggerUtility.getLogger(this.getClass());
	

//	@BeforeMethod(description = "Load the homepage of the website")
//	public void setup() {
//		logger.info("Load the homepage of the website");
//		homePage = new HomePage(EDGE);
//	}
	@Test(description = "Verifies with the valid user is able to login into the application", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
	// need to give this script for data providers IMP, 01:34 2nd video revisiom

	public void loginTest(User user) {

		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Saurabh Khandelwal");

	}

//	@Test(description = "Verifies with the valid user is able to login into the application", groups = { "e2e",
//			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
//
//	public void loginCSVTest(User user) {
//
//		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
//				"Saurabh Khandelwal");
//
//	}
//
//	@Test(description = "Verifies with the valid user is able to login into the application", groups = { "e2e",
//			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider"
//			, retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
//
//	public void loginExcelTest(User user) {
//
//		Logger logger = LoggerUtility.getLogger(this.getClass());
//		logger.info("Started my login Excel test");
//		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
//				"Saurabh Khandelwal");
//
//		logger.info("Login Excel test completed!!!");
//	}
}
