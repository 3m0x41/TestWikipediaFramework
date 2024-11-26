package Web;

import Common.Enums.BrowserType;
import Common.TestCategories;
import Common.WebDriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

public class WebBaseTest {

    protected WebDriver driver;
    protected Common.Utils.LoggingUtility logger = new Common.Utils.LoggingUtility();

    public WebBaseTest(BrowserType browserType) {
        this.driver = WebDriverProvider.createWebDriver(browserType);
    }

    @BeforeEach
    public void setup() {
        try {
            // Navigate to the desired URL
            driver.navigate().to("https://en.wikipedia.org/wiki/Main_Page");
            System.out.println("WebDriver initialized and URL navigated successfully.");
        } catch (Exception e) {
            // Log and fail the test if setup fails
            throw new RuntimeException("Setup failed: " + e.getMessage(), e);
        }
    }

    @AfterEach
    @Parameters(TestCategories.UI_WEB_SMOKE_TESTS)
    public void cleanup() {
        try {
            if (driver != null) {
                driver.quit();
                System.out.println("WebDriver quit successfully.");
            }
        } catch (Exception e) {
            // Log the exception if cleanup fails
            System.err.println("Cleanup failed: " + e.getMessage());
        }
    }
}