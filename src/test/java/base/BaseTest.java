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

public class BaseTest {

    // ✅ ThreadLocal driver for parallel safe execution
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    // ✅ Getter for driver
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String BROWSER) throws Throwable {

        PropertyFileUtility pp = new PropertyFileUtility();
        String URL = pp.toGetDataFromPropertiesFile("url");

        WebDriver localDriver;

        // ✅ Headless mode check (default false)
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        // ✅ Chrome setup
        if (BROWSER.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) {
                options.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
            }
            localDriver = new ChromeDriver(options);

        } 
        // ✅ Firefox setup
        else if (BROWSER.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if (isHeadless) {
                options.addArguments("-headless");
            }
            localDriver = new FirefoxDriver(options);

        } 
        // ✅ Edge setup with dual-mode
        else if (BROWSER.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();

            if (isHeadless) {
                // ✅ Headless for CI / GitHub
            	    WebDriverManager.edgedriver().setup(); 
                options.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
                WebDriverManager.edgedriver().setup(); // Auto download
                localDriver = new EdgeDriver(options);
            } else {
                // ✅ Local machine → manual exe
                System.setProperty("webdriver.edge.driver", "C:\\Drivers\\edgedriver_win64\\msedgedriver.exe");
                localDriver = new EdgeDriver(options);
            }

        } else {
            throw new RuntimeException("Invalid Browser: " + BROWSER);
        }

        // ✅ Set ThreadLocal driver
        tlDriver.set(localDriver);

        // ✅ Maximize window and open URL
        getDriver().manage().window().maximize();
        getDriver().get(URL);
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
