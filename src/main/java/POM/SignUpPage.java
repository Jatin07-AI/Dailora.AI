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

	public WebElement getAcceptAllBTN() {
		return AcceptAllBTN;
	}

	public WebElement getSIGNUPLINK() {
		return SIGNUPLINK;
	}

	public WebElement getNAME() {
		return NAME;
	}

	public WebElement getInvaliNameMsg() {
		return InvaliNameMsg;
	}

	public WebElement getEMAIL() {
		return EMAIL;
	}

	public WebElement getInvalidEmailMsg() {
		return InvalidEmailMsg;
	}

	public WebElement getCOUNTRYCODE() {
		return COUNTRYCODE;
	}

	public WebElement getPhoneNum() {
		return PhoneNum;
	}

	public WebElement getInvalidPhnNumMsg() {
		return InvalidPhnNumMsg;
	}

	public WebElement getPASSWORD() {
		return PASSWORD;
	}

	public WebElement getEYEICON1() {
		return EYEICON1;
	}

	public WebElement getWRNGPASSMSG() {
		return WRNGPASSMSG;
	}

	public WebElement getCNFPASSMSG() {
		return CNFPASSMSG;
	}

	public WebElement getEYEICON2() {
		return EYEICON2;
	}

	public WebElement getWRNGCNFPASSMSG() {
		return WRNGCNFPASSMSG;
	}

	public WebElement getSUBMITBTN() {
		return SUBMITBTN;
	}

	public WebElement getCLOSEBTN() {
		return CLOSEBTN;
	}

	public WebElement getSUBSCRIPTIONBTN() {
		return SUBSCRIPTIONBTN;
	}

	public WebElement getEnterCardNum() {
		return EnterCardNum;
	}

	public WebElement getCARDEXP() {
		return CARDEXP;
	}

	public WebElement getCARDCVC() {
		return CARDCVC;
	}

	public WebElement getBILLINGNAME() {
		return BILLINGNAME;
	}

	public WebElement getSELCOUNTRY() {
		return SELCOUNTRY;
	}

	public WebElement getADDRESS() {
		return ADDRESS;
	}

	public WebElement getADDRESS2() {
		return ADDRESS2;
	}

	public WebElement getPINCODE() {
		return PINCODE;
	}

	public WebElement getCITY() {
		return CITY;
	}

	public WebElement getSTATE() {
		return STATE;
	}

	public WebElement getPaymentSubmitBTN() {
		return PaymentSubmitBTN;
	}

	public WebElement getStartTrialBTN() {
		return StartTrialBTN;
	}

	public WebElement getLOGINEMAIL() {
		return LOGINEMAIL;
	}

	public WebElement getPROCESSINGBTN() {
		return PROCESSINGBTN;
	}
	
	
	
	
}
