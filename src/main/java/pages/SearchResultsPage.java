package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SearchResultsPage extends BasePage {
    // Locators
    private final By resultsHeader = By.xpath("//span[contains(text(), 'iPhone')]");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void validateSearchResults(String productName) {
        waitForElementPresence(resultsHeader); // Uses centralized wait method
        String headerText = getDriver().findElement(resultsHeader).getText();
        Assert.assertTrue(headerText.contains(productName), "Search results validation failed!");
    }
}