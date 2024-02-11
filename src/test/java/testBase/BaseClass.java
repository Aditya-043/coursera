package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.testng.annotations.Parameters;


public class BaseClass {
	
	public static WebDriver driver;
	
	public static Logger logger;
	
	public Properties p;
	
	@BeforeTest (groups= {"smoke","regression", "master"})
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException
	{
		
		//Loading properties file
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);	
	
		
	//Loading log4j2 file
	logger=LogManager.getLogger(this.getClass());
	
	//launching browser based on condition
	
	if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
 	{	
	
	DesiredCapabilities capabilities=new DesiredCapabilities();
	
	//os
	if(os.equalsIgnoreCase("windows"))
	{
		capabilities.setPlatform(Platform.WIN11);
	}
	else if(os.equalsIgnoreCase("mac"))
	{
		capabilities.setPlatform(Platform.MAC);
	}
	else
	{
		System.out.println("No matching os..");
		return;
	}
	
	//browser
	switch(br.toLowerCase())
	{
	case "chrome" : capabilities.setBrowserName("chrome"); break;
	case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
	default: System.out.println("No matching browser.."); return;
	}
	
	driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
	
    }
else if(p.getProperty("execution_env").equalsIgnoreCase("local"))
{
	//launching browser based on condition - locally
	switch(br.toLowerCase())
	{
	case "chrome": driver=new ChromeDriver(); break;
	case "edge": driver=new EdgeDriver(); break;
	default: System.out.println("No matching browser..");
				return;
	}
//	switch (br.toLowerCase()) {
//	case "chrome":
//		driver = new ChromeDriver();
//		break;
//	case "edge":
//		driver = new EdgeDriver();
//		break;
//	default:
//		System.out.println("No matching browser..");
//		return;
	}	
	
	
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	driver.get(p.getProperty("appURL"));
	
	}
	
	@AfterTest(groups= {"smoke","regression", "master"})
	public void tearDown() {
		driver.quit();
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

}
