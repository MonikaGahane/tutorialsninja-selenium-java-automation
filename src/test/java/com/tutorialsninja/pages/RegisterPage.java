package com.tutorialsninja.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {

	private WebDriver driver;

	//Web Element locators
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
	
	//Warning locators
	private By firstNameWarningMessage = By.xpath("//input[@id='input-firstname']/following-sibling::div[contains(@class,'text-danger')]");
	private By lastNameWarningMessage = By.xpath("//input[@id='input-lastname']/following-sibling::div[contains(@class, 'text-danger')]");
	private By emailWarningMessage = By.xpath("//input[@id='input-email']/following-sibling::div[contains(@class, 'text-danger')]");
	private By telephoneWarningMessage = By.xpath("//input[@id='input-telephone']/following-sibling::div[contains(@class, 'text-danger')]");
	private By passwordWarningMessage = By.xpath("//input[@id='input-password']/following-sibling::div[contains(@class, 'text-danger')]");
	private By privacyPolicyWarningMessage = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	
	private By confirmPasswordWarningMessage = By.xpath("//input[@id='input-confirm']/following-sibling::div[contains(@class,'text-danger')]");

	private By existingEmailWarningMessage = By.xpath("//div[contains(@class,'alert-danger') and contains(normalize-space(),'Warning: E-Mail Address is already registered!')]");
	
	private By invalidEmailWarningMessage = By.cssSelector(".text-danger");
	
	//Label Locators
	//private By firstNameLabel = By.xpath("//label[@for='input-firstname']"); ==> it is okay
	//but the 'required' class is applied on the parent container, not on the label. So locate the parent div to validate mandatory fields
	private By firstNameLabel = By.xpath("//label[@for='input-firstname']/parent::div");
	private By lastNameLabel = By.xpath("//label[@for='input-lastname']/parent::div");
	private By emailLabel = By.xpath("//label[@for='input-email']/parent::div");
	private By telephoneLabel = By.xpath("//label[@for='input-telephone']/parent::div");
	private By passwordLabel = By.xpath("//label[@for='input-password']/parent::div");
	private By confirmPasswordLabel = By.xpath("//label[@for='input-confirm']/parent::div");
	//for privacy policy their is no asterisk given in the UI section so we will use privacy policy check-box
	
	//constructor
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
	
	public String getExistingEmailWarningMessage() {
		return driver.findElement(existingEmailWarningMessage).getText();
	}
	
	//For Browser validation
	public String getInvalidEmailWarningMessageFromBrowserValidation() {
		return driver.findElement(emailInput).getAttribute("validationMessage");
	}
	
	//For UI validation
    public String getInvalidEmailWarningMessageFromUIValidation() {
        try {
            return driver.findElement(invalidEmailWarningMessage).getText();
        } catch (Exception e) {
            return "";
        }
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
	
	//required field checking
	public boolean isFieldMarkedRequired(By labelLocator) {
		String className = driver.findElement(labelLocator).getAttribute("class");
		return className.contains("required");		
	}
	
	//All mandatory fields checking one by one using above method 
	public boolean areAllMandatoryFieldsMarkedRequired() {
		return isFieldMarkedRequired(firstNameLabel)
				&& isFieldMarkedRequired(lastNameLabel)
				&& isFieldMarkedRequired(emailInput)
				&& isFieldMarkedRequired(telephoneLabel)
				&& isFieldMarkedRequired(passwordLabel)
				&& isFieldMarkedRequired(confirmPasswordLabel)
				&& isFieldMarkedRequired(privacyPolicyCheckbox);
	}
	
	//for getting asterisk (*) 
	//here asterisk is not present in html code, it is in css pseudo-code (::before) so we have to use javascript executor, because selenium can't access it directly
	public String getAsteriskFromBeforeContent(WebElement element) {
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;  // JavascriptExecutor is an interface so cannot create object using new so we can'y write it as "new JavascriptExecutor (driver);
		//"Because JavascriptExecutor is an interface implemented by the driver classes like ChromeDriver. Since we declare driver as WebDriver, we need to cast it to JavascriptExecutor to access executeScript() method."
				
		return (String) jsExecutor.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",element);
		//here argument[0] means the array of ::before content and [0] means from inspect, their on 0th position content = * is present
	}
	
	//for getting asterisk color
	// same as asterisk
	public String getColorOfAsteriskFromBeforeContent(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", element);
		
	}

	// Validation method for both asterisk(*) and its red color
	public boolean isFieldMarkedWithRedAsterisk(By labelLocator) {
		try {
			// Try to find label (for normal fields)
			WebElement label = driver.findElement(labelLocator).findElement(By.tagName("label"));

			String content = getAsteriskFromBeforeContent(label);
			String color = getColorOfAsteriskFromBeforeContent(label);

			return content.contains("*") && color.contains("rgb(255, 0, 0)");
		} catch (Exception e) {
			// For elements like Privacy Policy (no label)
			return false; // This will trigger your failure message
		}
	}
	
	//validate one by one by using above method
	public boolean areAllMandetoryFieldsAreMarkedWithRedAsterisk() {
		return isFieldMarkedWithRedAsterisk(firstNameLabel)
				&& isFieldMarkedWithRedAsterisk(lastNameLabel)
				&& isFieldMarkedWithRedAsterisk(emailLabel)
				&& isFieldMarkedWithRedAsterisk(telephoneLabel)
				&& isFieldMarkedWithRedAsterisk(passwordLabel)
				&& isFieldMarkedWithRedAsterisk(confirmPasswordLabel)
				&& isFieldMarkedWithRedAsterisk(privacyPolicyCheckbox);
		
	}
	
	
	
}
