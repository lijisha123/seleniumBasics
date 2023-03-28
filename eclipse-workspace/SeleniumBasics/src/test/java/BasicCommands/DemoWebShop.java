package BasicCommands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

public class DemoWebShop {
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
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@BeforeMethod
	@Parameters({ "browser", "base_url" })

	public void setUp(String browserName, String url) {
		testInitialise(browserName);
		driver.get(url);
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File("./screen shots/" + result.getName() + ".png"));
		}
		// driver.close();
		driver.quit();
	}

	@Test
	public void TC001_verifyObsquraTitle()

	{
		driver.get("https://selenium.obsqurazone.com/index.php");
		String actualTitle = driver.getTitle();
		String expectedTitle = "Obsqura Testing";
		Assert.assertEquals(actualTitle, expectedTitle, "invalid title found");

	}

	@Test
	public void TC002_verifyDemoshopLoginpage() {
		// driver.get("https://demowebshop.tricentis.com");
		WebElement loginLink = driver.findElement(By.xpath("//a[text()='Log in']"));
		loginLink.click();
		WebElement emailfield = driver.findElement(By.xpath("//input[@id='Email']"));
		WebElement passwordfield = driver.findElement(By.xpath("//input[@id='Password']"));
		emailfield.sendKeys("test1234@yop.com");
		passwordfield.sendKeys("111111");
		WebElement rememberMecheck = driver.findElement(By.xpath("//input[@id='RememberMe']"));
		rememberMecheck.click();
		WebElement loginButton = driver.findElement(By.xpath("//input[@value='Log in']"));
		loginButton.click();
	}

	@Test
	public void TC003_verifyDemoshopRegistrationPage() throws InterruptedException {
		// driver.get("https://demowebshop.tricentis.com");
		WebElement registerLink = driver.findElement(By.xpath("//a[text()='Register']"));
		registerLink.click();
		List<WebElement> gender = driver.findElements(By.xpath("//input[@name='Gender']"));
		selectGender("F", gender);
		WebElement firstNamefield = driver.findElement(By.xpath("//input[@id='FirstName']"));
		WebElement lastnamefield = driver.findElement(By.xpath("//input[@id='LastName']"));
		WebElement emailField = driver.findElement(By.xpath("//input[@id='Email']"));
		WebElement passwordField = driver.findElement(By.xpath("//input[@id='Password']"));
		WebElement confirmPasswordField = driver.findElement(By.xpath("//input[@id='ConfirmPassword']"));
		firstNamefield.sendKeys("test");
		lastnamefield.sendKeys("123");
		emailField.sendKeys("testtest12345@yop.com");
		passwordField.sendKeys("test12345");
		confirmPasswordField.sendKeys("test12345");
		WebElement registerButton = driver.findElement(By.xpath("//input[@id='register-button']"));
		registerButton.click();
		Thread.sleep(2000);
		WebElement validationmail = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
		String actualvalidationmail = validationmail.getText();
		String expectedValidationmail = "testtest12345@yop.com";
		Assert.assertEquals(expectedValidationmail, actualvalidationmail, "Invalid mail found");
	}

	@Test
	public void Tc004_verifyTitleexcelRead() throws IOException {
		// driver.get("https://demowebshop.tricentis.com");
		String actualTitle = driver.getTitle();
		String excelPath = "\\src\\test\\resources\\TestData.xlsx";
		String sheetName = "Home page";
		String expectedTitle = ExcelUtility.readStringData(excelPath, sheetName, 1, 0);
		Assert.assertEquals(expectedTitle, actualTitle, "Invalid data found");
	}

	public void selectGender(String gen, List<WebElement> gender) {
		for (int i = 0; i < gender.size(); i++) {
			String genderValue = gender.get(i).getAttribute("value");
			if (genderValue.equals(gen)) {
				gender.get(i).click();
			}
		}
	}

	@Test
	public void TC005_verifyRegistrationfromExcelsheet() throws IOException {
		// driver.get("https://demowebshop.tricentis.com");
		WebElement registerLink = driver.findElement(By.xpath("//a[text()='Register']"));
		registerLink.click();
		String actualTitle = driver.getTitle();
		String excelPath = "\\src\\test\\resources\\TestData.xlsx";
		String sheetName = "Register page";
		String expectedTitle = ExcelUtility.readStringData(excelPath, sheetName, 1, 0);
		Assert.assertEquals(expectedTitle, actualTitle, "invalid title found");
		// Assert.assertEquals(ExcelUtility.readStringData(excelPath, sheetName, 1,
		// 0),actualTitle, "invalid title found");
		List<WebElement> gender = driver.findElements(By.xpath("//input[@name='Gender']"));
		selectGender(ExcelUtility.readStringData(excelPath, sheetName, 1, 1), gender);
		WebElement firstNamefield = driver.findElement(By.xpath("//input[@id='FirstName']"));
		WebElement lastnamefield = driver.findElement(By.xpath("//input[@id='LastName']"));
		WebElement emailField = driver.findElement(By.xpath("//input[@id='Email']"));
		WebElement passwordField = driver.findElement(By.xpath("//input[@id='Password']"));
		WebElement confirmPasswordField = driver.findElement(By.xpath("//input[@id='ConfirmPassword']"));
		WebElement registerButton = driver.findElement(By.xpath("//input[@id='register-button']"));
		firstNamefield.sendKeys(ExcelUtility.readStringData(excelPath, sheetName, 1, 2));
		lastnamefield.sendKeys(ExcelUtility.readStringData(excelPath, sheetName, 1, 3));
		emailField.sendKeys(ExcelUtility.readStringData(excelPath, sheetName, 1, 4));
		passwordField.sendKeys(ExcelUtility.readStringData(excelPath, sheetName, 1, 5));
		confirmPasswordField.sendKeys(ExcelUtility.readStringData(excelPath, sheetName, 1, 6));
		registerButton.click();
		WebElement validationmail = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
		String actualvalidationmail = validationmail.getText();
		// String expectedValidationmail = "testtest12345@yop.com";
		Assert.assertEquals(ExcelUtility.readStringData(excelPath, sheetName, 1, 4), actualvalidationmail,
				"Invalid mail found");
	}

	@Test
	public void TC006_verifyDemowebshopFromexcelShettUsingList() throws IOException {
		// driver.get("https://demowebshop.tricentis.com");
		String actualTitle = driver.getTitle();
		List<ArrayList<String>> data = ExcelUtility.excelDataReader("\\src\\test\\resources\\TestData.xlsx",
				"Home page");
		String expectedTitle = data.get(1).get(0);
		Assert.assertEquals(expectedTitle, actualTitle, "invalid title found");
	}

	@Test(dataProvider = "InvalidCredentials")
	public void TC007_verifyLoginwithinvalidDatas(String uname, String password) {
		// driver.get("https://demowebshop.tricentis.com");
		WebElement loginLink = driver.findElement(By.xpath("//a[text()='Log in']"));
		loginLink.click();
		WebElement emailfield = driver.findElement(By.xpath("//input[@id='Email']"));
		WebElement passwordfield = driver.findElement(By.xpath("//input[@id='Password']"));
		emailfield.sendKeys(uname);
		passwordfield.sendKeys(password);
		// WebElement rememberMecheck =
		// driver.findElement(By.xpath("//input[@id='RememberMe']"));
		// rememberMecheck.click();
		WebElement loginButton = driver.findElement(By.xpath("//input[@value='Log in']"));
		loginButton.click();
		WebElement errorMessage = driver.findElement(By.xpath("//div[@class='validation-summary-errors']//span"));
		String actualMessage = errorMessage.getText();
		String expectedMessage = "Login was unsuccessful. Please correct the errors and try again.";
		Assert.assertEquals(expectedMessage, actualMessage, "Invalid message found");
	}

	@DataProvider(name = "InvalidCredentials")
	public Object[][] userCredentials() {
		Object[][] data = { { "test123@test.com", "123456" }, { "test@yop.com", "12345" },
				{ "test123@yop.com", "123456" } };
		return data;

	}

	@Test
	public void TC008_verifyRegistrationUsingrandongenerator() throws InterruptedException {
		//driver.get("https://demowebshop.tricentis.com");
		WebElement registerLink = driver.findElement(By.xpath("//a[text()='Register']"));
		registerLink.click();
		String firstName = RandomUtility.getfName();
		String lastName = RandomUtility.getlName();
		String emailId = RandomUtility.getRandomEmail();
		String password = firstName + "@123";
		List<WebElement> gender = driver.findElements(By.xpath("//input[@name='Gender']"));
		selectGender("F", gender);
		WebElement fName = driver.findElement(By.xpath("//input[@id='FirstName']"));
		WebElement lfield = driver.findElement(By.xpath("//input[@id='LastName']"));
		WebElement email = driver.findElement(By.xpath("//input[@id='Email']"));
		WebElement pword = driver.findElement(By.xpath("//input[@id='Password']"));
		WebElement confirmPassword = driver.findElement(By.xpath("//input[@id='ConfirmPassword']"));
		WebElement registerButton = driver.findElement(By.xpath("//input[@id='register-button']"));
		fName.sendKeys(firstName);
		lfield.sendKeys(lastName);
		email.sendKeys(emailId);
		pword.sendKeys(password);
		confirmPassword.sendKeys(password);
		registerButton.click();
		Thread.sleep(3000);
		WebElement validationmail = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
		String actualmail = validationmail.getText();
		System.out.println(actualmail);
		Assert.assertEquals(actualmail, emailId, "Invalid mail found");
	}

	@Test(dataProvider = "validCredentials")
	public void TC009_verifyLoginwithvalidDatas(String uname, String password) {
		//driver.get("https://demowebshop.tricentis.com");
		WebElement loginLink = driver.findElement(By.xpath("//a[text()='Log in']"));
		loginLink.click();
		WebElement emailfield = driver.findElement(By.xpath("//input[@id='Email']"));
		WebElement passwordfield = driver.findElement(By.xpath("//input[@id='Password']"));
		emailfield.sendKeys(uname);
		passwordfield.sendKeys(password);
		WebElement loginButton = driver.findElement(By.xpath("//input[@value='Log in']"));
		loginButton.click();
		WebElement successaccount = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
		String actualaccount = successaccount.getText();
		Assert.assertEquals(actualaccount, uname, "Invalid account found");
	}

	@DataProvider(name = "validCredentials")
	public Object[][] userValidCredentials() {
		Object[][] data = { { "data123@yop.com", "data123" }, { "data456@yop.com", "data456" } };
		return data;
	}

	@Test
	@Parameters({ "uName", "password" })
	public void TC010_verifyLoginwithValidDataUsingParameters(String uname, String password) {
		//driver.get("https://demowebshop.tricentis.com");
		WebElement loginLink = driver.findElement(By.xpath("//a[text()='Log in']"));
		loginLink.click();
		WebElement emailfield = driver.findElement(By.xpath("//input[@id='Email']"));
		WebElement passwordfield = driver.findElement(By.xpath("//input[@id='Password']"));
		emailfield.sendKeys(uname);
		passwordfield.sendKeys(password);
		WebElement loginButton = driver.findElement(By.xpath("//input[@value='Log in']"));
		loginButton.click();
		WebElement successaccount = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
		String actualaccount = successaccount.getText();
		Assert.assertEquals(actualaccount, uname, "Invalid account found");
	}

	@Test
	public void TC011_verifyRegistrationfromExcelSheetAndmailAsRandomgenerator() throws IOException {
		//driver.get("https://demowebshop.tricentis.com");
		WebElement registerLink = driver.findElement(By.xpath("//a[text()='Register']"));
		registerLink.click();
		List<ArrayList<String>> data = ExcelUtility.excelDataReader("\\src\\test\\resources\\TestData.xlsx",
				"Register page");
		String expectedTitle = data.get(1).get(0);
		String actualTitle = driver.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle, "invalid title found");
		List<WebElement> gender = driver.findElements(By.xpath("//input[@name='Gender']"));
		selectGender("F", gender);
		WebElement firstNamefield = driver.findElement(By.xpath("//input[@id='FirstName']"));
		WebElement lastnamefield = driver.findElement(By.xpath("//input[@id='LastName']"));
		WebElement emailField = driver.findElement(By.xpath("//input[@id='Email']"));
		WebElement passwordField = driver.findElement(By.xpath("//input[@id='Password']"));
		WebElement confirmPasswordField = driver.findElement(By.xpath("//input[@id='ConfirmPassword']"));
		WebElement registerButton = driver.findElement(By.xpath("//input[@id='register-button']"));
		String fName = data.get(1).get(2);
		String lName = data.get(1).get(3);
		String email = RandomUtility.getRandomEmail();
		String pword = data.get(1).get(5);
		String confirmPword = data.get(1).get(5);
		firstNamefield.sendKeys(fName);
		lastnamefield.sendKeys(lName);
		emailField.sendKeys(email);
		passwordField.sendKeys(pword);
		confirmPasswordField.sendKeys(confirmPword);
		registerButton.click();
		WebElement validationmail = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
		String actualmail = validationmail.getText();
		System.out.println(actualmail);
		Assert.assertEquals(actualmail, email, "Invalid mail found");
	}
}
