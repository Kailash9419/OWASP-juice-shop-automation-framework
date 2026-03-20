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

public class End2End_2 extends BaseClass {
	
	// LEVEL 1: Smoke Group
    // Iska kaam sirf ye check karna hai ki application "Zinda" hai ya nahi.
    @Test(groups = { "smoke", "regression" })
    public void loginAndDashboardCheck() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login(ConfigReader.get("email"), ConfigReader.get("password"));
        
        // Chhota assertion check karne ke liye ki login hua ya nahi
        Assert.assertTrue(driver.getCurrentUrl().contains("search"), "Login failed - Smoke Test Failed!");
        logger.info("Level 1: Smoke Test Passed");
    }

    // LEVEL 2: Sanity/Regression Group
    // Isme hum check karte hain ki main functionality (Basket/Checkout) kaam kar rahi hai.
    // 'dependsOnMethods' ensures ki agar login fail ho gaya toh ye skip ho jaye.
    @Test(groups = { "regression" }, dependsOnMethods = "loginAndDashboardCheck")
    public void productBasketAndCheckoutFlow() {
        // Product Search & Add
        ProductPage productPage = new ProductPage(driver);
        productPage.searchForProduct("Apple Juice");
        productPage.addProductToBasket("Apple Juice");

        // Basket Validation
        BasketPage basketPage = new BasketPage(driver);
        basketPage.navigateToCart();
        Assert.assertTrue(basketPage.verifyProductInCart().contains("Apple Juice"));
        basketPage.clickCheckout();

        // Address & Payment
        AddressPage addressPage = new AddressPage(driver);
        addressPage.addNewAddress("India", "Test User", "9876543210", "110001", "Delhi", "Main Street 123", "Delhi");
        addressPage.selectAddressAndContinue();

        PaymentPage payPage = new PaymentPage(driver);
        payPage.selectDeliveryMethod();
        payPage.completePaymentAndOrder("Test User", "1234567812345678");

        // Final Assertion
        Assert.assertTrue(payPage.getConfirmationMessage().contains("Thank you"), "Order failed!");
        logger.info("Level 2/3: Full Regression Flow Completed Successfully!");
    }

}
