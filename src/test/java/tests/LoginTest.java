package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Basetest;
import pages.LoginPage;

public class LoginTest extends Basetest {
	@Test
	public void valid_username_valid_password() {
		LoginPage lp = new LoginPage(driver);
		lp.login("Admin", "admin123");
		Assert.assertTrue(lp.is_loggedIn(), "User is not logged in");
	}
}
