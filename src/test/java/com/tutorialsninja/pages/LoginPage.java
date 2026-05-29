package com.tutorialsninja.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tutorialsninja.utils.TestUtils;

public class LoginPage {
	
	private WebDriver driver;
	
	//constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	//Web element locators
	private By loginPageHeader = By.xpath("//ul[@class='breadcrumb']//a[text()='Login']");
	
	private By newCustomerContinueButton = By.xpath("//a[text()='Continue']");
	private By registerLinkFromLoginSidebar = By.xpath("//aside[@id='column-right']//a[text()='Register']");
	
	private By emailAddressInput = By.id("input-email");
	private By passwordInput = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	
	private By invalidLoginWarningMessage = By.xpath("//div[contains(@class,'alert-danger')]");
	
	private By forgottenPasswordLink = By.xpath("//a[normalize-space()='Forgotten Password']");
	
	public boolean isLoginPageDisplayed() {
		return driver.findElement(loginPageHeader).isDisplayed();
	}
	
	//returning the next page i.e. register page
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
		driver.findElement(loginButton).click();
	}
	
	public void loginUserWithoutProvidingAnyData() {
		driver.findElement(loginButton).click();
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

}
