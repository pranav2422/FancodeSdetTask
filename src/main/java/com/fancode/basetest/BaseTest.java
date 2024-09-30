package com.fancode.basetest;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.RestAssured;

public class BaseTest {
	
	public ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;
    
	 @BeforeTest
		public void beforeTests() {
	      htmlReporter = new ExtentHtmlReporter(".//TestReport//AutomationReport.html");
	      htmlReporter.config().setEncoding("utf-8");
	      htmlReporter.config().setDocumentTitle("Automation Report");
	      htmlReporter.config().setReportName("FancodeSDETAssignment");
	      htmlReporter.config().setTheme(Theme.DARK);
	      extent = new ExtentReports();
	      extent.attachReporter(htmlReporter);
		}
	     
	   

		@BeforeMethod
		public void beforeMethods(Method testMethod ) {
			logger=extent.createTest(testMethod.getName());
		}

		@AfterMethod
		public void afterMethods(ITestResult result) {
			
			if(result.getStatus()==ITestResult.SUCCESS) {
				String methodName = result.getMethod().getMethodName();
				String logText = "Test Case: "+methodName + " Passed";
				Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
				logger.log(Status.PASS, m);
				
			}
			else if(result.getStatus()==ITestResult.FAILURE) {
				String methodName = result.getMethod().getMethodName();
				String logText = "Test Case: "+methodName + " Failed";
				Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
				logger.log(Status.FAIL, m);
				//System.out.println(" reach");
			}
			else if(result.getStatus()==ITestResult.SKIP) {
				String methodName = result.getMethod().getMethodName();
				String logText = "Test Case: "+methodName + " Skipped";
				Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
				logger.log(Status.SKIP, m);
				
			}
					
		}

		@AfterTest
		public void afterTests() {
			extent.flush();
			
		}
	
}
