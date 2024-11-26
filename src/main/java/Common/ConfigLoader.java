package Common;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;

public class ConfigLoader {

    public static Map<String, Object> loadDeviceConfigs(String deviceName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/resources/devices.json");
            Map<String, Object> allConfigs = mapper.readValue(file, Map.class);
            Map<String, Object> devicesConfig = (Map<String, Object>) allConfigs.get("devices");

            // Add credentials to each device configuration as needed
            devicesConfig.forEach((key, value) -> {
                Map<String, Object> config = (Map<String, Object>) value;
                config.put("bstack:user", System.getenv("BROWSERSTACK_USER"));
                config.put("bstack:key", System.getenv("BROWSERSTACK_KEY"));
            });

            return devicesConfig;
        } catch (Exception e) {
            e.printStackTrace(); // Consider a more sophisticated error handling strategy
            return null;
        }
    }
}