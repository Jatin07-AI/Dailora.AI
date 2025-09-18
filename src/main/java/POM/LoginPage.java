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
	
	public void loginToApp(String username,String password) {
		EMAIL.sendKeys(username);
		PASSWORD.sendKeys(password);
		SUBMIT.click();
	}

	public void logoutTOApp() throws Throwable {
		wb.visibilityOfElement(driver, AGENTS);
		AGENTS.click();
		System.out.println("Agent Category Clicked");
		 Thread.sleep(2000);
		LOGOUTDROPDOWN.click();
		System.out.println("Logout Dropdown clicked");
		wb.visibilityOfElement(driver, LOGOUTBTN);
		LOGOUTBTN.click();
		System.out.println("Logout button clicked");
	   	}
}