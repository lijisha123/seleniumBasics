package MySeleniumPrograms;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.Text;

public class practiceSelenium {
	private static final By By = null;
	public WebDriver driver;

	public void testInitialise(String browser) {
		if (browser.equals("chrome")) {
			ChromeOptions ops = new ChromeOptions();
			ops.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(ops);
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
	}
	{
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
}
	@BeforeMethod
	public void setup()
	{
		testInitialise("chrome");
	}
	@AfterMethod
	public void teardown()
	{
		driver.close();
		//driver.quit();
	}
	@Test
	public void Tc01_verify()
	{
		driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
		WebElement messageField=driver.findElement(By.id("single-input-field"));
		WebElement showMessagebutton=driver.findElement(By.partialLinkText("Show Message"));
		showMessagebutton.click();
		
		
	}
}
   
		

