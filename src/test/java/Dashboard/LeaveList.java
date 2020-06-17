package Dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeaveList {

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

		// click on Leave List
		driver.findElement(By.xpath("//div[@class='quickLaunge']//a//img[@src='/webres_5ebd1457b45137.49759927/orangehrmLeavePlugin/images/MyLeave.png']")).click();

		// select date from 
		driver.findElement(By.xpath("//img[@src='/webres_5ebd1457b45137.49759927/themes/default/images/calendar.png']")).click();
		driver.findElement(By.xpath("//a[text()=1]")).click();

		// select to date
		driver.findElement(By.xpath("//img[@src='/webres_5ebd1457b45137.49759927/themes/default/images/calendar.png']//preceding-sibling::input[@id='calToDate']")).click();
		driver.findElement(By.xpath("//a[text()=17]")).click();

		// show leave status
		// click on all check box
		driver.findElement(By.xpath("//input[@id='leaveList_chkSearchFilter_checkboxgroup_allcheck']")).click();
		driver.findElement(By.xpath("//input[@id='leaveList_txtEmployee_empName']")).sendKeys("Linda Anderson");

		// select Sub Unit from drop down
		Select subUnit = new Select(driver.findElement(By.xpath("//select[@id='leaveList_cmbSubunit']")));
		subUnit.selectByValue("0");

		// click on Include Past Employees
		driver.findElement(By.xpath("//input[@id='leaveList_cmbWithTerminated']")).click();

		// click on search button
		driver.findElement(By.xpath("//input[@id='btnSearch']")).click();
		
		// scroll down to see below  results
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
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
