package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.OpenCartConstants;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void accountPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());	//not using "new AccountPage()" here as its already given by doLogin
	}
	
	@Test(priority = 1)
	public void accountPageTitleTest() {
		String actualTitle = accPage.getAccountPageTitle();
		Assert.assertEquals(actualTitle, OpenCartConstants.ACCOUNT_PAGE_TITLE_VALUE);
	}
	
	@Test(priority = 2)
	public void accountPageURLTest() {
		String actualURL = accPage.getAccountPageURL();
		Assert.assertTrue(actualURL.contains(OpenCartConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test(priority = 3)
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test(priority = 4)
	public void accountPageHeadersCountTest() {
		List<String> actualAccountHeadersList = accPage.getAccountPageHeadersList();
		System.out.println("Account page headers: " + actualAccountHeadersList);
		Assert.assertEquals(actualAccountHeadersList.size(), OpenCartConstants.ACCOUNT_PAGE_HEADERS_COUNT);
	}
	
	@Test(priority = 5)
	public void accountPageHeadersValueTest() {
		List<String> actualAccountHeadersList = accPage.getAccountPageHeadersList();
		System.out.println("Actual Account page headers: " + actualAccountHeadersList);
		System.out.println("Expected Account page headers: " + OpenCartConstants.ACCOUNT_PAGE_HEADERS_LIST);
		Assert.assertEquals(actualAccountHeadersList, OpenCartConstants.ACCOUNT_PAGE_HEADERS_LIST);
	}
	
	@Test(priority = 6)
	public void accountPageRightPanelExistsTest() {
		Assert.assertTrue(accPage.isAccountPageRightPanelPresent());
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"MacBook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"}
		};
	}
	
	@Test(priority = 7, dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey) {
		searchResultPage = accPage.performSearch(searchKey);
		Assert.assertTrue(searchResultPage.getSearchProductsCount()>0);
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro"},
			{"MacBook", "MacBook Air"},
			{"iMac", "iMac"},
			{"Apple", "Apple Cinema 30\""},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Samsung", "Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test(priority = 8, dataProvider = "getProductTestData")
	public void selectProductTest(String searchKey, String productKey) {
		searchResultPage = accPage.performSearch(searchKey);
		if(searchResultPage.getSearchProductsCount()>0) {
			productInfoPage = searchResultPage.selectProduct(productKey);
			String actualProductHeader = productInfoPage.getProductHeaderValue();
			Assert.assertEquals(actualProductHeader, productKey);
		}
		
	}
	
}
