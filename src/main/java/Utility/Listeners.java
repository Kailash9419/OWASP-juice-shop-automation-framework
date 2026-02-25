package Utility;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Base.BaseClass;

public class Listeners extends BaseClass implements ITestListener {
	
    // Extent report initialize
    ExtentReports extent = ExtentManager.createInstance();
    
    // ThreadLocal ensures tests don't overwrite each other during parallel execution
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); 

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); // Store the test object for the current thread
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
            // Best practice: Get driver from the result instance to avoid nulls
            WebDriver driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName());
            
            // Adding screenshot to report
            extentTest.get().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
            logger.info("Screenshot captured successfully at: " + screenshotPath);
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