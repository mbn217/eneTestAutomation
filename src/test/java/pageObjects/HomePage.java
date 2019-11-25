package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.SeleniumUtils;



public class HomePage {

	private static Logger log = Logger.getLogger(HomePage.class);
	private WebDriver driver;
	/**
	 * Elements of the Home page
	 */
	@FindBy(xpath = "//a[contains(.,'How It Works')]")
	public WebElement howItWorksLabel; //This is the label on the login page
	
	@FindBy(xpath = "//a[contains(.,'Sell Books')]")
	public WebElement sellBooksLabel;
	
	@FindBy(xpath = "//a[contains(.,'Buy Books')]")
	public WebElement buyBooksLabel;
	
	@FindBy(xpath = "//a[contains(.,'Rent Books')]")
	public WebElement rentBooksLabel;
	
	@FindBy(xpath = "//a[contains(.,'Support')]")
	public WebElement supportLabel;
	
	@FindBy(xpath = "//a[contains(.,'Sign In')]")
	public WebElement loginLabel;
	
	@FindBy(xpath = "//a[contains(.,'Sell Books')]")
	public WebElement myBucksLabel;
	
	@FindBy(xpath = "//a[contains(.,'Sell Books')]")
	public WebElement searchField;
	
	/**
	 * Page Object Methods
	 */


	public String getHowItWorksHeaderLabel() {
		try {
			SeleniumUtils.waitForElement(howItWorksLabel);
			String actualLabel = howItWorksLabel.getText();
			log.info("The Element is FOUND --> " + actualLabel);
			return actualLabel;	
		} catch (NoSuchElementException e) {
			log.error("The Element is not FOUND --> " + e.getMessage());
			return null;
		} catch (Exception e) {
			log.error("Something went Wrong --> " + e.getMessage());
			return null;
		}

	}
	

	public String getSellBooksLabel() {
		try {
			SeleniumUtils.waitForElement(sellBooksLabel);
			String actualLabel = sellBooksLabel.getText();
			log.info("The Element is FOUND --> " + actualLabel);
			return actualLabel;	
		} catch (NoSuchElementException e) {
			log.error("The Element is not FOUND --> " + e.getMessage());
			return null;
		} catch (Exception e) {
			log.error("Something went Wrong --> " + e.getMessage());
			return null;
		}

	}
	

	public String getBuyBooksLabel() {
		try {
			SeleniumUtils.waitForElement(buyBooksLabel);
			String actualLabel = buyBooksLabel.getText();
			log.info("The Element is FOUND --> " + actualLabel);
			return actualLabel;	
		} catch (NoSuchElementException e) {
			log.error("The Element is not FOUND --> " + e.getMessage());
			return null;
		} catch (Exception e) {
			log.error("Something went Wrong --> " + e.getMessage());
			return null;
		}

	}
	

	public String getRentBooksLabel() {
		try {
			SeleniumUtils.waitForElement(rentBooksLabel);
			String actualLabel = rentBooksLabel.getText();
			log.info("The Element is FOUND --> " + actualLabel);
			return actualLabel;	
		} catch (NoSuchElementException e) {
			log.error("The Element is not FOUND --> " + e.getMessage());
			return null;
		} catch (Exception e) {
			log.error("Something went Wrong --> " + e.getMessage());
			return null;
		}

	}
	
	

	public String getSupportLabel() {
		try {
			SeleniumUtils.waitForElement(supportLabel);
			String actualLabel = supportLabel.getText();
			log.info("The Element is FOUND --> " + actualLabel);
			return actualLabel;	
		} catch (NoSuchElementException e) {
			log.error("The Element is not FOUND --> " + e.getMessage());
			return null;
		} catch (Exception e) {
			log.error("Something went Wrong --> " + e.getMessage());
			return null;
		}

	}
	
	public String getSignINLabel() {
		try {
			SeleniumUtils.waitForElement(loginLabel);
			String actualLabel = loginLabel.getText();
			log.info("The Element is FOUND --> " + actualLabel);
			return actualLabel;	
		} catch (NoSuchElementException e) {
			log.error("The Element is not FOUND --> " + e.getMessage());
			return null;
		} catch (Exception e) {
			log.error("Something went Wrong --> " + e.getMessage());
			return null;
		}

	}
	
	
	/**
	 * PageObject Constructor
	 * @throws Throwable 
	 */
	public HomePage(WebDriver driver) throws Throwable {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
