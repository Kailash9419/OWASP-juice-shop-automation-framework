package Utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

	public static String captureScreenshot(WebDriver driver, String testName) {

		// Add a timestamp so every failure has a unique file name
		String timeStamp = new java.text.SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);

		// Path where screenshot will be saved
		String destination = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timeStamp + ".png";
		try {
			FileUtils.copyFile(src, new File(destination));
		} catch (IOException e) {

			System.out.println("Screenshot Capture Failed: " + e.getMessage());
		}

		return destination; // Return path to attach it to Extent Report later
	}

}
