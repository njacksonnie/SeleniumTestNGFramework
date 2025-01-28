package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
    private static final String SCREENSHOT_DIR = "./screenshots/";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd-HHmmss");

    static {
        // Create screenshots directory if it doesn't exist
        File directory = new File(SCREENSHOT_DIR);
        if (!directory.exists()) {
            boolean dirCreated = directory.mkdirs();
            if (!dirCreated) {
                LoggerClass.log("Failed to create screenshots directory!");
            }
        }
    }

    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            // Generate timestamped filename
            String timestamp = DATE_FORMAT.format(new Date());
            String fileName = String.format("%s_%s.png", testName, timestamp);

            // Capture and save screenshot
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(SCREENSHOT_DIR + fileName);
            FileUtils.copyFile(screenshotFile, destFile);

            LoggerClass.log("Screenshot saved: " + destFile.getAbsolutePath());

            // Return the absolute path of the saved screenshot
            return destFile.getAbsolutePath();
        } catch (IOException | NullPointerException | ClassCastException e) {
            LoggerClass.log("Failed to capture screenshot: " + e.getMessage());
        }
        return null; // Return null if capturing the screenshot fails
    }
}
