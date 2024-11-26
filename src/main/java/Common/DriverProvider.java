package Common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class DriverProvider {

    public static AppiumDriver createDriver(Map<String, Object> capsMap) throws MalformedURLException {
        if (capsMap == null || capsMap.isEmpty()) {
            throw new IllegalArgumentException("Device configuration map is null or empty.");
        }

        UiAutomator2Options options = new UiAutomator2Options();

        capsMap.forEach((key, value) -> {
            switch (value) {
                case null -> {
                    System.err.println("Warning: Capability key '" + key + "' has a null value and will be skipped.");
                    return; // Skip this key-value pair
                }
                case Boolean b -> options.setCapability(key, b);
                case Integer i -> options.setCapability(key, i);
                default -> options.setCapability(key, value.toString());
            }
        });

        // Ensure essential capabilities are set (Fallback if missing in JSON)
        options.setAutomationName((String) capsMap.getOrDefault("automationName", "UiAutomator2"));
        options.setPlatformName((String) capsMap.getOrDefault("platformName", "Android"));
        options.setDeviceName((String) capsMap.getOrDefault("deviceName", "emulator-5554"));

        return new AppiumDriver(new URL("http://127.0.0.1:4723"), options);
    }
}