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
import io.github.bonigarcia.wdm.WebDriverManager;   // ✅ import WDM

public class BaseTest {
    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String BROWSER) throws Throwable {
        PropertyFileUtility pp = new PropertyFileUtility();
        String URL = pp.toGetDataFromPropertiesFile("url");

        if (BROWSER.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();   // ✅ auto-download
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
            driver = new ChromeDriver(options);

        } else if (BROWSER.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();   // ✅ auto-download
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-headless");
            driver = new FirefoxDriver(options);

        } else if (BROWSER.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();   // ✅ auto-download
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
            driver = new EdgeDriver(options);

        } else {
            throw new RuntimeException("Invalid Browser: " + BROWSER);
        }

        driver.manage().window().maximize();
        driver.get(URL);   // ✅ url load ho jaye yahi se
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
