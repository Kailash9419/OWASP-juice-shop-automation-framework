package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage {
	
	WebDriver driver;
    WebDriverWait wait;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By deliveryOption = By.xpath("//mat-row[contains(.,'Standard')]//mat-radio-button"); // Standard Delivery
    By deliveryContinueBtn = By.xpath("//button[contains(@aria-label, 'payment')]");
    
    By addNewCardBtn = By.id("mat-expansion-panel-header-0");
    By cardName = By.xpath("//mat-form-field[contains(.,'Name')]//input");
    By cardNumber = By.xpath("//mat-form-field[contains(.,'Card Number')]//input");
    By expiryMonth = By.xpath("//mat-form-field[contains(.,'Expiry Month')]//select"); // Juice Shop specific
    By expiryYear = By.xpath("//mat-form-field[contains(.,'Expiry Year')]//select");
    By submitCard = By.id("submitButton");
    
    By selectCardRadio = By.xpath("(//mat-radio-button)[1]"); // pehla saved card select kar lega
    By continueToReviewBtn = By.xpath("//button[@aria-label='Proceed to review']");
    By placeOrderBtn = By.id("checkoutButton");
    By successMessage = By.xpath("//h1[contains(text(),'Thank you for your purchase!')]");

    // Actions
    public void selectDeliveryMethod() {
        wait.until(ExpectedConditions.elementToBeClickable(deliveryOption)).click();
        driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();
    }

    public void completePaymentAndOrder(String name, String cardNum) {
    	
    	wait.until(ExpectedConditions.urlContains("payment"));
        // Simple card selection if already added, or add logic here
        
    	try {
    		
    		// Step 1: Check if a card is already saved
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement existingCard = shortWait.until(ExpectedConditions.presenceOfElementLocated(selectCardRadio));
            existingCard.click();
            System.out.println("Using existing saved card.");
			
		} catch (Exception e) {
			// Step 2: If no card found, ADD NEW CARD using your locators
            System.out.println("No saved card found. Adding new card...");
            
            wait.until(ExpectedConditions.elementToBeClickable(addNewCardBtn)).click();
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(cardName)).sendKeys(name);
            driver.findElement(cardNumber).sendKeys(cardNum);
            
            // Selecting Expiry (Standard sendKeys works for Select tags in Juice Shop)
            driver.findElement(expiryMonth).sendKeys("12");
            driver.findElement(expiryYear).sendKeys("2029");
            
            driver.findElement(submitCard).click();
            
            // Step 3: Select the newly added card
            WebElement newCard = wait.until(ExpectedConditions.elementToBeClickable(selectCardRadio));
            newCard.click();
		}
    	
    	// Step 4: Proceed to Review and Place Order
    	wait.until(ExpectedConditions.elementToBeClickable(continueToReviewBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
    }
    
    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }

}
