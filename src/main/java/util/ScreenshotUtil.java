package util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String destDir = System.getProperty("user.dir") + "/test-output/screenshots/";
            new File(destDir).mkdirs();
            String path = destDir + testName + "_" + timestamp + ".png";
            FileHandler.copy(src, new File(path));
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
