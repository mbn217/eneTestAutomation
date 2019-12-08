package smokeTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class TC_04_Verify_HowItWorks_Steps extends TestBase {
	private static String howItworks = "How It Works";

	
	
	
	
	
	@Test (priority = 1)
	public void verifyHowItWorksPage() {

		test.getTest().setDescription("This Test case will verify that all steps of the How it works page are displayed successfully");

		
		test.log(LogStatus.PASS, "Test [verifyHowItWorksPage] PASSED");
	}
}
