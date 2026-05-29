package com.tutorialsninja.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {

    private WebDriver driver;

    private By accountPageHeading = By.xpath("//ul[@class='breadcrumb']//a[normalize-space()='Account']");
    private By editAccountInfo = By.xpath("//a[contains(text(),'Edit your account information')]");  
    private By newsletterHeading = By.xpath("//div[@id='content']//h2[text()='Newsletter']");
    private By subscribeOrUnsubscribeToNewsletter = By.xpath("//div[@id='content' and @class='col-sm-9']//a[normalize-space()='Subscribe / unsubscribe to newsletter']");
    
    private By logoutLinkFromAccountPageSidebar = By.xpath("//aside[@id='column-right']//a[normalize-space()='Logout']");
    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public boolean isEditAccountInfoLinkDisplayed() {
    	return driver.findElement(editAccountInfo).isDisplayed();
    }

    public boolean isAccountPageDisplayed() {
        return driver.findElement(accountPageHeading).isDisplayed();
    }
    
    public boolean isNewsletterHeadingDisplayed() {
    	return driver.findElement(newsletterHeading).isDisplayed();
    }
    
    public void navigateToNewsletterPage() {
    	driver.findElement(subscribeOrUnsubscribeToNewsletter).click();
    }
    
    public void navigateToEditAccountInfoPage() {
    	driver.findElement(editAccountInfo).click();
    }
    
    public void logoutUser() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.elementToBeClickable(logoutLinkFromAccountPageSidebar)).click();
    	
    }
}
