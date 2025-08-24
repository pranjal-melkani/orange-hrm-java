package locators;

import org.openqa.selenium.By;

public class LoginPageLocators {
	public static final By USERNAME_FIELD = By.name("username");
	public static final By PASSWORD_FIELD = By.name("password");
	public static final By LOGIN_BTN = By.tagName("button");
	public static final By INVALID_CREDENTIALS_ERROR = By
			.xpath("//div[@class='orangehrm-login-error']//p[text()='Invalid credentials']");
}
