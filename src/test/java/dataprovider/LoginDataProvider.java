package dataprovider;

import org.testng.annotations.DataProvider;

import Utility.ExcelUtils;

public class LoginDataProvider {
	
	@DataProvider(name = "loginData")
	public Object[][] loginData(){
		
		ExcelUtils.loadExcelFile();
		int rows = ExcelUtils.getRowCount("Login");
		
		// Rows - 1 kyunki hum Header row (0) ko skip kar rahe hain
        // Column count 3 kar diya (Email, Pass, ExpectedResult)
		Object[][] data = new Object[rows][3];
		
		for(int i=1;i<=rows;i++) {
			data[i-1][0] = ExcelUtils.getCellData("Login", i, 0); // Email
            data[i-1][1] = ExcelUtils.getCellData("Login", i, 1); // Password
            data[i-1][2] = ExcelUtils.getCellData("Login", i, 2); // Expected (Success/Failure)
			
		}
		
		System.out.println(data);
		
		return data;
		
	}

}
