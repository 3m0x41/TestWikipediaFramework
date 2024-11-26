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
        ExtentTest test = getReporter().createTest(testName);
        setTest(test);
    }

    public static void endTest() {
        extent.removeTest(test.get());
    }

    public static void info(String message) {
        test.get().info(message);
    }

    public static void pass(String message) {
        test.get().pass(message);
    }

    public static void fail(String message) {
        test.get().fail(message);
    }

    public static void flushReports() {
        extent.flush();
    }
}