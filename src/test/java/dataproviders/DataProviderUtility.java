package dataproviders;


import org.testng.annotations.DataProvider;

import fileUtility.PropertyFileUtility;

public class DataProviderUtility{
  		
	@DataProvider(name = "loginData")
	public Object[][] getData() throws Throwable {
	    PropertyFileUtility pf = new PropertyFileUtility();

	    String VALID_EMAIL = pf.toGetDataFromPropertiesFile("validEmail");
	    String VALID_PASSWORD = pf.toGetDataFromPropertiesFile("validPassword");
	    String INVALID_EMAIL = pf.toGetDataFromPropertiesFile("invalidEmail1");
	    String INVALID_PASSWORD = pf.toGetDataFromPropertiesFile("invalidPassword1");

	    return new Object[][] {
	        {"validLogin", VALID_EMAIL, VALID_PASSWORD},          // ✅ Valid Email + Valid Password
	        {"invalidPassword", VALID_EMAIL, INVALID_PASSWORD},   // ❌ Valid Email + Invalid Password
	        {"invalidEmail", INVALID_EMAIL, VALID_PASSWORD},      // ❌ Invalid Email + Valid Password
	        {"invalidBoth", INVALID_EMAIL, INVALID_PASSWORD}      // ❌ Invalid Email + Invalid Password
	    };
	}
}


