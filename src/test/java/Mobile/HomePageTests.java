package Mobile;

import Common.TestCategories;
import PageObjects.HomePage;
import PageObjects.DotMenu;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {

    private HomePage homePage;
    private DotMenu dotMenu;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage(driver.get());
        dotMenu = new DotMenu(driver.get());
    }

    @Test
    @Parameters({TestCategories.UI_MOBILE_SMOKE_TESTS, TestCategories.UI_MOBILE_REGRESSION_TESTS})
    public void testClickDotMenu() {
        logger.logInfo("Opening Dot Menu");
        homePage.clickDotMenu();
        logger.logInfo("Verifying Dot Menu options are displayed");
        Assert.assertTrue(dotMenu.isLoginButtonDisplayed(), "Login button should be displayed.");
        Assert.assertTrue(dotMenu.isSettingsButtonDisplayed(), "Settings button should be displayed.");
        Assert.assertTrue(dotMenu.isSupportWikipediaButtonDisplayed(), "Support Wikipedia button should be displayed.");
    }

    @Test
    @Parameters(TestCategories.UI_MOBILE_SMOKE_TESTS)
    public void testEnterSearch() {
        String query = "example search";
        logger.logInfo("Entering search query: " + query);
        homePage.enterSearch(query);
        logger.logInfo("Verifying search results are displayed");
        Assert.assertTrue(homePage.isSearchResultsDisplayed(), "Search results should be displayed for the query.");
    }

    @Test
    @Parameters(TestCategories.UI_MOBILE_REGRESSION_TESTS)
    public void testClickMyList() {
        logger.logInfo("Clicking on My List");
        homePage.clickMyList();
        logger.logInfo("Verifying My List is displayed");
        Assert.assertTrue(homePage.isMyListDisplayed(), "My List should be displayed.");
    }

    @Test
    @Parameters(TestCategories.UI_MOBILE_REGRESSION_TESTS)
    public void testClickHistory() {
        logger.logInfo("Clicking on History");
        homePage.clickHistory();
        logger.logInfo("Verifying History is displayed");
        Assert.assertTrue(homePage.isHistoryDisplayed(), "History should be displayed.");
    }

    @Test
    @Parameters({TestCategories.ANDROID_TESTS, TestCategories.UI_MOBILE_REGRESSION_TESTS})
    public void testClickFindNearby() {
        logger.logInfo("Clicking on Find Nearby");
        homePage.clickFindNearby();
        logger.logInfo("Verifying Nearby functionality is triggered");
        Assert.assertTrue(homePage.isNearbyDisplayed(), "Nearby functionality should be triggered.");
    }
}
