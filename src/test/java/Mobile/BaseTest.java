package Mobile;

import Common.ConfigLoader;
import Common.Devices;
import Common.DriverProvider;
import Common.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;

public abstract class BaseTest {
    protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static final Map<String, Object> deviceConfigs;
    private static final String deviceToUse = Devices.PIXEL_3;
    protected static final int WAIT_TIMEOUT_IN_SECONDS = 10;

    // Method to log messages easily
    protected Common.Utils.LoggingUtility logger = new Common.Utils.LoggingUtility();

    static {
        deviceConfigs = ConfigLoader.loadDeviceConfigs(deviceToUse);
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        Map<String, Object> config = (Map<String, Object>) deviceConfigs.get(deviceToUse);
        driver.set(DriverProvider.createDriver(config.toString()));
        ExtentReportManager.getReporter();
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIMEOUT_IN_SECONDS));
            WebElement closeButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
            if (closeButton != null && closeButton.isDisplayed()) {
                closeButton.click();
            }
        } catch (Exception e) {
            logger.logError("Error in handling popup: " + e.getMessage());
        }
    }

    @BeforeMethod
    public void setUpMethod(Method method) {
        ExtentReportManager.startTest(method.getName());
    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentReportManager.getTest().fail("Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentReportManager.getTest().pass("Passed");
        }
        // TODO: Consider adding screenshot logic related to reports
    }

    @AfterClass
    public void tearDownClass() {
        try {
            if (driver.get() != null) {
                driver.get().quit();
            }
        } finally {
            driver.remove();  // Ensure the ThreadLocal is cleaned up
            ExtentReportManager.flushReports();  // Flush and close reports
            ExtentReportManager.endTest();
        }
    }
    public static AppiumDriver getDriver() {
        return driver.get();
    }
}