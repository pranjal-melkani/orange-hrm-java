package base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Basedriver {
	public WebDriver driver;
	
	public Basedriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public void wait_until_element_is_visible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void click_on_element(By locator) {
		wait_until_element_is_visible(locator);
		WebElement element = driver.findElement(locator);
		element.click();
	}
	
	public void send_keys(By locator, String input_text) {
		wait_until_element_is_visible(locator);
		WebElement element = driver.findElement(locator);
		element.sendKeys(input_text);
	}
	
	public String get_url() {
		return driver.getCurrentUrl();
	}
}
