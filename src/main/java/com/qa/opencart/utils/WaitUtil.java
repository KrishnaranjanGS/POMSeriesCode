package com.qa.opencart.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

	WebDriver driver;
	
	public WaitUtil(WebDriver driver) {
		this.driver = driver;
	}
	
/**
 * Checks that an element is present on the DOM of a page and returns the element. 
 * If element is present in DOM but not seen in UI, this method won't work.
 * @param locator: By locator
 * @param duration: timeout duration in seconds
 * @return WebElement: WebElement from the given By locator
 */
	public WebElement waitForElementPresent(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
/**
 * Checks that an element is present on the DOM of a page and visible in the UI.
 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
 * @param locator: By locator
 * @param duration: timeout duration in seconds
 * @return WebElement: WebElement from the given By locator
 */
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
/**
 * Waits for JS alert in the page until given timeout
 * @param timeOut: timeout duration in seconds
 * @return Alert
 */
	public Alert waitForAlertPresent(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

/**
 * return the text displayed in the JS alert
 * @param timeOut: timeout duration in seconds
 * @return String: text in the alert
 */
	public String getAlertText(int timeOut) {
		return waitForAlertPresent(timeOut).getText();
	}
	
/**
 * accept the JS alert	
 * @param timeOut: timeout duration in seconds
 */
	public void acceptAlert(int timeOut) {
		waitForAlertPresent(timeOut).accept();
	}

/**
 * dismiss the JS alert	
 * @param timeOut: timeout duration in seconds
 */
	public void dismissAlert(int timeOut) {
		waitForAlertPresent(timeOut).dismiss();
	}

/**
 * enter given value in the text box of the JS alert	
 * @param timeOut: timeout duration in seconds
 */
	public void alertSendKeys(String value, int timeOut) {
		waitForAlertPresent(timeOut).sendKeys(value);
	}
	
/**
 * wait for the title of the page until given string is present in title, and return the entire value of title 	
 * @param text: fractional value of title
 * @param timeOut: timeout duration in seconds
 * @return String: title of the page
 */
	public String waitForTitleAndFetch(String titleFractionValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.titleContains(titleFractionValue));
		return driver.getTitle();
	}
	
/**
 * wait for the title of page until given value IS the title, and return the value of the title	
 * @param titleValue: entire value of title
 * @param timeOut: timeout duration in seconds
 * @return String: title of the page
 */
	public String waitForTitleIsAndFetch(String titleValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();
	}
	
/**
 * wait for the URL until given string is present in the URL, and return the URL 
 * @param urlFractionValue: fractional value of URL
 * @param timeOut: timeout duration in seconds
 * @return String: current URL
 */
	public String waitForURLAndFetch(String urlFractionValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.urlContains(urlFractionValue));
		return driver.getCurrentUrl();
	}
	
/**
 * wait for the URL until given string is present in the URL, and return true or false 
 * @param urlFractionValue: fractional value of URL
 * @param timeOut: timeout duration in seconds
 * @return Boolean: true or false
 */
	public Boolean waitForURL(String urlFractionValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlContains(urlFractionValue));
	}
	
/**
 * wait for the URL until given value IS the URL, and return current URL	
 * @param urlValue: value of URL
 * @param timeOut: timeout duration in seconds
 * @return String: current URL
 */
	public String waitForURLIsAndFetch(String urlValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.urlToBe(urlValue));
		return driver.getCurrentUrl();
	}
	
/**
 * wait for the list of webElements to be visible on page	
 * @param locator: By locator
 * @param timeOut: timeout duration in seconds
 * @return List<WebElement>: list of WebElements from the by given locator
 */
	public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
/**
 * wait for the list of webElements until there is at least one element present on a web page.	
 * @param locator: By locator
 * @param timeOut: timeout duration in seconds
 * @return List<WebElement>: list of WebElements from the by given locator
 */
	public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
/**
 * wait for the frame to load and switch to it
 * @param idOrName: attribute value of id or name of the frame 
 * @param timeOut: timeout duration in seconds
 */
	public void waitForFrameAndSwitchToItByIdOrName(String idOrName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}
	
/**
 * wait for the frame to load and switch to it
 * @param frameIndex: index value of the frame 
 * @param timeOut: timeout duration in seconds
 */
	public void waitForFrameAndSwitchToItByIndex(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}
	
/**
 * wait for the frame to load and switch to it
 * @param frameElement: WebElement of the frame
 * @param timeOut: timeout duration in seconds
 */
	public void waitForFrameAndSwitchToItByFrameElement(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

/**
 * wait for the frame to load and switch to it
 * @param frameLocator: By locator of the frame
 * @param timeOut: timeout duration in seconds
 */
	public void waitForFrameAndSwitchToItByFrameLocator(By frameLocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
/**
 * wait for the element to load and click on it	
 * @param Locator: By locator of the clickable element
 * @param timeOut: timeout duration in seconds                                          
 */
	public void waitForElementAndClick(By Locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(Locator)).click();
	}
	
/**
 * wait for the element to be visible and enabled and return the element
 * @param Locator: By locator of the clickable element
 * @param timeOut: timeout duration in seconds       
 * @return WebElement: WebElement from the given By locator
 */	
	public WebElement waitForElementToBeClickable(By Locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.elementToBeClickable(Locator));
	}

/**
 * wait for the element to be visible and enabled and move to it using actions class click
 * @param locator: By locator of the element
 * @param timeOut: timeout duration in seconds
 */
	public void waitForElementAndMoveToItWithActions(By locator, int timeOut) {
		WebElement element = waitForElementToBeClickable(locator, timeOut);
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
	}
/**
 * wait for the element to be visible and enabled and click on it using actions class click
 * @param locator: By locator of the element
 * @param timeOut: timeout duration in seconds
 */
	public void waitForElementAndClickWithActions(By locator, int timeOut) {
		WebElement element = waitForElementToBeClickable(locator, timeOut);
		Actions act = new Actions(driver);
		act.click(element).build().perform();		
	}
	
}
