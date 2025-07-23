package com.ui.listeners;

import java.util.Properties;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JSONUtility;
import com.utility.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer {

//	private static final int MAX_NUMBER_OF_ATTEMPTS = Integer.parseInt(PropertiesUtil.readProperty(Env.DEV, "MAX_NUMBER_OF_ATTEMPTS"));//every tc will get 3 attempts
	
	private static final int MAX_NUMBER_OF_ATTEMPTS = JSONUtility.readJSON(Env.QA).getMAX_NUMBER_OF_ATTEMPTS();

	private static int currentAttempt = 1;
	
	@Override
	public boolean retry(ITestResult result) {

		if(currentAttempt<=MAX_NUMBER_OF_ATTEMPTS) {
			currentAttempt++;
			return true;
		}
		return false;
	}
/*First approach ->Using IRetryAnalyzer for retrying failed test cases imp for interview
 * "ITestResult" will give all info about tests but "result" will store info for failed tests
 * if retry method is true ie go ahead & run the failed tc
 * if retry method is false ie Testng will mark test as failed tc
 * Second approach ->testng-failed.xml in test output folder
 * rightclick on testng-failed.xml run as testng suite
 */
}
