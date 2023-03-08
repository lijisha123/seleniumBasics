package BasicCommands;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumDrivers {

	public static void main(String[] args) {
		WebDriver driver;
		driver=new ChromeDriver();
		driver.get("https://demowebshop.tricentis.com/");
		driver.close();
		
		
		WebDriver driver1;
		driver1=new EdgeDriver();
		driver1.get("https://www.google.com/");
		driver1.close();
			
		WebDriver driver2;
		driver2=new FirefoxDriver();
		driver2.get("https://www.google.com/");
		driver2.close();
		
			
	}

}
