package util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility {

    public static String takeScreenshot(WebDriver driver, String fileName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDir = System.getProperty("user.dir") + "/screenshots/";

        // agar folder exist nahi karta to bana de
        File dir = new File(screenshotDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = screenshotDir + fileName + "_" + timestamp + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(filePath));
            System.out.println("✅ Screenshot saved: " + filePath);
        } catch (IOException e) {
            System.out.println("❌ Failed to save screenshot: " + e.getMessage());
        }

        return filePath;
        
    }
}
