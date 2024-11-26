package Common;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import Common.Enums.BrowserType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.logging.Logger;

public class WebDriverProvider {
    private static final Logger LOGGER = Logger.getLogger(WebDriverProvider.class.getName());

    public static WebDriver createWebDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                LOGGER.info("Initializing Chrome WebDriver");
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

            case EDGE:
                LOGGER.info("Initializing Edge WebDriver");
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();

            case FIREFOX:
                LOGGER.info("Initializing Firefox WebDriver");
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            default:
                throw new UnsupportedOperationException("Browser type '" + browserType + "' is not supported.");
        }
    }
}