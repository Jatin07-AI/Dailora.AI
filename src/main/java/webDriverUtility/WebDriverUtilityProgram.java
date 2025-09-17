package webDriverUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtilityProgram {

	public void implicitlyWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	// Wait for element located by a locator (CSS or XPath) to become invisible
	public void waitForInvisibilityOfElementByXPath(WebDriver driver, String xpath, int timeoutInSeconds) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
	}
	
	public void visibilityOfElement(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void elementTobeClickable(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver,String nameorid) {
		driver.switchTo().frame(nameorid);
	}
	
	public void switchToFrame(WebDriver driver,WebElement FrameElement) {
		driver.switchTo().frame(FrameElement);
	}
	
	public void select(WebElement element,int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	
	public void select(WebElement element,String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	
	public void select(String Text,WebElement element) {
		Select sel = new Select(element);
		sel.selectByContainsVisibleText(Text);
	}
	
	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertAndDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public String switchToAlertAndGetText(WebDriver driver) {
		String text = driver.switchTo().alert().getText();
		return text;
	}
	
	public void switchToAlertAndSendKeys(WebDriver driver,String text) {
		driver.switchTo().alert().sendKeys(text);
	}
	
	public void mouseHoverOnWebElement(WebDriver driver,WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	public void mouseClickOnWebElement(WebDriver driver,WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).click().perform();
	}
	
	public void switchToWindow(WebDriver driver) {
		String parent = driver.getWindowHandle();
		Set<String> allWindowId = driver.getWindowHandles();
		allWindowId.remove(parent);
		for(String id:allWindowId) {
			driver.switchTo().window(id);
		}
			}
	
	public void doubleClickOnWebElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	public void rightClickOnWebElement(WebDriver driver,WebElement element) {
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}
	
	public void passInput(WebDriver driver,WebElement element,String text) {
		Actions act = new Actions(driver);
		act.click(element).sendKeys(text).perform();
	}
	
	public void takeScreenShot(WebDriver driver,String fileName) throws IOException{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File perm = new File("./errorShot/"+fileName+".png");
		FileHandler.copy(temp, perm);
	}
	
	public void toScrollBy(WebDriver driver,int x,int y) {
		JavascriptExecutor jse =(JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(" + x + "," + y + ")");

	}
}
