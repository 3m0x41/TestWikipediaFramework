package PageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected static final int WAIT_TIME_IN_SECONDS = 30;
    protected AppiumDriver driver;
    protected WebDriverWait wait;

    public BasePage(AppiumDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver instance cannot be null");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(WAIT_TIME_IN_SECONDS));
    }

    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void click(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    protected void enterText(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}