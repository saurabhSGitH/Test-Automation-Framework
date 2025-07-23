package com.ui.dataproviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;

public class LoginDataProvider {
	
	@DataProvider(name="LoginTestDataProvider")
	
	public Iterator<Object[]> loginDataProvider() throws FileNotFoundException {
		Gson gson = new Gson();
		File testDataFile = new File(System.getProperty("user.dir")+"\\testData\\loginData.json");
		FileReader fileReader = new FileReader(testDataFile);
		TestData data = gson.fromJson(fileReader, TestData.class);//deserialization 
/*Data provider can return data in Object[], Object[][] & interator format
 * POJO in java is plain old java object. its simple java class contains only field variable,
 * constructors, getters and setters and no business logic
 * Steps for data providers - 
 * Created test data folder-> Inside test data created login.json using data[] inside it key value pairs are given as json objects
 * Then did mapping of json objects to respective classes ie User.java & Test,java (POJO)
 * then created login data provider, in this reading json data provider with help of GSON class added this dependency
 * Then mapping done with deserialization reading json & creating java object giving reference to java object
 * then reading it with for each loop, get dtaa one by one a=& add in list ie dataToReturn 
 */


		
		List<Object[]> dataToReturn = new ArrayList<Object[]>();
		for(User user: data.getData()) {
			dataToReturn.add(new Object[] {user	});
	}

		return dataToReturn.iterator();
}
	@DataProvider(name="LoginTestCSVDataProvider")

	public Iterator<User> loginCSVDataProvider() {
	 return CSVReaderUtility.readCSVFile("loginData.csv");
	}
	 @DataProvider(name="LoginTestExcelDataProvider")

		public Iterator<User> loginExcelDataProvider() {
		 return ExcelReaderUtility.readExcelFile("LoginData.xlsx");
	
	
	}
	 }