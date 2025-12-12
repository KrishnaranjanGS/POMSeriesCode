package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class OrderPage {
	
	By price = By.id("price");
	
	public void getPrice() {
		System.out.println("get price");
	}
}
