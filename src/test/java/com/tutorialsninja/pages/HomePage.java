package com.tutorialsninja.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	private WebDriver driver;

	private By myAccountDropdown = By.xpath("//span[text()='My Account']");
	private By registerLink = By.linkText("Register");
	private By loginLink = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Login']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickMyAccount() {
		driver.findElement(myAccountDropdown).click();
	}

	public void clickRegisterLink() {
		driver.findElement(registerLink).click();
	}

	public void clickLoginLink() {
		driver.findElement(loginLink).click();
	}

	public void navigateToRegisterPageUsingRegisterLink() {
		clickMyAccount();
		clickRegisterLink();

		// explicit wait
		// for preventing failure of selenium
		new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname")));

	}

	public void navigateToRegisterPageUsingLoginLink() {
		clickMyAccount();
		clickLoginLink();

		// explicit wait
		// for preventing failure of selenium
		new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname")));

	}

}
