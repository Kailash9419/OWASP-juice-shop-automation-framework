package tests;

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

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void loginAndDashboardCheck() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login(ConfigReader.get("email"), ConfigReader.get("password"));
        boolean isLoggedIn = wait.until(
            org.openqa.selenium.support.ui.ExpectedConditions.urlContains("search"));
        Assert.assertTrue(isLoggedIn, "Login failed!");
        logger.info("Level 1: Smoke Test Passed");
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    // ✅ Removed dependsOnMethods — each test is now independent
    // ✅ Each test does its own login so retry always starts fresh
    public void productBasketAndCheckoutFlow() {

        // ✅ Login first — don't depend on previous test's session
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login(ConfigReader.get("email"), ConfigReader.get("password"));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("search"));
        logger.info("Login successful for checkout flow.");

        // Product Search & Add
        ProductPage productPage = new ProductPage(driver);
        productPage.searchForProduct("Apple Juice");
        productPage.addProductToBasket("Apple Juice");

        // Basket Validation
        BasketPage basketPage = new BasketPage(driver);
        basketPage.navigateToCart();
        Assert.assertTrue(
            basketPage.verifyProductInCart().contains("Apple Juice"),
            "Product not found in cart!"
        );
        basketPage.clickCheckout();

        // Address & Payment
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
        logger.info("Level 2/3: Full Regression Flow Completed!");
    }
}