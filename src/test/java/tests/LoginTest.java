package tests;

import org.testng.annotations.Test;
import POM.LoginPage;
import base.BaseTest;
import fileUtility.PropertyFileUtility;
import webDriverUtility.WebDriverUtilityProgram;


public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() throws Throwable {
        PropertyFileUtility pp = new PropertyFileUtility();
        String URL = pp.toGetDataFromPropertiesFile("url");
        String vEMAIL = pp.toGetDataFromPropertiesFile("validEmail");
        String vPASSWORD = pp.toGetDataFromPropertiesFile("validPassword");
        WebDriverUtilityProgram wb = new WebDriverUtilityProgram();
        wb.implicitlyWait(driver);
        driver.get(URL);

        LoginPage lp = new LoginPage(driver);
        lp.loginToApp(vEMAIL, vPASSWORD);
        System.out.println(" Login Successful: Valid Email & Valid Password");
        Thread.sleep(20000);
        lp.logoutTOApp();
        System.out.println("Logout From App");
    }

    @Test
    public void invalidPasswordTest() throws Throwable {
        PropertyFileUtility pp = new PropertyFileUtility();
        String URL = pp.toGetDataFromPropertiesFile("url");
        String vEMAIL = pp.toGetDataFromPropertiesFile("validEmail");
        String INVPASSWORD1 = pp.toGetDataFromPropertiesFile("invalidPassword1");
        WebDriverUtilityProgram wb = new WebDriverUtilityProgram();
        wb.implicitlyWait(driver);
        driver.get(URL);

        LoginPage lp = new LoginPage(driver);
        lp.loginToApp(vEMAIL, INVPASSWORD1);
        System.out.println("❌ Login Failed: Valid Email & Invalid Password");
    }

    @Test
    public void invalidEmailTest() throws Throwable {
        PropertyFileUtility pp = new PropertyFileUtility();
        String URL = pp.toGetDataFromPropertiesFile("url");
        String INVEMAIL1 = pp.toGetDataFromPropertiesFile("invalidEmail1");
        String vPASSWORD = pp.toGetDataFromPropertiesFile("validPassword");

        WebDriverUtilityProgram wb = new WebDriverUtilityProgram();
        wb.implicitlyWait(driver);
        driver.get(URL);

        
        LoginPage lp = new LoginPage(driver);
        lp.loginToApp(INVEMAIL1, vPASSWORD);
        System.out.println("❌ Login Failed: Invalid Email & Valid Password");
    }

    @Test
    public void invalidEmailAndPasswordTest() throws Throwable {
        PropertyFileUtility pp = new PropertyFileUtility();
        String URL = pp.toGetDataFromPropertiesFile("url");
        String INVEMAIL1 = pp.toGetDataFromPropertiesFile("invalidEmail1");
        String INVPASSWORD1 = pp.toGetDataFromPropertiesFile("invalidPassword1");
        WebDriverUtilityProgram wb = new WebDriverUtilityProgram();
        wb.implicitlyWait(driver);
        driver.get(URL);

        LoginPage lp = new LoginPage(driver);
        lp.loginToApp(INVEMAIL1, INVPASSWORD1);
        System.out.println("❌ Login Failed: Invalid Email & Invalid Password");
    }
}
