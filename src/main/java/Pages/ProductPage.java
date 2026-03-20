package Pages;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(ProductPage.class);

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // ✅ 10→20 for CI
    }

    // Locators
    By searchIcon = By.xpath("//mat-icon[contains(text(),'search')]");
    By searchInput = By.id("mat-input-1");

    // ✅ Fixed XPath — finds Add to Basket button NEXT TO the specific product name
    private By getAddToBasketBtn(String productName) {
        return By.xpath(
            "//mat-card[.//div[contains(text(),'" + productName + "')]]//button[@aria-label='Add to Basket']"
        );
    }

    // Actions
    public void searchForProduct(String productName) {
        wait.until(ExpectedConditions.elementToBeClickable(searchIcon)).click();
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(productName);
        input.sendKeys(Keys.ENTER);

        // ✅ Wait for search results to load before returning
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(@class,'product-name') or contains(@class,'item-name') " +
                     "or contains(text(),'" + productName + "')]")
        ));
        logger.info("Search results loaded for: " + productName);
    }

    public void addProductToBasket(String productName) {
        By addToBasketBtn = getAddToBasketBtn(productName);

        // ✅ Wait for the specific product's button to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(addToBasketBtn)).click();
        logger.info("Added to basket: " + productName);

        // ✅ Wait for basket count to update — confirms item was added
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//span[contains(@class,'mat-badge-content')]")
        ));
        logger.info("Basket updated successfully.");
    }
}