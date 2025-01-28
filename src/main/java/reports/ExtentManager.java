package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utils.DriverFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static final ThreadLocal<ExtentTest> testThreadLocal = new ThreadLocal<>();
    private static final String TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
    private static final String REPORT_PATH;
    private static ExtentReports extent;

    static {
        File reportDir = new File("test-output");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }
        REPORT_PATH = "test-output/ExtentReport_" + TIMESTAMP + ".html";
    }

    // Initialize ExtentReports (singleton)
    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_PATH);

            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("Selenium Test Report");

            extent.attachReporter(spark);
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Browser", DriverFactory.getBrowserName());
        }
        return extent;
    }

    // Set ExtentTest for current thread
    public static void setTest(String testName, String description) {
        ExtentTest test = getInstance().createTest(testName, description);
        testThreadLocal.set(test);
    }

    // Get ExtentTest for current thread
    public static ExtentTest getTest() {
        return testThreadLocal.get();
    }

    // Remove ExtentTest from thread local (cleanup after test execution)
    public static void removeTest() {
        testThreadLocal.remove();
    }
}
