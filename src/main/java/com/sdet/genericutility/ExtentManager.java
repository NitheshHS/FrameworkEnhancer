package com.sdet.genericutility;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	//1. configure extent report method which return extent report class ref
	/*
	 * -> Extent spark reporter location
	 * -> define the configuration(theme, document title, reportname)
	 * -> Attach all the configuration to report
	 * -> define system information(OS,url, browser)
	 */
	public ExtentReports configExtentReport(String path,String documentTitle,String reportName) {
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setDocumentTitle(documentTitle);
		reporter.config().setReportName(reportName);
		ExtentReports reports=new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("OS", System.getProperty("os.name"));
		return reports;
	}
	
	//2. Write a method to flush the report
	public void flushReport(ExtentReports reports) {
		reports.flush();
	}
	
	
	//3. write method to create test with parameters reports,testCaseName , author name and category of test
	public ExtentTest createTest(ExtentReports reports, String testCaseName, String authorName, String category) {
		return reports.createTest(testCaseName)
				.assignAuthor(authorName)
				.assignCategory(category);
	}
	
	//4. Write method for webdriver action info
	public static void info(ExtentTest test, String message) {
		test.log(Status.INFO,message);
	}

}
