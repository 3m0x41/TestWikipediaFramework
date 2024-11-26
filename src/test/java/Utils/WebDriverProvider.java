package Utils;

import Common.Enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverProvider {

    public static WebDriver createWebDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                System.out.println("Initializing Chrome WebDriver");
                System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
                return new ChromeDriver();

            case EDGE:
                System.out.println("Initializing Edge WebDriver");
                System.setProperty("webdriver.edge.driver", "path/to/msedgedriver");
                return new EdgeDriver();

            case FIREFOX:
                System.out.println("Initializing Firefox WebDriver");
                System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
                return new FirefoxDriver();

            default:
                throw new UnsupportedOperationException("Browser type not supported: " + browserType);
        }
    }
}