package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.WaitUtil;

public class OrderPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private WaitUtil waitUtil;
	
	By price = By.id("price");
	
	public OrderPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		waitUtil = new WaitUtil(driver);
	}
	
	public void getPrice() {
		System.out.println("get price");
	}
}
