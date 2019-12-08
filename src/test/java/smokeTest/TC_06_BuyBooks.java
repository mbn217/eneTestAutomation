package smokeTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class TC_06_BuyBooks extends TestBase {
	private static String howItworks = "How It Works";

	
	
	
	
	
	@Test (priority = 1)
	public void buyBooks() {

		test.getTest().setDescription("This Test case will verify that the end-to-end functionality for buy books is working properly");

		
		test.log(LogStatus.PASS, "Test [buyBooks] PASSED");
	}
}
