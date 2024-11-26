package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private AppiumDriver driver;

    // Locators
    @AndroidFindBy(id = "org.wikipedia.alpha:id/login_username_text")
    private WebElement usernameField;

    @AndroidFindBy(xpath = "//TextInputLayout[@resource-id='org.wikipedia.alpha:id/login_password_input']/android.widget.FrameLayout/android.widget.EditText")
    private WebElement passwordField;

    @AndroidFindBy(id = "org.wikipedia.alpha:id/login_button")
    private WebElement loginButton;

    @AndroidFindBy(id = "org.wikipedia.alpha:id/login_message")
    private WebElement loginMessage;

    @AndroidFindBy(id = "org.wikipedia.alpha:id/logout_button")
    private WebElement logoutButton;

    @AndroidFindBy(id = "org.wikipedia.alpha:id/welcome_message")
    private WebElement welcomeMessage;

    @AndroidFindBy(id = "org.wikipedia.alpha:id/admin_panel")
    private WebElement adminPanel;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Method to log in
    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    // Additional methods as needed, for example:
    public boolean isUsernameFieldVisible() {
        return isElementVisible(usernameField);
    }

    public boolean isPasswordFieldVisible() {
        return isElementVisible(passwordField);
    }

    public boolean isLoginButtonVisible() {
        return isElementVisible(loginButton);
    }

    public String getLoginErrorMessage() {
        return loginMessage.getText();
    }

    public boolean isLogoutVisible() {
        return isElementVisible(logoutButton);
    }

    public boolean isWelcomeMessageVisible() {
        return isElementVisible(welcomeMessage);
    }

    public boolean isAdminPanelVisible() {
        return isElementVisible(adminPanel);
    }

    private boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}