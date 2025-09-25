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

import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    protected String browserName;

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String BROWSER,org.testng.ITestContext context) throws Throwable {

        PropertyFileUtility pp = new PropertyFileUtility();
        String URL = pp.toGetDataFromPropertiesFile("url");

        WebDriver driver;
        browserName = BROWSER;
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        // ✅ Chrome
        if (BROWSER.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setAcceptInsecureCerts(true); // SSL errors ignore

            // disable popups/notifications
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            if (isHeadless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-software-rasterizer");
                options.addArguments("--no-sandbox");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--force-device-scale-factor=1");
                options.addArguments("--high-dpi-support=1");

            }
            driver = new ChromeDriver(options);
        }

        // ✅ Firefox
        else if (BROWSER.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.setAcceptInsecureCerts(true);
            options.addPreference("dom.webnotifications.enabled", false);
            options.addPreference("geo.enabled", false);

            if (isHeadless) {
                options.addArguments("--headless");
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");
            }
            driver = new FirefoxDriver(options);
        }

        // ✅ Edge
        else if (BROWSER.equalsIgnoreCase("edge")) {

            boolean isCI = System.getenv("GITHUB_ACTIONS") != null; // GitHub Actions detect

            if (isCI) {
                // CI environment → WebDriverManager auto download
                WebDriverManager.edgedriver().setup();
            } else {
                // Local environment → use locally installed driver
                String edgeDriverPath = "C:\\Drivers\\edgedriver_win64\\msedgedriver.exe"; // apna local path
                System.setProperty("webdriver.edge.driver", edgeDriverPath);
            }

            EdgeOptions options = new EdgeOptions();
            options.setAcceptInsecureCerts(true);
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-notifications");

            if (isHeadless) {
                options.addArguments("--headless=new", "--window-size=1920,1080");
                options.addArguments("--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
            }
            driver = new EdgeDriver(options);
        }

        else {
            throw new RuntimeException("Invalid Browser: " + BROWSER);
        }

        tlDriver.set(driver);
        
        // 👉 Listener ke liye driver context me set karo
        context.setAttribute("driver", driver);

        getDriver().manage().window().maximize();
        getDriver().get(URL);

        JavaUtilityProgram jp = new JavaUtilityProgram();
        System.out.println("🕒 Test Started At: " + jp.getCurrentDateAndTime() + " | Browser: " + browserName);
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            tlDriver.remove();
        }
    }
}
