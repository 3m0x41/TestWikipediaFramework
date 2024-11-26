package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class DotMenu extends BasePage {

    @AndroidFindBy(id = "00000000-0000-01fa-ffff-ffff0000011d")
    private WebElement loginButton;

    @AndroidFindBy(id = "00000000-0000-01fc-ffff-ffff00000182")
    private WebElement settingsButton;

    @AndroidFindBy(id = "00000000-0000-01fc-ffff-ffff00000183")
    private WebElement supportWikipediaButton;

    public DotMenu(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed();
    }

    public boolean isSettingsButtonDisplayed() {
        return settingsButton.isDisplayed();
    }

    public boolean isSupportWikipediaButtonDisplayed() {
        return supportWikipediaButton.isDisplayed();
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void clickSettings() {
        settingsButton.click();
    }

    public void clickSupportWikipedia() {
        supportWikipediaButton.click();
    }
}