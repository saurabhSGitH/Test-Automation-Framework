package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class MyAccountPage extends BrowserUtility{

	private static final By USER_NAME_lOCATOR = By.xpath("//a[@title=\"View my customer account\"]/span");
	public MyAccountPage(WebDriver driver) {
		super(driver);

	}
//In page method we cannot use void return type, page method needs to return something.
	public String getUserName() {
		return getVisibleText(USER_NAME_lOCATOR);
	}
	
}
