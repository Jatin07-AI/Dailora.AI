package tests;

import fileUtility.PropertyFileUtility;
import webDriverUtility.WebDriverUtilityProgram;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import POM.CreateAgentPage;
import base.BaseTest;

@Listeners(listeners.ListenerImplementation.class)
public class CreateAgentTest extends BaseTest {

    @Test
    public void createAndDeleteAgentTest() throws Throwable {

        // ------------------ Test Data ------------------ //
        PropertyFileUtility propertyUtil = new PropertyFileUtility();
        String appUrl      = propertyUtil.toGetDataFromPropertiesFile("url");
        String validEmail  = propertyUtil.toGetDataFromPropertiesFile("validEmail");
        String validPass   = propertyUtil.toGetDataFromPropertiesFile("validPassword");

        // ------------------ Launch Browser ------------------ //
        WebDriver driver = BaseTest.sdriver;
        WebDriverUtilityProgram webUtil = new WebDriverUtilityProgram();
        webUtil.implicitlyWait(driver);
        driver.get(appUrl);

        // ------------------ Page Objects ------------------ //
        CreateAgentPage agentPage = new CreateAgentPage(driver);

        // ------------------ Test Steps ------------------ //
        // 1. Login into application
        agentPage.loginIntoApp(validEmail, validPass);

        // 2. Create agent, assign number, configure call, and delete agent
        agentPage.createAgent();

       
    }
}
