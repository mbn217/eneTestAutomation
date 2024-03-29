package smokeTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class TC_01_Login_To_ENE_Dashboard extends TestBase {
	private static String expectedTitle = "ene book store";
	@Test (priority = 1)
	public void loginToENEdashboard() {

		test.getTest().setDescription("This Test case to verify that user is able to navigate to ene dashboard");

		Assert.assertEquals(driver.getTitle(), expectedTitle);
		test.log(LogStatus.PASS, "Test [loginToENEdashboard] PASSED");
	}
}
