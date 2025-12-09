package com.qa.opencart.tests;

//	Test classes should not have any automation methods (api) like .click() or .get or .sendkeys etc i.e any Selenium methods
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.OpenCartConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;


@Epic("E100 - Design login test suite for opencart application")
@Story("US101 - Design login page features for opencart")
public class LoginPageTest extends BaseTest{
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("Get title of the page. Author - Krishnaranjan")
	@Test
	public void LoginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, OpenCartConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("Get URL of the page. Author - Krishnaranjan")
	@Test
	public void LoginPageURLTest() {
		String actualURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains(OpenCartConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("Is the logo displayed? Author - Krishnaranjan")
	@Test
	public void LoginPageLogoTest() {
		Assert.assertTrue(loginPage.isLogoDisplayed());
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Is the forgot password link displayed? Author - Krishnaranjan")
	@Test
	public void forgotPasswordExistsTest() {
		Assert.assertTrue(loginPage.isForgotPasswordPresent());
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Is the login page right panel displayed? Author - Krishnaranjan")
	@Test
	public void LoginPageRightPanelExistsTest() {
		Assert.assertTrue(loginPage.isLoginPageRightPanelPresent());
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Is the login page footer displayed? Author - Krishnaranjan")
	@Test
	public void FooterPresentTest() {
		Assert.assertTrue(loginPage.isFooterPresent());
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Login to the application. Author - Krishnaranjan")
	@Test(priority = 2)
	public void LoginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
}
