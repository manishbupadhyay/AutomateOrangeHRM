package Dashboard;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssignLeave {

	WebDriver driver = null;
	
	@BeforeTest
	public void setUpchromeDriver() {
		// set up chrome driver
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		// maximize chrome browser window
		driver.manage().window().maximize();
	}
	
	@Test
	public void dashboard() {
		// open OrangeHRM login page
		driver.get("https://opensource-demo.orangehrmlive.com/");
		// Find username textbox and Enter username
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		// Find password textbox and Enter password
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		// Find login button and click
		driver.findElement(By.xpath("//input[@id='btnLogin']")).sendKeys(Keys.RETURN);
		
		// now dashboard page is showing
		
		//Take screenshot
		File screenshotFile  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		try {
			FileUtils.copyFile(screenshotFile , new File("C:\\SeleniumScreenshots\\screenshot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// click on Assign leave button
		driver.findElement(By.xpath("//div[@class='quickLaunge']//a//img[@src='/webres_5ebd1457b45137.49759927/orangehrmLeavePlugin/images/ApplyLeave.png']")).click();
		
		// find Employee name text box and enter name
		driver.findElement(By.xpath("//input[@id='assignleave_txtEmployee_empName']")).sendKeys("Hannah Flores");
		
		// Select leave type from drop down
		Select leaveType = new Select(driver.findElement(By.id("assignleave_txtLeaveType")));
		leaveType.selectByVisibleText("FMLA US");
		
		// select from date from date picker
		driver.findElement(By.xpath("//img[@src='/webres_5ebd1457b45137.49759927/themes/default/images/calendar.png']//preceding-sibling::input[@id='assignleave_txtFromDate']")).click();
	    driver.findElement(By.xpath("//a[text()='17']")).click();
	    
	    // select to date
	    driver.findElement(By.xpath("//form[@id='frmLeaveApply']/fieldset/ol/li[5]/img")).click();
	    driver.findElement(By.xpath("//a[text()='17']")).click();
	    
		// select Partial Days from drop down
		Select partialDays = new Select(driver.findElement(By.id("assignleave_partialDays")));
		partialDays.selectByVisibleText("All Days");
		
		// find Comment textarea and enter comment
		driver.findElement(By.id("assignleave_txtComment")).sendKeys("This is sick leave");
		
		// find assign button and click
		driver.findElement(By.id("assignBtn")).sendKeys(Keys.RETURN);
	}
	
	@AfterTest
	public void closeBrowser() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// close browser
		driver.close();
		driver.quit();
		System.out.println("Test Completed Successfully");
	}
	
	
}
