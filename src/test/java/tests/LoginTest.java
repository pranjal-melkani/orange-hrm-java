package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Basetest;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTest extends Basetest {
	@Test(priority = 1)
	public void valid_username_valid_password() {
		LoginPage lp = new LoginPage(driver);
		lp.login("Admin", "admin123");
		Assert.assertTrue(lp.is_loggedIn(), "User is not logged in");
		DashboardPage dp = new DashboardPage(driver);
		Assert.assertTrue(dp.dashboard_isVisible(), "User is not logged in");
	}

	@Test(priority = 2)
	public void valid_username_invalid_password() {
		LoginPage lp = new LoginPage(driver);
		lp.login("Admin", "wrong_password");
		Assert.assertTrue(lp.invalid_credentials_error_isVisible(), "Invalid Credentials error is not visible");
	}
	
	@Test(priority = 3)
	public void invalid_username_valid_password() {
		LoginPage lp = new LoginPage(driver);
		lp.login("wrong_username", "admin123");
		Assert.assertTrue(lp.invalid_credentials_error_isVisible(), "Invalid Credentials error is not visible");
	}
	
	@Test(priority = 4)
	public void invalid_username_invalid_password() {
		LoginPage lp = new LoginPage(driver);
		lp.login("wrong_username", "wrong_password");
		Assert.assertTrue(lp.invalid_credentials_error_isVisible(), "Invalid Credentials error is not visible");
	}
}
