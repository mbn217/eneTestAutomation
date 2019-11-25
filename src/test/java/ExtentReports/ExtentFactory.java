package ExtentReports;

import org.apache.log4j.Logger;

import com.relevantcodes.extentreports.ExtentReports;

import utilities.ConfigurationReader;

/**
 * @author Mohamed.Nheri
 * @purpose This is is a Report class using Extent Report library API To see the
 *          report you can navigate to Extent Report folder in Eclipse project
 *          and open it using firefox (IE currently not compatible)
 * @param N/A
 * @return extent: instance of the ExtentReports class
 */
public class ExtentFactory {
	private static Logger log = Logger.getLogger(ExtentFactory.class);

	public static ExtentReports getInstance() {
		log.info("Instantiating an instance of the ExtentReports ");
		ExtentReports extent;
		extent = new ExtentReports("./target/ene.html", false);
		extent.addSystemInfo("Selenium Version", "3.11.0");
		extent.addSystemInfo("Environment", "Production").addSystemInfo("Application", "ene");

		return extent;
	}
}