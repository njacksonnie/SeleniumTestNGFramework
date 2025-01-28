package listeners;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentManager;
import utils.DriverFactory;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentManager.setTest(result.getMethod().getMethodName(), "Test Execution"); // Updated line
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().log(Status.PASS, MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN)); // Updated line
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String screenshotPath = ScreenshotUtils.captureScreenshot(DriverFactory.getDriver(), result.getName());
        ExtentManager.getTest() // Updated line
                .fail(result.getThrowable())
                .addScreenCaptureFromPath(screenshotPath)
                .log(Status.FAIL, MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().log(Status.SKIP, MarkupHelper.createLabel("Test Skipped", ExtentColor.ORANGE)); // Updated line
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        ExtentManager.getInstance().flush();
    }
}