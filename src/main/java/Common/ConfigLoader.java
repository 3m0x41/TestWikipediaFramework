package Common;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;

public class ConfigLoader {

    public static Map<String, Object> loadDeviceConfigs(String deviceName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/resources/devices.json");
            System.out.println("Loading devices from: " + file.getAbsolutePath());

            Map<String, Object> allConfigs = mapper.readValue(file, Map.class);
            System.out.println("Loaded configurations: " + allConfigs);

            Map<String, Object> devicesConfig = (Map<String, Object>) allConfigs.get("devices");
            System.out.println("Available devices: " + devicesConfig.keySet());

            if (!devicesConfig.containsKey(deviceName)) {
                throw new RuntimeException("Device configuration not found for: " + deviceName);
            }

            Map<String, Object> selectedDeviceConfig = (Map<String, Object>) devicesConfig.get(deviceName);
            System.out.println("Loaded configuration for device: " + deviceName);

            // Resolve the APK path dynamically
            if (selectedDeviceConfig.containsKey("app")) {
                String appPath = (String) selectedDeviceConfig.get("app");

                // Dynamically resolve the path relative to the project directory
                String projectPath = System.getProperty("user.dir");
                File apkFile = new File(projectPath, appPath);

                if (!apkFile.exists()) {
                    throw new RuntimeException("App file does not exist at path: " + apkFile.getAbsolutePath());
                } else {
                    System.out.println("App file exists at: " + apkFile.getAbsolutePath());
                }

                // Replace the relative path with the absolute path in the configuration
                selectedDeviceConfig.put("app", apkFile.getAbsolutePath());
            }

            return devicesConfig;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading device configurations: " + e.getMessage(), e);
        }
    }
}