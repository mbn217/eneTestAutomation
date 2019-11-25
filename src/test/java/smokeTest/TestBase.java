package smokeTest;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ExtentReports.ExtentFactory;
import pageObjects.HomePage;
import utilities.Driver;
import utilities.Environment;
import utilities.JavaUtils;
import utilities.SeleniumUtils;

public class TestBase {
	private static Logger log = Logger.getLogger(TestBase.class);
	protected WebDriver driver;
	HomePage homePage;
	ExtentReports report;
	ExtentTest test;
	Environment env;
	

	@BeforeMethod(alwaysRun = true, description = "Method Level Setup!")
	@Parameters({ "browser", "environment","testEnvironment" })
	public void setUP(@Optional("firefox") String browser, @Optional("local") String environment, 
			@Optional("prod") String testEnvironment) throws Throwable {
		log.info("Test [" + getClass().getSimpleName() + "] *********STARTED********");
        ConfigFactory.setProperty("env", testEnvironment);
        env = ConfigFactory.create(Environment.class);
		// Create Driver
		if (environment.equals("grid")) {
			driver = Driver.runRemoteDriver(browser);
		} else {
			driver = Driver.getDriver(browser);
		}
		homePage = new HomePage(driver);
		SeleniumUtils.driver = driver;
		driver.get(env.url());
		report = ExtentFactory.getInstance();
		test = report.startTest(getClass().getSimpleName());
	}

	@AfterMethod(alwaysRun = true, description = "Method tearDown!")
	public void tearDown(ITestResult result) throws Exception {
		try {
			Thread.sleep(1000);
			if (ITestResult.FAILURE == result.getStatus()) {
				String imagePath = test.addScreenCapture(SeleniumUtils.getScreenshot(result.getName()));
				test.log(LogStatus.FAIL, "Test case [" + getClass().getSimpleName() + "] FAILED", imagePath);
			}
		} catch (Exception e) {
			log.error("Something went wrong! " + e.getMessage());
		} finally {
			Driver.closeDriver();	
			report.endTest(test);
			report.flush();
			JavaUtils.javaWait(2);
			log.info("Test [" + getClass().getSimpleName() + "] ********ENDED********");
		}

	}

}
