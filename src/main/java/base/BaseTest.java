package base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;

import fileUtility.PropertyFileUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import javaUtility.JavaUtilityProgram;

public class BaseTest {

    public WebDriver driver = null;
    public static WebDriver sdriver = null;

    public PropertyFileUtility plib = new PropertyFileUtility();
    public JavaUtilityProgram jlib = new JavaUtilityProgram();

    // =========================
    // SUITE LEVEL
    // =========================
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        System.out.println("✅ Connect to the database");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        System.out.println("❎ Disconnect from the database");
    }

    // =========================
    // CLASS LEVEL (Driver per Class)
    // =========================
    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void beforeClass(@Optional("chrome") String BROWSER, ITestContext context) throws Throwable {
        System.out.println("🚀 Launching the Browser: " + BROWSER);

        String URL = plib.toGetDataFromPropertiesFile("url");
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        // Chrome
        if (BROWSER.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setAcceptInsecureCerts(true);

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            if (isHeadless) {
                options.addArguments("--headless=new", "--window-size=1920,1080");
            }
            driver = new ChromeDriver(options);
        }
        // Firefox
        else if (BROWSER.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.setAcceptInsecureCerts(true);
            options.addPreference("dom.webnotifications.enabled", false);

            if (isHeadless) {
                options.addArguments("--headless");
            }
            driver = new FirefoxDriver(options);
        }
        // Edge
        else if (BROWSER.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.setAcceptInsecureCerts(true);
            driver = new EdgeDriver(options);
        }
        else {
            throw new RuntimeException("❌ Invalid Browser Name: " + BROWSER);
        }

        sdriver = driver; // static driver ready for Listener
        context.setAttribute("driver", driver);

        driver.manage().window().maximize();
        driver.get(URL);

        System.out.println("🕒 Test started at: " + jlib.getCurrentDateAndTime());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (driver != null) {
            driver.quit(); // ✅ Browser closed after class
            System.out.println("🛑 Browser closed");
        }
    }
}
