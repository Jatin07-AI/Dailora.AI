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

    // ✅ ThreadLocal driver
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    // ✅ Getter method for driver (use this in tests instead of direct driver)
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String BROWSER) throws Throwable {
        PropertyFileUtility pp = new PropertyFileUtility();
        String URL = pp.toGetDataFromPropertiesFile("url");

        WebDriver localDriver;  // ✅ local variable

        if (BROWSER.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
            localDriver = new ChromeDriver(options);

        } else if (BROWSER.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-headless");
            localDriver = new FirefoxDriver(options);

        } else if (BROWSER.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
            localDriver = new EdgeDriver(options);

        } else {
            throw new RuntimeException("Invalid Browser: " + BROWSER);
        }

        // ✅ set ThreadLocal driver
        tlDriver.set(localDriver);

        getDriver().manage().window().maximize();
        getDriver().get(URL);   // ✅ url load ho jaye yahi se
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            tlDriver.remove();   // ✅ cleanup for parallel safety
        }
    }
}
