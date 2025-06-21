package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	// DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException{
		
		String path=".\\testData\\Opencart_LoginData.xlsx"; // Taking the xl file from testData package
		
		ExcelUtility xlutil=new ExcelUtility(path); // We pass path parameter here because ExcelUtility.java has a constructor 
		
		int totalrows=xlutil.getRowCount("Sheet1"); // We invoked this method from ExcelUtility.java to get the total numbers of rows
		int totalcols=xlutil.getCellCount("Sheet1", 1); // We invoked this method from ExcelUtility.java // Here 1 is the row number
		
		String logindata[][]=new String [totalrows][totalcols]; // We have to have the same number of rows and cols in two dimensional Array which are in our Sheet1 that is why we passed totalrows and totalcols in two dimensional Array
		
		for(int i=1; i<=totalrows;i++) { // 1//Read the data from xl storing in two dimensional Array
			
			for(int j=0;j<totalcols;j++) { //0/ 
				
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j); // Why [i-1] because Array Index starts from 0 so we are not wasting the oth position in Two Dimensional Array and storing the values in two dimensional Array
			}
		}
		return logindata; // Returning the two dimensional Array
	}
	
	// DataProvider 2
	
	// DataProvider 3

}
