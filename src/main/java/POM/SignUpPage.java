package POM;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement SUBSCRIPTIONBTN;
	
	@FindBy(id = "cardNumber")
	private WebElement EnterCardNum;
	
	@FindBy (id = "cardExpiry")
	private WebElement CARDEXP;
	
	@FindBy (id = "cardCvc")
	private WebElement CARDCVC;
	
	@FindBy (id= "billingName")
	private WebElement BILLINGNAME;
	
	@FindBy (id="billingCountry")
	private WebElement SELCOUNTRY;
	
	@FindBy (id="billingAddressLine1")
	private WebElement ADDRESS;
	
	@FindBy (id = "billingAddressLine2")
	private WebElement ADDRESS2;
	
	@FindBy (id = "billingPostalCode")
	private WebElement PINCODE;
	
	@FindBy (id = "billingLocality")
	private WebElement CITY;
	
	@FindBy (id = "billingAdministrativeArea")
	private WebElement STATE;
	
	@FindBy (id = "submit-button-processing-label")
	private WebElement PaymentSubmitBTN;
	
	@FindBy (xpath = "//span[contains(@class,'SubmitButton-Text') and normalize-space(text())='Start trial']")
	private WebElement StartTrialBTN;
	
	@FindBy (name = "email")
	private WebElement LOGINEMAIL;
	
	@FindBy (xpath = "//span[text()=\"Processing...\"]")
	private WebElement PROCESSINGBTN;
	
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
		
		Thread.sleep(20000);
		
		String expectedUrl = "https://dev.dialora.ai/signup/choose-plan?email=";
	    String actualUrl = driver.getCurrentUrl();

	    if (actualUrl.contains(expectedUrl)) {
	        System.out.println("✅ User successfully reached Purchase Plan page: " + actualUrl);
	    } else {
	        System.out.println("❌ Login failed or wrong page loaded. Actual: " + actualUrl);
	    }
		
		SUBSCRIPTIONBTN.click();
		
		Thread.sleep(20000);
		
		String paymentExpectedURL = "https://checkout.stripe.com/";
	    String paymentActualUrl = driver.getCurrentUrl();

	    if (paymentActualUrl.contains(paymentExpectedURL)) {
	        System.out.println("✅ User successfully reached at Payment Page: " + paymentActualUrl);
	    } else {
	        System.out.println("❌ Login failed or wrong page loaded. Actual: " + actualUrl);
	    }
		
	    EnterCardNum.sendKeys("4242424242424242");
	    CARDEXP.sendKeys("1230");
	    CARDCVC.sendKeys("123");
	    wb.select(SELCOUNTRY, "IN");
	    
	    BILLINGNAME.sendKeys("Codiste");
	    ADDRESS.sendKeys("Sarkhej - Gandhinagar Highway");
	    Actions act = new Actions(driver);
	    ADDRESS.sendKeys(Keys.ENTER);
	    ADDRESS2.sendKeys("805-807");
	    PINCODE.sendKeys("382470");
	    CITY.sendKeys("Ahmedabad");
	    	wb.select(STATE, "GJ");
	    	Thread.sleep(2000);
		wb.mouseClickOnWebElement(driver, StartTrialBTN);
		wb.waitForInvisibilityOfElement(driver, PROCESSINGBTN, 50);
		Thread.sleep(10000);
	   

	    
	    
	    String PaymentScuucessfulExpURL = "https://dev.dialora.ai/signup/success?session_id";
	    String PaymentScuucessfulActualURL = driver.getCurrentUrl();

	    if (PaymentScuucessfulActualURL.contains(PaymentScuucessfulExpURL)) {
	        System.out.println("✅ User Get Payment Successful Page: " + PaymentScuucessfulActualURL);
	    } else {
	        System.out.println("❌ Login failed or wrong page loaded. Actual: " + PaymentScuucessfulActualURL);
	    }
	    
	    Thread.sleep(15000);
	 
	    String createdUSER = LOGINEMAIL.getAttribute("value");
	    if(createdUSER.equals(email)){
	        System.out.println("✅ Email auto-populated correctly: " + createdUSER);
	    } else {
	        System.out.println("❌ Email mismatch! Expected: " + email + " | Found: " + createdUSER);
	    }
	    
	    String LOGINExPUrl = "https://dev.app.dialora.ai/login?";
	    String LOGINActualUrl = driver.getCurrentUrl();

	    if (LOGINActualUrl.contains(LOGINExPUrl)) {
	        System.out.println("✅ User successfully reached at Payment Page: " + LOGINActualUrl);
	    } else {
	        System.out.println("❌ Login failed or wrong page loaded. Actual: " + LOGINActualUrl);
	    }
	   
	   
	}
	
	
}
