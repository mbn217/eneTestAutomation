package utilities;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

/**
 * @author Mohamed.Nheri
 * @Purpose This is the Selenium utility class that will contain most of the reusable
 *          selenium methods needed to create the tests
 * @Usage Methods in this class are static , so you can just use class name with
 *        method name concatenated by "." example: SeleniumUtils.openBrowser
 */
public class SeleniumUtils {

	public static WebDriver driver;
	public static JavascriptExecutor JSdriver;
	private static Logger log = Logger.getLogger(SeleniumUtils.class);
	public static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());



	/**
	 * @author Mohamed.Nheri
	 * @Purpose This method will accept an alert if its displayed
	 * @param timeout
	 *            the time needed to wait for an alert to display
	 */
	public static void acceptAlertIfAvailable(long timeout) {
		log.info("Checking if there is a popup alert to accept it");
		long waitForAlert = System.currentTimeMillis() + timeout;
		boolean boolFound = false;
		do {
			try {
				Alert alert = driver.switchTo().alert();
				if (alert != null) {
					String alertMsg = alert.getText();
					alert.accept();
					log.info("Pop-up alert [" + alertMsg + "] was found and accepted");
					boolFound = true;
				}
			} catch (NoAlertPresentException ex) {
				log.error("Pop-up alert Not handled and Exception occured --> " + ex.getMessage());
			}
		} while ((System.currentTimeMillis() < waitForAlert) && (!boolFound));
	}

	/**
	 * @author Mohamed.Nheri
	 * @Purpose This method will dismiss an alert if its displayed
	 * @param timeout
	 *            the time needed to wait for an alert to display
	 */
	public static void dismissAlertIfAvailable(long timeout) {
		log.info("Checking if there is a popup alert to dismiss it");
		long waitForAlert = System.currentTimeMillis() + timeout;
		boolean boolFound = false;
		do {
			try {
				Alert alert = driver.switchTo().alert();
				if (alert != null) {
					String alertMsg = alert.getText();
					alert.dismiss();
					log.info("Pop-up alert [" + alertMsg + "] was found and dismissed");
					boolFound = true;
				}
			} catch (NoAlertPresentException ex) {
				log.error("Pop-up alert Not handled and Exception occured --> " + ex.getMessage());
			}
		} while ((System.currentTimeMillis() < waitForAlert) && (!boolFound));
	}

	/**
	 * @author Mohamed.Nheri
	 * @Purpose This method will wait for an element until its visible , the default
	 *          wait is 30seconds
	 * @param element
	 *            -> the element you want to wait for their visibility
	 * @return boolean value , true if element is present and false if not
	 */
	public static boolean waitForElement(WebElement element) throws IOException {
		log.info("Waiting for an element in the page...");
		boolean isElementPresent = true;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			log.info("Element is visible");
			return isElementPresent;
		} catch (Exception e) {
			log.info("waitForElement method failed! " + e.getMessage());
			return !isElementPresent;
		}
	}


	/**
	 * @author Mohamed.Nheri
	 * @Purpose This method will return the time that the page took to load
	 * @param N/A
	 * @return loadtime
	 */
	public static long getPageLoadTime() {
		log.info("Checking load time of the page");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");
		long navigationStart = (Long) js.executeScript("return window.performance.timing.navigationStart;");
		long loadtime = (loadEventEnd - navigationStart) / 1000;
		System.out.println("Page Load Time is " + loadtime + " seconds.");
		return loadtime;
	}


	/**
	 * @author Mohamed.Nheri
	 * @Purpose This method will Delete all cookies present in the application
	 * @param N/A
	 * @return N/A
	 */
	public static void deleteAllCookies() {
		log.info("Delete all cookies");
		driver.manage().deleteAllCookies();
	}

	/**
	 * @author Mohamed.Nheri
	 * @Purpose This method will check if the element is present in the webpage
	 *          present in the application
	 * @param element
	 *            --> Element of the webpage
	 * @return true if present and false otherwise
	 */
	public static boolean isElementPresent(WebElement element) {
		log.info("Checking if element is present");
		try {
			element.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			log.error("The Element is not FOUND --> " + e.getMessage());
			return false;
		} catch (Exception e) {
			log.error("Something went Wrong --> " + e.getMessage());
			return false;
		}
	}


	
	/**
	 * @author Mohamed.Nheri
	 * @Purpose This method will turn off the implicit wait
	 * @param N/A
	 * @return N/A
	 */
	public static void turnOffImplicitWaits() {
		log.info("Turning off implicit wait");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}


	/**
	 * @author Mohamed.Nheri
	 * @Date 05/11/2018
	 * @Purpose This method will take screen shot of the failed test 
	 * @param screenshotName --> The name we want to use for the screen shot taken
	 * @return destination --> the destination path of the screen shot
	 */
	public static String getScreenshot(String screenshotName) throws Exception {
		log.info("Taking a screen shot for the failed test case");
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "\\src" + "\\test\\resources\\ScreenShots\\"
				+ screenshotName + timeStamp + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		// Returns the captured file path
		log.info("Screen Shots directory : "+ destination);
		return destination;
	}
	

	/**
	 * @author Mohamed.Nheri
	 * @Purpose This method will wait for a page to load
	 * @param timeOutInSeconds --> time we want to wait for the element to be visible
	 * @return N/A
	 */
	public static void waitForPageToLoad(long timeOutInSeconds) {
		log.info("Waiting for page to load");
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			log.info("Waiting --> " + timeOutInSeconds +" Seconds");
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(expectation);
		} catch (Throwable error) {
			log.error(
					"Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
		}
	}

	
	
	
}
