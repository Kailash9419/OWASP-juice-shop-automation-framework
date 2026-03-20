package Utility;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Base.BaseClass;

public class Listeners implements ITestListener { // ✅ Removed extends BaseClass
    
    private static final Logger logger = LogManager.getLogger(Listeners.class); // ✅ Own logger
    
    ExtentReports extent = ExtentManager.createInstance();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        logger.info("--- Starting Test Execution: " + result.getName() + " ---");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed Successfully");
        logger.info("PASSED: Test Case " + result.getName() + " finished successfully.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
        logger.error("FAILED: Test Case " + result.getName() + " encountered an error.");
        
        try {
            // ✅ Correct way — cast instance to BaseClass directly
            Object instance = result.getInstance();
            if (instance instanceof BaseClass) {
                BaseClass baseInstance = (BaseClass) instance;
                WebDriver driver = baseInstance.getDriver(); // ✅ Use a public getter
                
                if (driver != null) {
                    String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName());
                    extentTest.get().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
                    logger.info("Screenshot captured at: " + screenshotPath);
                } else {
                    logger.warn("Driver is null — skipping screenshot.");
                }
            }
        } catch (Exception e) {
            logger.error("Exception while taking screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        logger.info("--- All Test Cases Finished. Report Flushed. ---");
    }
}