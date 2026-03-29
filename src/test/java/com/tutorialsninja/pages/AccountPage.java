package com.tutorialsninja.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {

    private WebDriver driver;

    private By accountPageHeading = By.xpath("//ul[@class='breadcrumb']//a[normalize-space()='Account']");
    private By editAccountInfo = By.xpath("//a[contains(text(),'Edit your account information')]");  
    private By newsletterHeading = By.xpath("//div[@id='content']//h2[text()='Newsletter']");
    private By subscribeOrUnsubscribeToNewsletter = By.xpath("//div[@id='content' and @class='col-sm-9']//a[normalize-space()='Subscribe / unsubscribe to newsletter']");
    
    
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
}
