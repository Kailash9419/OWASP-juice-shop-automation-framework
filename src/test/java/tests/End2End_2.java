package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import Base.BaseClass;
import Pages.AddressPage;
import Pages.BasketPage;
import Pages.LoginPage;
import Pages.PaymentPage;
import Pages.ProductPage;
import Utility.ConfigReader;
import Utility.RetryAnalyzer;

public class End2End_2 extends BaseClass {

    // ✅ retryAnalyzer wired properly
    @Test(groups = { "smoke", "regression" }, retryAnalyzer = RetryAnalyzer.class)
    public void loginAndDashboardCheck() {
        
    	LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login(ConfigReader.get("email"), ConfigReader.get("password"));
        
        // ✅ Wait for URL to update instead of asserting immediately
        boolean isLoggedIn = wait.until(ExpectedConditions.urlContains("search"));
        Assert.assertTrue(isLoggedIn, "Login failed - URL did not contain 'search'!");

        logger.info("Level 1: Smoke Test Passed");
    }

    // ✅ retryAnalyzer added here too
    // ✅ dependsOnMethods kept but alwaysRun = false (default) is fine
    @Test(
        groups = { "regression" },
        dependsOnMethods = "loginAndDashboardCheck",
        retryAnalyzer = RetryAnalyzer.class
    )
    public void productBasketAndCheckoutFlow() {
        ProductPage productPage = new ProductPage(driver);
        productPage.searchForProduct("Apple Juice");
        productPage.addProductToBasket("Apple Juice");

        BasketPage basketPage = new BasketPage(driver);
        basketPage.navigateToCart();
        Assert.assertTrue(
            basketPage.verifyProductInCart().contains("Apple Juice"),
            "Product not found in cart!"
        );
        basketPage.clickCheckout();

        AddressPage addressPage = new AddressPage(driver);
        addressPage.addNewAddress(
            "India", "Test User", "9876543210",
            "110001", "Delhi", "Main Street 123", "Delhi"
        );
        addressPage.selectAddressAndContinue();

        PaymentPage payPage = new PaymentPage(driver);
        payPage.selectDeliveryMethod();
        payPage.completePaymentAndOrder("Test User", "1234567812345678");

        Assert.assertTrue(
            payPage.getConfirmationMessage().contains("Thank you"),
            "Order failed!"
        );
        logger.info("Level 2/3: Full Regression Flow Completed Successfully!");
    }
}