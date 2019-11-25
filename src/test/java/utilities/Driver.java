package utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Mohamed.Nheri
 * @Purpose This is Driver class that will initialize the browser based on the
 *          entry in the configuration file
 * @Usage Methods in this class are static , so you can just use class name with
 *        method name concatenated by "." example: Driver.getDriver
 */
public class Driver {
	private static WebDriver driver;
	private static Logger log = Logger.getLogger(Driver.class);

	/**
	 * @author Mohamed.Nheri
	 * @Purpose it will open the browser based on the entry in the configuration
	 *          file
	 * @return driver sessionID
	 * @throws Throwable 
	 */

	// to do .. Change setproperty to WebDriverManager 2.0 from Apache for cleaner
	// code

	public static WebDriver getDriver(String browser) throws Throwable {
		// Condition to check if the driver have been initialized before or not
		String driverType = browser==null? ConfigurationReader.getProperty("browser") : browser;
		// if browser has value, use browser
		// else use the value from the configuration file
		if (driver == null ) {
			log.info("Checking for a broswer to open");
			// create webdriver based on the value of browser parameter
			switch (driverType.toLowerCase()) {
			case "chrome":
				//System.setProperty("webdriver.chrome.driver", ConfigurationReader.getProperty("chrome.driver.path"));
				WebDriverManager.chromedriver().setup();
			    ChromeOptions options = new ChromeOptions();
			    options.setExperimentalOption("useAutomationExtension", false);
			    //disable automation info bar
			    options.addArguments("disable-infobars");
				driver = new ChromeDriver(options);
				break;


			case "firefox":
				//System.setProperty("webdriver.gecko.driver", ConfigurationReader.getProperty("firefox.driver.path"));
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Opened firefox browser");
				break;

			default:
				//System.setProperty("webdriver.gecko.driver", ConfigurationReader.getProperty("firefox.driver.path"));
				driver = new FirefoxDriver();
				WebDriverManager.firefoxdriver().setup();
				log.info("No browser specified, Selecting Firefox as Default browser");
				break;
			}
			driver.manage().window().maximize();
			log.info("Browser maximized");

		}

		return driver;
	}

	/**
	 * @author Mohamed.Nheri
	 * @Purpose it will close the browser
	 * @return NA
	 * @throws InterruptedException
	 */
	public static void closeDriver() throws InterruptedException {
		if (driver != null) {
			driver.quit();
			Thread.sleep(2);
			driver = null;
			log.info("The Browser was closed successfully");
		}
	}
	
	
	
	
	public static WebDriver runRemoteDriver(String browser) {
		log.info("Running Remote Webdriver...");
		String hubUrl = "http://localhost:4444/wd/hub";
		DesiredCapabilities capabilities = null;
		System.out.println("Starting " + browser + " on grid");

		// Creating driver
		switch (browser) {
		case "chrome":
			log.info("Starting " + browser + " on grid");
			capabilities=DesiredCapabilities.chrome(); 
			capabilities.setCapability("jenkins.label", "Selenium-WODC-Windows");
			capabilities.setPlatform(Platform.WIN8); 
			break;

		case "firefox":
			log.info("Starting " + browser + " on grid");
			capabilities=DesiredCapabilities.firefox(); 
			capabilities.setPlatform(Platform.WIN8); 
			break;
		}

		try {
			driver = (new RemoteWebDriver(new URL(hubUrl), capabilities));
		} catch (MalformedURLException e) {
			log.error("Somthing went wrong ---> "+ e.getMessage());
		}

			return driver;
	}
	
	
}
