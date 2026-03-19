package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
	
	WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By selectAddressRadio = By.xpath("//mat-radio-button"); // Selects first available address
    By continueBtn = By.xpath("//button[@aria-label='Proceed to payment selection']");
    By deliveryOptionRadio = By.xpath("//mat-cell[contains(text(),'Standard')]/preceding-sibling::mat-cell//mat-radio-button");
    By continueToPaymentBtn = By.xpath("//button[@aria-label='Proceed to delivery method selection']");

    // Actions
    public void selectAddressAndContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(selectAddressRadio)).click();
        driver.findElement(continueBtn).click();
    }

    public void selectDeliveryAndContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(deliveryOptionRadio)).click();
        driver.findElement(continueToPaymentBtn).click();
    }

}
