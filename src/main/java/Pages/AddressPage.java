package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddressPage {

	WebDriver driver;
    WebDriverWait wait;

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By addNewAddressBtn = By.xpath("//button[@aria-label='Add a new address']");
    By countryInput = By.xpath("//input[@placeholder='Please provide a country.']");
    By nameInput = By.xpath("//input[@placeholder='Please provide a name.']");
    By mobileInput = By.xpath("//input[@placeholder='Please provide a mobile number.']");
    By zipInput = By.xpath("//input[@placeholder='Please provide a ZIP code.']");
    By addressInput = By.id("address");
    By cityInput = By.xpath("//input[@placeholder='Please provide a city.']");
    By stateInput = By.xpath("//input[@placeholder='Please provide a state.']");
    By submitBtn = By.id("submitButton");
    
    By selectAddressRadio = By.xpath("//mat-radio-button");
    By continueBtn = By.xpath("//button[@aria-label='Proceed to payment selection']");

    // Actions
    public void addNewAddress(String country, String name, String mobile, String zip, String city, String address, String state) {
        wait.until(ExpectedConditions.elementToBeClickable(addNewAddressBtn)).click();
        driver.findElement(countryInput).sendKeys(country);
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(mobileInput).sendKeys(mobile);
        driver.findElement(zipInput).sendKeys(zip);
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(stateInput).sendKeys(state);
        driver.findElement(submitBtn).click();
    }

    public void selectAddressAndContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(selectAddressRadio)).click();
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }
}
