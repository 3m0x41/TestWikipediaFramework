package WebPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebBasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Updated constructor
    public WebBasePage(WebDriver driver) {
        this.driver = driver;
        // Initialize WebDriverWait with a timeout of 30 seconds using Duration
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void waitPageTitleLoaded(String title) {
        wait.until((ExpectedCondition<Boolean>) driver -> driver.getTitle().equals(title));
    }

    public WebElement waitAndFindElement(By locator) {
        return wait.until(driver -> driver.findElement(locator));
    }
}
