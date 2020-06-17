package Dashboard;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Timesheets {

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

		// click on timesheets image
		driver.findElement(By.xpath("//div[@class='quickLaunge']//a//img[@src='/webres_5ebd1457b45137.49759927/orangehrmTimePlugin/images/MyTimesheet.png']")).click();

		// clear hint text from Employee Name textbox
		driver.findElement(By.xpath("//input[@id='employee']")).clear();

		// type a for hint text
		driver.findElement(By.xpath("//input[@id='employee']")).sendKeys("a");
		
		// Implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// select even text from hint list
		driver.findElement(By.xpath("//div[@class='ac_results']//child::li[contains(@class,'ac_even')]")).click();

		// click on view button
		driver.findElement(By.xpath("//input[@id='btnView']")).click();

		// No Timesheets Found   
		String s = "No Timesheets Found";
		if(s.equalsIgnoreCase("No Timesheets Found")) {
			// click on Add Timesheet
			driver.findElement(By.xpath("//input[@id='btnAddTimesheet']")).click();

			// Select a Day to Create Timesheet
			driver.findElement(By.xpath("//img[@src='/webres_5ebd1457b45137.49759927/themes/default/images/calendar.png']//preceding-sibling::input[@id='time_date']")).click();

			// select date 18
			driver.findElement(By.xpath("//a[text()='18']")).click();
		}
		else {
			System.out.println("There are Employees records");
		}
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
