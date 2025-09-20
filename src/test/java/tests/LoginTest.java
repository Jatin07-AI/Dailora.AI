package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import POM.LoginPage;
import base.BaseTest;
import fileUtility.PropertyFileUtility;
import webDriverUtility.WebDriverUtilityProgram;


public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void validLoginTest() throws Throwable {
        PropertyFileUtility pp = new PropertyFileUtility();
        String URL = pp.toGetDataFromPropertiesFile("url");
        String vEMAIL = pp.toGetDataFromPropertiesFile("validEmail");
        String vPASSWORD = pp.toGetDataFromPropertiesFile("validPassword");
        WebDriver driver = BaseTest.getDriver();
        WebDriverUtilityProgram wb = new WebDriverUtilityProgram();
        wb.implicitlyWait(driver);
        driver.get(URL);

        LoginPage lp = new LoginPage(driver);
        lp.getEMAIL().sendKeys(vEMAIL);
        lp.getPASSWORD().sendKeys(vPASSWORD);
        lp.getSUBMIT().click();
        Thread.sleep(10000);
        Reporter.log(" Login Successful: Valid Email & Valid Password",true);
        
        Thread.sleep(10000);
        wb.visibilityOfElement(driver, lp.getAGENTS());
        lp.getAGENTS().click();
	    wb.visibilityOfElement(driver, lp.getLOGOUTDROPDOWN());
	    lp.getLOGOUTDROPDOWN().click();
	    Thread.sleep(2000);
	    wb.visibilityOfElement(driver, lp.getLOGOUTBTN());
	    
	    lp.getLOGOUTBTN().click();
	    Reporter.log("Logout Successfully",true);
    }

    @Test(priority = 2)
    public void invalidPasswordTest() throws Throwable {
        PropertyFileUtility pp = new PropertyFileUtility();
        String URL = pp.toGetDataFromPropertiesFile("url");
        String vEMAIL = pp.toGetDataFromPropertiesFile("validEmail");
        String INVPASSWORD1 = pp.toGetDataFromPropertiesFile("invalidPassword1");
        WebDriver driver = BaseTest.getDriver();
        WebDriverUtilityProgram wb = new WebDriverUtilityProgram();
        wb.implicitlyWait(driver);
        driver.get(URL);

        wb.implicitlyWait(driver);
        LoginPage lp = new LoginPage(driver);
        lp.getEMAIL().sendKeys(vEMAIL);
        lp.getPASSWORD().sendKeys(INVPASSWORD1);
        lp.getSUBMIT().click();
        Reporter.log("❌ Login Failed: Valid Email & Invalid Password",true);
    }

    @Test(priority = 3)
    public void invalidEmailTest() throws Throwable {
        PropertyFileUtility pp = new PropertyFileUtility();
        String URL = pp.toGetDataFromPropertiesFile("url");
        String INVEMAIL1 = pp.toGetDataFromPropertiesFile("invalidEmail1");
        String vPASSWORD = pp.toGetDataFromPropertiesFile("validPassword");

        WebDriverUtilityProgram wb = new WebDriverUtilityProgram();
        WebDriver driver = BaseTest.getDriver();
        wb.implicitlyWait(driver);
        driver.get(URL);

        wb.implicitlyWait(driver);
        LoginPage lp = new LoginPage(driver);
        lp.getEMAIL().sendKeys(INVEMAIL1);
        lp.getPASSWORD().sendKeys(vPASSWORD);
        lp.getSUBMIT().click();
        Reporter.log("❌ Login Failed: Invalid Email & Valid Password",true);
    }

    @Test(priority = 4)
    public void invalidEmailAndPasswordTest() throws Throwable {
        PropertyFileUtility pp = new PropertyFileUtility();
        String URL = pp.toGetDataFromPropertiesFile("url");
        String INVEMAIL1 = pp.toGetDataFromPropertiesFile("invalidEmail1");
        String INVPASSWORD1 = pp.toGetDataFromPropertiesFile("invalidPassword1");
        WebDriverUtilityProgram wb = new WebDriverUtilityProgram();
        WebDriver driver = BaseTest.getDriver();
        wb.implicitlyWait(driver);
        driver.get(URL);

        wb.implicitlyWait(driver);
        LoginPage lp = new LoginPage(driver);
        lp.getEMAIL().sendKeys(INVEMAIL1);
        lp.getPASSWORD().sendKeys(INVPASSWORD1);
        lp.getSUBMIT().click();
        Reporter.log("❌ Login Failed: Invalid Email & Invalid Password",true);
    }
}
