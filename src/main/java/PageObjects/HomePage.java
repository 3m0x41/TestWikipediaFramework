package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    private final WebElement dotMenuButton = driver.findElement(By.id("org.wikipedia.alpha:id/menu_overflow_button"));

    private final WebElement searchField = driver.findElement(By.id("org.wikipedia.alpha:id/fragment_feed_header"));

    private final WebElement myListButton = driver.findElement(By.xpath("((//android.widget.ImageView[@resource-id=\"org.wikipedia.alpha:id/icon\"])[2])"));

    private final WebElement historyButton = driver.findElement(By.xpath("((//android.widget.ImageView[@resource-id=\"org.wikipedia.alpha:id/icon\"])[3])"));

    private final WebElement findNearbyButton = driver.findElement(By.xpath("((//android.widget.ImageView[@resource-id=\"org.wikipedia.alpha:id/icon\"])[4])"));

    // Locator for the search results container
    private final WebElement searchResultsContainer = driver.findElement(By.id("org.wikipedia.alpha:id/search_results_list"));

    public HomePage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickDotMenu() {
        waitForElementToBeClickable(dotMenuButton);
        dotMenuButton.click();
    }

    public void enterSearch(String query) {
        searchField.click();  // Click to focus on the search field
        searchField.clear();  // Clear any existing text
        searchField.sendKeys(query);  // Enter the search query
    }

    public void clickMyList() {
        myListButton.click();
    }

    public void clickHistory() {
        historyButton.click();
    }

    public void clickFindNearby() {
        findNearbyButton.click();
    }

    public boolean isHistoryDisplayed() {
        return isElementDisplayed(historyButton);
    }

    public boolean isFindNearbyDisplayed() {
        return isElementDisplayed(findNearbyButton);
    }

    public boolean isMyListDisplayed() {
        return isElementDisplayed(myListButton);
    }

    // Method to check if search results are displayed
    public boolean isSearchResultsDisplayed() {
        return isElementDisplayed(searchResultsContainer);
    }

    private boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNearbyDisplayed() {
        return isElementDisplayed(findNearbyButton);
    }
}