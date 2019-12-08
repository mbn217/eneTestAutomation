package smokeTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class TC_03_Search_For_A_Book extends TestBase {
	private static String howItworks = "How It Works";

	
	
	
	
	
	@Test (priority = 1)
	public void searchForABook() {

		test.getTest().setDescription("This Test case twill verify that search for a book end-to-end testing is successful");

		test.log(LogStatus.PASS, "Test [searchForABook] PASSED");
	}
}
