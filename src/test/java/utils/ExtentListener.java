package utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.Basetest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentListener implements ITestListener {
	ExtentSparkReporter htmlreporter;
	ExtentReports report;
	ExtentTest test;
	WebDriver driver;

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
		driver = ((Basetest) result.getInstance()).driver;
		Utilities ut = new Utilities(driver);
		try {
			ut.capture_screenshot(result.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}

		String screenshot_path = System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\"
				+ result.getName() + ".png";

		if (result.getName().equalsIgnoreCase("valid_username_valid_password")) {
			test = report.createTest("Valid Login");
			test.addScreenCaptureFromPath(screenshot_path);
			test.generateLog(Status.PASS, MarkupHelper.createLabel("User logged in successfully", ExtentColor.GREEN));
		} else if (result.getName().equalsIgnoreCase("valid_username_invalid_password")) {
			test = report.createTest("Invalid Login - Wrong password");
			test.addScreenCaptureFromPath(screenshot_path);
			test.generateLog(Status.PASS,
					MarkupHelper.createLabel("Invalid login credentials error is visible", ExtentColor.GREEN));
		} else if (result.getName().equalsIgnoreCase("invalid_username_valid_password")) {
			test = report.createTest("Invalid Login - Wrong username");
			test.addScreenCaptureFromPath(screenshot_path);
			test.generateLog(Status.PASS,
					MarkupHelper.createLabel("Invalid login credentials error is visible", ExtentColor.GREEN));
		} else if (result.getName().equalsIgnoreCase("invalid_username_invalid_password")) {
			test = report.createTest("Invalid Login - Wrong username & password");
			test.addScreenCaptureFromPath(screenshot_path);
			test.generateLog(Status.PASS,
					MarkupHelper.createLabel("Invalid login credentials error is visible", ExtentColor.GREEN));
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		driver = ((Basetest) result.getInstance()).driver;
		Utilities ut = new Utilities(driver);
		try {
			ut.capture_screenshot(result.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}

		String screenshot_path = System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\"
				+ result.getName() + ".png";
		Throwable t = result.getThrowable();

		if (result.getName().equalsIgnoreCase("valid_username_valid_password")) {
			test = report.createTest("Valid Login");
		} else if (result.getName().equalsIgnoreCase("valid_username_invalid_password")) {
			test = report.createTest("Invalid Login - Wrong password");
		} else if (result.getName().equalsIgnoreCase("invalid_username_valid_password")) {
			test = report.createTest("Invalid Login - Wrong username");
		} else if (result.getName().equalsIgnoreCase("invalid_username_invalid_password")) {
			test = report.createTest("Invalid Login - Wrong username & password");
		}

		test.addScreenCaptureFromPath(screenshot_path);
		test.generateLog(Status.FAIL, MarkupHelper.createLabel(t.getMessage(), ExtentColor.RED));

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test = report.createTest(result.getName());
		test.generateLog(Status.SKIP, MarkupHelper.createLabel("Test Skipped", ExtentColor.YELLOW));
	}
}
