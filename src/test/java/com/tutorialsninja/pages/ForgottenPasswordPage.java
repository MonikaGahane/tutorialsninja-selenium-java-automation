package com.tutorialsninja.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgottenPasswordPage {
	
	private WebDriver driver;
	
	//constructor
	public ForgottenPasswordPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By forgottenPasswordPageHeader = By.xpath("//ul[@class='breadcrumb']//a[normalize-space()='Forgotten Password']");
	private By emailAddressInput = By.id("input-email");
	
	public boolean isForgottenPasswordPageDisplayed() {
		return driver.findElement(forgottenPasswordPageHeader).isDisplayed() 
				&& driver.findElement(emailAddressInput).isDisplayed();
	}
}
