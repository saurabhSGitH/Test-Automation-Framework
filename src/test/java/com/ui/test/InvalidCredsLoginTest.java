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
public class InvalidCredsLoginTest extends TestBase {

	Logger logger = LoggerUtility.getLogger(this.getClass());

	private static final String INVALID_EMAIL_ADDRESS = "jatinvsharma@gmail.com";
	private static final String INVALID_PASSWORD = "QWERTY@gmail.com";

	@Test(description = "Verifies if the proper error message is shown for the user when they enter invalid credentials ", groups = {
			"e2e", "sanity", "smoke" })
	// need to give this script for data providers IMP, 01:34 2nd video revisiom

	public void loginTest() {

		assertEquals(homePage.goToLoginPage().doLoginWithInvalidCredentials(INVALID_EMAIL_ADDRESS, INVALID_PASSWORD)
				.getErrorMessage(), "Authentication failed.");

	}

}
