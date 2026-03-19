package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Yahan hum products ko "Add to Basket" karenge.

public class ProductPage  {
	
	WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By searchIcon = By.xpath("//mat-icon[contains(text(),'search')]");
    By searchInput = By.id("mat-input-1");
    
    // Dynamic XPath template for Add to Basket button
    String addToBasketBtnTemplate = "//button[@aria-label='Add to Basket']";

    // Actions
    public void searchForProduct(String productName) {
        wait.until(ExpectedConditions.elementToBeClickable(searchIcon)).click();
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(productName + Keys.ENTER);
    }

    public void addProductToBasket(String productName) {
        By dynamicAddToCart = By.xpath(String.format(addToBasketBtnTemplate, productName));
        wait.until(ExpectedConditions.elementToBeClickable(dynamicAddToCart)).click();
    }

}
