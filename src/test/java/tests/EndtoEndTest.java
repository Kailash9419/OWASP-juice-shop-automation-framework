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

public class EndtoEndTest extends BaseClass {
	
	@Test
    public void testProductOrderFlow() {
        logger.info("Starting End-to-End Order Flow Test");

        
        // 1. Login Flow
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login(ConfigReader.get("email"), ConfigReader.get("password"));
        logger.info("Login successful with user: " + ConfigReader.get("email"));

        // 2. Product Search & Add to Basket
        ProductPage productPage = new ProductPage(driver);
        String productName = "Apple Juice";
        productPage.searchForProduct(productName);
        productPage.addProductToBasket(productName);
        logger.info(productName + " added to basket successfully.");

        // 3. Basket Validation
        BasketPage basketPage = new BasketPage(driver);
        basketPage.navigateToCart();
        String actualProduct = basketPage.verifyProductInCart();
        
        // Validation: Check if the correct product is in the cart
        Assert.assertTrue(actualProduct.contains(productName), 
            "Expected product not found in cart! Found: " + actualProduct);
        logger.info("Product verification in cart successful.");

        // 4. Proceed to Checkout
        basketPage.clickCheckout();
        logger.info("Proceeded to checkout page.");
        
        // 5. Add address details
        AddressPage addressPage = new AddressPage(driver);
        addressPage.addNewAddress("India", "Test User", "9876543210", "110001", "Delhi", "Main Street 123", "Delhi");
        addressPage.selectAddressAndContinue();
        logger.info("Address selected successfully");
        
        // 6. DELIVERY & PAYMENT (Naya Page Class)
        PaymentPage payPage = new PaymentPage(driver);
        payPage.selectDeliveryMethod();
        
        // Card details (Dummy)
        payPage.completePaymentAndOrder("Test User", "1234567812345678");
        logger.info("Payment submitted");

        // 7. FINAL ASSERTION
        String confirmation = payPage.getConfirmationMessage();
        Assert.assertTrue(confirmation.contains("Thank you for your purchase"), "Order failed!");
        logger.info("End-to-End Flow Completed Successfully!");
    }

}
