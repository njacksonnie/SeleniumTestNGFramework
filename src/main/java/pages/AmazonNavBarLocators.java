package pages;

import org.openqa.selenium.By;

public class AmazonNavBarLocators {
    // Search components
    public static final By SEARCH_BOX = By.id("twotabsearchtextbox");
    public static final By SEARCH_BUTTON = By.id("nav-search-submit-button");
    public static final By DEPARTMENT_DROPDOWN = By.id("searchDropdownBox");

    // Main navigation items
    public static final By HAMBURGER_MENU = By.id("nav-hamburger-menu");
    public static final By ACCOUNT_AND_LISTS = By.id("nav-link-accountList");
    public static final By RETURNS_AND_ORDERS = By.id("nav-orders");
    public static final By CART = By.id("nav-cart");

    // Location and language
    public static final By DELIVERY_LOCATION = By.id("nav-global-location-popover-link");

    // Fresh category flyouts
    public static final By FRESH_MEAT_FLYOUT = By.cssSelector(".f3-cgi-flyout-store-box.f3-cgi-flyout-store-box-right");
    public static final By FRESH_FLYOUT = By.cssSelector(".f3-cgi-flyout-store-box.f3-cgi-flyout-store-box-left");

    // Cart counter
    public static final By CART_COUNT = By.id("nav-cart-count");

    // Additional navigation elements (customize as needed)
    public static final By TODAYS_DEALS = By.linkText("Today's Deals");
    public static final By CUSTOMER_SERVICE = By.linkText("Customer Service");
    public static final By GIFT_CARDS = By.linkText("Gift Cards");
    public static final By SELL = By.linkText("Sell");
}