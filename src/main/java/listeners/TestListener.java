package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import util.ScreenshotUtility;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
            if (driver != null) {
                ScreenshotUtility.takeScreenshot(driver, result.getName());
            }
        } catch (Exception e) {
            System.out.println("❌ Listener screenshot failed: " + e.getMessage());
        }
    }
}
