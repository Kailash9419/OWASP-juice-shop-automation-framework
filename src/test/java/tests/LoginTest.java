package tests;

import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.LoginPage;
import dataprovider.LoginDataProvider;

public class LoginTest extends BaseClass {
	
	 	@Test(dataProvider = "loginData",
	 			dataProviderClass = LoginDataProvider.class)
	 	public void verifyLoginNavigation(String email, String password) {

	        LoginPage lp = new LoginPage(driver);

	        lp.navigateToLoginPage();   // Accounts click
	        
	        lp.login(email, password);     // Login click
	    }
	

}
