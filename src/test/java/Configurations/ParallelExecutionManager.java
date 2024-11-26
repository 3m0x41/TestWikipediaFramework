package Configurations;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class ParallelExecutionManager {

    public static void main(String[] args) {
        // Create a TestNG suite
        XmlSuite suite = new XmlSuite();
        suite.setName("ParallelExecutionSuite");
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount(4);

        // Create API tests
        XmlTest apiTest = new XmlTest(suite);
        apiTest.setName("API Tests");
        List<XmlClass> apiClasses = new ArrayList<>();
        apiClasses.add(new XmlClass("API.WikipediaBaseTests"));
        apiClasses.add(new XmlClass("API.APIBaseTest"));
        apiTest.setClasses(apiClasses);

        // Create Mobile tests
        XmlTest mobileTest = new XmlTest(suite);
        mobileTest.setName("Mobile Tests");
        List<XmlClass> mobileClasses = new ArrayList<>();
        mobileClasses.add(new XmlClass("Mobile.FirstTest"));
        mobileClasses.add(new XmlClass("Mobile.HomePageTests"));
        mobileTest.setClasses(mobileClasses);

        // Create Web tests
        XmlTest webTest = new XmlTest(suite);
        webTest.setName("Web Tests");
        List<XmlClass> webClasses = new ArrayList<>();
        webClasses.add(new XmlClass("Web.FirstTest"));
        webClasses.add(new XmlClass("Web.SecondTest"));
        webTest.setClasses(webClasses);

        // Add suite to TestNG and execute
        TestNG testng = new TestNG();
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testng.setXmlSuites(suites);
        testng.run();
    }
}
