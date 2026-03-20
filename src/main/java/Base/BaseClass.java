package Base; 

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Utility.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
    
    // Protected taaki child classes (tests) ise use kar sakein
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    // Logger initialization - Log4j2
    public static final Logger logger = LogManager.getLogger(BaseClass.class);
    
    @BeforeMethod(alwaysRun = true)
    public void setup() {
    	
    	//safety guard
    	resetDriver();
    	
        logger.info("**************** Starting Test Setup ****************");
        
        try {
            WebDriverManager.chromedriver().setup();
            
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
           // options.addArguments("--remote-allow-origins=*");
            
            
            driver = new ChromeDriver(options);
            
            if (driver == null) {
                throw new RuntimeException("Driver is NULL after initialization!");
            }
            
            logger.info("Driver initialized: " + (driver != null));
           // logger.info("Chrome Browser launched successfully.");
            
            //driver.manage().window().maximize();
            driver.get(ConfigReader.get("url"));
            logger.info("Navigated to URL: " + ConfigReader.get("url"));
            
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            //wait for app to fully load
            wait.until(ExpectedConditions.presenceOfElementLocated(
            	    By.cssSelector("mat-toolbar")));
            	logger.info("App fully loaded.");

            // Handling the Welcome Banner
            try {
                WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[aria-label='Close Welcome Banner']")));
                closeBtn.click();
                logger.info("Welcome banner closed successfully.");
            } catch (Exception e) {
                logger.warn("Welcome banner did not appear or could not be closed. Skipping.");
            }
            
        } catch (Exception e) {
        	logger.error("Failed to initialize the driver setup", e);
            throw new RuntimeException("Driver initialization failed", e);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser session closed.");
        }
        logger.info("**************** Test Execution Finished ****************");
    }
    
    //reset the driver in case of retry
    public void resetDriver() {
        if (driver != null) {
            try { 
                driver.quit(); 
            } catch (Exception ignored) {}
            driver = null;
            logger.info("Driver reset for retry.");
        }
    }
    
    // ✅ Add this getter in BaseClass.java
    public WebDriver getDriver() {
        return driver;
    }

    
    
    
}