package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ElementValidator {
    private static final Logger logger = LoggerFactory.getLogger(ElementValidator.class);
    private static final int TIMEOUT = 10;
    private WebDriver driver;
    private WebDriverWait wait;

    public ElementValidator(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    // Check if element exists in the DOM
    public boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            logger.error("Element not found: {}", locator);
            return false;
        }
    }

    // Check if element is visible and enabled
    public boolean isElementInteractable(WebDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed() && element.isEnabled();
        } catch (TimeoutException e) {
            logger.error("Element not visible or enabled: {}", locator);
            return false;
        }
    }

    // Check if element is not stale
    public boolean isElementNotStale(WebDriver driver, By locator) {
        try {
            WebElement element = driver.findElement(locator);
            // Attempt to interact with the element
            element.isDisplayed(); // Triggers staleness check
            return true;
        } catch (StaleElementReferenceException e) {
            logger.error("Element is stale: {}", locator);
            return false;
        }
    }

    // Comprehensive validation (combine all checks)
    public boolean validateElement(WebDriver driver, By locator) {
        return isElementPresent(driver, locator) &&
                isElementInteractable(driver, locator) &&
                isElementNotStale(driver, locator);
    }
}