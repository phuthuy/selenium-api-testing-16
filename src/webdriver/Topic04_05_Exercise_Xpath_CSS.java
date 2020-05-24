package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic04_05_Exercise_Xpath_CSS {
	WebDriver driver;
	String myAccount = "//div[@class='footer']//a[text()='My Account']";
	String verifyLoginPage = "//h1[contains(text(),'Login or Create an Account')]";
	String id_tbUsername = "email";
	String id_tbPassword = "pass";
	String id_btnLogin = "send2";
	String msgValidationEmail = "//input[@id='email']/following-sibling::div";
	String msgValidationPassword = "//input[@id='pass']/following-sibling::div";
	String msgInvalidLogin ="//*[text()='Invalid login or password.']";
	//My Account Page
	String titleDasboard = "//div[@class='page-title']/h1";
	String txthello = "//p[@class='hello']/strong";
	String xpath_ContactInfo = "//*[@class='box-head' and contains(.,'Account Information')]/following-sibling::div//div[@class='col-1' and contains(.,'%s')]";
	String btnLogOut ="//div[@id='header-account']//ul//li[last()]";
	String msgSuccess ="//li[@class='success-msg']//span";
	
	
	/**
	 * Access to link http://live.demoguru99.com/
	 * Click "My Account"
	 * */
	@BeforeTest
	public void beforeTest(){
		System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver");
		driver = new ChromeDriver();
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath(myAccount)).click();
		Assert.assertTrue(driver.findElement(By.xpath(verifyLoginPage)).isDisplayed());
		
	}
	
	@Test
	public void Testcase01() {
		driver.findElement(By.id(id_tbUsername)).clear();
		driver.findElement(By.id(id_tbPassword)).clear();
		driver.findElement(By.id(id_btnLogin)).click();
		Assert.assertEquals(driver.findElement(By.xpath(msgValidationEmail)).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath(msgValidationPassword)).getText(), "This is a required field.");
		
	}
	@Test
	public void Testcase02() {
		driver.findElement(By.id(id_tbUsername)).clear();
		driver.findElement(By.id(id_tbUsername)).sendKeys("123412341234@123244.12313");
		driver.findElement(By.id(id_tbPassword)).clear();
		driver.findElement(By.id(id_tbPassword)).sendKeys("123456");
		driver.findElement(By.id(id_btnLogin)).click();
		Assert.assertEquals(driver.findElement(By.xpath(msgValidationEmail)).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
//		Assert.assertEquals(driver.findElement(By.xpath(msgValidationPassword)).getText(), "This is a required field.");
		
	}
	@Test
	public void Testcase03() {
		driver.findElement(By.id(id_tbUsername)).clear();
		driver.findElement(By.id(id_tbUsername)).sendKeys("online123@gmail.com");
		driver.findElement(By.id(id_tbPassword)).clear();
		driver.findElement(By.id(id_tbPassword)).sendKeys("123");
		driver.findElement(By.id(id_btnLogin)).click();
//		Assert.assertEquals(driver.findElement(By.xpath(msgValidationEmail)).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath(msgValidationPassword)).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	@Test
	public void Testcase04() {
		driver.findElement(By.id(id_tbUsername)).clear();
		driver.findElement(By.id(id_tbUsername)).sendKeys("online123@gmail.com");
		driver.findElement(By.id(id_tbPassword)).clear();
		driver.findElement(By.id(id_tbPassword)).sendKeys("123123123");
		driver.findElement(By.id(id_btnLogin)).click();
		Assert.assertTrue(driver.findElement(By.xpath(msgInvalidLogin)).isDisplayed());
	
		
	}
	@Test
	public void Testcase05() {
		driver.findElement(By.id(id_tbUsername)).clear();
		driver.findElement(By.id(id_tbUsername)).sendKeys("automation_13@gmail.com");
		
		driver.findElement(By.id(id_tbPassword)).clear();
		driver.findElement(By.id(id_tbPassword)).sendKeys("123123");
		driver.findElement(By.id(id_btnLogin)).click();
		
		String locatorName = String.format(xpath_ContactInfo, "Automation Testing");
		String locatorEmail = String.format(xpath_ContactInfo, "automation_13@gmail.com");
		Assert.assertEquals(driver.findElement(By.xpath(titleDasboard)).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.xpath(txthello)).getText(), "Hello, Automation Testing!");
		Assert.assertTrue(driver.findElement(By.xpath(locatorName)).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath(locatorEmail)).isDisplayed());
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//a[contains(.,'Account')]")).click();
		driver.findElement(By.xpath(btnLogOut)).click();
		
	}
	@Test
	public void Testcase06() {
		String email = randomEmail();
		//driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(myAccount)).click();
	    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	    driver.findElement(By.id("firstname")).clear();
	    driver.findElement(By.id("firstname")).sendKeys("Automation");
	    driver.findElement(By.id("lastname")).clear();
	    driver.findElement(By.id("lastname")).sendKeys("Testing");
	    driver.findElement(By.id("email_address")).clear();
	    driver.findElement(By.id("email_address")).sendKeys(email);
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("123123");
	    driver.findElement(By.id("confirmation")).clear();
	    driver.findElement(By.id("confirmation")).sendKeys("123123");
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath(msgSuccess)).getText(), "Thank you for registering with Main Website Store.");
		String locatorName = String.format(xpath_ContactInfo, "Automation Testing");
		String locatorEmail = String.format(xpath_ContactInfo, email);
		Assert.assertTrue(driver.findElement(By.xpath(locatorName)).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath(locatorEmail)).isDisplayed());
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//a[contains(.,'Account')]")).click();
		driver.findElement(By.xpath(btnLogOut)).click();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(driver.getTitle(), "Home page");
		
	}
	public String randomEmail() {
		Random rd =  new Random();
		int nub = rd.nextInt(99999) +1;
		String email = "UserAutomation"+nub+"@gmail.com";
		return email;
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}


}
