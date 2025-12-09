package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsUtil {

	private WebDriver driver;

	public JsUtil(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * get page title using JS
	 */
	public String getTitleByJs() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.title;").toString();
	}

	/**
	 * go to previous page using JS
	 */
	public void goBackByJs() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(-1);");
	}

	/**
	 * go to next page using JS
	 */
	public void goForwardByJs() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(1);");
	}

	/**
	 * refresh page using JS
	 */
	public void refreshBrowserByJs() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0);");
	}

	/**
	 * Enter text in a textbox using element's 'id' attribute
	 * 
	 * @param id
	 * @param text
	 */
	public void sendKeysByJS(String id, String text) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "document.getElementById('" + id + "').value='" + text + "';";
		js.executeScript(script);
	}

	/**
	 * Click on an element using JS
	 * 
	 * @param element
	 */
	public void clickByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * get the inner text of all elements in the page using JS
	 * 
	 * @return
	 */
	public String getPageInnerTextByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.documentElement.innerText;").toString();
	}

	/**
	 * draw a border around the given element using JS
	 * 
	 * @param element
	 */
	public void drawBorderByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	/**
	 * highlight the given element using JS
	 * 
	 * @param element
	 */
	public void flashElementByJS(WebElement element) {
		String bgColor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 5; i++) {
			changeColor("rgb(0,100,0)", element);
			changeColor(bgColor, element);
		}
	}

	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * scroll to the bottom of page using JS
	 */
	public void scrollTopToBottomByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}

	/**
	 * scroll to the top of the page
	 */
	public void scrollBottomToTopByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight,0);");
	}

	/**
	 * scroll down by given height
	 * 
	 * @param height
	 */
	public void scrollDownByJS(String height) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, '" + height + "');");
	}

	/**
	 * scroll to element
	 * 
	 * @param element
	 */
	public void scrollIntoViewByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Wait till page loads (timeout value in millisec) using JS:
	 * 
	 * @param timeOut
	 */
	public void waitForPageLoad(int timeOut) {
		long endTime = System.currentTimeMillis() + timeOut;
		while (System.currentTimeMillis() < endTime) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String pageState = js.executeScript("return document.readyState").toString();
			if (pageState.equals("complete")) {
				System.out.println("page DOM is fully loaded now..");
				break;
			}

		}
	}
}
