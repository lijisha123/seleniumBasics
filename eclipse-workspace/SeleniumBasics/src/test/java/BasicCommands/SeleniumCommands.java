package BasicCommands;

import java.sql.Driver;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.asynchttpclient.util.StringBuilderPool;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.internal.Systematiser;

import com.google.thirdparty.publicsuffix.PublicSuffixPatterns;

public class SeleniumCommands {
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

	public void setUp() {
		testInitialise("firefox");
	}

	@AfterMethod
	public void tearDown() {
		// driver.close();
		driver.quit();
	}

	@Test
	public void TC_001_verifyObsquraTitle() {
		driver.get("https://selenium.obsqurazone.com/index.php");
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		String expectedTitle = "Obsqura Testing";
		Assert.assertEquals(actualTitle, expectedTitle, "Invalid title found");
	}

	@Test
	public void TC_002_verifySingleInputFieldMessage() {
		driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
		WebElement messageField = driver.findElement(By.id("single-input-field"));
		WebElement showButton = driver.findElement(By.id("button-one"));
		WebElement message = driver.findElement(By.id("message-one"));
		messageField.sendKeys("selenium test");
		showButton.click();
		String actualmessage = message.getText();
		System.out.println(actualmessage);
		String expectedMessage = "Your Message : selenium test";
		Assert.assertEquals(actualmessage, expectedMessage, "Invalid message found");
	}

	@Test
	public void TC_003_verifyTwoInputFieldMessage() {
		driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
		WebElement valueAfield = driver.findElement(By.id("value-a"));
		WebElement valueBfield = driver.findElement(By.id("value-b"));
		WebElement getTotalButton = driver.findElement(By.id("button-two"));
		WebElement totalMessage = driver.findElement(By.id("message-two"));
		valueAfield.sendKeys("100");
		valueBfield.sendKeys("200");
		getTotalButton.click();
		String actualtotalMessageText = totalMessage.getText();
		String expectedtotalMessageText = "Total A + B : 300";
		Assert.assertEquals(actualtotalMessageText, expectedtotalMessageText, "Invalid total meaasge text");
		System.out.println(actualtotalMessageText);
	}

	@Test

	public void TC_004_verifyLocators() throws InterruptedException {
		driver.get("https://demowebshop.tricentis.com/register");
		WebElement firstNamefield = driver.findElement(By.name("FirstName"));
		WebElement lastNamefield = driver.findElement(By.name("LastName"));
		WebElement emailField = driver.findElement(By.id("Email"));
		WebElement passwordField = driver.findElement(By.id("Password"));
		WebElement ConfirmpasswordField = driver.findElement(By.id("ConfirmPassword"));

		firstNamefield.sendKeys("test666");
		lastNamefield.sendKeys("test666");
		emailField.sendKeys("test666@yop.com");
		passwordField.sendKeys("test666");
		ConfirmpasswordField.sendKeys("test666");

		WebElement registerButton = driver.findElement(By.name("register-button"));
		registerButton.click();
		Thread.sleep(3000);
		WebElement getresulttext = driver.findElement(By.className("result"));
		String actualtext = getresulttext.getText();
		System.out.println(actualtext);
		String expectedtext = "Your registration completed";
		Assert.assertEquals(expectedtext, actualtext, "invalid text found");
	}

	@Test
	public void TC_005_verifyDemoShopLoginPage() {
		driver.get("https://demowebshop.tricentis.com");
		WebElement loginlink = driver.findElement(By.className("ico-login"));
		loginlink.click();
		WebElement emailField1 = driver.findElement(By.name("Email"));
		WebElement passwordField1 = driver.findElement(By.name("Password"));
		emailField1.sendKeys("test1234@yop.com");
		passwordField1.sendKeys("111111");
		// WebElement
		// loginButton=driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input"));
		WebElement loginButton = driver.findElement(By.cssSelector("body > div.master-wrapper-page > div.master-wrapper-content > div.master-wrapper-main > div.center-2 > div > div.page-body > div.customer-blocks > div.returning-wrapper > div.form-fields > form > div.buttons > input"));
		loginButton.click();

	}

	@Test
	public void TC_006_verifyNewToursRegistration() throws InterruptedException {
		driver.get("https://demo.guru99.com/test/newtours");
		// WebElement registerLink=driver.findElement(By.linkText("REGISTER"));
		WebElement registerLink = driver.findElement(By.partialLinkText("REGIST"));
		registerLink.click();
		WebElement firstNamefield = driver.findElement(By.name("firstName"));
		WebElement lastNamefield = driver.findElement(By.name("lastName"));
		WebElement phoneNumberfield = driver.findElement(By.name("phone"));
		WebElement emailfield = driver.findElement(By.name("userName"));

		WebElement addressfield = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[7]/td[2]/input"));
		WebElement cityNamefield = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[8]/td[2]/input"));
		WebElement statenamefield = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[9]/td[2]/input"));
		WebElement postalcode = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[10]/td[2]/input"));

		WebElement userNamefield = driver.findElement(By.cssSelector("#email"));
		WebElement passwordfield = driver.findElement(By.cssSelector("body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table > tbody > tr:nth-child(14) > td:nth-child(2) > input[type=password]"));
		WebElement confirmPasswordfield = driver.findElement(By.cssSelector("body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table > tbody > tr:nth-child(15) > td:nth-child(2) > input[type=password]"));

		WebElement submitButton = driver.findElement(By.name("submit"));

		firstNamefield.sendKeys("test111");
		lastNamefield.sendKeys("test111");
		phoneNumberfield.sendKeys("1111111111");
		emailfield.sendKeys("test111@gmail.com");

		addressfield.sendKeys("test111");
		cityNamefield.sendKeys("test111");
		statenamefield.sendKeys("test111");
		postalcode.sendKeys("111111");

		userNamefield.sendKeys("test111@gmail.com");
		passwordfield.sendKeys("111111");
		confirmPasswordfield.sendKeys("111111");

		submitButton.click();
		Thread.sleep(3000);
		WebElement getTextresult = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[2]/font"));
		String actualText = getTextresult.getText();
		String ExpectedTextresult = "Thank you for registering. You may now sign-in using the user name and password you've just entered.";
		System.out.println("actualText");
		Assert.assertEquals(ExpectedTextresult, actualText, "Invalid text found");
	}

	@Test
	public void TC006_verifyEmptyfieldValidation() {
		driver.get("https://selenium.obsqurazone.com/form-submit.php");
		WebElement submitbutton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
		submitbutton.click();
		WebElement firstnamefieldvalidation = driver.findElement(By.xpath("//input[@id='validationCustom01']//following-sibling::div[1]"));
		WebElement lastnamefieldvalidation = driver.findElement(By.xpath("//input[@id='validationCustom02']//following-sibling::div[1]"));
		WebElement usernamefieldvalidation = driver.findElement(By.xpath("//input[@id='validationCustomUsername']/following-sibling::div[@class='invalid-feedback'][1]"));
		WebElement citynamefieldvalidation = driver.findElement(By.xpath("//input[@id='validationCustom03']//following-sibling::div[1]"));
		WebElement statenamefieldvalidation = driver.findElement(By.xpath("//input[@id='validationCustom04']//following-sibling::div[1]"));
		WebElement zipcodefieldvalidation = driver.findElement(By.xpath("//input[@id='validationCustom05']//following-sibling::div[1]"));
		WebElement checkBoxvalidation = driver.findElement(By.xpath("//input[@class='form-check-input']/following-sibling::div[1]"));

		String actualFirstnameText = firstnamefieldvalidation.getText();
		String actuallastnameText = lastnamefieldvalidation.getText();
		String actualusernameText = usernamefieldvalidation.getText();
		String actualcitynameText = citynamefieldvalidation.getText();
		String actualstatenameText = statenamefieldvalidation.getText();
		String actualzipcodeText = zipcodefieldvalidation.getText();
		String actualcheckboxText = checkBoxvalidation.getText();

		String ExpectedFirstnameText = "Please enter First name.";
		String ExpectedastnameText = "Please enter Last name.";
		String ExpectedusernameText = "Please choose a username.";
		String ExpectedcitynameText = "Please provide a valid city.";
		String ExpectedstatenameText = "Please provide a valid state.";
		String ExpectedzipcodeText = "Please provide a valid zip.";
		String ExpectedcheckboxText = "You must agree before submitting.";

		System.out.println("actualFirstnameText");
		Assert.assertEquals(ExpectedFirstnameText, actualFirstnameText, "Invalid text found");
		System.out.println("actuallastnameText");
		Assert.assertEquals(ExpectedastnameText, actuallastnameText, "Invalid text found");
		System.out.println("actualusernameText");
		Assert.assertEquals(ExpectedusernameText, actualusernameText, "Invalid text found");
		System.out.println("actualcitynameText");
		Assert.assertEquals(ExpectedcitynameText, actualcitynameText, "Invalid text found");
		System.out.println("actualstatenameText");
		Assert.assertEquals(ExpectedstatenameText, actualstatenameText, "Invalid text found");
		System.out.println("actualzipcodeText");
		Assert.assertEquals(ExpectedzipcodeText, actualzipcodeText, "Invalid text found");
		System.out.println("actualcheckboxText");
		Assert.assertEquals(ExpectedcheckboxText, actualcheckboxText, "Invalid text found");
	}

	@Test

	public void TC_007_verifyEmptystateandZipcode() {

		driver.get("https://selenium.obsqurazone.com/form-submit.php");
		WebElement firstnamefield = driver.findElement(By.xpath("//input[@id='validationCustom01']"));
		WebElement lastnamefield = driver.findElement(By.xpath("//input[@id='validationCustom02']"));
		WebElement usernamefield = driver.findElement(By.xpath("//input[@id='validationCustomUsername'][1]"));
		WebElement citynamefield = driver.findElement(By.xpath("//input[@id='validationCustom03']"));
		WebElement statenamefield = driver.findElement(By.xpath("//input[@id='validationCustom04']"));
		WebElement zipcodefield = driver.findElement(By.xpath("//input[@id='validationCustom05']"));
		WebElement checkBox = driver.findElement(By.xpath("//input[@class='form-check-input']"));

		firstnamefield.sendKeys("test");
		lastnamefield.sendKeys("new");
		usernamefield.sendKeys("testnew@gmail.com");
		citynamefield.sendKeys("test123");
		checkBox.click();
		WebElement submitbutton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
		submitbutton.click();

		WebElement statenamefieldvaliadation = driver.findElement(By.xpath("//input[@id='validationCustom04']/following-sibling::div[@class='invalid-feedback']"));
		WebElement zipcodefieldvaliadation = driver.findElement(By.xpath("//input[@id='validationCustom05']/following-sibling::div[@class='invalid-feedback']"));

		String actualStatenamemessage = statenamefieldvaliadation.getText();
		String actualZipmessage = zipcodefieldvaliadation.getText();

		String expectedStatenamemessage = "Please provide a valid state.";
		String expectedZipcodemessage = "Please provide a valid zip.";

		Assert.assertEquals(expectedStatenamemessage, actualStatenamemessage, "invalid state name found");
		Assert.assertEquals(expectedZipcodemessage, actualZipmessage, "invalid zip code found");
	}

	@Test
	public void TC008_verifyObsqueraTestingForm() {
		driver.get("https://selenium.obsqurazone.com/form-submit.php");
		WebElement firstnamefield = driver.findElement(By.xpath("//input[@id='validationCustom01']"));
		WebElement lastnamefield = driver.findElement(By.xpath("//input[@id='validationCustom02']"));
		WebElement usernamefield = driver.findElement(By.xpath("//input[@id='validationCustomUsername'][1]"));
		WebElement citynamefield = driver.findElement(By.xpath("//input[@id='validationCustom03']"));
		WebElement statenamefield = driver.findElement(By.xpath("//input[@id='validationCustom04']"));
		WebElement zipcodefield = driver.findElement(By.xpath("//input[@id='validationCustom05']"));
		WebElement checkBox = driver.findElement(By.xpath("//input[@class='form-check-input']"));

		firstnamefield.sendKeys("test");
		lastnamefield.sendKeys("new");
		usernamefield.sendKeys("testnew@gmail.com");
		citynamefield.sendKeys("test123");
		statenamefield.sendKeys("testnewnew");
		zipcodefield.sendKeys("123456");
		checkBox.click();
		WebElement submitbutton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
		submitbutton.click();

		WebElement successMessageText = driver.findElement(By.xpath("//div[@id='message-one']"));
		String actualMessageText = successMessageText.getText();
		String expectedmessageText = "Form has been submitted successfully!";

		Assert.assertEquals(expectedmessageText, actualMessageText, "invalid message found");

	}

	@Test
	public void TC009_verifyNewlwtterSubscriptionUsingCSSSelector() {
		driver.get("https://demowebshop.tricentis.com");
		WebElement emailfieldElement = driver.findElement(By.cssSelector("input#newsletter-email"));
		WebElement subscribeButton = driver.findElement(By.cssSelector("input#newsletter-subscribe-button"));

		emailfieldElement.sendKeys("test123@ghj.com");
		subscribeButton.click();
	}

	@Test
	public void TC010_verifyQuitandclose() {
		driver.get("https://demo.guru99.com/popup.php");
		WebElement clickLink = driver.findElement(By.xpath("//a[text()='Click Here']"));
		clickLink.click();
	}

	@Test
	public void TC011_VerifyInstantdemoRequestform() throws InterruptedException {
		driver.get("https://phptravels.com/demo/");
		WebElement firstNamefield = driver.findElement(By.cssSelector("input.first_name.input.mb1"));
		WebElement lastNamefield = driver.findElement(By.cssSelector("input.last_name.input.mb1"));
		WebElement businessNamefield = driver.findElement(By.cssSelector("input.business_name.input.mb1"));
		WebElement emailField = driver.findElement(By.cssSelector("input.email.input.mb1"));
		WebElement submitButton = driver.findElement(By.cssSelector("button#demo"));
		WebElement verificationNumberone = driver.findElement(By.cssSelector("span#numb1"));
		WebElement verificationNumbertwo = driver.findElement(By.cssSelector("span#numb2"));
		WebElement resultField = driver.findElement(By.cssSelector("input#number"));

		String actualVerificationnumberOne = verificationNumberone.getText();
		String actualVerificationnumberTwo = verificationNumbertwo.getText();
		int numberOne = Integer.parseInt(actualVerificationnumberOne);
		int numberTwo = Integer.parseInt(actualVerificationnumberTwo);
		int total = numberOne + numberTwo;
		String sum = String.valueOf(total);

		firstNamefield.sendKeys("test");
		lastNamefield.sendKeys("one");
		businessNamefield.sendKeys("abcd");
		emailField.sendKeys("testone@abcd.com");
		resultField.sendKeys(sum);
		Thread.sleep(3000);
		submitButton.click();

		WebElement completedBox = driver.findElement(By.cssSelector("circle#colored"));
		completedBox.isDisplayed();
	}

	@Test
	public void TC012_verifyNavigateto() {
		driver.navigate().to("https://demowebshop.tricentis.com");
	}

	@Test
	public void TC013_verifyRefresh() {
		driver.get("https://demowebshop.tricentis.com");
		WebElement emailfieldElement = driver.findElement(By.xpath("//input[@id='newsletter-email']"));
		emailfieldElement.sendKeys("abc@hgf.com");
		driver.navigate().refresh();
	}

	@Test
	public void TC014_verifyForwardandBackwardNavigation() throws InterruptedException {
		driver.get("https://demowebshop.tricentis.com");
		WebElement loginLink = driver.findElement(By.xpath("//a[text()='Log in']"));
		loginLink.click();
		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().forward();
	}

	@Test
	public void TC015_verifyWebelementCommands() throws InterruptedException {
		driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
		WebElement subjectField = driver.findElement(By.xpath("//input[@id='subject']"));
		WebElement descriptionField = driver.findElement(By.xpath("//textarea[@id='description']"));
		WebElement submitButton = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));

		subjectField.sendKeys("selenium");
		descriptionField.sendKeys("Automation testing");
		subjectField.clear();
		String classattributevalues = subjectField.getAttribute("class");
		System.out.println("text--------" + classattributevalues);
		String tagNamevalue = subjectField.getTagName();
		System.out.println("text1--------" + tagNamevalue);
		subjectField.sendKeys("selenium testing");
		submitButton.click();
		Thread.sleep(5000);
		WebElement validationMessage = driver.findElement(By.xpath("//div[@id='message-one']"));
		String actualValidationMessage = validationMessage.getText();
		String expectedValidationMessage = "Form has been submitted successfully!";
		Assert.assertEquals(expectedValidationMessage, actualValidationMessage, "Invalid message found");
	}

	@Test
	public void TC016_verifyIsdisplayed() {
		driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
		WebElement subjectField = driver.findElement(By.xpath("//input[@id='subject']"));
		subjectField.sendKeys("selenium testing");
		boolean status = subjectField.isDisplayed();
		System.out.println(status);
		Assert.assertTrue(status, "subject field is not displayed");
	}

	@Test
	public void TC017_verifyIsselected() {
		driver.get("https://selenium.obsqurazone.com/check-box-demo.php");
		WebElement singleDemocheckBox = driver.findElement(By.xpath("//input[@id='gridCheck']"));
		boolean statusBeforeclick = singleDemocheckBox.isSelected();
		System.out.println("status-----" + statusBeforeclick);
		Assert.assertFalse(statusBeforeclick, "check box is selected");
		singleDemocheckBox.click();
		boolean statusAfterclick = singleDemocheckBox.isSelected();
		System.out.println("status-----" + statusAfterclick);
		Assert.assertTrue(statusAfterclick, "check box is not selected");
	}

	@Test
	public void TC018_verifyIsenabled() {
		driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
		WebElement submitButton = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
		boolean status = submitButton.isEnabled();
		System.out.println("status-----" + status);
		Assert.assertTrue(status, "submit button is not enabled");
		Point point = submitButton.getLocation(); // to get location of an element
		System.out.println(point.x + "," + point.y);
		Dimension dim = submitButton.getSize();
		System.out.println(dim.height + "," + dim.width);
		String backGroundcolor = submitButton.getCssValue("background-color");
		System.out.println(backGroundcolor);
		WebElement inputElement = driver.findElement(By.tagName("input"));
		System.out.println(inputElement);
		List<WebElement> elements = driver.findElements(By.tagName("input"));
		System.out.println(elements);
		submitButton.submit();
	}

	@Test
	public void TC019_verifyTheMessageDisplayedInNewTab() {
		driver.get("https://demoqa.com/browser-windows");
		WebElement newTabbutton = driver.findElement(By.xpath("//button[@id='tabButton']"));
		boolean newTabbuttonStaus = newTabbutton.isEnabled();
		Assert.assertTrue(newTabbuttonStaus, "button is not enabled");
		newTabbutton.click();
		driver.navigate().to("https://demoqa.com/sample");
		WebElement samplePage = driver.findElement(By.xpath("//h1[@id='sampleHeading']"));// child window
		String actualMessage = samplePage.getText();
		String expectedMessage = "This is a sample page";
		Assert.assertEquals(actualMessage, expectedMessage, "Invalid message found");
	}

	@Test
	public void TC020_verifyTheMessageDisplayedInNewWindow() {
		driver.get("https://demoqa.com/browser-windows");
		String parentWindow = driver.getWindowHandle();
		System.out.println("Parent window ID=" + parentWindow);
		WebElement newWindowbutton = driver.findElement(By.xpath("//button[@id='windowButton']"));
		newWindowbutton.click();
		Set<String> handles = driver.getWindowHandles();
		System.out.println("window handles=" + handles);
		Iterator<String> handleIDs = handles.iterator();
		while (handleIDs.hasNext()) {
			String childWindow = handleIDs.next();
			if (!childWindow.equals(parentWindow)) {
				driver.switchTo().window(childWindow);
				WebElement sampleHeading = driver.findElement(By.xpath("//h1[@id='sampleHeading']"));
				String actalMessage = sampleHeading.getText();
				String expectedMessage = "This is a sample page";
				Assert.assertEquals(actalMessage, expectedMessage, "Invalid message found");
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
	}

	@Test
	public void TC021_verifySimplealert() {
		driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
		WebElement clickMebutton = driver.findElement(By.xpath("//button[@class='btn btn-success']"));
		clickMebutton.click();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println(alertText);
		alert.accept();
	}

	@Test
	public void TC022_verifyConfirmalert() {
		driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
		WebElement clickMebutton = driver.findElement(By.xpath("//button[@class='btn btn-warning']"));
		clickMebutton.click();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println(alertText);
		alert.dismiss();
	}

	@Test
	public void TC023_verifyPromptalert() throws InterruptedException {
		driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
		WebElement clickForpromptButton = driver.findElement(By.xpath("//button[@class='btn btn-danger']"));
		clickForpromptButton.click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println(alertText);
		alert.sendKeys("Testing");
		alert.accept();
	}

	@Test
	public void TC024_verifyTextinAframe() {
		driver.get("https://demoqa.com/frames");
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		 int noOFframes=frames.size();//using index
		 System.out.println(noOFframes);
	    //driver.switchTo().frame(7);
		driver.switchTo().frame("frame1");// using id
		//WebElement frame = driver.findElement(By.id("frame1"));
		//driver.switchTo().frame("frame1");
		WebElement heading = driver.findElement(By.id("sampleHeading"));
		String headingText = heading.getText();
		System.out.println(headingText);
		// driver.switchTo().parentFrame();
		driver.switchTo().defaultContent();
		}
	@Test
	public void TC025_verifyRightclick()
	{
		
	driver.get("https://demo.guru99.com/test/simple_context_menu.html");
	WebElement rightClickbutton=driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"));
	Actions action=new Actions(driver);
	action.contextClick(rightClickbutton).build().perform();
	//action.build().perform();
	}
	@Test
	public void TC026_verifyDoubleclick()
	{
		
	driver.get("https://demo.guru99.com/test/simple_context_menu.html");
	WebElement doubleClickbutton=driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
	Actions action=new Actions(driver);
	action.doubleClick(doubleClickbutton).build().perform();
	//action.build().perform();
	Alert alert=driver.switchTo().alert();
	alert.accept();
	}
	@Test
	public void TC027_verifyMouseover()
	{
		driver.get("https://demoqa.com/menu/");
		WebElement mainItem1=driver.findElement(By.xpath("//a[@href='#']"));
		Actions action=new Actions(driver);
		action.moveToElement(mainItem1).build().perform();
		action.moveByOffset(40,50).build().perform();
		}
	@Test
	public void TC028_verifyDragandDrop()
	{
	driver.get("https://demoqa.com/droppable");
	WebElement dragmeButton=driver.findElement(By.id("draggable"));
	WebElement dropmeButton=driver.findElement(By.id("droppable"));
	Actions action=new Actions(driver);
	action.dragAndDrop(dragmeButton,dropmeButton).build().perform();
}
	@Test
	public void TC029_verifyDragandDropbyOffset()
	{
		driver.get("https://demoqa.com/dragabble");
		WebElement dragmeButton=driver.findElement(By.xpath("//div[@id='dragBox']"));
		Actions action=new Actions(driver);
		action.dragAndDropBy(dragmeButton, 40, 50);
}
	@Test
	public void TC030_verifyDragandDrop()
	{
		driver.get("https://selenium.obsqurazone.com/drag-drop.php\r\n");
		WebElement draggable1field=driver.findElement(By.xpath("//span[text()='Draggable n°1']"));
		WebElement draggable2field=driver.findElement(By.xpath("//span[text()='Draggable n°2']"));
		WebElement draggable3field=driver.findElement(By.xpath("//span[text()='Draggable n°3']"));
		WebElement draggable4field=driver.findElement(By.xpath("//span[text()='Draggable n°4']"));
		WebElement dropfield=driver.findElement(By.xpath("//div[@id='mydropzone']"));
		Actions action=new Actions(driver);
		action.dragAndDrop(draggable1field,dropfield).build().perform();
		action.dragAndDrop(draggable2field,dropfield).build().perform();
		action.dragAndDrop(draggable3field,dropfield).build().perform();
		action.dragAndDrop(draggable4field,dropfield).build().perform();
	}
	
	
}

