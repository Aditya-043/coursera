package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.JavaScriptManager;

public class ForUniversitiesPage extends BasePage{

	// Constructor
	public ForUniversitiesPage(WebDriver driver) {
		super(driver);
	}
	JavaScriptManager jsm = new JavaScriptManager();
	
	
	// Locators
	@FindBy(xpath="//div[@aria-label='Banner']//a[3]")
	WebElement universityBtn_loc;
	
	@FindBy(xpath = "//a[normalize-space()='Solutions']")
	private WebElement solutionsLink;
	
	@FindBy(xpath = "//p[normalize-space()='Upskill teams of 5 to 125 employees']")
	private WebElement upskillTeam;
	
	@FindBy(xpath = "//span[@class='cds-button-label'][normalize-space()='Get Started'][1]")
	private WebElement getStartedButton;
	
	@FindBy(xpath="//h1[normalize-space()='Accelerate team performance']")
	private WebElement scrollDownToGetStarted_loc;
	
	
	// Actions
	public void navigateToForm() {
		
		jsm.highlightBorder(driver, universityBtn_loc);
		universityBtn_loc.click();
		
		jsm.highlightBorder(driver, solutionsLink);
		solutionsLink.click();
		
		jsm.highlightBorder(driver, upskillTeam);
		upskillTeam.click();
		
		jsm.scrollDownTillElementPresent(driver, scrollDownToGetStarted_loc);
		jsm.highlightBorder(driver, getStartedButton);
		getStartedButton.click();
	}

}
