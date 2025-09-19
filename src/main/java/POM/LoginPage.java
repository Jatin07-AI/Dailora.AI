package POM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webDriverUtility.WebDriverUtilityProgram;

public class LoginPage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	WebDriverUtilityProgram wb = new WebDriverUtilityProgram();
	
	@FindBy(name = "email")
	private WebElement EMAIL;
		
	@FindBy(name = "password")
	private WebElement PASSWORD;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement SUBMIT;
	
	@FindBy(xpath = "//button[@id='radix-:rv:']")
	private WebElement LOGOUTDROPDOWN;
	
	@FindBy(xpath = "//div[@role='menuitem' and contains(., 'Log out')]")
	private WebElement LOGOUTBTN;
	
	@FindBy(id = "zs_fl_close")
	private WebElement CLOSEBTN;
	
	@FindBy(id="undefined-form-item-message")
	private WebElement MSGLOGINFAILED;
	
	@FindBy(xpath = "//p[contains(text(),'Email must be')]")
	private WebElement WRONGEMAIL;

	@FindBy(xpath = "//span[text()='Agents']")
	private WebElement AGENTS;
	
	public WebElement getEMAIL() {
		return EMAIL;
	}

	public WebElement getPASSWORD() {
		return PASSWORD;
	}

	public WebElement getSUBMIT() {
		return SUBMIT;
	}

	public WebElement getLOGOUTDROPDOWN() {
		return LOGOUTDROPDOWN;
	}

	public WebElement getLOGOUTBTN() {
		return LOGOUTBTN;
	}

	public WebElement getCLOSEBTN() {
		return CLOSEBTN;
	}

	public WebElement getMSGLOGINFAILED() {
		return MSGLOGINFAILED;
	}

	public WebElement getWRONGEMAIL() {
		return WRONGEMAIL;
	}
	
	public void loginToApp(String username,String password) throws Throwable{
		EMAIL.sendKeys(username);
		System.out.println("Emailed Entered");
		PASSWORD.sendKeys(password);
		System.out.println("Password Entered");
		SUBMIT.click();
		System.out.println("Submit button clicked");
		Thread.sleep(20000);
	}

	public void logoutTOApp() throws Throwable {
		// verify by URL
	    String expectedUrl = "https://dev.app.dialora.ai/dashboard";
	    String actualUrl = driver.getCurrentUrl();

	    if (actualUrl.equals(expectedUrl)) {
	        System.out.println("✅ User successfully reached Dashboard page" + actualUrl);
	    } else {
	        System.out.println("❌ Login failed or wrong page loaded. Actual: " + actualUrl);
	    }
		
		Thread.sleep(10000);
		wb.visibilityOfElement(driver, LOGOUTDROPDOWN);
		Thread.sleep(5000);
		LOGOUTDROPDOWN.click();
		System.out.println("Logout Dropdown clicked");
		wb.visibilityOfElement(driver, LOGOUTBTN);
		LOGOUTBTN.click();
		System.out.println("Logout button clicked");
	   	}
}