package com.tutorialsninja.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {
	private WebDriver driver;
	
	//constructor
	public LogoutPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By logoutPageHeader = By.xpath("//ul[@class='breadcrumb']//a[normalize-space()='Logout']");
	
	private By continueButton = By.xpath("//a[normalize-space()='Continue']");
	
	public boolean isLogoutPageDisplayed() {		
		return driver.findElement(logoutPageHeader).isDisplayed();		
	}
	
	public void clickOnContinueButton() {
		driver.findElement(continueButton).click();
	}
}
