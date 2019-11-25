package smokeTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class TC_02_Verify_ENE_Headers extends TestBase {
	private static String howItworks = "How It Works";
	private static String sellBooks = "Sell Books";
	private static String buyBooks = "Buy Books";
	private static String rentBooks = "Rent Books";
	private static String support = "Support";
	private static String signIN = "Sign In";
	
	
	
	
	
	@Test (priority = 1)
	public void verifyIfAllTheHeadersLabelArePresent() {

		test.getTest().setDescription("This Test case twill verify that the header of the ene book store are displayed successfully");

		Assert.assertEquals(homePage.getHowItWorksHeaderLabel(), howItworks);
		Assert.assertEquals(homePage.getSellBooksLabel(), sellBooks);
		Assert.assertEquals(homePage.getBuyBooksLabel(), buyBooks);
		Assert.assertEquals(homePage.getRentBooksLabel(), rentBooks);
		Assert.assertEquals(homePage.getSupportLabel(), support);
		Assert.assertEquals(homePage.getSignINLabel(), signIN);
		
		test.log(LogStatus.PASS, "Test [verifyIfAllTheHeadersLabelArePresent] PASSED");
	}
}
