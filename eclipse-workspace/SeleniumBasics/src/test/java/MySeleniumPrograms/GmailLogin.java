package MySeleniumPrograms;

import java.awt.Window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GmailLogin {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.luluhypermarket.com/en-ae/login");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		String currenturl=driver.getCurrentUrl();
		System.out.println(currenturl);
	    String gettitle=driver.getTitle();
		System.out.println(gettitle);
		String pagesource=driver.getPageSource();
		System.out.println(pagesource);
		
		WebElement emailfield=driver.findElement(By.id("emailAddress"));
		WebElement passwordfield=driver.findElement(By.id("j_password"));
		//WebElement rememberlabel=driver.findElement(By.className("exampleCheck1"));
		//WebElement loginbutton=driver.findElement(By.className("submit"));
		
		emailfield.sendKeys("lijishanair23@gmail.com");
		passwordfield.sendKeys("arjunvinu09");
		//rememberlabel.click();
		//loginbutton.click();
		

		
		
		//driver.close();
		

	}

}
