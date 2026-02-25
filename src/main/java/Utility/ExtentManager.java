package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        if (extent == null) {
            // Report ka path (reports folder ke andar)
            String path = System.getProperty("user.dir") + "/reports/Index.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(path);
            
            // Design Configuration
            spark.config().setTheme(Theme.DARK); // Dark theme professional lagta hai
            spark.config().setDocumentTitle("Automation Test Report");
            spark.config().setReportName("OWASP Juice Shop - Functional Testing");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            
            // System info jo report ke header mein dikhegi
            extent.setSystemInfo("QA Engineer", "Your Name");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }
}