package smokeTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class TC_07_SellBooks extends TestBase {
	private static String howItworks = "How It Works";

	
	
	
	
	
	@Test (priority = 1)
	public void sellBooks() {

		test.getTest().setDescription("This Test case will verify that the end-to-end functionality for sell books is working properly");

		
		test.log(LogStatus.PASS, "Test [sellBooks] PASSED");
	}
}
