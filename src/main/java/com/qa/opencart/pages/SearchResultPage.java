package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.WaitUtil;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private WaitUtil waitUtil;
	
	private By searchResults = By.cssSelector("div#content div.product-layout");
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		waitUtil = new WaitUtil(driver);
	}
	
	public int getSearchProductsCount() {
		int productsCount = waitUtil.waitForElementsVisible(searchResults, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Products count: " + productsCount);
		return productsCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productLocator = By.linkText(productName);
		waitUtil.waitForElementVisible(productLocator, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
	}

}
