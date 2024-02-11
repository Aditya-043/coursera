package pageObjects;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import utilities.ExcelUtility;
import utilities.JavaScriptManager;

public class LanguageLearningPage extends BasePage {

	ExcelUtility excelutility = new ExcelUtility();
	
	// Constructor
	public LanguageLearningPage(WebDriver driver) {
		super(driver);
	}
	
	// Locators
	@FindBy(xpath="//a[@aria-label='Coursera']")
	WebElement logoClick_loc;
	
	@FindBy(xpath="//input[starts-with(@placeholder,'What do you')]")
	WebElement inputBox_loc;
	
	@FindBy(xpath="//button[@class=\"nostyle search-button\"]")
	WebElement searchBtn_loc;
	
	@FindBy(xpath="//button[@aria-label='Show more Language options']")
	WebElement showMore_loc;
	
	
	@FindBy(xpath="//div[@id='checkbox-group']//div[@class='cds-checkboxAndRadio-labelText']")
	List<WebElement> allLang_loc;
	
	@FindBy(xpath="//div[@aria-label='Select Language options']/div/label/div/span")
	List<WebElement> AllLanguages;
	
	@FindBy(xpath="(//button[@class='cds-149 cds-button-disableElevation cds-button-primary css-1wio7h1'])[1]")
	WebElement apply_loc;
	
	@FindBy(xpath="//div[@data-testid=\"search-filter-group-Level\"]//div[@class=\"cds-checkboxAndRadio-labelText\"]")
	List<WebElement> allLevel_loc;
	
	@FindBy(xpath="//div[@class=\"rc-SearchResultsHeader\"]//span")
	WebElement totalCount_loc;
	
	@FindBy(xpath="//span[normalize-space()='Clear All']")
	WebElement clearAllBtn_loc;
	
	@FindBy(xpath="//span[normalize-space()='Close']//span[@class='cds-button-endIcon']")
	WebElement closeBtn_loc;
	
	
	
	// Actions
	JavaScriptManager jsm = new JavaScriptManager();
	
	JavascriptExecutor js = (JavascriptExecutor)driver;
	public void basicOperations()
	{
		// Click on Logo
		jsm.highlightBorder(driver, logoClick_loc);
		logoClick_loc.click();
		
		// Clear existing text in input Box
		jsm.highlightBorder(driver, inputBox_loc);
		inputBox_loc.click();
		js.executeScript("arguments[0].value='';",inputBox_loc);
		
		
		// Pass Language Learning in input Box
		inputBox_loc.sendKeys("Language Learning");
		
		// Click on Search Button
		js.executeScript("arguments[0].click()",searchBtn_loc);
		
	}
	

	
	public String storeOnlyCharacter(String cLang) {
		String result="";
		for(int i=0;i<cLang.length();i++) {
			if(cLang.charAt(i)>='a'&&cLang.charAt(i)<='z'||cLang.charAt(i)>='A'&&cLang.charAt(i)<='Z') {
				result += cLang.charAt(i);
			}
		}
		return result;
	}
	public Set<String> ExtractFiveLanguage() {
		jsm.highlightBorder(driver, showMore_loc);
		js.executeScript("arguments[0].click()",showMore_loc);
		Set<String> langs = new HashSet<>();
		int i=0;
		for(;i<=5;i++) {
			String value = storeOnlyCharacter(AllLanguages.get(i).getText());
			langs.add(value.trim());
		}
		
		jsm.highlightBorder(driver, closeBtn_loc);
		js.executeScript("arguments[0].click()", closeBtn_loc); //close btn
		return langs;
	}
	
	public void getCourseCount() throws InterruptedException, IOException
	{
	   
		List<String> language = new ArrayList<>(ExtractFiveLanguage());
		int row=0;
		int subRow = 0;

		for(int i=0;i<5;i++)
		{
			Thread.sleep(2000);
			jsm.highlightBorder(driver, showMore_loc);
			js.executeScript("arguments[0].click()",showMore_loc);
			Thread.sleep(2000);
			String store="";
			for(WebElement ele: AllLanguages)
			{
				store = storeOnlyCharacter(ele.getText()).trim();
				if(store.equalsIgnoreCase(language.get(i)))
				{
					ele.click();
					Thread.sleep(3000);
					System.out.println("Language : "+ele.getText());
					excelutility.setCellData("CourseCount", row, 0, ele.getText());
					
					jsm.highlightBorder(driver, apply_loc);
					apply_loc.click();
					Thread.sleep(3000);
					for(WebElement level : allLevel_loc) {
						System.out.println("--------------------------------------------");
						
						js.executeScript("arguments[0].click()", level);  // Check the checkBox
						Thread.sleep(3000);
						System.out.println(level.getText());
						excelutility.setCellData("CourseCount", subRow, 1, level.getText());
						String count = totalCount_loc.getText();
						
						//Convert int-String to only int Part  //eg:  "728 results"  -> 728
						Pattern pattern = Pattern.compile("\\d+");
						Matcher matcher = pattern.matcher(count);
						String langCount = "";
						if(matcher.find())
						{
							langCount = matcher.group();
						}
						
						System.out.println("Total Count : " + langCount);
						excelutility.setCellData("CourseCount", subRow, 2, langCount);
						js.executeScript("arguments[0].click()", level);   // Unchecking the checkbox					
						Thread.sleep(3000);
						row++;
						subRow++;
					}
					System.out.println("********************************************");
					
					jsm.highlightBorder(driver, showMore_loc);
					js.executeScript("arguments[0].click()",showMore_loc);
					Thread.sleep(2000);
					
					jsm.highlightBorder(driver, clearAllBtn_loc);
					js.executeScript("arguments[0].click()",clearAllBtn_loc);
					Thread.sleep(2000);
					
					jsm.highlightBorder(driver, closeBtn_loc);
					js.executeScript("arguments[0].click()", closeBtn_loc);
					row++;
					subRow++;
					break;
				}
					
			}
			
		}
	}
}
