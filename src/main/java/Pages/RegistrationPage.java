package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

	WebDriver driver;
	WebDriverWait wait;

	// Constructor
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Locators
	By emailField = By.id("emailControl");
	By passwordField = By.id("passwordControl");
	By repeatPasswordField = By.id("repeatPasswordControl");
	By securityQuestionDropdown = By.name("securityQuestion");
	By securityQuestionOption = By.xpath("//mat-option[1]"); // Pehla option select karne ke liye
	By securityAnswerField = By.id("securityAnswerControl");
	By registerButton = By.id("registerButton");
	
	// Navigation helper (Agar direct registration page par jana ho)
	By accountBtn = By.id("navbarAccount");
	By loginBtn = By.id("navbarLoginButton");
	By newUserLink = By.linkText("Not yet a customer?");

	// Actions
	
	public void navigateToRegistrationPage() {
		wait.until(ExpectedConditions.elementToBeClickable(accountBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(newUserLink)).click();
	}

	public void registerUser(String email, String password, String securityAnswer) {
		
		// 1. Enter Email
		WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
		emailInput.clear();
		emailInput.sendKeys(email);

		// 2. Enter Passwords
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(repeatPasswordField).sendKeys(password);

		// 3. Handle Security Question Dropdown
		driver.findElement(securityQuestionDropdown).click();
		wait.until(ExpectedConditions.elementToBeClickable(securityQuestionOption)).click();

		// 4. Enter Security Answer
		driver.findElement(securityAnswerField).sendKeys(securityAnswer);

		// 5. Click Register
		wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
	}
}
