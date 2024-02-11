package testCases;

import java.io.IOException;

//import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LanguageLearningPage;
//import pageObjects.WebDevPage;
import testBase.BaseClass;
import utilities.ScreenShot;

public class TC_003 extends BaseClass{	
	
	@Test(groups= {"regression", "master"})
	public void AllCourses() throws IOException, InterruptedException {
		ScreenShot ss = new ScreenShot();
		logger.info("*****  Starting TC_003  *****");
		try {
			LanguageLearningPage languageLearningPage = new LanguageLearningPage(driver);
			
			logger.info("Passing Language Learning in input Box..");
			languageLearningPage.basicOperations();
			ss.screenshot(driver, "PassingInputSS");
			
			logger.info("Get Course Count");
			languageLearningPage.getCourseCount();
			ss.screenshot(driver, "TotalCcourseSS");
			logger.info("*****  Finished TC_003  *****");
			
		}
		catch(Exception e)
		{
			logger.info("Language Learning Page Test Case is Failed.");
			System.out.println(e.getMessage());
			
		}
		
	}
}
