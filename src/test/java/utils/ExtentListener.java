package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentListener implements ITestListener {
	ExtentSparkReporter htmlreporter;
	ExtentReports report;
	ExtentTest test;

	public void config_report() {
		String timestamp = new SimpleDateFormat("dd-MM-yy hh-mm-ss").format(new Date());
		String report_path = System.getProperty("user.dir") + "\\reports\\Automation Report " + timestamp + ".html";

		htmlreporter = new ExtentSparkReporter(report_path);
		report = new ExtentReports();
		report.attachReporter(htmlreporter);

		report.setSystemInfo("User", "pranjal.melkani");
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("Architecture", "x64");

		htmlreporter.config().setDocumentTitle("Orange HRM Automation");
		htmlreporter.config().setReportName("Log Report");
		htmlreporter.config().setTheme(Theme.DARK);
	}

	@Override
	public void onStart(ITestContext context) {
		config_report();
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test = report.createTest(result.getName());
		test.generateLog(Status.PASS, MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test = report.createTest(result.getName());
		test.generateLog(Status.FAIL, MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test = report.createTest(result.getName());
		test.generateLog(Status.SKIP, MarkupHelper.createLabel("Test Skipped", ExtentColor.YELLOW));
	}
}
