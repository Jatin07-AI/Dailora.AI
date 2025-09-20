package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

import POM.SignUpPage;
import base.BaseTest;
import fileUtility.PropertyFileUtility;
import javaUtility.JavaUtilityProgram;
import webDriverUtility.WebDriverUtilityProgram;

public class SignUpTest extends BaseTest {

    // ✅ Valid Signup Test
    @Test(priority = 1)
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

        WebDriver driver = BaseTest.getDriver();
        WebDriverUtilityProgram wb = new WebDriverUtilityProgram();
        wb.implicitlyWait(driver);
        driver.get(URL);

        wb.implicitlyWait(driver);
        SignUpPage sp = new SignUpPage(driver);

        sp.getSIGNUPLINK().click();
        sp.getAcceptAllBTN().click();
        sp.getNAME().sendKeys(RANDOMENAME);
        sp.getEMAIL().sendKeys(UNIQUEEMAIL);
        wb.select(sp.getCOUNTRYCODE(), "IN");
        sp.getPhoneNum().sendKeys(PHONENUMBER);
        sp.getPASSWORD().sendKeys(RPASSWORD);
        wb.mouseHoverOnWebElement(driver, sp.getEYEICON1());
        wb.mouseClickOnWebElement(driver, sp.getEYEICON1());
        sp.getCNFPASSMSG().sendKeys(RPASSWORD);
        wb.mouseHoverOnWebElement(driver, sp.getEYEICON2());
        wb.mouseClickOnWebElement(driver, sp.getEYEICON2());
        wb.visibilityOfElement(driver, sp.getSUBMITBTN());
        Thread.sleep(5000);
        sp.getSUBMITBTN().click();

        Thread.sleep(10000);
        wb.implicitlyWait(driver);
        String expectedUrl = "https://dev.dialora.ai/signup/choose-plan?email=";
        String actualUrl = driver.getCurrentUrl();

        if (actualUrl.contains(expectedUrl)) {
            Reporter.log("✅ User successfully reached Purchase Plan page: " + actualUrl, true);
        } else {
            Reporter.log("❌ Login failed or wrong page loaded. Actual: " + actualUrl, true);
        }

        sp.getSUBSCRIPTIONBTN().click();
        Thread.sleep(20000);

        wb.implicitlyWait(driver);
        String paymentExpectedURL = "https://checkout.stripe.com/";
        String paymentActualUrl = driver.getCurrentUrl();

        if (paymentActualUrl.contains(paymentExpectedURL)) {
            Reporter.log("✅ User successfully reached at Payment Page: " + paymentActualUrl, true);
        } else {
            Reporter.log("❌ Login failed or wrong page loaded. Actual: " + paymentActualUrl, true);
        }

        wb.visibilityOfElement(driver, sp.getEnterCardNum());
        sp.getEnterCardNum().sendKeys("4242424242424242");
        sp.getCARDEXP().sendKeys("1230");
        sp.getCARDCVC().sendKeys("123");
        wb.select(sp.getSELCOUNTRY(), "IN");
        sp.getBILLINGNAME().sendKeys("Codiste");
        sp.getADDRESS().sendKeys("Sarkhej - Gandhinagar Highway");
        Actions act = new Actions(driver);
        sp.getADDRESS().sendKeys(Keys.ENTER);
        sp.getADDRESS2().sendKeys("805-807");
        sp.getPINCODE().sendKeys("382470");
        sp.getCITY().sendKeys("Ahmedabad");
        wb.select(sp.getSTATE(), "GJ");
        Thread.sleep(2000);
        wb.mouseClickOnWebElement(driver, sp.getStartTrialBTN());
        wb.waitForInvisibilityOfElement(driver, sp.getPROCESSINGBTN(), 50);
       Thread.sleep(3000);
        String PaymentScuucessfulExpURL = "https://dev.dialora.ai/signup/success?session_id";
        String PaymentScuucessfulActualURL = driver.getCurrentUrl();

        
        if (PaymentScuucessfulActualURL.contains(PaymentScuucessfulExpURL)) {
            Reporter.log("✅ User Get Payment Successful Page: " + PaymentScuucessfulActualURL, true);
        } else {
            Reporter.log("❌ Login failed or wrong page loaded. Actual: " + PaymentScuucessfulActualURL, true);
        }

        Thread.sleep(5000);
        String createdUSER = sp.getLOGINEMAIL().getAttribute("value");
        if (createdUSER.equals(UNIQUEEMAIL)) {
            Reporter.log("✅ Email auto-populated correctly: " + createdUSER, true);
        } else {
            Reporter.log("❌ Email mismatch! Expected: " + UNIQUEEMAIL + " | Found: " + createdUSER, true);
        }

        String LOGINExPUrl = "https://dev.app.dialora.ai/login?";
        String LOGINActualUrl = driver.getCurrentUrl();

        if (LOGINActualUrl.contains(LOGINExPUrl)) {
            Reporter.log("✅ User successfully reached at Login Page: " + LOGINActualUrl, true);
        } else {
            Reporter.log("❌ Login failed or wrong page loaded. Actual: " + LOGINActualUrl, true);
        }
    }
}
