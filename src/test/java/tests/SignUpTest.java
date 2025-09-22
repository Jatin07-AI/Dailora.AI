package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import POM.SignUpPage;
import base.BaseTest;
import fileUtility.PropertyFileUtility;
import javaUtility.JavaUtilityProgram;

public class SignUpTest extends BaseTest {

	@Test(priority = 1)
	public void validSignUpTest() throws Throwable {
	    PropertyFileUtility pp = new PropertyFileUtility();
	    String URL = pp.toGetDataFromPropertiesFile("url");

	    JavaUtilityProgram jp = new JavaUtilityProgram();
	    String randomName = jp.generateRandomName();
	    String uniqueEmail = jp.generateUniqueEmail();
	    String phone = jp.generateUniquePhoneNumber();
	    String pass = new JavaUtilityProgram.generateRandomPassword().getRandomPass(9);

	    WebDriver driver = BaseTest.getDriver();
	    driver.get(URL);

	    SignUpPage sp = new SignUpPage(driver);

	    sp.openSignup();
	    sp.fillSignupPage(randomName, uniqueEmail, phone, pass);
	    sp.submitSignup();

	    sp.clickSubscription();
	    sp.fillPaymentDetails();
	    sp.startTrial();

	    // Verification Example
	    Assert.assertEquals(sp.getAutoPopulatedEmail(), uniqueEmail, "Email mismatch!");
	    
	    Reporter.log("✅ Signup Email: "+ uniqueEmail,true);
	    Reporter.log("✅ Auto-Populated Email: " + sp.getAutoPopulatedEmail(),true);
	}
}
