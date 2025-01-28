package utils;

import config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<String> browserName = new ThreadLocal<>();

    public static WebDriver createDriver(String browser) {
        String selectedBrowser = (browser == null || browser.isEmpty()) ? "chrome" : browser.toLowerCase();
        browserName.set(selectedBrowser);

        LoggerClass.log("Initializing browser: " + selectedBrowser);

        try {
            WebDriver webDriver;

            switch (selectedBrowser) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (Config.isHeadless()) {
                        chromeOptions.addArguments("--headless=new");
                    }
                    webDriver = new ChromeDriver(chromeOptions);
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (Config.isHeadless()) {
                        firefoxOptions.addArguments("-headless");
                    }
                    webDriver = new FirefoxDriver(firefoxOptions);
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + selectedBrowser);
            }

            driver.set(webDriver);

            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            webDriver.manage().window().maximize();

            return webDriver;
        } catch (Exception e) {
            LoggerClass.log("Failed to initialize browser: " + selectedBrowser + ". Error: " + e.getMessage());
            throw e;
        }
    }

    public static WebDriver getDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver == null) {
            throw new IllegalStateException("WebDriver has not been initialized. Call createDriver() first.");
        }
        return webDriver;
    }

    public static void quitDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            LoggerClass.log("Quitting browser: " + browserName.get());
            try {
                webDriver.manage().deleteAllCookies();
                webDriver.quit();
            } catch (Exception e) {
                LoggerClass.log("Error while quitting browser: " + browserName.get() + ". Error: " + e.getMessage());
            } finally {
                driver.remove();
                browserName.remove();
            }
        }
    }

    public static String getBrowserName() {
        return browserName.get();
    }
}
