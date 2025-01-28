package tests;

import config.Config;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.DriverFactory;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        driver = DriverFactory.createDriver(browser);

        String baseUrl = Config.get("base_url");
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new RuntimeException("Base URL is not configured in the configuration file.");
        }

        System.out.println("Launching URL: " + baseUrl);
        driver.get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
