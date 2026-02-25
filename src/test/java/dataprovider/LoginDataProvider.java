package dataprovider;

import org.testng.annotations.DataProvider;

import Utility.ExcelUtils;

public class LoginDataProvider {
	
	@DataProvider(name = "loginData")
	public Object[][] loginData(){
		
		ExcelUtils.loadExcelFile();
		
		int rows = ExcelUtils.getRowCount("Login");
		
		Object[][] data = new Object[rows][2];
		
		for(int i=1;i<=rows;i++) {
			data[i-1][0] = ExcelUtils.getCellData("Login", i, 0);
			data[i-1][1] = ExcelUtils.getCellData("Login", i, 1);
		}
		
		System.out.println(data);
		
		return data;
		
	}

}
