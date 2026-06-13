package com.tutorialsninja.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tutorialsninja.utils.TestConstants;
import com.tutorialsninja.utils.TestUtils;

public class LoginPage {

	private WebDriver driver;

	// constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Web element locators
	private By loginPageHeader = By.xpath("//ul[@class='breadcrumb']//a[text()='Login']");

	private By newCustomerContinueButton = By.xpath("//a[text()='Continue']");
	private By registerLinkFromLoginSidebar = By.xpath("//aside[@id='column-right']//a[text()='Register']");

	private By emailAddressInput = By.id("input-email");
	private By passwordInput = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");

	private By invalidLoginWarningMessage = By.xpath("//div[contains(@class,'alert-danger')]");

	private By forgottenPasswordLink = By.xpath("//a[normalize-space()='Forgotten Password']");

	private By sidebarMyAccountLink = By.xpath("//div[@class='list-group']//a[normalize-space()='My Account']");

	private By invalidLoginWarningForMultipleAttempts = By.xpath(
			"//div[contains(@class, 'alert-danger') and normalize-space(text()='Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.')]");

	public boolean isLoginPageDisplayed() {
		return driver.findElement(loginPageHeader).isDisplayed();
	}

	public void clickOnLoginButton() {
		driver.findElement(loginButton).click();
	}

	// returning the next page i.e. register page
	public RegisterPage clickNewCustomerContinue() {
		driver.findElement(newCustomerContinueButton).click();
		return new RegisterPage(driver);
	}

	public void clickRegisterLinkFromLoginSidebar() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement registerLink = wait.until(ExpectedConditions.elementToBeClickable(registerLinkFromLoginSidebar));
		registerLink.click();
	}

	public void loginUser(String emailAddress, String password) {
		driver.findElement(emailAddressInput).sendKeys(emailAddress);
		driver.findElement(passwordInput).sendKeys(password);
		clickOnLoginButton();
	}

	public void loginUserWithoutProvidingAnyData() {
		clickOnLoginButton();
	}

	public void loginUserUsingKeyboardKeys(String emailAddress, String password) {

		TestUtils.pressTabKey(driver, 23);

		Actions actions = new Actions(driver);

		actions.pause(Duration.ofSeconds(2)).sendKeys(emailAddress).pause(Duration.ofSeconds(2)).sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(2)).sendKeys(password).pause(Duration.ofSeconds(2)).sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(2)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(2)).sendKeys(Keys.ENTER)
				.build().perform();
	}

	public void clickOnForgottenPasswordLink() {
		driver.findElement(forgottenPasswordLink).click();
	}

	public String getInvalidLoginWarningMessage() {
		return driver.findElement(invalidLoginWarningMessage).getText();
	}

	public String getLoginEmailAddressPlaceholder() {
		return driver.findElement(emailAddressInput).getAttribute("placeholder");
	}

	public String getLoginPasswordPlaceholder() {
		return driver.findElement(passwordInput).getAttribute("placeholder");
	}

	public String getInvalidLoginWarningForMultipleAttempts() {
		return driver.findElement(invalidLoginWarningForMultipleAttempts).getText();
	}

	public void clickSidebarMyAccountLink() {
		driver.findElement(sidebarMyAccountLink).click();
	}

	public void enterLoginPassword() {
		driver.findElement(passwordInput).sendKeys(TestConstants.PASSWORD);
	}

	public String getLoginPasswordFieldType() {
		return driver.findElement(passwordInput).getAttribute("type");
	}

	public void copyPasswordTextUsingKeboardKeys() {
		enterLoginPassword();
		driver.findElement(passwordInput).sendKeys(Keys.chord(Keys.CONTROL, "a"));		//for selecting text
		driver.findElement(passwordInput).sendKeys(Keys.chord(Keys.CONTROL, "c"));		//for copying text
		copiedTextPasteIntoEmailField();
	}
	
	public void copiedTextPasteIntoEmailField() {
		driver.findElement(emailAddressInput).sendKeys(Keys.chord(Keys.CONTROL, "v"));
	}
	
	public String getPasswordTextValue() {
		return driver.findElement(passwordInput).getAttribute("value");
	}
	
	public String getEmailAddressTextValue() {
		return driver.findElement(emailAddressInput).getAttribute("value");		
	}
	
	public void copyPasswordTextUsingRightClick() {
		enterLoginPassword();
		
		WebElement loginPasswordField = driver.findElement(passwordInput);
		
		Actions actions = new Actions(driver);
		actions.contextClick(loginPasswordField).perform();
		
		copiedTextPasteIntoEmailField();
		
	}
}
