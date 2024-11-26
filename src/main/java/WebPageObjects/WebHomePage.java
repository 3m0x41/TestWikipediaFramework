package WebPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebHomePage extends WebBasePage {

    private final By LoginUpButtonLocator = By.id("pt-login-2");
    private final By SearchButtonField = By.cssSelector("[title='Search Wikipedia [Alt+Shift+f]']");

    public WebHomePage(WebDriver driver) {
        super(driver);
        waitPageTitleLoaded("Wikipedia, the free encyclopedia");
    }

    private WebElement getLoginButton() {
        return waitAndFindElement(LoginUpButtonLocator);
    }

    public void clickLoginButton() {
        getLoginButton().click();
    }

    public void enterSearchQuery(String query) {
        WebElement searchInput = waitAndFindElement(SearchButtonField);
        searchInput.clear(); // Clear any existing text in the input field
        searchInput.sendKeys(query);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void clickSearchButton() {
        WebElement searchButton = waitAndFindElement(SearchButtonField);
        searchButton.click();
    }
}
