package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import fileUtility.PropertyFileUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import javaUtility.JavaUtilityProgram;

public class BaseTest {

    // ✅ ThreadLocal driver for parallel safe execution
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    protected String browserName;
    
    // ✅ Getter for driver
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String BROWSER) throws Throwable {

        PropertyFileUtility pp = new PropertyFileUtility();
        String URL = pp.toGetDataFromPropertiesFile("url");

        WebDriver driver;
        browserName = BROWSER; // ✅ store browser name
        // ✅ Headless mode check (default false)
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        // ✅ Chrome setup
        if (BROWSER.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) {
                options.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
            }
            driver = new ChromeDriver(options);

        } 
        else if (BROWSER.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if (isHeadless) {
                options.addArguments("--headless");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }
            driver = new FirefoxDriver(options);
        }

        // ✅ Edge setup with dual-mode
        else if (BROWSER.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();

            if (isHeadless) {
                // ✅ Headless for CI / GitHub Actions
                options.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
                System.setProperty("webdriver.edge.driver", "/usr/bin/msedgedriver"); // pre-installed path
                driver = new EdgeDriver(options);
            } else {
                // ✅ Local machine → manual exe
                System.setProperty("webdriver.edge.driver", "C:\\Drivers\\edgedriver_win64\\msedgedriver.exe");
                driver = new EdgeDriver(options);
            }


        } else {
            throw new RuntimeException("Invalid Browser: " + BROWSER);
        }

        // ✅ Set ThreadLocal driver
        tlDriver.set(driver);

        // ✅ Maximize window and open URL
        getDriver().manage().window().maximize();
        getDriver().get(URL);
        
     // ✅ print date + browser
        JavaUtilityProgram jp = new JavaUtilityProgram();
        System.out.println("🕒 Test Started At: " + jp.getCurrentDateAndTime() + " | Browser: " + browserName);
    
    }

    @AfterMethod
    public void tearDown() {
        // ✅ Quit driver and cleanup ThreadLocal
        if (getDriver() != null) {
            getDriver().quit();
            tlDriver.remove();
        }
    }
}
