package testCases;
import org.testng.annotations.Test;
import testBase.BaseClass;
import utilities.ScreenShot;
import pageObjects.FindCourses;
public class TC_002 extends BaseClass{

	@Test(groups= {"regression", "master"})
	public void VerifyFindCoursesPage() throws Exception
	{
		ScreenShot ss = new ScreenShot();
		logger.info("**** Starting TC_002 ***");
		try {
			FindCourses findcourses = new FindCourses(driver);
			logger.info("Capturing FindCourses Page SS");
			ss.screenshot(driver, "FindCoursesSS");
			
			logger.info("Search Web Development Course passing in input Box.");
			findcourses.setSearchBox();
			
			logger.info("CLick on Search Button");
			findcourses.clickSearchBtn();
			
			logger.info("Click on Show More Languages..");
			findcourses.clickOnShowMore();
			
			logger.info("Select Language From The All Langugage List");
			findcourses.selectLanguage();
			
			logger.info("Click on Apply Button");
			findcourses.clickOnApplyBtn();
			
			logger.info("Select Beginner Level");
			findcourses.clickOnBeginnerLoc();
			
			logger.info("Showing all Course Details..");
			findcourses.getCourseDetails();
			
			logger.info("***** Finished TC_002 *****");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			ss.screenshot(driver, "Failed SS");
			logger.info("*** Test Case Failed ***");
		}
		
		
		
		
		
	}
}
