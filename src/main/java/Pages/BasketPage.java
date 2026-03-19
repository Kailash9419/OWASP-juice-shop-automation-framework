package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketPage {

	WebDriver driver;
    WebDriverWait wait;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By cartBtn = By.xpath("//button[@aria-label='Show the shopping cart']");
    By checkoutBtn = By.id("checkoutButton");
    By itemInCart = By.xpath("//mat-cell[contains(@class, 'mat-column-product')]");

    // Actions
    public void navigateToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartBtn)).click();
    }

    public String verifyProductInCart() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(itemInCart)).getText();
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }
}
