package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CourseraPage extends BasePage{

	//Constructor
	public CourseraPage(WebDriver driver) {
		super(driver);
	}	
	
	//Locators
	@FindBy(xpath="//div[@class='rc-LoggedOutDesktopHeader context-header']//a[1]")
	WebElement logoClick_loc;
	
	// Actions
	public boolean VerifyCourseraTitle() throws InterruptedException {
		Thread.sleep(3000);
		boolean status = logoClick_loc.isDisplayed();
		if(status) {
			System.out.println("Title Verified");
		}else {
			System.out.println("Title Not Matching");
		}
		return true;
	}
}
