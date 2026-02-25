package Utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;
    private static final int maxTry = 1; // It will retry 1 time if a test fails

    @Override
    public boolean retry(ITestResult result) {
        if (count < maxTry) {
            count++;
            return true; // Tells TestNG to run the test again
        }
        return false;
    }
}