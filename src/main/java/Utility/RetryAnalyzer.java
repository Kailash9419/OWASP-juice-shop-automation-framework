package Utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private static final int maxTry = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (count < maxTry) {
            count++;

            // ✅ Reset driver via public method
            Object instance = result.getInstance();
            if (instance instanceof Base.BaseClass) {
                Base.BaseClass base = (Base.BaseClass) instance;
                base.resetDriver();
            }
            return true;
        }
        return false;
    }
}