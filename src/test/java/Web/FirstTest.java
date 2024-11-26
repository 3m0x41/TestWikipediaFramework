package Web;

import Common.Enums.BrowserType;
import WebPageObjects.WebHomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FirstTest extends WebBaseTest {

    private final String expectedTitle = "Wikipedia, the free encyclopedia";
    private final String searchQuery = "Selenium (software)";
    // Constructor to specify the browser type
    public FirstTest() {
        super(BrowserType.CHROME); // Change to BrowserType.EDGE or BrowserType.FIREFOX if needed
    }

    @Test
    public void testMainPageTitleAndSearchFunctionality() {
        try {
            WebHomePage webHomePage = new WebHomePage(driver);
            logger.logInfo("Check title of the page");
            String actualTitle = driver.getTitle();
            Assertions.assertEquals(expectedTitle, actualTitle, "Page title does not match!");

            logger.logInfo("Click Search Button");
            webHomePage.clickSearchButton();
            logger.logInfo("Perform Search");
            webHomePage.enterSearchQuery(searchQuery);

            logger.logInfo("Assert that the new page title contains the search query");
            String newTitle = driver.getTitle();
            Assertions.assertTrue(newTitle.contains("Selenium (software)"), "Search result page title does not contain the query!");

            System.out.println("Test executed successfully.");
        } catch (Exception e) {
            // Log the exception
            throw new RuntimeException("Test failed: " + e.getMessage(), e);
        }
    }
}
