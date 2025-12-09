package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.WaitUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private WaitUtil waitUtil;
	
	private By productHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img"); 
	private By productDescription = By.xpath("//div[@id='tab-description']");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By productQuantity = By.id("input-quantity");
	private By addToCartButton = By.id("button-cart");
	private By successMessage = By.cssSelector("div.alert.alert-success");
	
	private Map<String, String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		waitUtil = new WaitUtil(driver);
	}
	
	public String getProductHeaderValue() {
		String productHeaderValue = eleUtil.doGetText(productHeader);
		System.out.println("Product header: " + productHeaderValue);
		return productHeaderValue;
	}
	
	public int getProductImagesCount() {
		int imagesCount = waitUtil.waitForElementsVisible(productImages, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Product images count: " + imagesCount);
		return imagesCount;
	}
	
	public int getProductDescriptionSize() {
		List<WebElement> productDescriptionElements = waitUtil.waitForElementsVisible(productDescription, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT);
		String descriptionText=null;
		for(WebElement e: productDescriptionElements) {
				descriptionText = descriptionText + "\n" + e.getText();
			}
		int textSize = descriptionText.length();
		System.out.println("Length of the description text: " + textSize);
		return textSize;
	}
	
	public Map<String, String> getProductInfo() {
//		productInfoMap = new HashMap<String, String>();				// random order 
		productInfoMap = new LinkedHashMap<String, String>();		// ordered 
//		productInfoMap = new TreeMap<String, String>();				// alphabetically sorted based on key (caps first and small letters next)
		productInfoMap.put("product name", getProductHeaderValue());
		getProductMetaData();
		getProductPricingData();
		System.out.println(productInfoMap);
		return productInfoMap;
	}
	
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.doGetElements(productMetaData);
		for(WebElement e: metaDataList) {
			String metaInfo = e.getText();
			String metaData[] = metaInfo.split(":");
			String key = metaData[0].trim();
			String value = metaData[1].trim();
			productInfoMap.put(key, value);
		}
	}
	
	private void getProductPricingData() {
		List<WebElement> priceList = eleUtil.doGetElements(productPriceData);
		String price = priceList.get(0).getText();
		String exTax = priceList.get(1).getText();
		String exTaxVal = exTax.split(":")[1].trim();
		productInfoMap.put("product price", price);
		productInfoMap.put("Ex Tax", exTaxVal);
	}
	
	public void enterQuantity(int quantity) {
		System.out.println("Product quantity: " + quantity);
		driver.findElement(productQuantity).clear();
		eleUtil.doSendKeys(productQuantity, String.valueOf(quantity));
	}
	
	public String addProductToCart() {
		eleUtil.doClick(addToCartButton);
		String successMsg = waitUtil.waitForElementVisible(successMessage, OpenCartConstants.DEFAULT_SHORT_TIME_OUT).getText();
		StringBuilder stringBuilder = new StringBuilder(successMsg);
		String message = stringBuilder.substring(0, stringBuilder.length()-1).replace("\n", "").toString();
		System.out.println("Cart success message: " + message);
		return message;
	}
}
