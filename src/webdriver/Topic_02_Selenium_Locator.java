package webdriver;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_02_Selenium_Locator {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("email")).sendKeys("email");
	}

	@Test
	public void TC_02_Class() {
		driver.findElement(By.className("")).sendKeys("");
	}

	@Test
	public void TC_03_TagName() {
		Integer inputTagNumber = driver.findElements(By.tagName("input")).size();
		System.out.println("input: " + inputTagNumber);
	}

	@Test
	public void TC_04_Name() {
		driver.findElement(By.name("login[username]")).sendKeys("username");
	}

	@Test
	public void TC_05_LinkText() {
		driver.findElement(By.linkText("ABOUT US")).click();
	}

	@Test
	public void TC_06_Partial_LinkText() {
		driver.findElement(By.partialLinkText("RETURNS")).click();
	}

	@Test
	public void TC_07_CSS() {

	}

	@Test
	public void TC_08_Xpath() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}