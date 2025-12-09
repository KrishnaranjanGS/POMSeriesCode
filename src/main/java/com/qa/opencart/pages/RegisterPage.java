package com.qa.opencart.pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.WaitUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private WaitUtil waitUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By eMail = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPwd = By.id("input-confirm");
	private By subscribeYes = By.cssSelector("label.radio-inline input[value='1']");
	private By subscribeNo = By.cssSelector("label.radio-inline input[value='0']");
	private By agreeCheckbox = By.xpath("//input[@type='checkbox']");
	private By continueBtn = By.xpath("//input[@type='submit']");
	private By registerSuccessMsg = By.xpath("//h1[contains(text(),'Your Account Has Been Created')]");
	private By registerFailureMsg = By.xpath("//div[contains(@class,'alert-danger')]");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		waitUtil = new WaitUtil(driver);
	}
	
	public String getRegisterPageTitle() {
		String title = waitUtil.waitForTitleIsAndFetch(OpenCartConstants.REGISTER_PAGE_TITLE_VALUE, OpenCartConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Register Page title: " + title);
		return title;
	}
	
	public String getRegisterPageURL() {
		String url = waitUtil.waitForURLAndFetch(OpenCartConstants.REGISTER_PAGE_URL_FRACTION_VALUE, OpenCartConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Login page URL: " + url);
		return url;
	}
	
	public String doGenerateRandomEmailId() {
//		Random random = new Random();
//		String randomMailId = "testUser" + random.nextInt(1000) + "@opencart.com";
		String randomMailId = "testUser" + System.currentTimeMillis() + "@opencart.com";
		return randomMailId;
	}
	
	public boolean doRegister(String firstName, String lastName, String eMail, String telephone, 
			String password, String cnfPassword, String subscribe) {
		waitUtil.waitForElementVisible(this.firstName, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.eMail, eMail);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(confirmPwd, cnfPassword);
			if(subscribe.equalsIgnoreCase("yes")) {
				eleUtil.doClick(subscribeYes);
			}
			else {
				eleUtil.doClick(subscribeNo);
			}
		eleUtil.doActionsClick(agreeCheckbox);
		eleUtil.doClick(continueBtn);
			try {
				String successMessage = waitUtil.waitForElementVisible(registerSuccessMsg, OpenCartConstants.DEFAULT_SHORT_TIME_OUT).getText();
				System.out.println("Register account successful. Success message: \n" + successMessage);
				System.out.println("User details: " + firstName + " " + lastName + " " +  eMail + " " + telephone);
				return true;
			} catch (Exception e) {
				System.out.println("Register account was not successful!");
				System.out.println("Reason: " + eleUtil.doGetText(registerFailureMsg));
				return false;
			}
	}
	
	
}