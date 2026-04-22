package com.tutorialsninja.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditAccountInfo {
	
	private WebDriver driver;
	
	//constructor
	public EditAccountInfo(WebDriver driver) {
		this.driver = driver;
	}
	
	//locators
	private By editInformationHeading = By.xpath("//ul[@class='breadcrumb']//a[normalize-space()='Edit Information']");
	private By firstNameInputField = By.id("input-firstname");
	private By lastNameInputField = By.id("input-lastname");
	private By emailInputField = By.id("input-email");
	private By telephoneInputField = By.id("input-telephone");
	
	public boolean isEditInformationPageDisplayed() {
		return driver.findElement(editInformationHeading).isDisplayed();
	}
	
	//getter methods
	public String getFirstNameInputFieldValue() {
		return driver.findElement(firstNameInputField).getAttribute("value");
	}
	
	public String getLastNameInputFieldValue() {
		return driver.findElement(lastNameInputField).getAttribute("value");
	}
	
	public String getEmailInputFieldValue() {
		return driver.findElement(emailInputField).getAttribute("value");
	}
	
	public String getTelephoneFieldValue() {
		return driver.findElement(telephoneInputField).getAttribute("value");
	}
	
}
