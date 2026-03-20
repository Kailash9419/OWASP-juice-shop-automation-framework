package Utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private static final int maxTry = 1;
    private static final Logger logger = LogManager.getLogger(RetryAnalyzer.class);

    @Override
    public boolean retry(ITestResult result) {
        // ✅ Only retry on actual FAILURE
        if (result.getStatus() == ITestResult.FAILURE && count < maxTry) {
            count++;
            logger.warn("🔄 Retrying test: " + result.getName() + " | Attempt: " + count);

            Object instance = result.getInstance();
            if (instance instanceof Base.BaseClass) {
                Base.BaseClass base = (Base.BaseClass) instance;
                base.resetDriver();
                logger.warn("✅ Driver reset for retry.");
            } else {
                logger.error("❌ Instance is NOT BaseClass — resetDriver() skipped! Class: " 
                    + instance.getClass().getName());
            }
            return true;
        }
        return false;
    }
}