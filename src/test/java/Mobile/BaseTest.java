package Mobile;

import Common.ConfigLoader;
import Common.Devices;
import Common.DriverProvider;
import Common.ExtentReportManager;
import com.aventstack.extentreports.ExtentTest;
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
    protected static final int WAIT_TIMEOUT_IN_SECONDS = 20;

    // Method to log messages easily
    protected Common.Utils.LoggingUtility logger = new Common.Utils.LoggingUtility();

    static {
        deviceConfigs = ConfigLoader.loadDeviceConfigs(deviceToUse);
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        Map<String, Object> config = (Map<String, Object>) deviceConfigs.get(deviceToUse);
        if (config == null || config.isEmpty()) {
            throw new RuntimeException("No configuration found for device: " + deviceToUse);
        }

        driver.set(DriverProvider.createDriver(config));

        //Handle annoying popup on older androids
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIMEOUT_IN_SECONDS));
            WebElement okButton = (WebElement) wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
            if (okButton != null && okButton.isDisplayed()) {
                okButton.click();
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
        ExtentTest currentTest = ExtentReportManager.getTest();
        if (currentTest != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                currentTest.fail("Test failed: " + result.getThrowable());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                currentTest.pass("Test passed.");
            } else if (result.getStatus() == ITestResult.SKIP) {
                currentTest.skip("Test skipped: " + result.getThrowable());
            }
        } else {
            System.err.println("ExtentTest not initialized for this thread. Unable to log test result.");
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