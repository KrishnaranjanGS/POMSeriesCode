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

import io.qameta.allure.Step;

//	We have to adhere to following guidelines:
//	1. Maintain private By locators (so that other they cannot be accessed from other classes directly)
//	2. Create a page constructor to pass driver
//	3. Create page actions/methods

//	Do not use static keyword for any variables/ methods. If static is used, only 1 version of it will be available and it'll cause
//	issues during parallel execution.
//	Page classes should not have any assertions


public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private WaitUtil waitUtil;

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@class='btn btn-primary' and @value='Login']");
	private By forgotPassword = By.linkText("Forgotten Password");
	private By loginPageLogo = By.id("logo");
	private By footerLinks = By.xpath("//footer//ul/li");
	private By rightPanelElements = By.xpath("//aside[@id='column-right']//a");
	private By registerPageLink = By.xpath("//a[text()='Register' and @class='list-group-item']");
	private By logoutLink = By.xpath("//a[text()='Logout' and @class='list-group-item']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		waitUtil = new WaitUtil(driver);
	}

	@Step("Fetching login page title")
	public String getLoginPageTitle() {
		String title = waitUtil.waitForTitleIsAndFetch(OpenCartConstants.LOGIN_PAGE_TITLE_VALUE, OpenCartConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Login page title: " + title);
		return title;
	}

	@Step("Fetching login page URL")
	public String getLoginPageURL() {
		String url = waitUtil.waitForURLAndFetch(OpenCartConstants.LOGIN_PAGE_URL_FRACTION_VALUE, OpenCartConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Login page URL: " + url);
		return url;
	}

	@Step("Checking if login page logo is displayed")
	public boolean isLogoDisplayed() {
		return waitUtil.waitForElementVisible(loginPageLogo, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}

	@Step("Checking if forgot password is displayed in login page")
	public boolean isForgotPasswordPresent() {
		return waitUtil.waitForElementVisible(forgotPassword, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}

	@Step("Checking if right panel is displayed in login page")
	public boolean isLoginPageRightPanelPresent() {
		List<WebElement> rightPanelLinks = waitUtil.waitForElementsVisible(rightPanelElements, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> expectedRightPanelElementsText = new ArrayList<String>(Arrays.asList("Login","Register","Forgotten Password","My Account","Address Book","Wish List","Order History","Downloads","Recurring payments","Reward Points","Returns","Transactions","Newsletter"));
		List<String> rightPanelElementsText = new ArrayList<>();
			for(WebElement e: rightPanelLinks) {
				rightPanelElementsText.add(e.getText());
			}
		System.out.println("Actual right panel elements: " + rightPanelElementsText);
		return rightPanelElementsText.containsAll(expectedRightPanelElementsText);
	}

	@Step("Checking if footer is displayed in login page")
	public boolean isFooterPresent() {
		List<WebElement> actualFooterElements = waitUtil.waitForElementsVisible(footerLinks, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> expectedFooterElements = new ArrayList<String>(Arrays.asList("About Us", "Delivery Information", "Privacy Policy", "Terms & Conditions", "Contact Us", "Returns", "Site Map", "Brands", "Gift Certificates", "Affiliate", "Specials", "My Account", "Order History", "Wish List", "Newsletter"));
		List<String> footerText = new ArrayList<>();
			for(WebElement e: actualFooterElements) {
				footerText.add(e.getText());
			}
			System.out.println("Actual footer elements: " +footerText);
		return footerText.containsAll(expectedFooterElements);
	}

	@Step("Logging in to application with username: {0} and password: {1}")
	public AccountPage doLogin(String userNameValue, String passwordValue) {
		waitUtil.waitForElementVisible(emailId, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(userNameValue);
		eleUtil.doSendKeys(password, passwordValue);
		eleUtil.doClick(loginButton);
		return new AccountPage(driver);
	}
	
	@Step("Navigate to register page")
	public RegisterPage goToRegisterPage() {
		waitUtil.waitForElementToBeClickable(registerPageLink, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new RegisterPage(driver);
	}
	
	@Step("Logging out of the application")
	public void doLogout() {
			try {
				eleUtil.doClick(logoutLink);
				waitUtil.waitForElementVisible(By.xpath("//h1[text()='Account Logout']"), OpenCartConstants.DEFAULT_SHORT_TIME_OUT);
				System.out.println("Successfully Logged out!");
			} catch (Exception e) {
				System.out.println("Logout button is not available.");
			}
		
	}

}
