package pages;

import exceptions.LocatorValidationException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementValidator;
import utils.LoggerClass;

import java.time.Duration;

public class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ElementValidator validator;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.validator = new ElementValidator(driver);
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    // ------------------------------------------------------------
    // Updated Methods with Locator Validation and LoggerClass
    // ------------------------------------------------------------

    protected void safeClick(WebElement element, By locator) {
        if (!validator.validateElement(driver, locator)) {
            throw new LocatorValidationException("Locator validation failed: " + locator);
        }

        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            LoggerClass.log("Clicked element: " + locator);
        } catch (StaleElementReferenceException e) {
            if (!validator.validateElement(driver, locator)) {
                throw new LocatorValidationException("Locator validation failed after staleness: " + locator);
            }
            WebElement refreshedElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
            refreshedElement.click();
            LoggerClass.log("Retried click after staleness: " + locator);
        }
    }

    protected void safeType(WebElement element, By locator, String text) {
        if (!validator.validateElement(driver, locator)) {
            throw new LocatorValidationException("Locator validation failed: " + locator);
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
            LoggerClass.log("Typed '" + text + "' into element: " + locator);
        } catch (StaleElementReferenceException e) {
            if (!validator.validateElement(driver, locator)) {
                throw new LocatorValidationException("Locator validation failed after staleness: " + locator);
            }
            WebElement refreshedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            refreshedElement.sendKeys(text);
            LoggerClass.log("Retried typing after staleness: " + locator);
        }
    }

    protected void waitForElementPresence(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        LoggerClass.log("Waited for presence of element: " + locator);
    }

    protected void waitForElementVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        LoggerClass.log("Waited for visibility of element: " + locator);
    }

    protected void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        LoggerClass.log("Waited for element to be clickable: " + locator);
    }

    protected void waitForElementInvisibility(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        LoggerClass.log("Waited for invisibility of element: " + locator);
    }
}
