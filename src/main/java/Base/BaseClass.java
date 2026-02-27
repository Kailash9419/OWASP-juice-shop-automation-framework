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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import Utility.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
    
    // Protected taaki child classes (tests) ise use kar sakein
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    // Logger initialization - Log4j2
    public static final Logger logger = LogManager.getLogger(BaseClass.class);
    
    @BeforeClass
    public void setup() {
        logger.info("**************** Starting Test Setup ****************");
        
        try {
            WebDriverManager.chromedriver().setup();
            
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            
            
            driver = new ChromeDriver(options);
            logger.info("Chrome Browser launched successfully.");
            
            //driver.manage().window().maximize();
            driver.get(ConfigReader.get("url"));
            logger.info("Navigated to URL: " + ConfigReader.get("url"));
            
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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
            logger.error("Failed to initialize the driver setup: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser session closed.");
        }
        logger.info("**************** Test Execution Finished ****************");
    }
}