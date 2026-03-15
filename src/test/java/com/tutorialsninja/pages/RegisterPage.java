package com.tutorialsninja.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

	private WebDriver driver;

	//locators
	private By registerPageHeader = By.xpath("//ul[@class='breadcrumb']//a[text()='Register']");
	
	
	private By firstNameInput = By.id("input-firstname");
	private By lastNameInput = By.id("input-lastname");
	private By emailInput = By.id("input-email");
	private By telephoneInput = By.id("input-telephone");
	private By passwordInput = By.id("input-password");
	private By confirmPasswordInput = By.id("input-confirm");
	private By privacyPolicyCheckbox = By.name("agree");
	private By continueButton = By.xpath("//input[@value='Continue']");

	private By newsletterYesRadio = By.xpath("//input[@name='newsletter'][@value='1']");
	private By newsletterNoRadio = By.xpath("//input[@name='newsletter'][@value='0']");
	
	private By firstNameWarningMessage = By.xpath("//input[@id='input-firstname']/following-sibling::div[contains(@class,'text-danger')]");
	private By lastNameWarningMessage = By.xpath("//input[@id='input-lastname']/following-sibling::div[contains(@class, 'text-danger')]");
	private By emailWarningMessage = By.xpath("//input[@id='input-email']/following-sibling::div[contains(@class, 'text-danger')]");
	private By telephoneWarningMessage = By.xpath("//input[@id='input-telephone']/following-sibling::div[contains(@class, 'text-danger')]");
	private By passwordWarningMessage = By.xpath("//input[@id='input-password']/following-sibling::div[contains(@class, 'text-danger')]");
	private By privacyPolicyWarningMessage = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	
	private By confirmPasswordWarningMessage = By.xpath("//input[@id='input-confirm']/following-sibling::div[contains(@class,'text-danger')]");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isRegisterPageDisplayed() {
		return driver.findElement(registerPageHeader).isDisplayed();
	}

	//for mandatory fields 
	public void registerUser(String firstName, String lastName, String email, String telephone, String password) {

		driver.findElement(firstNameInput).sendKeys(firstName);
		driver.findElement(lastNameInput).sendKeys(lastName);
		driver.findElement(emailInput).sendKeys(email);
		driver.findElement(telephoneInput).sendKeys(telephone);
		driver.findElement(passwordInput).sendKeys(password);
		driver.findElement(confirmPasswordInput).sendKeys(password);
		driver.findElement(privacyPolicyCheckbox).click();
		driver.findElement(continueButton).click();
	}

	//for newsletter subscription
	public void registerUser(String firstName, String lastName, String email, String telephone, String password, boolean subscribeNewsletter) {

		driver.findElement(firstNameInput).sendKeys(firstName);
		driver.findElement(lastNameInput).sendKeys(lastName);
		driver.findElement(emailInput).sendKeys(email);
		driver.findElement(telephoneInput).sendKeys(telephone);
		driver.findElement(passwordInput).sendKeys(password);
		driver.findElement(confirmPasswordInput).sendKeys(password);

		if (subscribeNewsletter) {
			driver.findElement(newsletterYesRadio).click();
		} else {
			driver.findElement(newsletterNoRadio).click();
		}

		driver.findElement(privacyPolicyCheckbox).click();
		driver.findElement(continueButton).click();
	}
	
	//for not filling data
	public void clickContinueWithoutFillingAnything() {
		driver.findElement(continueButton).click();
	}
	
	//getter methods for warnings
	public String getFirstNameWarningMessage() {
		return driver.findElement(firstNameWarningMessage).getText();
	}
	
	public String getLastNameWarningMessage() {
		return driver.findElement(lastNameWarningMessage).getText();
	}
	
	public String getEmailWarningMessage() {
		return driver.findElement(emailWarningMessage).getText();
	}
	
	public String getTelephoneWarningMessage() {
		return driver.findElement(telephoneWarningMessage).getText();
	}
	
	public String getPasswordWarningMessage() {
		return driver.findElement(passwordWarningMessage).getText();
	}
	
	public String getPrivacyPolicyWarningMessage() {
		return driver.findElement(privacyPolicyWarningMessage).getText();
	}
	
	
	public String getConfirmPasswordWarningMessage() {
		return driver.findElement(confirmPasswordWarningMessage).getText();
	}
	
	//for different passwords into password and confirm password fields
	public void registerUser(String firstName, String lastName, String email, String telephone, String password, String confirmPassword) {
		driver.findElement(firstNameInput).sendKeys(firstName);
		driver.findElement(lastNameInput).sendKeys(lastName);
		driver.findElement(emailInput).sendKeys(email);
		driver.findElement(telephoneInput).sendKeys(telephone);
		driver.findElement(passwordInput).sendKeys(password);
		driver.findElement(confirmPasswordInput).sendKeys(confirmPassword);
		driver.findElement(privacyPolicyCheckbox).click();
		driver.findElement(continueButton).click();
	}
	
	
}
