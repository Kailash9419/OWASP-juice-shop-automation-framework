//Store your locators (By.id, By.xpath) for the email field, password field, and login button.

package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;

	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Locators
	By accountBtn = By.xpath("//span[text() = \" Account \"]");
	By loginBtn = By.cssSelector("button[aria-label = 'Go to login page']");
	By email = By.id("email");
	By pass = By.id("password");
	By loginClick = By.id("loginButton");

	// Actions

	public void navigateToLoginPage() {
		if (!driver.getCurrentUrl().contains("login")) {
			wait.until(ExpectedConditions.elementToBeClickable(accountBtn)).click();
			wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
		}
	}

	public void login(String userName, String password) {

		// 1. Wait, CLEAR, and type email
		WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(email));
		emailInput.clear();
		emailInput.sendKeys(userName);

		// 2. Clear and type password
		WebElement passInput = driver.findElement(pass);
		passInput.clear();
		passInput.sendKeys(password);

		// 3. Click login
		driver.findElement(loginClick).click();

	}
	
	// Error message check karne ke liye (Negative Test)
	public String getErrorMessage() {
	    By errorMsgLocator = By.xpath("//div[contains(@class, 'error')]");
	    try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsgLocator)).getText();
        } catch (Exception e) {
            return "Error element not found";
        }
	}

	// User Profile check karne ke liye (Positive Test)
	public boolean isUserLoggedIn() {
	    // Agar login ho gaya toh URL change ho jayega ya 'Account' menu mein email dikhega
	    return driver.getCurrentUrl().contains("search");
	}

}
