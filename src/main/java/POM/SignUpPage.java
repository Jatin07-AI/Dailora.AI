package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import webDriverUtility.WebDriverUtilityProgram;

public class SignUpPage {

	WebDriver driver;
	
	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	WebDriverUtilityProgram wb = new WebDriverUtilityProgram();
	@FindBy(xpath = "//button[text()='Accept All']")
	private WebElement AcceptAllBTN;
	
	@FindBy(linkText = "Signup?")
	private WebElement SIGNUPLINK;
	
	@FindBy(xpath = "//input[@placeholder='John Doe']")
	private WebElement NAME;
	
	@FindBy(xpath = "//p[contains(text(),'Name must')]")
	private WebElement InvaliNameMsg;
	
	@FindBy(name = "email")
	private WebElement EMAIL;
	
	@FindBy(xpath = "//p[contains(text(),'Email must')]")
	private WebElement InvalidEmailMsg;
	
	@FindBy(xpath = "//select[contains(@aria-label,'country')]")
	private WebElement COUNTRYCODE;
	
	@FindBy(xpath = "//input[@class='PhoneInputInput']")
	private WebElement PhoneNum;
	
	@FindBy(xpath = "//p[contains(text(),'number must')]")
	private WebElement InvalidPhnNumMsg;
	
	@FindBy(xpath = "//input[@type='password']")
	private WebElement PASSWORD;
	
	@FindBy(xpath = "//input[@name='password']/following-sibling::button")
	private WebElement EYEICON1;
	
	@FindBy(xpath = "//p[contains(text(),'Password must')]")
	private WebElement WRNGPASSMSG;
	
	@FindBy(xpath = "//input[@name='confirmPassword']")
	private WebElement CNFPASSMSG;
	
	@FindBy(xpath = "//input[@name='confirmPassword']/following-sibling::button")
	private WebElement EYEICON2;
	
	@FindBy(xpath = "//p[contains(text(),'as Password')]")
	private WebElement WRNGCNFPASSMSG;
	
	@FindBy(xpath = "//button[text()='Start 3 Days Free Trial']")
	private WebElement SUBMITBTN;
	
	@FindBy(id = "zs_fl_close")
	private WebElement CLOSEBTN;
	
	public void signUp(String randomName,String email, String phoneNum,String ranPass) throws Throwable{
		
		
		SIGNUPLINK.click();
		AcceptAllBTN.click();
		NAME.sendKeys(randomName);
		EMAIL.sendKeys(email);
		wb.select(COUNTRYCODE,"IN");
		PhoneNum.sendKeys(phoneNum);
		PASSWORD.sendKeys(ranPass);
		wb.mouseHoverOnWebElement(driver, EYEICON1);
		wb.mouseClickOnWebElement(driver, EYEICON1);
		CNFPASSMSG.sendKeys(ranPass);
		wb.mouseHoverOnWebElement(driver, EYEICON2);
		wb.mouseClickOnWebElement(driver, EYEICON2);
		wb.visibilityOfElement(driver, SUBMITBTN);
		Thread.sleep(5000);
		SUBMITBTN.click();
	}
	
	
}
