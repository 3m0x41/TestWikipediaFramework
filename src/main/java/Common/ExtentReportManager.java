package Common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getReporter() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-reports.html");
            sparkReporter.config().setReportName("Automation Test Results");
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setTimelineEnabled(true);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static void startTest(String testName) {
        ExtentTest extentTest = getReporter().createTest(testName);
        setTest(extentTest);
    }

    public static void endTest() {
        test.remove(); // Clear ThreadLocal reference
    }

    public static void info(String message) {
        if (test.get() != null) {
            test.get().info(message);
        }
    }

    public static void pass(String message) {
        if (test.get() != null) {
            test.get().pass(message);
        }
    }

    public static void fail(String message) {
        if (test.get() != null) {
            test.get().fail(message);
        }
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}