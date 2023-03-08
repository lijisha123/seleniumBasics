package BasicCommands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumSampleProgram {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\junoj\\eclipse-workspace\\SeleniumBasics\\src\\test\\resources\\driverFiles\\chromedriver.exe");
		driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		String currenturl=driver.getCurrentUrl();
		System.out.println(currenturl);
		String pagetitle=driver.getTitle();
		System.out.println(pagetitle);
		String pagesource=driver.getPageSource();
		System.out.println(pagesource);
		WebElement messageField=driver.findElement(By.id("single-input-field"));
		WebElement showButton=driver.findElement(By.id("button-one"));
		WebElement message=driver.findElement(By.id("message-one"));
		WebElement valueAfield=driver.findElement(By.id("value-a"));
		WebElement valueBfield=driver.findElement(By.id("value-b"));
		WebElement getTotalButton=driver.findElement(By.id("button-two"));
		WebElement totalMessage=driver.findElement(By.id("message-two"));
		
		messageField.sendKeys("selenium test");
		showButton.click();
		String mymessage=message.getText();
		System.out.println(mymessage);
		valueAfield.sendKeys("100");
		valueBfield.sendKeys("200");
		getTotalButton.click();
		String totalMessageText=totalMessage.getText();
		System.out.println(totalMessageText);
		//driver.close();
		
		
	}
	}
