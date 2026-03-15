package com.tutorialsninja.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	private WebDriver driver;
	
	private By loginPageHeader = By.xpath("//ul[@class='breadcrumb']//a[text()='Login']");
	private By newCustomerContinueButton = By.xpath("//a[text()='Continue']");
	private By registerLinkFromLoginSidebar = By.xpath("//aside[@id='column-right']//a[text()='Register']");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
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

}
