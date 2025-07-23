package com.ui.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.utility.BrowserUtility;

public class LoginTest1 {

	public static void main(String[] args) {

		WebDriver wd = new ChromeDriver();// why creating object or chromedriver and giving reference of webdriver why
											// not chromedriver
		// itself because of loose coupling->chromedriver is childclass & webdriver is
		// interface, parent entity
		// whenever object is created of chromedriver memory is allocated, browser
		// window is launched till this code
		
		/*
		 * Rules for clean code Test Method! 1. Test scripts should be small 2. Dont put
		 * conditional statements, try catch, loops in test methods 3. Testscripts
		 * should only have test steps 4. Reduce the use of local variables 5. Should
		 * have atleast 1 assertion.verify something in test.
		 */

		BrowserUtility browserUtility = new BrowserUtility(wd);
		browserUtility.goToWebsite("http://www.automationpractice.pl/index.php");
		browserUtility.maximizeWindow();

		By signInLinkLocator = By.xpath("//a[contains(text(),\"Sign in\")]");
		browserUtility.clickOn(signInLinkLocator);

		By emailTextboxLocator = By.id("email");
		browserUtility.enterText(emailTextboxLocator, "degawow873@hosintoy.com");

		By passwordTextboxLocator = By.id("passwd");
		browserUtility.enterText(passwordTextboxLocator, "Password");

		By submitLoginButtonLocator = By.id("SubmitLogin");
		browserUtility.clickOn(submitLoginButtonLocator);

	}

}
