package BasicCommands;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

 

 public class DemoWebsiteTesting {
	 private static final By By = null;
	 public WebDriver driver;
	 public void testInitialise(String browser) {
			if (browser.equals("chrome")) {
				driver = new ChromeDriver();
			} else if (browser.equals("firefox")) {
				driver = new FirefoxDriver();
			} else if (browser.equals("edge")) {
				driver = new EdgeDriver();
			} else {
				try {
					throw new Exception("Invalid browser");
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			driver.manage().window().maximize();
		    driver.manage().deleteAllCookies();
	 }
	 
	 @BeforeMethod 
	 public void setup()
	 {
		 testInitialise("chrome");
		
	 }
	 @AfterMethod
	 public void tearDown()
	 {
		 driver.close();
	 }
	 @Test
	 public void TC001_verifyDemoQATitle()
	 {
		 driver.get("https://demoqa.com");
		 String actualTitle=driver.getTitle();
		 System.out.println(actualTitle);
		 String expectedTitle="DEMOQA";
		 Assert.assertEquals(actualTitle, expectedTitle,"invalid title found");
		 
	 }
	 @Test
	 public void TC002_verifyDemoQARegistration()
	 {
		 driver.get("https://demoqa.com");
		 WebElement joinNowLink=driver.findElement(By.className("banner-image"));
		 joinNowLink.click();
		 WebElement goRegistrationLink=driver.findElement(By.linkText("Go To Registration "));
		 goRegistrationLink.click();
		 WebElement firstnamefield=driver.findElement(By.name("firstName"));
		 WebElement lastnamefield=driver.findElement(By.name("lastName"));
		 WebElement emailfield=driver.findElement(By.name("email"));
		 WebElement mobileNumberfield=driver.findElement(By.name("mobile"));
		 WebElement citynamefield=driver.findElement(By.id("city"));
		 WebElement messageTextfield=driver.findElement(By.cssSelector("#message"));
		 //WebElement codeTextfield=driver.findElement(By.className("upcoming__registration--captcha"));
		 WebElement sendButton=driver.findElement(By.xpath("//*[@id=\"enroll-form\"]/button"));
		 
		 firstnamefield.sendKeys("test111");
		 lastnamefield.sendKeys("test111");
		 emailfield.sendKeys("test111@gmail.com");
		 mobileNumberfield.sendKeys("1111111111");
		 citynamefield.sendKeys("test111");
		 messageTextfield.sendKeys("test111");
		 //codeTextfield.sendKeys("");
		 sendButton.click();
		 

	 }
	 }
	 
	
	
	


