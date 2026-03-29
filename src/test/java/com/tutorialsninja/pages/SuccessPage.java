package com.tutorialsninja.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessPage {

    private WebDriver driver;

    private By successHeading = By.xpath("//h1[text()='Your Account Has Been Created!']");
    private By contentText = By.id("content");
    private By continueButton = By.linkText("Continue");
    private By logoutButtonFromSuccessPageSidebar = By.xpath("//aside[@id='column-right']//a[text()='Logout']");

    public SuccessPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSuccessPageDisplayed() {
        return driver.findElement(successHeading).isDisplayed();
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
