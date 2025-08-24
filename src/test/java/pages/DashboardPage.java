package pages;

import org.openqa.selenium.WebDriver;

import base.Basedriver;
import locators.DashboardPageLocators;

public class DashboardPage extends Basedriver {

	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	public boolean dashboard_isVisible() {
		try {
			wait_until_element_is_visible(DashboardPageLocators.DASHBOARD_HEADER);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
