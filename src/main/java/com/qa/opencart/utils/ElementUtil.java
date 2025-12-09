package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JsUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JsUtil(driver);
	}

	/**
	 * get page title
	 */
	public String getPageTitle() {
		String pageTitle = driver.getTitle();
		System.out.println("page title: " + pageTitle);
		return pageTitle;
	}

	/**
	 * get the page URL
	 */
	public String getPageUrl() {
		String pageUrl = driver.getCurrentUrl();
		System.out.println("page URL: " + pageUrl);
		return pageUrl;
	}

	/**
	 * This method returns WebElement.
	 */
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
			if(Boolean.parseBoolean(DriverFactory.highlight)) {
				jsUtil.flashElementByJS(element);
			}
	return element;
	}

	/**
	 * enter value in textbox.
	 */
	public void doSendKeys(By locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * Enter text in a textbox using Actions class send keys
	 * 
	 * @param locator
	 * @param value
	 */
	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
	}

	/**
	 * perform click action on an element.
	 * 
	 * @param locator
	 */
	public void doClick(By locator) {
		getElement(locator).click();
	}

	/**
	 * Actions class: click
	 * 
	 * @param locator
	 */
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator)).build().perform();
	}

	/**
	 * return the text of an element. Return type is string.
	 * 
	 * @param locator
	 * @return
	 */
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	/**
	 * checks if the given element is displayed, returns true/ false.
	 * 
	 * @param locator
	 * @return
	 */
	public Boolean doElementDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	/**
	 * returns the give attribute's value of an element. Return type is string.
	 * 
	 * @param locator
	 * @param attrName
	 * @return
	 */
	public String doGetAttribute(By locator, String attrName) {
		return getElement(locator).getDomAttribute(attrName);
//		return getElement(locator).getAttribute(attrName);
	}

	/**
	 * returns a list of all elements of a particular type determined by the
	 * locator. Return type is list of webelement.
	 * 
	 * @param locator
	 * @return
	 */
	public List<WebElement> doGetElements(By locator) {
		return driver.findElements(locator);
	}

	/**
	 * returns a list of elements having the given attribute. Return type is list of
	 * webelement.
	 * 
	 * @param Locator
	 * @param attrName
	 */
	public void doGetEleAttrs(By Locator, String attrName) {
		List<WebElement> eleList = doGetElements(Locator);
		for (WebElement e : eleList) {
			String attrVal = e.getDomAttribute(attrName);
			System.out.println(attrName + "-->" + attrVal);
		}
	}

	/**
	 * returns the count of elements by given locator. Return type is int.
	 * 
	 * @param locator
	 * @return
	 */
	public int doGetEleListSize(By locator) {
		int eleListCount = doGetElements(locator).size();
		System.out.println("Element --> " + locator + " --> count: " + eleListCount);
		return eleListCount;
	}

	/**
	 * returns a list of text corresponding to elements having given locator. Return
	 * type is list of string.
	 * 
	 * @param locator
	 * @return
	 */
	public List<String> doGetElementsTextList(By locator) {
		List<String> eleTxtList = new ArrayList<String>();
		List<WebElement> eleList = doGetElements(locator);
		for (WebElement e : eleList) { // for each element e in eleList,
			String txt = e.getText(); // get text from element, and store in a variable txt
			eleTxtList.add(txt); // string is stored in array list
		}
		return eleTxtList;
	}

//	*******************Select based drop down utils*********************
	/**
	 * selects a value in the dropdown based on given index (Select Based).
	 * 
	 * @param locator
	 * @param index
	 */
	public void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	/**
	 * selects a value in the dropdown based on given value (Select Based).
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	/**
	 * selects a value in the dropdown based on given visible text (Select Based).
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSelectDropDownByVisibleText(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}

	/**
	 * returns a list of WebElements from a drop down list. Returns a list of
	 * webelements (Select Based).
	 * 
	 * @param locator
	 * @return
	 */
	public List<WebElement> doGetDropDownOptionsList(By locator) {
		Select select = new Select(getElement(locator));
		return select.getOptions();
	}

	/**
	 * returns the count of elements in a drop down list. Returns an integer value.
	 * 
	 * @param locator
	 * @return
	 */
	public int doGetDropDownOptionsCount(By locator) {
		int optionsCount = doGetDropDownOptionsList(locator).size();
		System.out.println("Number of elements in drop down list: " + optionsCount);
		return optionsCount;
	}

	/**
	 * returns the text of each element in a drop down list. return type is a list
	 * of string
	 * 
	 * @param locator
	 * @return
	 */
	public List<String> getDropDownOptionsTextList(By locator) {
		List<WebElement> optionsList = doGetDropDownOptionsList(locator);
		List<String> optionsTextList = new ArrayList();
		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsTextList.add(text);
		}
		return optionsTextList;
	}

	/**
	 * select given element from the drop down list.
	 * 
	 * @param locator
	 * @param expValue
	 */
	public void doSelectDropDownValue(By locator, String expValue) {
		List<WebElement> optionsList = doGetDropDownOptionsList(locator);
		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(expValue)) {
				e.click();
				break;
			}
		}
	}

//	**************************************************************

	/**
	 * get list of results in free text search suggestions and select one
	 * 
	 * @param locator
	 * @param value
	 */
	public void doGetFTSsuggestionList(By locator, String value) {
		List<WebElement> suggestionsList = doGetElements(locator);
		System.out.println("No. of elements in FTS suggestion list: " + suggestionsList.size());
		for (WebElement e : suggestionsList) {
			String text = e.getText();
			if (text.length() > 0) {
				System.out.println(text);
			}
			if (e.getText().toLowerCase().contains(value)) {
				e.click();
				break;
			}
		}
	}


}
