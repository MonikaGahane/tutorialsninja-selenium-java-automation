package com.tutorialsninja.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

	private WebDriver driver;
	
	//constructor
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	//Web Element locators
	private By registerPageHeader = By.xpath("//ul[@class='breadcrumb']//a[text()='Register']");
	
	//Sections
	private By personalDetailsHeading = By.xpath("//legend[text()='Your Personal Details']");
	private By passwordHeading = By.xpath("//legend[text()='Your Password']");
	private By newsletterHeading = By.xpath("//legend[text()='Newsletter']");
	
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
	
	private By privacyPolicyLink = By.linkText("Privacy Policy");
	
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
	
	//Right column links
	private By rightColumn = By.id("column-right");
	private By rightColumnLoginLink = By.xpath("//aside[@id='column-right']//a[text()='Login']");
	private By rightColumnRegisterLink = By.xpath("//aside[@id='column-right']//a[text()='Register']");
	private By rightColumnForgotPasswordLink = By.xpath("//aside[@id='column-right']//a[text()='Forgotten Password']");
	private By rightColumnMyAccountLink = By.xpath("//aside[@id='column-right']//a[text()='My Account']");
	
	// Footer sections
	private By footer = By.tagName("footer");
	private By footerInformationSection = By.xpath("//h5[text()='Information']");
	private By footerCustomerServiceSection = By.xpath("//h5[text()='Customer Service']");
	private By footerExtrasSection = By.xpath("//h5[text()='Extras']");
	private By footerMyAccountSection = By.xpath("//h5[text()='My Account']");
	
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
	
	//method for registering account without clicking the privacy policy check-box
	public void registerUserWithoutClickingPrivacyPolicyCheckbox(String firstName, String lastName, String email, String telephone, String password, boolean subscribeNewsletter) {
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
		try {
			return driver.findElement(telephoneWarningMessage).getText();
			} catch (NoSuchElementException e) {
				return "";
			}
	}
	
	public String getPasswordWarningMessage() {
		try {
			return driver.findElement(passwordWarningMessage).getText();
		} catch (NoSuchElementException e) {
			return "";
		}
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
				&& isFieldMarkedRequired(emailLabel)
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
	
	//method for checking the privacy policy check-box is not selected by default.. (so method should give result as not selected because in our case test passed from UI)
	public boolean isPrivacyPolicyCheckboxSelected() {
		return driver.findElement(privacyPolicyCheckbox).isSelected();
	}
	
	
	//getters for retrieving the type of password and confirm password fields
	//in HTML code "type = password" that makes the password as masked "....."
	public String getPasswordFieldType() {
		return driver.findElement(passwordInput).getAttribute("type");
	}
	
	public String getConfirmPasswordFieldType() {
		return driver.findElement(confirmPasswordInput).getAttribute("type");
	}
	
	public void enterPasswordAndConfirmPassword(String password) {
		driver.findElement(passwordInput).sendKeys(password);
		driver.findElement(confirmPasswordInput).sendKeys(password);
	}
	
	
	//Generic click method for any link on Register Page
	public void clickOnLink(String linkText) {
		driver.findElement(By.linkText(linkText)).click();
	}

	//For Right Links
	public void clickOnRightColumnLink(String linkText) {
		driver.findElement(By.xpath("//aside[@id='column-right']//a[normalize-space()='"+linkText+"']")).click();		
	}

	//For Header Links which have drop-down menu. So we hover first and then click
	public void hoverAndClickHeaderLink(String linkText) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		//Wait until Header is visible
		WebElement headerLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='nav navbar-nav']//a[normalize-space()='"+linkText+"']")));
		
		//scroll into view (for firefox stability)
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", headerLink);
		
		//wait until clickable
		wait.until(ExpectedConditions.elementToBeClickable(headerLink));
		
		Actions actions =new Actions(driver);
		
		//For Hover
		actions.moveToElement(headerLink).perform();
		
		//For sub-menu (from drop-down)
		//Here eg. "Show AllDesktops" is given, means their is no gap in between "All" and "Desktop"
		String showAllOption = "Show All" + linkText;
		
		//Wait + click
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(showAllOption)));		
		driver.findElement(By.linkText(showAllOption)).click();
		
	}

	//For Header links without drop-down menu
	public void clickHeaderLink(String linkText) {
		driver.findElement(By.xpath("//ul[@class='nav navbar-nav']//a[normalize-space()='" + linkText + "']")).click();
	}
		
	//For Footer links
	public void clickFooterLink(String linkText) {
		driver.findElement(By.xpath("//footer//a[normalize-space()='" + linkText + "']")).click();
	}
	
	//For getting Page Title
	public String getPageTitle() {
		return driver.getTitle();
	}

	//For privacyPolicyPopup
	public boolean isPrivacyPolicyPopupDisplayed() {		
		return driver.findElement(By.xpath("//div[@class='modal-header']//h4[normalize-space()='Privacy Policy']")).isDisplayed();				
	}

	//For closing the privacy policy popup
	public void closePrivacyPolicyPopup() {
		driver.findElement(By.xpath("//div[@class='modal-header']//button[@class='close']")).click();
	}	
	
	
	public String getPageHeading() {
	    return driver.findElement(By.xpath("//h1[text()='Register Account']")).getText();
	}

	public String getBreadcrumb() {
	    return driver.findElement(By.xpath("//ul[@class='breadcrumb']")).getText();
	}

	public String getPageURL() {
	    return driver.getCurrentUrl();
	}
	
	//for UI validation
	//sections
	public boolean isPersonalDetailsSectionDisplayed() {
	    return driver.findElement(personalDetailsHeading).isDisplayed();
	}

	public boolean isPasswordSectionDisplayed() {
	    return driver.findElement(passwordHeading).isDisplayed();
	}

	public boolean isNewsletterSectionDisplayed() {
	    return driver.findElement(newsletterHeading).isDisplayed();
	}
	
	//fields
	public boolean isFirstNameFieldDisplayed() {
	    return driver.findElement(firstNameInput).isDisplayed();
	}

	public boolean isLastNameFieldDisplayed() {
	    return driver.findElement(lastNameInput).isDisplayed();
	}

	public boolean isEmailFieldDisplayed() {
		return driver.findElement(emailInput).isDisplayed();
	}

	public boolean isTelephoneFieldDisplayed() {
	    return driver.findElement(telephoneInput).isDisplayed();
	}

	public boolean isPasswordFieldDisplayed() {
	    return driver.findElement(passwordInput).isDisplayed();
	}

	public boolean isConfirmPasswordFieldDisplayed() {
	    return driver.findElement(confirmPasswordInput).isDisplayed();
	}

	//placeholders ("First Name" inside the box before typing anything-> i.e. placeholder)
	public String getFirstNamePlaceholder() {
	    return driver.findElement(firstNameInput).getAttribute("placeholder");
	}

	public String getLastNamePlaceholder() {
	    return driver.findElement(lastNameInput).getAttribute("placeholder");
	}

	public String getEmailPlaceholder() {
	    return driver.findElement(emailInput).getAttribute("placeholder");
	}

	public String getTelephonePlaceholder() {
	    return driver.findElement(telephoneInput).getAttribute("placeholder");
	}

	public String getPasswordPlaceholder() {
	    return driver.findElement(passwordInput).getAttribute("placeholder");
	}

	public String getConfirmPasswordPlaceholder() {
	    return driver.findElement(confirmPasswordInput).getAttribute("placeholder");
	}
	//newsletter
	public boolean isNewsletterYesRadioDisplayed() {
	    return driver.findElement(newsletterYesRadio).isDisplayed();
	}

	public boolean isNewsletterNoRadioSelected() {
	    return driver.findElement(newsletterNoRadio).isSelected();
	}
	//privacy policy
	public boolean isPrivacyPolicyLinkDisplayed() {
	    return driver.findElement(privacyPolicyLink).isDisplayed();
	}
	public boolean isPrivacyPolicyCheckboxDisplayed() {
	    return driver.findElement(privacyPolicyCheckbox).isDisplayed();
	}

	//continue button
	public boolean isContinueButtonDisplayed() {
	    return driver.findElement(continueButton).isDisplayed();
	}
	public boolean isContinueButtonEnabled() { //isEnabled =  isClickable?
	    return driver.findElement(continueButton).isEnabled();
	}
	
	//Right column 
	public boolean isRegisterPageRightColumnDisplayed() {
		return driver.findElement(rightColumn).isDisplayed();
	}
	public boolean isRegisterPageRightColumnLoginDisplayed() {
	    return driver.findElement(rightColumnLoginLink).isDisplayed();
	}

	public boolean isRegisterPageRightColumnRegisterDisplayed() {
	    return driver.findElement(rightColumnRegisterLink).isDisplayed();
	}

	public boolean isRegisterPageRightColumnForgotPasswordDisplayed() {
	    return driver.findElement(rightColumnForgotPasswordLink).isDisplayed();
	}

	public boolean isRegisterPageRightColumnMyAccountDisplayed() {
	    return driver.findElement(rightColumnMyAccountLink).isDisplayed();
	}
	
	//footer
	public boolean isRegisterPageFooterDisplayed() {
	    return driver.findElement(footer).isDisplayed();
	}

	public boolean isRegisterPageFooterInformationSectionDisplayed() {
	    return driver.findElement(footerInformationSection).isDisplayed();
	}

	public boolean isRegisterPageFooterCustomerServiceSectionDisplayed() {
	    return driver.findElement(footerCustomerServiceSection).isDisplayed();
	}

	public boolean isRegisterPageFooterExtrasSectionDisplayed() {
	    return driver.findElement(footerExtrasSection).isDisplayed();
	}

	public boolean isRegisterPageFooterMyAccountSectionDisplayed() {
	    return driver.findElement(footerMyAccountSection).isDisplayed();
	}
	
}				
				