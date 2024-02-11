package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CourseraPage;
import testBase.BaseClass;
import utilities.ScreenShot;
public class TC_001 extends BaseClass{
	
	@Test(priority=1, groups= {"smoke", "master"})
	public void Verify_CourseraPage() throws Exception{
		
		ScreenShot ss = new ScreenShot();
		logger.info("*****  Starting TC_001 *****");
		try {
			CourseraPage cp = new CourseraPage(driver);
			logger.info("Capturing Coursera Tilte SS");
			ss.screenshot(driver, "CourseraPageSS");
			
			logger.info("Verifying Coursera Title");
			boolean status = cp.VerifyCourseraTitle();
			logger.info("***** Finished TC_001 *****");
			Assert.assertEquals(status, true);
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Coursera Page did not displayed");
			logger.info("Coursera Title not Verified..");
		}
	}  
}
