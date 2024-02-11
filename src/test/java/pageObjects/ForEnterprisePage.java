package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.JavaScriptManager;

public class ForEnterprisePage extends BasePage{
	
	// Constructor
	public ForEnterprisePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	// Locators
	@FindBy(xpath = "//a[normalize-space()='For Enterprise']")
	WebElement forEnterpriseBtn;
	
	@FindBy(xpath = "//p[normalize-space()='Coursera']")
	WebElement courseraFooter_loc;
	 
	//Actions
	JavaScriptManager jsm = new JavaScriptManager();
	 public void clickForEnterpriseBtn() {
		 
		 // Scroll Down to Footer
		 jsm.scrollDownTillElementPresent(driver, courseraFooter_loc);
		 
		 // Click on Enterprise Link
		 JavascriptExecutor jse = (JavascriptExecutor)driver;
		 jsm.highlightBorder(driver, forEnterpriseBtn);
		 jse.executeScript("arguments[0].click()", forEnterpriseBtn);
	 }
}
