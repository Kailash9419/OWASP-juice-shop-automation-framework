package Utility;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop = new Properties();

	static {

		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
								+ "/resources/config.properties");

			prop.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {

		return prop.getProperty(key);
	}
}
