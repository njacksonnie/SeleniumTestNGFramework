package tests;

import org.testng.annotations.Test;
import pages.AmazonHomePage;
import pages.SearchResultsPage;
import utils.DriverFactory;

public class AmazonSearchTest extends BaseTest {

    @Test
    public void testAmazonSearch() {
        AmazonHomePage homePage = new AmazonHomePage(DriverFactory.getDriver());
        SearchResultsPage searchResultsPage = new SearchResultsPage(DriverFactory.getDriver());
        homePage.search("iPhone");
        searchResultsPage.validateSearchResults("iPhone");
    }
}