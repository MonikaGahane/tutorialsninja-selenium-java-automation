package com.tutorialsninja.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewsletterPage {
	
	private WebDriver driver;
	
	private By newsletterPageHeading = By.xpath("//ul[@class='breadcrumb']//a[normalize-space()='Newsletter']");
	private By yesRadioSelected = By.xpath("//input[@name='newsletter' and @value='1']");
	private By noRadioSelected = By.xpath("//input[@name='newsletter' and @value='0']");
	
	public NewsletterPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isNewsletterPageDisplayed() {
		return driver.findElement(newsletterPageHeading).isDisplayed();
	}
	
	public boolean isNewsletterSubscribed() {
		return driver.findElement(yesRadioSelected).isSelected();
	}
	
	public boolean isNewsletterUnsubscribed() {
		return driver.findElement(noRadioSelected).isSelected();
	}
	

}
