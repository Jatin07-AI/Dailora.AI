package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import POM.SignUpPage;
import base.BaseTest;
import fileUtility.PropertyFileUtility;
import javaUtility.JavaUtilityProgram;
import webDriverUtility.WebDriverUtilityProgram;

public class SignUpTest extends BaseTest {

    // ✅ Valid Signup Test
    @Test
    public void validSignUpTest() throws Throwable {
        PropertyFileUtility pp = new PropertyFileUtility();
        String URL = pp.toGetDataFromPropertiesFile("url");

        // Random test data
        JavaUtilityProgram jp = new JavaUtilityProgram();
        String RANDOMENAME = jp.generateRandomName();
        String UNIQUEEMAIL = jp.generateUniqueEmail();
        String PHONENUMBER = jp.generateUniquePhoneNumber();
        JavaUtilityProgram.generateRandomPassword RP = new JavaUtilityProgram.generateRandomPassword();
        String RPASSWORD = RP.getRandomPass(9);
        WebDriverUtilityProgram wb = new WebDriverUtilityProgram();
        wb.implicitlyWait(driver);
        driver.get(URL);

        SignUpPage sp = new SignUpPage(driver);
        sp.signUp(RANDOMENAME, UNIQUEEMAIL, PHONENUMBER, RPASSWORD);

        System.out.println("✅ Valid Signup Successful on with email: " + UNIQUEEMAIL);
        
        Thread.sleep(5000);
        driver.quit();
    }
}