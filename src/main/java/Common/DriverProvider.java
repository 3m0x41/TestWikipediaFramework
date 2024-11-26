package Common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class DriverProvider {

    public static AppiumDriver createDriver(String deviceName) throws MalformedURLException {
        // Load device configurations
        Map<String, Object> capsMap = ConfigLoader.loadDeviceConfigs(deviceName);

        // Initialize UiAutomator2Options
        UiAutomator2Options options = new UiAutomator2Options();

        // Set capabilities from the configuration map
        capsMap.forEach((key, value) -> {
            if (value instanceof Boolean) {
                options.setCapability(key, (Boolean) value);
            } else if (value instanceof Integer) {
                options.setCapability(key, (Integer) value);
            } else {
                options.setCapability(key, value.toString());
            }
        });

        // Ensure essential capabilities are set (Fallback if missing in JSON)
        if (!capsMap.containsKey("automationName")) {
            options.setAutomationName("UiAutomator2");
        }
        if (!capsMap.containsKey("platformName")) {
            options.setPlatformName("Android");
        }
        if (!capsMap.containsKey("deviceName")) {
            options.setDeviceName("emulator-5554");
        }

        // Return the new AppiumDriver instance
        return new AppiumDriver(new URL("http://127.0.0.1:4723"), options);
    }
}