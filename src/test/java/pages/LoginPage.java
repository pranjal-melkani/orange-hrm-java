package pages;

import org.openqa.selenium.WebDriver;

import base.Basedriver;
import locators.LoginPageLocators;

public class LoginPage extends Basedriver {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void login(String username, String password) {
		send_keys(LoginPageLocators.USERNAME_FIELD, username);
		send_keys(LoginPageLocators.PASSWORD_FIELD, password);
		click_on_element(LoginPageLocators.LOGIN_BTN);
	}

	public boolean is_loggedIn() {
		String current_url = get_url();
		if (current_url.contains("/dashboard")) {
			return true;
		}
		return false;
	}
}
