package LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRMLoginPage {
	
	WebDriver driver = null;
	
	@BeforeTest
	public void setUpDriver() {
		//set up chrome driver
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@Test
	public void openLoginPage() {
		// open OrangeHRM login page
		driver.get("https://opensource-demo.orangehrmlive.com/");
		// Find username textbox and Enter username
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		// Find password textbox and Enter password
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		// Find login button and click
		driver.findElement(By.xpath("//input[@id='btnLogin']")).sendKeys(Keys.RETURN);
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
