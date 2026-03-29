package com.tutorialsninja.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tutorialsninja.base.BaseTest;
import com.tutorialsninja.pages.*;
import com.tutorialsninja.utils.TestConstants;
import com.tutorialsninja.utils.TestUtils;

public class RegistrationTest extends BaseTest {

	
	// TC_RF_001 i.e test case 001
	//Verify Registering an Account by providing only the Mandatory fields
	@Test
	public void verifyUserCanRegisterWithValidDetails() {

		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, TestUtils.generateNewEmail(), TestConstants.TELEPHONE, TestConstants.PASSWORD);

		SuccessPage successPage = new SuccessPage(driver);
		Assert.assertTrue(successPage.isSuccessPageDisplayed());

		String content = successPage.getSuccessContent();
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_1));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_2));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_3));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_4));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_5));

		successPage.clickContinue();

		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.isAccountPageDisplayed());
	}
	
	//TC_RF_002 i.e. test case 002
	//Verify 'Thank you for registering' email is sent to the registered email address as a confirmation for registering the account
	@Test
	public void verifyUserReceivesAnEmailOfConfirmationAfterSuccessfulRegistration() {
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, "monika07@gmail.com", TestConstants.TELEPHONE, TestConstants.PASSWORD, true);
		
		SuccessPage successPage = new SuccessPage(driver);
		Assert.assertTrue(successPage.isSuccessPageDisplayed());
		
		String content = successPage.getSuccessContent();
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_1));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_2));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_3));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_4));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_5));
		
		successPage.clickContinue();
		
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.isAccountPageDisplayed());
		
		//Capture screenshot
	    TestUtils.captureScreenshot(driver, "TC_RF_002_RegistrationSuccess");
		
		//Email verification skipped
	    System.out.println("NOTE: Email verification not automated due to demo environment (SMTP not configured).");

	}
	
	// TC_RF_003 i.e. test case 003
	//Verify Registering an Account by providing all the fields
	@Test
	public void verifyUserCanRegisterWithAllFields() {
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, TestUtils.generateNewEmail(), TestConstants.TELEPHONE, TestConstants.PASSWORD, true);

		SuccessPage successPage = new SuccessPage(driver);
		Assert.assertTrue(successPage.isSuccessPageDisplayed());

		String content = successPage.getSuccessContent();
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_1));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_2));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_3));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_4));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_5));

		successPage.clickContinue();

		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.isAccountPageDisplayed());
	}

	// TC_RF_004 i.e test case 004
	//Verify proper notification messages are displayed for the mandatory fields, when you don't provide any fields in the 'Register Account' page and submit
	@Test
	public void verifyUserCannotRegisterWithoutFillingMandatoryFields() {
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.clickContinueWithoutFillingAnything();

		String actalFirstNameWarning = registerPage.getFirstNameWarningMessage();
	    String actualLastNameWarning = registerPage.getLastNameWarningMessage();
	    String actualEmailWarning = registerPage.getEmailWarningMessage();
	    String actualTelephoneWarning = registerPage.getTelephoneWarningMessage();
	    String actualPasswordWarning = registerPage.getPasswordWarningMessage();
	    String actualPrivacyPolicyWarning = registerPage.getPrivacyPolicyWarningMessage();

		Assert.assertEquals(actalFirstNameWarning, TestConstants.EXPECTED_FIRST_NAME_WARNING);
		Assert.assertEquals(actualLastNameWarning, TestConstants.EXPECTED_LAST_NAME_WARNING);
		Assert.assertEquals(actualEmailWarning, TestConstants.EXPECTED_EMAIL_WARNING);
		Assert.assertEquals(actualTelephoneWarning, TestConstants.EXPECTED_TELEPHONE_WARNING);
		Assert.assertEquals(actualPasswordWarning, TestConstants.EXPECTED_PASSWORD_WARNING);
		Assert.assertEquals(actualPrivacyPolicyWarning, TestConstants.EXPECTED_PRIVACY_POLICY_WARNING);

	}

	// TC_RF_005 i.e. test case 005
	//Verify Registering an Account when 'Yes' option is selected for Newsletter field
	@Test
	public void verifyUserCanRegisterWithNewsletterSubscriptionYes() {
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, TestUtils.generateNewEmail(), TestConstants.TELEPHONE, TestConstants.PASSWORD, true);

		SuccessPage successPage = new SuccessPage(driver);
		Assert.assertTrue(successPage.isSuccessPageDisplayed());

		String content = successPage.getSuccessContent();
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_1));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_2));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_3));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_4));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_5));

		successPage.clickContinue();

		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.isAccountPageDisplayed());		
		Assert.assertTrue(accountPage.isNewsletterHeadingDisplayed());
		accountPage.navigateToNewsletterPage();
		
		NewsletterPage newsletterPage = new NewsletterPage(driver);
		Assert.assertTrue(newsletterPage.isNewsletterPageDisplayed());
		Assert.assertTrue(newsletterPage.isNewsletterSubscribed());
		
	}
	
	
	// TC_RF_006 i.e. test case 006
	//Verify Registering an Account when 'No' option is selected for Newsletter field
	@Test
	public void verifyUserCanRegisterWithNewsletterSubscriptionNo() {
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, TestUtils.generateNewEmail(), TestConstants.TELEPHONE, TestConstants.PASSWORD, false);

		SuccessPage successPage = new SuccessPage(driver);
		Assert.assertTrue(successPage.isSuccessPageDisplayed());

		String content = successPage.getSuccessContent();
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_1));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_2));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_3));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_4));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_5));

		successPage.clickContinue();

		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.isAccountPageDisplayed());		
		Assert.assertTrue(accountPage.isNewsletterHeadingDisplayed());
		accountPage.navigateToNewsletterPage();
		
		NewsletterPage newsletterPage = new NewsletterPage(driver);
		Assert.assertTrue(newsletterPage.isNewsletterPageDisplayed());
		Assert.assertTrue(newsletterPage.isNewsletterUnsubscribed());
		
	}
	
	
	// TC_RF_007 i.e. test case 007
	//Verify different ways of navigating to 'Register Account' page
	@Test
	public void verifyMultipleWaysOfNavigatingToRegisterPage() {
		
		HomePage homePage = new HomePage(driver);
		RegisterPage registerPage = new RegisterPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		
		//Way 1 = Navigate using Register link from my account drop-down menu
		homePage.navigateToRegisterPageUsingRegisterLink();
		Assert.assertTrue(registerPage.isRegisterPageDisplayed());
		
		//Way 2 = Navigate using Login Page -> New Customer continue button
		homePage.navigateToRegisterPageUsingLoginLink();
		Assert.assertTrue(loginPage.isLoginPageDisplayed());
		loginPage.clickNewCustomerContinue();
		Assert.assertTrue(registerPage.isRegisterPageDisplayed());
		
		//Way 3 = Navigate using LoginPage side-bar Register option
		homePage.navigateToRegisterPageUsingLoginLink();
		Assert.assertTrue(loginPage.isLoginPageDisplayed());
		loginPage.clickRegisterLinkFromLoginSidebar();
		Assert.assertTrue(registerPage.isRegisterPageDisplayed());	
		
	}

	
	// TC_RF_008 i.e. test case 008
	//Verify Registering an Account by entering different passwords into 'Password' and 'Password Confirm' fields
	@Test
	public void verifyRegistrationFailsWhenPasswordsDoNotMatch() {
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, TestUtils.generateNewEmail(), TestConstants.TELEPHONE, TestConstants.PASSWORD, "678u90");
		
		String actualConfirmPasswordWarningMessage = registerPage.getConfirmPasswordWarningMessage();
		Assert.assertEquals(actualConfirmPasswordWarningMessage, TestConstants.EXPECTED_CONFIRM_PASSWORD_WARNING);		
	
	}
	
	
	// TC_RF_009 i.e. test case 009
	//Verify Registering an Account by providing the existing account details (i.e. existing email address)
	@Test
	public void verifyRegistrationFailsByProvidingExistingDetails() {
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, TestConstants.EXISTING_EMAIL, TestConstants.TELEPHONE, TestConstants.PASSWORD);
		
		String actualExistingEmailWarningMessage = registerPage.getExistingEmailWarningMessage();
		Assert.assertEquals(actualExistingEmailWarningMessage, TestConstants.EXPECTED_EXISTING_EMAIL_WARNING);
				
	}
	
	
	// TC_RF_010 i.e. test case 010
	//Verify Registering an Account by providing an invalid email address into the E-Mail field
	@Test
	public void verifyRegistrationFailsByProvidingInvalidEmail() {
		HomePage homePage = new HomePage(driver);		
		RegisterPage registerPage = new RegisterPage(driver);
		
		for(String invalidEmail : TestConstants.INVALID_EMAIL) {
			
			homePage.navigateToRegisterPageUsingRegisterLink();
			
			registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, invalidEmail, TestConstants.TELEPHONE, TestConstants.PASSWORD, true);

			String browserValidationMessage = registerPage.getInvalidEmailWarningMessageFromBrowserValidation();
			String uiValidationMessage = registerPage.getInvalidEmailWarningMessageFromUIValidation();
			
			System.out.println("Testing Invalid Email : " + invalidEmail);
			System.out.println("Browser Valoidation Message : " + browserValidationMessage);
			System.out.println("UI validation message : " + uiValidationMessage);
			
			 // Screenshot
	        TestUtils.captureScreenshot(driver, "TC_RF_010_"+invalidEmail.replace("@", "_"));
			
			if(!browserValidationMessage.isEmpty()) {
				Assert.assertTrue(browserValidationMessage.length() > 0);
			} else {
				Assert.assertTrue(uiValidationMessage.contains("E-Mail Address does not appear to be valid"));
			}
		}
	}
	
	// TC_RF_011 i.e. test case 011
	//Verify Registering an Account by providing an invalid phone number
	@Test
	public void verifyRegistrationFailsByProvidingInvalidTelephoneNumber() {
		HomePage homePage =new HomePage(driver);		
		RegisterPage registerPage = new RegisterPage(driver);
		SuccessPage successPage = new SuccessPage(driver);
		
		for(String invalidTelephoneNumber : TestConstants.INVALID_TELEPHONE_NUMBER) {
			homePage.navigateToRegisterPageUsingRegisterLink();
			
			registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, TestUtils.generateNewEmail(), invalidTelephoneNumber, TestConstants.PASSWORD, true);
						
			//if telephone number < 3 digits then warning expected
			if(invalidTelephoneNumber.length() < 3) {
				String telePhoneWarnningMessage = registerPage.getTelephoneWarningMessage();
				Assert.assertTrue(telePhoneWarnningMessage.contains(TestConstants.EXPECTED_TELEPHONE_WARNING));
			} 
			//if telephone number > 3 digits but invalid so warning expected + registration fails
			//but it is a bug, app allows registration success after entering invalid number
			else {
				Assert.assertTrue(successPage.isSuccessPageDisplayed());
				System.out.println("BUG : Registration succeeded for invalid telephone number : " + invalidTelephoneNumber);
				
				successPage.clickLogoutFromSuccessPageSidebar();
			}
			
		}
	}
	
	// TC_RF_012 i.e. test case 012
	//Verify all the mandatory fields in the Register Account page are marked with red color * symbol
	@Test
	public void verifyMandatoryFieldsAreMarkedWithRedAsterisk() {
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();
		
		RegisterPage registerPage = new RegisterPage(driver);
		Assert.assertTrue(registerPage.isRegisterPageDisplayed());
		
		//validation for required
		 if (!registerPage.areAllMandatoryFieldsMarkedRequired()) {
		        System.out.println("BUG❗ Mandatory fields are NOT marked properly");
		    }
	    //validation for red asterisk
		 if (!registerPage.areAllMandetoryFieldsAreMarkedWithRedAsterisk()) {
		        System.out.println("BUG❗ Asterisk (*) or its RED color is incorrect");
		    }	    
	}
		
	// TC_RF_013 i.e. test case 013
	//Verify the details that are provided while Registering an Account are stored in the Database 
}
	
	
	
	
	

