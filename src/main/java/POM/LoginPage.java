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
	
	@FindBy(xpath = "//div[@class='relative']")
	private WebElement LOGOUTDROPDOWN;
	
	@FindBy(xpath = "//div[text()='Log out']")
	private WebElement LOGOUTBTN;
	
	@FindBy(id = "zs_fl_close")
	private WebElement CLOSEBTN;
	
	@FindBy(id="undefined-form-item-message")
	private WebElement MSGLOGINFAILED;
	
	@FindBy(xpath = "//p[contains(text(),'Email must be')]")
	private WebElement WRONGEMAIL;
	
	// Simple login method
	// replace your LoginIntoApp(...) with this simple version
	public void LoginIntoApp(String username, String password) throws Throwable{
	    EMAIL.clear();
	    EMAIL.sendKeys(username);
	    PASSWORD.clear();
	    PASSWORD.sendKeys(password);
	    Thread.sleep(2000);
	    wb.visibilityOfElement(driver, CLOSEBTN);
	    CLOSEBTN.click();
	    // click submit
	    SUBMIT.click();
	}
	
	//IF LOGIN FAILED DUE TO INVALID CREDNTIALS
	public String InvalidCredentials() {
		WebElement tostMsg = MSGLOGINFAILED;
		wb.visibilityOfElement(driver, tostMsg);
		String AuthMsg = tostMsg.getText();
		return AuthMsg;
		}

	//IF EMAIL IS INVALID OR IN INVALID FORMAT
	public String WrongMail() {
		WebElement InvalidEmail = WRONGEMAIL;
		wb.visibilityOfElement(driver, InvalidEmail);
		String InvaliMail = InvalidEmail.getText();
		return InvaliMail;
	}
	
	
	// Simple logout method
	public void LogoutFromApp() throws Throwable {
		LOGOUTDROPDOWN.click();
		Thread.sleep(1000);
		LOGOUTBTN.click();
		Thread.sleep(1000);
		
	}
	
	public String getLoginResult(String username) {
		try {
			return "Invalid Email :" + WrongMail();
		} catch (Exception e) {
			
			try {
				return "Invalid Credentials :" + InvalidCredentials();
			} catch (Exception e2) {
				return "Login Successful for user :" + username;
			}
			
		}
		
	}


	
}