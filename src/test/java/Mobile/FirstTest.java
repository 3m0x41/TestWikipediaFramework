package Mobile;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirstTest extends BaseTest {

    public FirstTest() {
        // No argument constructor for TestNG
    }

    @Test(dataProvider = "testData")
    public void clickElementTest() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIMEOUT_IN_SECONDS));
        logger.logInfo("Navigating to login page");

        WebElement element2 = (WebElement) wait.until(
                ExpectedConditions.elementToBeClickable(By.id("org.wikipedia.alpha:id/single_fragment_toolbar_wordmark")));

        System.out.println("Clicked on the start button successfully!");
        logger.logInfo("Assert title is visible");
        Assert.assertTrue(element2.isDisplayed(), "The button is not visible after clicking");
    }
}