package WebPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebLogInPage extends WebBasePage {

    // Constructor
    public WebLogInPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By usernameInputLocator = By.id("username");
    private final By passwordInputLocator = By.id("password");
    private final By loginButtonLocator = By.cssSelector("button[type='submit']");

    private WebElement getUsernameInput() {
        return waitAndFindElement(usernameInputLocator);
    }

    private WebElement getPasswordInput() {
        return waitAndFindElement(passwordInputLocator);
    }

    private WebElement getLoginButton() {
        return waitAndFindElement(loginButtonLocator);
    }

    public void login(String username, String password) {
        getUsernameInput().sendKeys(username);
        getPasswordInput().sendKeys(password);
        getLoginButton().click();
    }
}