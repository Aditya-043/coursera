package testCases;
import org.testng.annotations.Test;

import pageObjects.AccountDetailsFormPage;
import pageObjects.ForEnterprisePage;
import pageObjects.ForUniversitiesPage;
import pageObjects.PlanDetailsFormPage;
import testBase.BaseClass;
import utilities.ScreenShot;


public class TC_004 extends BaseClass{
	
	@Test(groups= {"smoke", "master"})
	public void fillFormWithInvalidInput() throws Exception {
        
		ScreenShot ss = new ScreenShot();
		logger.info("**** Starting TC_004 ***");
		try {
			ForEnterprisePage forEnterprisePage = new ForEnterprisePage(driver);
	        ForUniversitiesPage forUniversitiesPage = new ForUniversitiesPage(driver);
	        PlanDetailsFormPage planDetailsPage = new PlanDetailsFormPage(driver);
	        AccountDetailsFormPage accountDetailsPage = new AccountDetailsFormPage(driver);
	        
	        logger.info("Click on Enterprise Link..");
	        logger.info("Capturing For Enterprise SS");
	        ss.screenshot(driver, "For EnterpriseSS");
	        forEnterprisePage.clickForEnterpriseBtn();
	        
	        logger.info("Click on University Link");
	        logger.info("Click on Solution Link");
	        logger.info("Click on upSkills link");
	        logger.info("Click on get Started Button");
	        ss.screenshot(driver, "For EnterpriseSS");
	        forUniversitiesPage.navigateToForm();
	        
	        
	        logger.info("Filling the Plan Details form");
	        logger.info("Click on Continue Button.");
	        logger.info("Capturing the Plan Details Form ScreenShot");
	        ss.screenshot(driver, "FormFill SS");
	        planDetailsPage.fillPlanDetails();
	        
	        logger.info("Filling the AccountDetails form");
	        logger.info("Click on Continue Button.");
	        logger.info("Capturing the Account Details Form ScreenShot");
	        ss.screenshot(driver, "FormFill SS");
	        accountDetailsPage.fillForm();
	        
	        logger.info("Identifying error in a page..");
	        logger.info("Click on Continue Button.");
	        logger.info("Capturing the Email Error ScreenShot");
	        ss.screenshot(driver, "ErrorEmailSS");
	        accountDetailsPage.getEmailErrorMessage();
	        logger.info("**** Finished TC_004 ***");
		}
        catch(Exception e)
		{
        	logger.info("Test Case 4 Failed..");
        	ss.screenshot(driver, "FailedSS4");
        	System.out.println(e.getMessage());
		}

    }
}
