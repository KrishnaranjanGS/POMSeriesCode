package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductPageTest extends BaseTest{
	
	@BeforeClass
	public void productPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());	
	}
	
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro", 4},
			{"MacBook", "MacBook Air", 4},
			{"iMac", "iMac", 3},
			{"Apple", "Apple Cinema 30\"", 6},
			{"Samsung", "Samsung SyncMaster 941BW", 1},
			{"Samsung", "Samsung Galaxy Tab 10.1", 7}
		};
	}
	
	@Test(dataProvider = "getProductImagesTestData")
	public void productImagesCountTest(String searchKey, String productKey, int expectedCount) {
		searchResultPage = accPage.performSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productKey);
		int actProductImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actProductImagesCount, expectedCount);
	}
	
	@DataProvider
	public Object[][] getProductDescriptionTestData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro"},
			{"MacBook", "MacBook Air"},
			{"MacBook", "MacBook"},
			{"iMac", "iMac"},
			{"Apple", "Apple Cinema 30\""},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Samsung", "Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test(dataProvider = "getProductDescriptionTestData")
	public void productDescriptionCountTest(String searchKey, String productKey) {
		searchResultPage = accPage.performSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productKey);
		int productDescriptionCount = productInfoPage.getProductDescriptionSize();
		Assert.assertTrue(productDescriptionCount>0);
	}
	
	@DataProvider
	public Object[][] getProductInfoTestData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro"}
		};
	}
	
	@Test(dataProvider = "getProductInfoTestData")
	public void productInfoTest(String searchKey, String productKey) {
		searchResultPage = accPage.performSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productKey);
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("product name"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("product price"), "$2,000.00");
		softAssert.assertAll();
	}
	
	
	@DataProvider
	public Object[][] addToCartTestData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro"}
		};
	}
	
	@Test(dataProvider = "addToCartTestData")
	public void addToCartTest(String searchKey, String productKey) {
		searchResultPage = accPage.performSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productKey);
		productInfoPage.enterQuantity(2);
		String actualCartMessage = productInfoPage.addProductToCart();
		softAssert.assertTrue(actualCartMessage.contains("Success"));
		softAssert.assertTrue(actualCartMessage.contains(productKey));
		softAssert.assertEquals(actualCartMessage, "Success: You have added "+productKey+" to your shopping cart!");
		softAssert.assertAll();
	}
}
