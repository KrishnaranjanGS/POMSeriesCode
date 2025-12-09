package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.WaitUtil;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private WaitUtil waitUtil;
	
	private By logout = By.linkText("Logout") ;
	private By accountHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By rightPanelElements = By.xpath("//aside[@id='column-right']//a");
	private By searchButton = By.cssSelector("#search button");
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		waitUtil = new WaitUtil(driver);
	}
	
	public String getAccountPageTitle() {
		String title = waitUtil.waitForTitleAndFetch(OpenCartConstants.ACCOUNT_PAGE_TITLE_VALUE, OpenCartConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Account page title: " + title);
		return title;
	}
	
	public String getAccountPageURL() {
		String url = waitUtil.waitForURLAndFetch(OpenCartConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE, OpenCartConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Account page URL: " + url);
		return url;
	}
	
	public boolean isLogoutLinkExist() {
		return waitUtil.waitForElementVisible(logout, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public boolean isSearchExist() {
		return waitUtil.waitForElementVisible(search, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public List<String> getAccountPageHeadersList() {
		List<WebElement> headerList = waitUtil.waitForElementsVisible(accountHeaders, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> headerText = new ArrayList<String>();
			for (WebElement e: headerList) {
				String text = e.getText();
				headerText.add(text);
			}
		return headerText;
	}
	
	public boolean isAccountPageRightPanelPresent() {
		List<WebElement> rightPanelLinks = waitUtil.waitForElementsVisible(rightPanelElements, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> expectedRightPanelElementsText = new ArrayList<String>(Arrays.asList("My Account","Edit Account","Password","Address Book","Wish List","Order History","Downloads","Recurring payments","Reward Points","Returns","Transactions","Newsletter","Logout"));
		List<String> rightPanelElementsText = new ArrayList<>();
			for(WebElement e: rightPanelLinks) {
				rightPanelElementsText.add(e.getText());
			}
		System.out.println("Actual right panel elements: " + rightPanelElementsText);
		return rightPanelElementsText.containsAll(expectedRightPanelElementsText);
	}
	
	public SearchResultPage performSearch(String searchKey) {
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchButton);
			return new SearchResultPage(driver);
		}
		else {
			System.out.println("Search is not available.");
			return null;
		}
	}
	
}
