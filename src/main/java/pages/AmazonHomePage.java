package pages;

import interfaces.Searchable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonHomePage extends BasePage implements Searchable {
    // Amazon-specific locators
    private final By searchBox = By.id("twotabsearchtextbox");
    private final By searchButton = By.id("nav-search-submit-button");

    private final By freshMeatFlyout = By.cssSelector(".f3-cgi-flyout-store-box.f3-cgi-flyout-store-box-right");
    private final By freshFlyout = By.cssSelector(".f3-cgi-flyout-store-box.f3-cgi-flyout-store-box-left");

    public AmazonHomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void search(String searchTerm) {
        // Use getDriver() instead of direct 'driver' access
        safeType(getDriver().findElement(searchBox), searchBox, searchTerm);
        safeClick(getDriver().findElement(searchButton), searchButton);
    }
}