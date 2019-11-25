package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;

import pageObjects.HomePage;



public class ConfigurationReader {
	private static Properties configFile;
	private static Logger log = Logger.getLogger(ConfigurationReader.class);
	
	/**
	 * @author Mohamed.Nheri
	 * @Purpose this method will load the properties file
	 * @param N/A
	 * @return N/A
	 * @throws FileNotFoundException if the properties file is not found
	 * @throws Exception if general error occur while loading the properties file
	 */
	static {
		log.info("Reading configuration.properties File");
		String path = "./src/main/resources/configuration.properties";

		try {
			FileInputStream input = new FileInputStream(path);

			configFile = new Properties();
			configFile.load(input);

			input.close();

		} catch (FileNotFoundException e) {
			log.error("The File is not FOUND --> " + e.getMessage());

		} catch (Exception e) {
			log.error("Something went Wrong --> " + e.getMessage());

		}
	}

	/**
	 * @author Mohamed.Nheri
	 * @Purpose this method will return the property value of the chosen string
	 * @param key
	 *            -> the key value you want to retrieve
	 * @return a String type for the value of the key in the config file
	 */
	public static String getProperty(String key) {
		return configFile.getProperty(key);
	}

	/**
	 * @author Mohamed.Nheri
	 * @Purpose this method will return the property value of the chosen string
	 * @param key
	 *            -> the key value you want to retrieve
	 * @return a boolean type for the value of the key in the config file
	 */
	public static boolean getBooleanProperty(String key) {
		boolean isHighlight = Boolean.parseBoolean(configFile.getProperty(key));
		return isHighlight;
	}

}
