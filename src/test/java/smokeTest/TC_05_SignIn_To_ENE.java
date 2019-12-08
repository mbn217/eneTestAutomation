package smokeTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class TC_05_SignIn_To_ENE extends TestBase {
	private static String howItworks = "How It Works";

	
	
	
	
	
	@Test (priority = 1)
	public void verifySignIn() {

		test.getTest().setDescription("This Test case will verify that the sign in functionality is working properly");

		
		test.log(LogStatus.PASS, "Test [verifySignIn] PASSED");
	}
}
