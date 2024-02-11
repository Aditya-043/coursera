package pageObjects;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.JavaScriptManager;

public class AccountDetailsFormPage extends BasePage{

	public Properties p;
	
	
	// Constructor
	public AccountDetailsFormPage(WebDriver driver) {
		super(driver);
	}
	
	// Locators
	@FindBy(xpath = "//input[@placeholder='Full name']")
	private WebElement fullNameInput;
	
	@FindBy(xpath = "//input[@aria-label='Business email']")
	private WebElement emailInput;
	
	@FindBy(xpath = "//input[@aria-label='Job title']")
	private WebElement jobInput;
	
	@FindBy(xpath = "//input[@aria-label='Organization name']")
	private WebElement organizationNameInput;
	
	@FindBy(xpath="//span[contains(text(),'Continue')]")
	private WebElement continueButton;
	
	@FindBy(xpath="//span[normalize-space()='Please enter a valid email.']")
	private WebElement errorMsg;
	
	@FindBy(xpath="//span[contains(text(), \"No thanks\")]")
	private WebElement discountClose_loc;
	
	
	//Actions
	JavaScriptManager jsm = new JavaScriptManager();
	public void fillForm() throws Exception {
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		Thread.sleep(3000);
		
		// pass fullName to input Box
		fullNameInput.sendKeys(p.getProperty("fullName"));
		
		// pass email to input Box
		emailInput.sendKeys(p.getProperty("email"));
		
		// pass job Value to input Box
		jobInput.sendKeys(p.getProperty("jobInputValue"));
		
		// pass organization Name to input Box
		organizationNameInput.sendKeys(p.getProperty("organizationName"));
		
		// Click on Continue Button
		jsm.scrollDownTillElementPresent(driver, jobInput);
		jsm.highlightBorder(driver, continueButton);
		continueButton.click();
		
	
		try {
			jsm.highlightBorder(driver, discountClose_loc);
			discountClose_loc.click();
		} catch (Exception e) {
			//System.out.println("PopUp is Not Displaying..");
		}
	}
	
	public String getEmailErrorMessage() {
        //checking if discount popup is displayed and closing it 
        try{ 
        	if(discountClose_loc.isDisplayed())
        		discountClose_loc.click();
        }
        catch(Exception e){}
        // Wait for the error message to be present in the DOM
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	// Using XPath to locate the second error message element
    	WebElement emailErrorElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"cds-react-aria-14-validation-label\"]/span")));
    	System.out.println(errorMsg.getText());
        // Return the text of the error message
        return emailErrorElement.getText();
        // Check if the error message is displayed
        //return emailErrorElement.isDisplayed();

 
    }	
}
