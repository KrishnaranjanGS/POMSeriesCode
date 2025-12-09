package com.qa.opencart.tests;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.ExcelUtils;


public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void registerPageSetup() {
		regPage = loginPage.goToRegisterPage();
	}
	
	@Test
	public void registerPageTitleTest() {
		String pageTitle = regPage.getRegisterPageTitle();
		AssertJUnit.assertEquals(pageTitle, OpenCartConstants.REGISTER_PAGE_TITLE_VALUE);
	}
	
	@Test
	public void registerPageUrlTest() {
		String pageUrl = regPage.getRegisterPageURL();
		AssertJUnit.assertTrue(pageUrl.contains(OpenCartConstants.REGISTER_PAGE_URL_FRACTION_VALUE));
	}
	
	@DataProvider
	public Object[][] getRegisterTestData() {
		Object regData[][] = ExcelUtils.getTestData(OpenCartConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider = "getRegisterTestData")
	public void registerTest(String firstName, String lastName, String phone, String password, 
			String cnfPassword, String subscribe) {
		boolean regSuccessPage = regPage.doRegister(firstName, lastName, regPage.doGenerateRandomEmailId(), phone, password, 
				cnfPassword,  subscribe);
		Assert.assertTrue(regSuccessPage);
	}
	
	@AfterMethod
	public void logoutToRegisterPage() {
		loginPage.doLogout();
		loginPage.goToRegisterPage();
	}
}
