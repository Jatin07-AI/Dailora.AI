package POM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
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
	
	@FindBy(xpath = "//span[text()='Dashboard']")
	private WebElement DASHBOARD;
	
	@FindAll({
	    @FindBy(xpath = "//button[@data-testid='user-profile-button']"),
	    @FindBy(xpath = "//button[@id='radix-:rv:']"),
	    @FindBy(xpath = "//button[@data-testid='user-profile-button' and @id='radix-:rv:']"),
	})
	private WebElement LOGOUTDROPDOWN;
	
	@FindAll({
	@FindBy(xpath = "//div[@role='menuitem' and contains(., 'Log out')]"),
	@FindBy(xpath = "//div[text()='Log Out']"),})
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

	public WebElement getDASHBOARD() {
		return DASHBOARD;
	}

	public void setDASHBOARD(WebElement dASHBOARD) {
		DASHBOARD = dASHBOARD;
	}

	public WebElement getAGENTS() {
		return AGENTS;
	}
	
}