package tests;

import org.testng.Reporter;
import org.testng.annotations.Test;

import POM.LoginPage;
import base.BaseTest;
import dataproviders.DataProviderUtility;
import javaUtility.JavaUtilityProgram;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = DataProviderUtility.class)
    public void loginTest(String username, String password) throws Throwable {

    	Reporter.log("Running test on browser: " + currentBrowser,true);
    	
        LoginPage lp = new LoginPage(driver);
        JavaUtilityProgram jp = new JavaUtilityProgram();
        String DateAndTime = jp.getCurrentDateAndTime();
        Reporter.log("Attempting login on: " +DateAndTime,true);
        Reporter.log("Attempting login with username: " +username +" : "+ password,true);

        // perform login (assumes LoginIntoApp waits for either success or error)
        lp.LoginIntoApp(username, password);
        
        String Result = lp.getLoginResult(username);
        
        
     // log result in console + reporter
        Reporter.log("[" + currentBrowser + "] Login result for user [" + username +" "+ password +"] : " + Result, true);
        Reporter.log("[Thread ID : " + Thread.currentThread().getId() + "] " + Result, true);

        if (Result.contains("Invalid Email") || Result.contains("Invalid Credentials")) {
            Reporter.log("[" + currentBrowser + "] Login failed → Closing browser", true);
            driver.quit();
        } else if (Result.contains("Login Successful")) {
            Reporter.log("[" + currentBrowser + "] Login successful → Logging out and closing browser", true);
            lp.LogoutFromApp();
            driver.quit();
        }
    }
}
