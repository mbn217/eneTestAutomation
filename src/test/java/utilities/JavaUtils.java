package utilities;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

/**
 * @author Mohamed.Nheri
 * @Purpose This is the java utility class that will contain most of the
 *          reusable java methods
 * @Usage Methods in this class are static , so you can just use class name with
 *        method name concatenated by "." example: JavaUtils.deleteFile
 */
public class JavaUtils {

	private static Logger log = Logger.getLogger(JavaUtils.class);
	private static final Long MILLISECONDS_IN_SECOND = Long.valueOf(1000);

	

	/**
	 * @author Mohamed.Nheri
	 * @Purpose This method will wait n time in seconds
	 * @param timeToWaitInSeconds
	 *            --> the time to wait in seconds
	 * @return N/A
	 */
	public static void javaWait(int timeToWaitInSeconds) {
		log.info("waiting " + timeToWaitInSeconds + " seconds");
		try {
			Thread.sleep(timeToWaitInSeconds);
		} catch (InterruptedException e) {
			log.error("Something went Wrong --> " + e.getMessage());
		}
	}

	/**
	 * @author Mohamed.Nheri
	 * @Purpose This function gets current Time Stamp as String
	 * @param N/A
	 * @return -Time Stamp as String
	 */
	public static String getTimeStamp() {
		log.info("Getting current time");
		java.util.Date date = new java.util.Date();
		return new Timestamp(date.getTime()).toString();
	}

	

}
