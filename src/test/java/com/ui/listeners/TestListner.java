package com.ui.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.test.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;

public class TestListner implements ITestListener {

	Logger logger = LoggerUtility.getLogger(this.getClass());

	ExtentSparkReporter extentSparkReporter;// Visual output UI of report
	ExtentReports extentReports;// Main class - does heavy lifting
	ExtentTest extentTest;// Individual test logger

	public void onTestStart(ITestResult result) {

		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		//extentTest = extentReports.createTest(result.getMethod().getMethodName());

		ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {

		logger.info(result.getMethod().getMethodName() + " " + "PASSED");

		//extentTest.log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");
		ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");
	}

	public void onTestFailure(ITestResult result) {
		logger.error(result.getMethod().getMethodName() + " " + "FAILED");
		logger.error(result.getThrowable().getMessage());
		//extentTest.log(Status.PASS, result.getMethod().getMethodName() + " " + "FAILED");
		ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "FAILED");
		ExtentReporterUtility.getTest().log(Status.FAIL,result.getThrowable().getMessage());
		
		Object testclass = result.getInstance();
		BrowserUtility browserUtility = ((TestBase)testclass).getInstance();
		
		logger.info("Capturing screenshot for the failed tests");
		String screenshotPath = browserUtility.takeScreenShot(result.getMethod().getMethodName());
		
		logger.info("Attaching screenshot to HTML file");

		ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenshotPath);
	}

	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName() + " " + "SKIPPED");
		//extentTest.log(Status.PASS, result.getMethod().getMethodName() + " " + "SKIPPED");
		ExtentReporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "SKIPPED");

	}

	public void onStart(ITestContext context) {
		logger.info("Test Suite Started");
		extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//report.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		ExtentReporterUtility.setupSparkReporter("report.html");

	}

	public void onFinish(ITestContext context) {
		logger.info("Test Suite Completed");
		ExtentReporterUtility.flushReport();
	}
}
