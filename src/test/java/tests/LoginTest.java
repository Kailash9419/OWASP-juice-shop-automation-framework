	package tests;
	
	import org.testng.Assert;
import org.testng.annotations.Test;
	
	import Base.BaseClass;
	import Pages.LoginPage;
	import dataprovider.LoginDataProvider;
	
	public class LoginTest extends BaseClass {
		
		 	@Test(dataProvider = "loginData",
		 			dataProviderClass = LoginDataProvider.class)
		 	public void verifyLoginNavigation(String email, String password, String expectedResult) {
	
		        LoginPage lp = new LoginPage(driver);
	
		        lp.navigateToLoginPage();   // Accounts click
		        
		        lp.login(email, password);     // Login click
		        
		        // DYNAMIC ASSERTIONS
		        if (expectedResult.equalsIgnoreCase("success")) {
		            Assert.assertTrue(lp.isUserLoggedIn(), "FAIL: Valid user " + email + " could not login.");
		            logger.info("PASS: Login successful as expected.");
		        } 
		        else if (expectedResult.equalsIgnoreCase("failure")) {
		            String error = lp.getErrorMessage();
		            Assert.assertTrue(error.contains("Invalid"), "FAIL: Error message not shown for invalid data: " + email);
		            logger.info("PASS: System correctly blocked invalid login for " + email);
		        }
		    }
		
	}
