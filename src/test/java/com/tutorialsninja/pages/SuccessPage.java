package com.tutorialsninja.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SuccessPage {

    private WebDriver driver;
    
    public SuccessPage(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    private By successHeader = By.xpath("//ul[@class='breadcrumb']//a[normalize-space()='Success']");
    private By contentText = By.id("content");
    private By continueButton = By.linkText("Continue");
    private By logoutButtonFromSuccessPageSidebar = By.xpath("//aside[@id='column-right']//a[text()='Logout']");
   

    public boolean isSuccessPageDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
    	return wait.until(ExpectedConditions.visibilityOfElementLocated(successHeader)).isDisplayed();
        
    }

    public String getSuccessContent() {
        return driver.findElement(contentText).getText();
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }
    
    public void clickLogoutFromSuccessPageSidebar() {
    	driver.findElement(logoutButtonFromSuccessPageSidebar).click();
    }
}
