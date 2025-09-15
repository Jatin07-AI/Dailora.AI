package dataproviders;


import org.testng.annotations.DataProvider;

import fileUtility.PropertyFileUtility;

public class DataProviderUtility{
  		
		
	
	    @DataProvider(name = "loginData")
	    public Object[][] getData() throws Throwable{
	    	
	    	PropertyFileUtility pf = new PropertyFileUtility();
	    	
	    	String VALID_EMAIL = pf.toGetDataFromPropertiesFile("validEmail");
	    	String VALID_PASSWORD = pf.toGetDataFromPropertiesFile("validPassword");
	    	String INVALID_EMAIL = pf.toGetDataFromPropertiesFile("invalidEmail1");
	    	String INVALID_PASSWORD = pf.toGetDataFromPropertiesFile("invalidPassword1");
	    	
	        return new Object[][] {
	            {VALID_EMAIL,VALID_PASSWORD},  // ✅ valid un & password
	            {INVALID_EMAIL,VALID_PASSWORD}, // ❌ invalid email & valid password 
	            {INVALID_PASSWORD,VALID_EMAIL},  // ❌ invalid password & valid email
	            {INVALID_EMAIL,INVALID_PASSWORD} // ❌ invalid email & invalid password
	        };
	    }
	}


