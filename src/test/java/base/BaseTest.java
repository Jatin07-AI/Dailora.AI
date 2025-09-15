package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import fileUtility.PropertyFileUtility;
import webDriverUtility.WebDriverUtilityProgram;

public class BaseTest {
    // make driver available to subclasses
    public WebDriver driver;
    protected String currentBrowser;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) throws Throwable {
    	this.currentBrowser = browser;
        PropertyFileUtility pp = new PropertyFileUtility();
        String URL = pp.toGetDataFromPropertiesFile("url");
        WebDriverUtilityProgram wb = new WebDriverUtilityProgram();
        
       
        
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("brave")) {
        	 // 👇 Brave setup
            ChromeOptions options = new ChromeOptions();
            // replace with your Brave installation path
            options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
            driver = new ChromeDriver(options);
		} else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize();
        wb.waitForPageToLoad(driver);
        driver.get(URL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
