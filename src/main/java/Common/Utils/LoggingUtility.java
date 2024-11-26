package Common.Utils;

import Common.ExtentReportManager;

public class LoggingUtility {
    public void logInfo(String message) {
        ExtentReportManager.info(message);
    }
    public void logError(String message) {
        ExtentReportManager.fail(message);
    }
    // Additional logging methods...
}
