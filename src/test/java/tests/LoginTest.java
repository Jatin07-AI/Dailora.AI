package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import POM.LoginPage;
import base.BaseTest;
import dataproviders.DataProviderUtility;
import fileUtility.PropertyFileUtility;
import webDriverUtility.WebDriverUtilityProgram;


public class LoginTest extends BaseTest {
	
	@Test(dataProvider = "loginData", dataProviderClass = DataProviderUtility.class)
	public void loginTest(String testCase, String email, String password) throws Throwable {
	    PropertyFileUtility pp = new PropertyFileUtility();
	    String URL = pp.toGetDataFromPropertiesFile("url");

	    WebDriver driver = BaseTest.getDriver();
	    WebDriverUtilityProgram wb = new WebDriverUtilityProgram();
	    wb.implicitlyWait(driver);
	    driver.get(URL);

	    LoginPage lp = new LoginPage(driver);
	    lp.getEMAIL().sendKeys(email);
	    lp.getPASSWORD().sendKeys(password);
	    lp.getSUBMIT().click();

	    switch (testCase) {
	        case "validLogin":
	            Reporter.log("✅ Login Successful: Valid Email & Valid Password", true);

	            // logout steps
	            wb.waitForPageLoad(driver, 20);
	            wb.visibilityOfElement(driver, lp.getAGENTS());
	            lp.getAGENTS().click();
	            Thread.sleep(5000);
	            wb.elementxTobeClickable(driver, lp.getLOGOUTDROPDOWN());
	            lp.getLOGOUTDROPDOWN().click();
	            wb.visibilityOfElement(driver, lp.getLOGOUTBTN());
	            lp.getLOGOUTBTN().click();
	            Reporter.log("Logout Successfully", true);
	            break;

	        case "invalidPassword":
	            Reporter.log("❌ Login Failed: Valid Email & Invalid Password", true);
	            break;

	        case "invalidEmail":
	            Reporter.log("❌ Login Failed: Invalid Email & Valid Password", true);
	            break;

	        case "invalidBoth":
	            Reporter.log("❌ Login Failed: Invalid Email & Invalid Password", true);
	            break;
	    }
	}

	
    }
