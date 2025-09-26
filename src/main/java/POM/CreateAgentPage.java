package POM;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import util.ScreenshotUtility;
import webDriverUtility.WebDriverUtilityProgram;

public class CreateAgentPage {

	WebDriver driver;

	public CreateAgentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	WebDriverUtilityProgram wb = new WebDriverUtilityProgram();
	ScreenshotUtility sh = new ScreenshotUtility();

	// ------------------ LOGIN ELEMENTS ------------------ //
	@FindBy(name = "email")
	private WebElement emailInput;

	@FindBy(name = "password")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginButton;

	// ------------------ CREATE AGENT ELEMENTS ------------------ //
	@FindBy(xpath = "//button[@data-tour-id='create-agent']")
	private WebElement createAgentButton;

	@FindAll({@FindBy(xpath = "//h3[text()='Dental Booking Assistant Agent']"),
	@FindBy(xpath = "//p[contains(text(),'Helps patients book dental')]"),
	})
	private WebElement selectAgentTemplateButton;

	// ------------------ ASSIGN NUMBER ELEMENTS ------------------ //
	@FindBy(xpath = "//button[contains(text(),'Incoming')]")
	private WebElement incomingCallTab;

	@FindBy(xpath = "//div[text()='Select Contact Number']")
	private WebElement contactNumberDropdown;

	@FindBy(xpath = "//div[@data-value='+97233822722']")
	private WebElement contactNumberOption;

	@FindBy(xpath = "//button[text()='Assign']")
	private WebElement assignButton;

	@FindBy(xpath = "//button[text()='Unlink']")
	private WebElement unlinkButton;

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveAgentButton;

	// ------------------ GET A CALL ELEMENTS ------------------ //
	@FindBy(xpath = "//button[text()='Get a Call']")
	private WebElement getCallButton;

	@FindAll({
	@FindBy(xpath = "//span[text()='Select Phone number']"),
	@FindBy(xpath = "//button[span[text()='Select a phone number']]"),
    })
	private WebElement phoneNumberDropdown;

	@FindBy(xpath = "//span[text()='+972 3 382 2722']")
	private WebElement phoneNumberOption;

	@FindBy(xpath = "//*[name()='svg']//*[name()='path' and @d='m7 15 5 5 5-5']")
	private WebElement countryDropdown;

	@FindBy(xpath = "//div[@data-value='United States']")
	private WebElement countryUSAOption;

	@FindBy(name = "phone")
	private WebElement phoneNumberInput;

	@FindBy(xpath = "//span[text()='Save']")
	private WebElement savePhoneButton;

	// ------------------ AGENT MANAGEMENT ELEMENTS ------------------ //
	@FindBy(xpath = "//span[text()='Agents']")
	private WebElement agentsMenu;

	@FindBy(xpath = "//div[text()='Dental Booking Assistant Agent']")
	private WebElement createdAgentRecord;

	@FindBy(xpath = "//button[text()='Delete']")
	private WebElement deleteAgentButton;
	
	@FindBy(xpath = "//div[@role='alertdialog']")
	private WebElement AlertDeletepopup;

	@FindBy(xpath = "//body//button[contains(.,'Proceed')]")
	private WebElement proceedDeleteButton;

	@FindBy(xpath = "//h1[contains(text(),'Unleash AI Power - ')]")
	private WebElement PosterAfterDeleteAgent;


	// ------------------ CONFIRMATION MESSAGES ------------------ //
	@FindBy(xpath = "//div[text()='Dental Booking Assistant Agent successfully updated.']")
	private WebElement agentUpdatedSuccessMessage;

	// ------------------ DeletePopup Is available or not ------------------ //
		@FindBy(xpath = "//h2[text()='Delete this agent?']")
		private WebElement DeletepopupForProceedBTN;
	
	// ------------------ ACTION METHODS ------------------ //

	public void loginIntoApp(String email, String password) throws InterruptedException {
		Reporter.log("STEP: Enter email and password", true);
		emailInput.sendKeys(email);
		passwordInput.sendKeys(password);
		loginButton.click();
		Reporter.log("RESULT: Login successful with email: " + email, true);
		Thread.sleep(10000);
	}

	public void createAgent() throws InterruptedException {
		// Click Create Agent
		Reporter.log("STEP: Wait and click on Create Agent button", true);
		wb.waitForPageLoad(driver, 50);
		wb.visibilityOfElement(driver, createAgentButton);
		wb.mouseClickOnWebElement(driver, createAgentButton);
		Reporter.log("RESULT: Create Agent button clicked successfully", true);

		// Select Agent Template
		Reporter.log("STEP: Select agent template", true);
		wb.elementxTobeClickable(driver, selectAgentTemplateButton);
		wb.mouseClickOnWebElement(driver, selectAgentTemplateButton);
		Reporter.log("RESULT: Agent template selected successfully", true);

		// Assign Number
		Reporter.log("STEP: Assign phone number to agent", true);
		wb.waitForPageLoad(driver, 30);
		wb.visibilityOfElement(driver, contactNumberDropdown);
		Thread.sleep(2000);
		wb.scrollToElement(driver, contactNumberDropdown);
		Thread.sleep(2000);
		contactNumberDropdown.click();
		Reporter.log("ACTION: Clicked on phone number dropdown", true);
		wb.visibilityOfElement(driver, contactNumberOption);
		contactNumberOption.click();
		Reporter.log("ACTION: Phone number selected", true);
		assignButton.click();
		Reporter.log("RESULT: Phone number assigned successfully", true);
		wb.visibilityOfElement(driver, unlinkButton);
		wb.scrollToElement(driver, saveAgentButton);
		Reporter.log("ACTION: Scrolled to Save button", true);
		driver.navigate().refresh();
		Reporter.log("ACTION: Page refreshed", true);

		wb.visibilityOfElement(driver, saveAgentButton);
		wb.mouseClickOnWebElement(driver, saveAgentButton);
		Reporter.log("RESULT: Agent saved successfully", true);
		

		// Handle dynamic popup if appears
		try {
		    // Wait max 5 seconds for the success message to appear
		    wb.visibilityOfElement(driver, agentUpdatedSuccessMessage);
		    // Wait until it disappears
		    wb.waitForInvisibilityOfElement(driver, agentUpdatedSuccessMessage, 10);
		    Reporter.log("Popup appeared and disappeared successfully", true);
		} catch (Exception e) {
		    // Popup didn't appear → just continue
		    Reporter.log("Popup did not appear, continue execution", true);
		}


		// Get a Call
		driver.navigate().refresh();
		wb.waitForPageLoad(driver, 20);
		Reporter.log("STEP: Configure Get a Call", true);
		wb.visibilityOfElement(driver, getCallButton);
		wb.mouseClickOnWebElement(driver, getCallButton);
		Reporter.log("ACTION: Clicked on Get a Call button", true);
		wb.visibilityOfElement(driver,phoneNumberDropdown);
		wb.mouseClickOnWebElement(driver, phoneNumberDropdown);
		Reporter.log("ACTION: Clicked on Phone Number dropdown", true);
		wb.visibilityOfElement(driver, phoneNumberOption);
		wb.mouseClickOnWebElement(driver, phoneNumberOption);
		Reporter.log("ACTION: Selected phone number from options", true);
		wb.elementxTobeClickable(driver, countryDropdown);
		wb.mouseClickOnWebElement(driver, countryDropdown);
		Reporter.log("ACTION: Clicked on Country dropdown", true);
		wb.visibilityOfElement(driver, countryUSAOption);
		countryUSAOption.click();
		Reporter.log("ACTION: Selected India as country", true);
		wb.visibilityOfElement(driver, phoneNumberInput);
		phoneNumberInput.sendKeys("206 237 5589");
		Reporter.log("ACTION: Entered phone number", true);
		wb.visibilityOfElement(driver, savePhoneButton);
		savePhoneButton.click();
		Thread.sleep(10000);
		Reporter.log("RESULT: Phone number saved successfully", true);
		wb.visibilityOfElement(driver, getCallButton);
		driver.navigate().refresh();
		wb.waitForPageLoad(driver, 20);
		Reporter.log("ACTION: Page refreshed after saving phone number", true);

		// Force click Delete button (JS se)
		try {
		    wb.clickElementByJS(driver, deleteAgentButton);
		    Reporter.log("ACTION: Delete button clicked via JS", true);
		} catch (Exception e) {
		    deleteAgentButton.click();
		    Thread.sleep(40000);
		    Reporter.log("ACTION: Delete button clicked normally as fallback", true);
		}
		
		wb.visibilityOfElement(driver, AlertDeletepopup);
		Reporter.log("RESULT: Alert dialog opened successfully", true);
		
		
		// Now click Proceed button inside dialog
		try {
			wb.visibilityOfElement(driver, proceedDeleteButton);
		    proceedDeleteButton.click();
		    Reporter.log("RESULT: Proceed button clicked normally", true);
		    wb.visibilityOfElement(driver, PosterAfterDeleteAgent);
			Reporter.log("Deleted Agent Successfully");
		} catch (Exception e) {
		    Reporter.log("Proceed button normal click failed, trying JS", true);
		   
		    wb.clickElementByJS(driver, proceedDeleteButton);
		    Reporter.log("RESULT: Proceed button clicked via JS", true);
		    
		}
		
			
			
		
		
	}
}
