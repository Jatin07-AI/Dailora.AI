package POM;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	private WebElement SignUpLink;
	
	@FindBy(xpath = "//input[@placeholder='John Doe']")
	private WebElement NameField;
	
	@FindBy(name = "email")
	private WebElement EmailField;
	
	@FindBy(xpath = "//select[contains(@aria-label,'country')]")
	private WebElement CuntryCode;
	
	@FindBy(xpath = "//input[@class='PhoneInputInput']")
	private WebElement PhoneNumField;
	
	@FindBy(xpath = "//input[@type='password']")
	private WebElement PasswordField;
	
	@FindBy(xpath = "//input[@name='password']/following-sibling::button")
	private WebElement EyeIcon1;
	
	@FindBy(xpath = "//input[@name='confirmPassword']")
	private WebElement CnfPasswordField;
	
	@FindBy(xpath = "//input[@name='confirmPassword']/following-sibling::button")
	private WebElement EyeIcon2;
	
	@FindBy(xpath = "//button[text()='Start 3 Days Free Trial']")
	private WebElement SubmitBTN;
	
	@FindAll({
	    @FindBy(xpath = "//button[@type='submit']"),
	    @FindBy(xpath = "//button[@data-test-id='choose-trial-plan']")
	})
	private WebElement SubscriptionBTN;

	@FindAll({
	    @FindBy(id="cardNumber"),
	    @FindBy(name  = "cardNumber"),
	    @FindBy(xpath = "//input[@aria-label='Card number']")
	})
	private WebElement EnterCardNumField;
	
	@FindBy (id = "cardExpiry")
	private WebElement EnterCardExpField;
	
	@FindBy (id = "cardCvc")
	private WebElement EnterCardCVCField;
	
	@FindBy (id= "billingName")
	private WebElement EnterBillingNameField;
	
	@FindBy (id="billingCountry")
	private WebElement SelectCountryDropdown;
	
	@FindBy (id="billingAddressLine1")
	private WebElement AddressField1;
	
	@FindBy (id = "billingAddressLine2")
	private WebElement AddressField2;
	
	@FindBy (id = "billingPostalCode")
	private WebElement PincodeField;
	
	@FindBy (id = "billingLocality")
	private WebElement CityField;
	
	@FindBy (id = "billingAdministrativeArea")
	private WebElement StateField;
	
	@FindBy (id = "submit-button-processing-label")
	private WebElement LoaderAtPaymentPage;
	
	@FindBy (xpath = "//span[contains(@class,'SubmitButton-Text') and normalize-space(text())='Start trial']")
	private WebElement StartTrialBTN;
	
	@FindBy (name = "email")
	private WebElement LoginEmailField;
	
	@FindBy (xpath = "//span[text()=\"Processing...\"]")
	private WebElement ProcessingBTN;

	public void openSignup() {
		wb.visibilityOfElement(driver, SignUpLink);
		SignUpLink.click();
		AcceptAllBTN.click();
	} 
	
	public void fillSignupPage(String name,String email,String phone,String password) {
		NameField.sendKeys(name);
		EmailField.sendKeys(email);
		wb.select(CuntryCode,"IN");
		PhoneNumField.sendKeys(phone);
		PasswordField.sendKeys(password);
		wb.mouseClickOnWebElement(driver, EyeIcon1);
		
		CnfPasswordField.sendKeys(password);
		wb.mouseClickOnWebElement(driver, EyeIcon2);
	}
	
	public void submitSignup() throws InterruptedException {
		wb.visibilityOfElement(driver, SubmitBTN);
		SubmitBTN.click();
		Thread.sleep(5000);
	}
	
	public void clickSubscription() {
		wb.waitForPageLoad(driver, 50);
		wb.elementxTobeClickable(driver, SubscriptionBTN);
		SubscriptionBTN.click();
	}
	
	public void fillPaymentDetails() throws Throwable{
		wb.waitForPageLoad(driver, 80);
		wb.elementxTobeClickable(driver, EnterCardNumField);
		EnterCardNumField.sendKeys("4242424242424242");
        EnterCardExpField.sendKeys("1230");
        EnterCardCVCField.sendKeys("123");
        wb.select(SelectCountryDropdown, "IN");
        EnterBillingNameField.sendKeys("Codiste");
        AddressField1.sendKeys("Sarkhej - Gandhinagar Highway");
        new Actions(driver).sendKeys(Keys.ENTER).perform();
        AddressField2.sendKeys("805-807");
        PincodeField.sendKeys("382470");
        CityField.sendKeys("Ahmedabad");
        wb.select(StateField, "GJ");
	}
	
	 public void startTrial() {
	        wb.mouseClickOnWebElement(driver, StartTrialBTN);
	        
	        // Wait until StartTrial button enabled ho jaye
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	        wait.until(driver -> StartTrialBTN.isEnabled());
	    }

	    public String getAutoPopulatedEmail(String uniqueEmail) {
	    		wb.waitForPageLoad(driver,60);
	    		wb.elementxTobeClickable(driver, LoginEmailField);
	    		
	    		// Pehle field ka value le lo
	    	    String emailId = LoginEmailField.getAttribute("value");
	    	    

	    	    if (emailId == null || emailId.trim().isEmpty()) {
	    	        // Agar auto-populate nahi hua → apna email dal do
	    	        LoginEmailField.clear();
	    	        LoginEmailField.sendKeys(uniqueEmail);
	    	        emailId = LoginEmailField.getAttribute("value");
	    	        System.out.println("Email was empty, entered manually: " + emailId);
	    	    } else {
	    	        // Agar auto-populate ho gaya → use hi verify kar
	    	        System.out.println("Email auto-populated: " + emailId);
	    	    }
	    	    
	        return emailId;
	    }
}
