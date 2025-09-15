package tests;

import org.testng.Reporter;
import org.testng.annotations.Test;

import POM.SignUpPage;
import base.BaseTest;
import javaUtility.JavaUtilityProgram;




public class SignUpTest extends BaseTest{
	
		@Test
		public void signUpInAc() throws Throwable{
			
			JavaUtilityProgram jp = new JavaUtilityProgram();
			
			String email = jp.generateUniqueEmail();
			String phoneNum = jp.generateUniquePhoneNumber();
			String randomName = jp.generateRandomName();
			JavaUtilityProgram.generateRandomPassword gp = new JavaUtilityProgram.generateRandomPassword();
			String ranPass = gp.getRandomPass(8);
			String TimeStemp = jp.getCurrentDateAndTime();
			 
			
			Reporter.log("Attempting Login On: " + TimeStemp);
			Reporter.log("Attempting SignUp Using: "+ email + ":" +ranPass + ":" + phoneNum,true);
			Reporter.log("Running Test On Current Browser:" + currentBrowser,true);
		
			SignUpPage sp = new SignUpPage(driver);
			sp.signUp(randomName, email, phoneNum, ranPass);
		}
	
}
