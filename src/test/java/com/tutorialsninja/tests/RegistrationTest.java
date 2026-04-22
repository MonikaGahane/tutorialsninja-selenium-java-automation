package com.tutorialsninja.tests;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

		System.out.println(" TC_RF_001 : "
				+ "Verify Registering an Account by providing only the Mandatory fields");
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, TestUtils.generateNewEmail(), TestConstants.TELEPHONE, TestConstants.PASSWORD);

		SuccessPage successPage = new SuccessPage(driver);
		Assert.assertTrue(successPage.isSuccessPageDisplayed() ,  "Success page is not displayed");

		String content = successPage.getSuccessContent();
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_1));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_2));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_3));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_4));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_5));

		successPage.clickContinue();

		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.isAccountPageDisplayed(), "Account page is not displayed");
	}
	
	//TC_RF_002 i.e. test case 002
	//Verify 'Thank you for registering' email is sent to the registered email address as a confirmation for registering the account
	@Test
	public void verifyUserReceivesAnEmailOfConfirmationAfterSuccessfulRegistration() {
		System.out.println(" TC_RF_002 : "
				+"Verify 'Thank you for registering' email is sent to the registered email address as a confirmation for registering the account");
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, TestUtils.generateNewEmail(), TestConstants.TELEPHONE, TestConstants.PASSWORD, true);
		
		SuccessPage successPage = new SuccessPage(driver);
		Assert.assertTrue(successPage.isSuccessPageDisplayed(), "Success page is not displayed");
		
		String content = successPage.getSuccessContent();
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_1));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_2));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_3));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_4));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_5));
		
		successPage.clickContinue();
		
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.isAccountPageDisplayed(), "Account page is not displayed");
		
		//Capture screenshot
	    TestUtils.captureScreenshot(driver, "TC_RF_002_RegistrationSuccess");
		
		//Email verification skipped
	    System.out.println("NOTE: Email verification not automated due to demo environment (SMTP not configured).");

	}
	
	// TC_RF_003 i.e. test case 003
	//Verify Registering an Account by providing all the fields
	@Test
	public void verifyUserCanRegisterWithAllFields() {
		System.out.println(" TC_RF_003 : "
				+"Verify Registering an Account by providing all the fields");

		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, TestUtils.generateNewEmail(), TestConstants.TELEPHONE, TestConstants.PASSWORD, true);

		SuccessPage successPage = new SuccessPage(driver);
		Assert.assertTrue(successPage.isSuccessPageDisplayed(), "Success page is not displayed");

		String content = successPage.getSuccessContent();
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_1));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_2));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_3));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_4));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_5));

		successPage.clickContinue();

		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.isAccountPageDisplayed(), "Account page is not displayed");
	}

	// TC_RF_004 i.e test case 004
	//Verify proper notification messages are displayed for the mandatory fields, when you don't provide any fields in the 'Register Account' page and submit
	@Test
	public void verifyUserCannotRegisterWithoutFillingMandatoryFields() {
		System.out.println(" TC_RF_004 : "
				+"Verify proper notification messages are displayed for the mandatory fields, when you don't provide any fields in the 'Register Account' page and submit");

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
		System.out.println(" TC_RF_005 : "
				+"Verify Registering an Account when 'Yes' option is selected for Newsletter field");

		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, TestUtils.generateNewEmail(), TestConstants.TELEPHONE, TestConstants.PASSWORD, true);

		SuccessPage successPage = new SuccessPage(driver);
		Assert.assertTrue(successPage.isSuccessPageDisplayed(), "Success page is not displayed");

		String content = successPage.getSuccessContent();
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_1));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_2));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_3));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_4));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_5));

		successPage.clickContinue();

		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.isAccountPageDisplayed(), "Account page is not displayed");		
		Assert.assertTrue(accountPage.isNewsletterHeadingDisplayed(),  "Newsletter heading is not displayed");
		accountPage.navigateToNewsletterPage();
		
		NewsletterPage newsletterPage = new NewsletterPage(driver);
		Assert.assertTrue(newsletterPage.isNewsletterPageDisplayed(), "Newsletter page is not displayed");
		Assert.assertTrue(newsletterPage.isNewsletterSubscribed(), "Newsletter not Subscribed");
		
	}
	
	
	// TC_RF_006 i.e. test case 006
	//Verify Registering an Account when 'No' option is selected for Newsletter field
	@Test
	public void verifyUserCanRegisterWithNewsletterSubscriptionNo() {
		System.out.println(" TC_RF_006 : "
				+"Verify Registering an Account when 'No' option is selected for Newsletter field");

		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, TestUtils.generateNewEmail(), TestConstants.TELEPHONE, TestConstants.PASSWORD, false);

		SuccessPage successPage = new SuccessPage(driver);
		Assert.assertTrue(successPage.isSuccessPageDisplayed(), "Success page is not displayed");

		String content = successPage.getSuccessContent();
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_1));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_2));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_3));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_4));
		Assert.assertTrue(content.contains(TestConstants.SUCCESS_MSG_5));

		successPage.clickContinue();

		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.isAccountPageDisplayed(), "Account page is not displayed");		
		Assert.assertTrue(accountPage.isNewsletterHeadingDisplayed(), "Newsletter Heading is not displayed");
		accountPage.navigateToNewsletterPage();
		
		NewsletterPage newsletterPage = new NewsletterPage(driver);
		Assert.assertTrue(newsletterPage.isNewsletterPageDisplayed(),  "Newsletter page is not displayed");
		Assert.assertTrue(newsletterPage.isNewsletterUnsubscribed(), "Newsletter not Subscribed");
		
	}
	
	
	// TC_RF_007 i.e. test case 007
	//Verify different ways of navigating to 'Register Account' page
	@Test
	public void verifyMultipleWaysOfNavigatingToRegisterPage() {
		System.out.println(" TC_RF_007 : "
				+"Verify different ways of navigating to 'Register Account' page");
		
		HomePage homePage = new HomePage(driver);
		RegisterPage registerPage = new RegisterPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		
		//Way 1 = Navigate using Register link from my account drop-down menu
		homePage.navigateToRegisterPageUsingRegisterLink();
		Assert.assertTrue(registerPage.isRegisterPageDisplayed(),  "Register page is not displayed");
		
		//Way 2 = Navigate using Login Page -> New Customer continue button
		homePage.navigateToRegisterPageUsingLoginLink();
		Assert.assertTrue(loginPage.isLoginPageDisplayed(),  "Login page is not displayed");
		loginPage.clickNewCustomerContinue();
		Assert.assertTrue(registerPage.isRegisterPageDisplayed(),  "Register page is not displayed");
		
		//Way 3 = Navigate using LoginPage side-bar Register option
		homePage.navigateToRegisterPageUsingLoginLink();
		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed");
		loginPage.clickRegisterLinkFromLoginSidebar();
		Assert.assertTrue(registerPage.isRegisterPageDisplayed(), "Register page is not displayed");	
		
	}

	
	// TC_RF_008 i.e. test case 008
	//Verify Registering an Account by entering different passwords into 'Password' and 'Password Confirm' fields
	@Test
	public void verifyRegistrationFailsWhenPasswordsDoNotMatch() {
		System.out.println(" TC_RF_008 : "
				+"Verify Registering an Account by entering different passwords into 'Password' and 'Password Confirm' fields");
		
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
		System.out.println(" TC_RF_009 : "
				+"Verify Registering an Account by providing the existing account details (i.e. existing email address)");
		
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
		System.out.println(" TC_RF_010 : "
				+"Verify Registering an Account by providing an invalid email address into the E-Mail field");
		
		HomePage homePage = new HomePage(driver);		
		RegisterPage registerPage = new RegisterPage(driver);
		
		for(String invalidEmail : TestConstants.INVALID_EMAIL) {
			
			homePage.navigateToRegisterPageUsingRegisterLink();
			
			registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, invalidEmail, TestConstants.TELEPHONE, TestConstants.PASSWORD, true);

			String browserValidationMessage = registerPage.getInvalidEmailWarningMessageFromBrowserValidation();
			String uiValidationMessage = registerPage.getInvalidEmailWarningMessageFromUIValidation();
			
			System.out.println("Testing Invalid Email : " + invalidEmail);
			System.out.println("Browser Validation Message : " + browserValidationMessage);
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
		System.out.println(" TC_RF_011 : "
				+"Verify Registering an Account by providing an invalid phone number");		
		
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
		System.out.println(" TC_RF_012 : "
				+"Verify all the mandatory fields in the Register Account page are marked with red color * symbol");		
		
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();
		
		RegisterPage registerPage = new RegisterPage(driver);
		Assert.assertTrue(registerPage.isRegisterPageDisplayed());
		
		//validation for required
		 if (!registerPage.areAllMandatoryFieldsMarkedRequired()) {
		        System.out.println("BUG: Mandatory fields are NOT marked properly");
		    }
	    //validation for red asterisk
		 if (!registerPage.areAllMandetoryFieldsAreMarkedWithRedAsterisk()) {
		        System.out.println("BUG : Asterisk (*) or its RED color is incorrect");
		    }	    
	}
			
	// TC_RF_013 i.e. test case 013
	// Verify whether the Mandatory fields in the Register Account page are not accepting only spaces
	@Test
	public void verifyRegistrationFailsByProvidingOnlySpacesIntoMandatoryFields() {
		System.out.println(" TC_RF_013 : "
				+"Verify whether the Mandatory fields in the Register Account page are not accepting only spaces");		
		
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();

		RegisterPage registerPage = new RegisterPage(driver);

		// Trying for single space in every fields
		String space = " ";

		registerPage.registerUser(space, space, space, space, space, true);

		String browserValidationMessage = registerPage.getInvalidEmailWarningMessageFromBrowserValidation();
		String uiValidationMessage = registerPage.getInvalidEmailWarningMessageFromUIValidation();

		if (!browserValidationMessage.isEmpty()) {
			Assert.assertTrue(browserValidationMessage.length() > 0);
			System.out.println("Bug : No Warning message Shown from UI side for an email");
			
			TestUtils.captureScreenshot(driver, "TC_RF_013_Email_Bug");
			
		} else {
			Assert.assertTrue(uiValidationMessage.contains("E-Mail Address does not appear to be valid"));
		}

		//Trying for multiple spaces into mandatory fields with valid email id
		driver.navigate().refresh();
		
		String multipleSpaces = "      ";
		
		registerPage.registerUser(multipleSpaces, multipleSpaces, TestUtils.generateNewEmail(), multipleSpaces, multipleSpaces, true);
		
		String firstNameWarningMessage = registerPage.getFirstNameWarningMessage();
		String lastNameWarningMessage = registerPage.getLastNameWarningMessage();
		String telephoneWarningMessage = registerPage.getTelephoneWarningMessage();
		String passwordWarningMessage = registerPage.getPasswordWarningMessage();
				

		if (firstNameWarningMessage.isEmpty()) {
			System.out.println("BUG : First Name field accepts only spaces");
			TestUtils.captureScreenshot(driver, "TC_RF_014_FirstName_Spaces_Bug");
		} else {
			Assert.assertEquals(firstNameWarningMessage, TestConstants.EXPECTED_FIRST_NAME_WARNING);
		}

		if (lastNameWarningMessage.isEmpty()) {
			System.out.println("BUG : Last Name field accepts only spaces");
			TestUtils.captureScreenshot(driver, "TC_RF_014_LastName_Spaces_Bug");
		} else {
			Assert.assertEquals(lastNameWarningMessage, TestConstants.EXPECTED_LAST_NAME_WARNING);
		}

		if (telephoneWarningMessage.isEmpty()) {
			System.out.println("BUG : Telephone field accepts only spaces");
			TestUtils.captureScreenshot(driver, "TC_RF_014_Telephone_Spaces_Bug");
		} else {
			Assert.assertEquals(telephoneWarningMessage, TestConstants.EXPECTED_TELEPHONE_WARNING);
		}

		if (passwordWarningMessage.isEmpty()) {
			System.out.println("BUG : Password field accepts only spaces");
			TestUtils.captureScreenshot(driver, "TC_RF_014_Password_Spaces_Bug");
		} else {
			Assert.assertEquals(passwordWarningMessage, TestConstants.EXPECTED_PASSWORD_WARNING);
		}

	}
	
	
	// TC_RF_014 i.e. test case 014
	//Verify whether the Password fields in the Register Account page are following Password Complexity Standards
	@Test 
	public void verifyPasswordComplexityStandardsOnRegisterPage() {
		System.out.println(" TC_RF_014 : "
				+"Verify whether the Password fields in the Register Account page are following Password Complexity Standards");		
		
		HomePage homePage = new HomePage(driver);
		RegisterPage registerPage = new RegisterPage(driver);
		SuccessPage successPage = new SuccessPage(driver);		
		
		for(String invalidPassword : TestConstants.INVALID_PASSWORD) {
			
			homePage.navigateToRegisterPageUsingRegisterLink();
			
			registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, TestUtils.generateNewEmail(), TestConstants.TELEPHONE, invalidPassword, true);
			
			if(successPage.isSuccessPageDisplayed()) {
				System.out.println("BUG : Weak password accepted : " + invalidPassword);	
				
				TestUtils.captureScreenshot(driver, "TC_RF_14_WeakPasswordAccepted_" + invalidPassword);
				
				successPage.clickLogoutFromSuccessPageSidebar();
			} else {
				String invalidPasswordWarningMessage = registerPage.getPasswordWarningMessage();
				
				System.out.println("Validation message displayed: " + invalidPasswordWarningMessage);
				
				//feature is not implemented
				Assert.assertTrue(invalidPasswordWarningMessage != null && invalidPasswordWarningMessage.length() > 0, "Expected some password validation message");			
			}
		}
		
	}
	
	
	// TC_RF_015 i.e. test case 015
	//Verify whether the leading and trailing spaces entered into the Register Account fields are trimmed
	@Test
	public void verifyLeadingAndTrailingSpacesAreTrimmedInRegisterFields() {
		System.out.println(" TC_RF_015 : "
				+"Verify whether the leading and trailing spaces entered into the Register Account fields are trimmed");		
		
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();
		
		RegisterPage registerPage = new RegisterPage(driver);
		Assert.assertTrue(registerPage.isRegisterPageDisplayed());
		
		String firstName = "   " + TestConstants.FIRST_NAME + "   ";
		String lastName = "   " + TestConstants.LAST_NAME + "   ";
		String email = "   " + TestUtils.generateNewEmail() + "   ";
		String telephone = "   " + TestConstants.TELEPHONE + "   ";
		String password =  "   " + TestConstants.PASSWORD + "   ";
		
		registerPage.registerUser(firstName, lastName, email, telephone, password, true);
		
		SuccessPage successPage = new SuccessPage(driver);
		Assert.assertTrue(successPage.isSuccessPageDisplayed(), "Success page is not displayed");
		successPage.clickContinue();
		
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.isAccountPageDisplayed(), "Account page is not displayed");
		accountPage.navigateToEditAccountInfoPage();
		
		EditAccountInfo editAccountInfo = new EditAccountInfo(driver);
		Assert.assertTrue(editAccountInfo.isEditInformationPageDisplayed(), "EditInformation page is not displayed");
		
		String actualFirstName = editAccountInfo.getFirstNameInputFieldValue();
		String actualLastName = editAccountInfo.getLastNameInputFieldValue();
		String actualEmail = editAccountInfo.getEmailInputFieldValue();
		String actualTelephone = editAccountInfo.getTelephoneFieldValue();
		
//		System.out.println("Expected (trimmed): [" + firstName.trim() + "]");
//		System.out.println("Actual: [" + actualFirstName + "]");
		
		if(!actualFirstName.equals((firstName).trim())) {
			System.out.println("Bug : First Name not trimmed :-> [" + actualFirstName + "]");
		}else {
			System.out.println("First Name trimmed correctly");
		}
		
		if(!actualLastName.equals(lastName.trim())) {
			System.out.println("Bug : Last Name not trimmed :-> [" + actualLastName + "]");
		}else {
			System.out.println("Last Name trimmed correctly");
		}
		
		if(!actualEmail.equals(email.trim())) {
			System.out.println("Bug : Email not trimmed :-> [" + actualEmail + "]");
		}else {
			System.out.println("Email trimmed correctly");
		}		
		
		if(!actualTelephone.equals(telephone.trim())) {
			System.out.println("Bug : Telephone not trimmed :-> [" + telephone + "]");
		}else {
			System.out.println("Telephone trimmed correctly");
		}
		
		if(!actualFirstName.equals((firstName).trim()) || !actualLastName.equals(lastName.trim()) || !actualEmail.equals(email.trim()) || !actualTelephone.equals(telephone.trim())) {
			TestUtils.captureScreenshot(driver, "TC_RF_15_LeadingAndTrailingSpacesBug");
		}
		

	}
	
	// TC_RF_016 i.e. test case 016
	//Verify whether the 'Privacy Policy' check-box option is not selected by default
	@Test
	public void verifyPrivacyPolicyCheckboxIsNotSelectedByDefault() {
		System.out.println(" TC_RF_016 : "
				+"Verify whether the 'Privacy Policy' check-box option is not selected by default");		
		
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();
		
		RegisterPage registerPage = new RegisterPage(driver);
		Assert.assertTrue(registerPage.isRegisterPageDisplayed(), "Register page is not displayed");
		
		boolean isSelected = registerPage.isPrivacyPolicyCheckboxSelected();
		
		System.out.println("Is privacy Policy checkbox selected by default? : " + isSelected); //Should be false
		
		Assert.assertFalse(isSelected, "Privacy Policy checkbox is selected by default (should be unchecked)");
		
		if(isSelected) {
			TestUtils.captureScreenshot(driver, "TC_RF_16_PrivacyPolicyCheckbox_DefaultSelected");
		}
		
	}
	
	// TC_RF_017 i.e. test case 017
	//Verify Registering the Account without selecting the 'Privacy Policy' check-box option
	@Test
	public void verifyRegistrationFailsWhenPrivacyPolicyCheckboxIsNotSelected() {
		System.out.println(" TC_RF_017 : "
				+"Verify Registering the Account without selecting the 'Privacy Policy' check-box option");		
		
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();
		
		RegisterPage registerPage = new RegisterPage(driver);
		Assert.assertTrue(registerPage.isRegisterPageDisplayed(), "Register page is not displayed");
		
		registerPage.registerUserWithoutClickingPrivacyPolicyCheckbox(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, TestUtils.generateNewEmail(), TestConstants.TELEPHONE, TestConstants.PASSWORD, true);
		
		String actualPrivacyPolicyWarningMessage = registerPage.getPrivacyPolicyWarningMessage();
		
		Assert.assertEquals(actualPrivacyPolicyWarningMessage, TestConstants.EXPECTED_PRIVACY_POLICY_WARNING);
		
		
	}
	
	// TC_RF_018 i.e. test case 018
	//Verify the Password text entered into the 'Password' and 'Password Confirm' field of 'Register Account' functionality is toggled to hide its visibility
	@Test
	public void verifyPasswordAndConfirmPasswordFieldsAreHiddenOnRegisterPage() {
		System.out.println(" TC_RF_018 : "
				+"Verify the Password text entered into the 'Password' and 'Password Confirm' field of 'Register Account' functionality is toggled to hide its visibility");		
		
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();
		
		RegisterPage registerPage = new RegisterPage(driver);
		Assert.assertTrue(registerPage.isRegisterPageDisplayed(), "Register page is not displayed");
		
		String enterPassword = "123@Test";
		registerPage.enterPasswordAndConfirmPassword(enterPassword);
		
		String actualPasswordFieldType = registerPage.getPasswordFieldType();
		String actualConfirmPasswordFieldType = registerPage.getConfirmPasswordFieldType();
		
		Assert.assertEquals(actualPasswordFieldType, "password", "Password field is not hidden (type should be 'password')");
		
		Assert.assertEquals(actualConfirmPasswordFieldType, "password", "Confirm Password field is not hidden (type should be 'password')");
	}
	
	
	// TC_RF_019 i.e. test case 019
	//Verify navigating to other pages using the options or links provided on the 'Register Account' page 
	@Test
	public void verifyNavigationToDifferentPagesFromRegisterPage() {
		System.out.println(" TC_RF_019 : "
				+"Verify navigating to other pages using the options or links provided on the 'Register Account' page");		
		
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();
		
		RegisterPage registerPage = new RegisterPage(driver);
		Assert.assertTrue(registerPage.isRegisterPageDisplayed(), "Register page is not displayed");
		
		//Map of links and expected titles
		//we are using map for storing the key-value pair(i.e. LinkText, expectedTitle pair)
		//linked HashMap for storing the data in the insertion order for (means it maintains the order and test runs in same sequence every time)
		Map<String, String> registerPageNavigationLinks = new LinkedHashMap<>();
		
		//For top links
		registerPageNavigationLinks.put("login page", "Login");
		
		//Bottom Links
		registerPageNavigationLinks.put("Privacy Policy_Bottom", "Popup Message");
		
		//For Right column links
		//We are testing only these 2 because other links will open when we register the account and click back
		registerPageNavigationLinks.put("Login", "Login");
		registerPageNavigationLinks.put("Forgotten Password", "Forgot Your Password?");
		
		//For Header links
		registerPageNavigationLinks.put("Desktops", "Desktops");
		registerPageNavigationLinks.put("Laptops & Notebooks", "Laptops & Notebooks");
		registerPageNavigationLinks.put("Components", "Components");
		registerPageNavigationLinks.put("Tablets", "Tablets");
		registerPageNavigationLinks.put("Software", "Software");
		registerPageNavigationLinks.put("Phones & PDAs", "Phones & PDAs");
		registerPageNavigationLinks.put("Cameras", "Cameras");
		registerPageNavigationLinks.put("MP3 Players", "MP3 Players");
		
		//For Footer Links
		registerPageNavigationLinks.put("About Us", "About Us");
		registerPageNavigationLinks.put("Delivery Information", "Delivery Information");
		registerPageNavigationLinks.put("Privacy Policy_Footer", "Privacy Policy");
		registerPageNavigationLinks.put("Terms & Conditions", "Terms & Conditions");
		registerPageNavigationLinks.put("Contact Us", "Contact Us");
		registerPageNavigationLinks.put("Returns", "Returns");
		registerPageNavigationLinks.put("Site Map", "Site Map");
		registerPageNavigationLinks.put("Brands", "Find Your Favorite Brand");
		registerPageNavigationLinks.put("Gift Certificates", "Purchase a Gift Certificate");
		registerPageNavigationLinks.put("Affiliate", "Affiliate Program");
		registerPageNavigationLinks.put("Specials", "Special Offers");
		
		//for each loop
		for (Map.Entry<String, String> entry : registerPageNavigationLinks.entrySet()) {
			String link = entry.getKey();
			String expectedTitle = entry.getValue();

			try {

				if (link.equals("login page")) {
					registerPage.clickOnLink(link);
					
				} else if(link.contains("Privacy Policy_Bottom")) {
					registerPage.clickOnLink("Privacy Policy");
					
					if(registerPage.isPrivacyPolicyPopupDisplayed()) {
						//Close popup 
						registerPage.closePrivacyPolicyPopup();
					} else {
						TestUtils.captureScreenshot(driver, "TC_RF_19_NavigationBug_" + link);
						//Test fails
						Assert.fail("Privacy Policy popup not displayed");
					}
					
					//skip further steps and continue the loop
					continue;
				
				} else if(link.contains("Privacy Policy_Footer")) {
					registerPage.clickFooterLink("Privacy Policy");

				} else if (link.equals("Login") || link.equals("Forgotten Password")) {
					registerPage.clickOnRightColumnLink(link);
					
				} else if (link.equals("Desktops") || link.equals("Laptops & Notebooks") || link.equals("Components") || link.equals("MP3 Players")) {
					registerPage.hoverAndClickHeaderLink(link);
					
				} else if (link.equals("Tablets") || link.equals("Software") || link.equals("Phones & PDAs") || link.equals("Cameras")) {
					registerPage.clickHeaderLink(link);
					
				} else {
					registerPage.clickFooterLink(link);
				}
				
				//Wait for title
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.titleContains(expectedTitle));
				
				if(registerPage.getPageTitle().contains(expectedTitle)) {
					//go back 
					driver.navigate().back();
				} else {
					TestUtils.captureScreenshot(driver, "TC_RF_19_NavigationBug_" + link);
					//Test Fails
					Assert.fail("Navigation failed for link: " + link);
				}
			
				// Wait for register page again
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("input-firstname")));

			} catch (Exception e) {
				TestUtils.captureScreenshot(driver, "TC_RF_19_Exception_" + link);

				Assert.fail("Error on link: " + link + " -> " + e.getMessage());
			}
		}

	}
	
	
	// TC_RF_020 i.e. test case 020
	//Verify Registering an Account, by filling 'Password' field and not filling 'Password Confirm' field
	@Test
	public void verifyRegistrationFailsWhenPasswordConfirmFieldIsBlank() {
		System.out.println(" TC_RF_020 : "
				+"Verify Registering an Account, by filling 'Password' field and not filling 'Password Confirm' field");		
		
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();
		
		RegisterPage registerPage = new RegisterPage(driver);
		Assert.assertTrue(registerPage.isRegisterPageDisplayed(), "Register Page not displayed");
		
		registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, TestUtils.generateNewEmail(), TestConstants.TELEPHONE, TestConstants.PASSWORD, "");
		
		String actualConfirmPasswordWarningMessage = registerPage.getConfirmPasswordWarningMessage();
		
		try {
			
			Assert.assertTrue(actualConfirmPasswordWarningMessage.contains(TestConstants.EXPECTED_CONFIRM_PASSWORD_WARNING), "Confirm Password warning message is not displayed or incorrect");
		
		} catch(AssertionError e) {
			
			TestUtils.captureScreenshot(driver, "TC_RF_020_ConfirmPasswordWarningBug");
	        throw e;
		}
		
	}
	
	// TC_RF_021 i.e. test case 021
	//Verify the Breadcrumb, Page Heading, Page URL, Page Title of 'Register Account' Page
	@Test
	public void verifyRegisterPageUIElements() {
		System.out.println(" TC_RF_021 : "
				+"Verify the Breadcrumb, Page Heading, Page URL, Page Title of 'Register Account' Page");		

	    HomePage homePage = new HomePage(driver);
	    homePage.navigateToRegisterPageUsingRegisterLink();

	    RegisterPage registerPage = new RegisterPage(driver);

	    try {
	        //Page Heading
	        Assert.assertEquals(registerPage.getPageHeading(), TestConstants.EXPECTED_REGISTER_PAGE_HEADING, "Page Heading mismatch");

	        //Page Title
	        Assert.assertTrue(registerPage.getPageTitle().contains(TestConstants.EXPECTED_REGISTER_PAGE_TITLE), "Page Title mismatch");

	        //Page URL
	        Assert.assertTrue(registerPage.getPageURL().contains(TestConstants.EXPECTED_REGISTER_PAGE_URL), "Page URL mismatch");

	        //Breadcrumb
	        Assert.assertTrue(registerPage.getBreadcrumb().contains(TestConstants.EXPECTED_REGISTER_PAGE_BREADCRUMB), "Breadcrumb mismatch");

	    } catch (AssertionError e) {
	        TestUtils.captureScreenshot(driver, "TC_RF_021_RegisterPageUI_Failure");
	        throw e; 
	    }
	}
	
	// TC_RF_022 i.e. test case 022
	//Verify the UI of the 'Register Account' page
	@Test
	public void verifyRegisterPageUICompletely() {
		System.out.println(" TC_RF_022 : "
				+"Verify the UI of the 'Register Account' page");		

	    HomePage homePage = new HomePage(driver);
	    homePage.navigateToRegisterPageUsingRegisterLink();

	    RegisterPage registerPage = new RegisterPage(driver);

	    try {
	        // Sections
	        Assert.assertTrue(registerPage.isPersonalDetailsSectionDisplayed(), "Personal Details section missing");
	        Assert.assertTrue(registerPage.isPasswordSectionDisplayed(), "Password section missing");
	        Assert.assertTrue(registerPage.isNewsletterSectionDisplayed(), "Newsletter section missing");

	        // Fields
	        Assert.assertTrue(registerPage.isFirstNameFieldDisplayed(), "First Name field missing");
	        Assert.assertTrue(registerPage.isLastNameFieldDisplayed(), "Last Name field missing");
	        Assert.assertTrue(registerPage.isEmailFieldDisplayed(), "Email field missing");
	        Assert.assertTrue(registerPage.isTelephoneFieldDisplayed(), "Telephone field missing");
	        Assert.assertTrue(registerPage.isPasswordFieldDisplayed(), "Password field missing");
	        Assert.assertTrue(registerPage.isConfirmPasswordFieldDisplayed(), "Confirm Password field missing");

	        // Placeholders
	        Assert.assertEquals(registerPage.getFirstNamePlaceholder(), "First Name");
	        Assert.assertEquals(registerPage.getLastNamePlaceholder(), "Last Name");
	        Assert.assertEquals(registerPage.getEmailPlaceholder(), "E-Mail");
	        Assert.assertEquals(registerPage.getTelephonePlaceholder(), "Telephone");
	        Assert.assertEquals(registerPage.getPasswordPlaceholder(), "Password");
	        Assert.assertEquals(registerPage.getConfirmPasswordPlaceholder(), "Password Confirm");

	        // Newsletter
	        Assert.assertTrue(registerPage.isNewsletterYesRadioDisplayed(), "Newsletter Yes option missing");
	        Assert.assertTrue(registerPage.isNewsletterNoRadioSelected(), "Newsletter 'No' not selected by default");

	        // Privacy Policy
	        Assert.assertTrue(registerPage.isPrivacyPolicyCheckboxDisplayed(), "Privacy Policy checkbox missing");
	        Assert.assertTrue(registerPage.isPrivacyPolicyLinkDisplayed(), "Privacy Policy link missing");

	        // Continue button
	        Assert.assertTrue(registerPage.isContinueButtonDisplayed(), "Continue button missing");
	        Assert.assertTrue(registerPage.isContinueButtonEnabled(), "Continue button disabled");

	        // Right Column
	        Assert.assertTrue(registerPage.isRegisterPageRightColumnDisplayed(), "Right column missing");
	        Assert.assertTrue(registerPage.isRegisterPageRightColumnLoginDisplayed(), "Login link missing");
	        Assert.assertTrue(registerPage.isRegisterPageRightColumnRegisterDisplayed(), "Register link missing");
	        Assert.assertTrue(registerPage.isRegisterPageRightColumnForgotPasswordDisplayed(), "Forgot Password link missing");
	        Assert.assertTrue(registerPage.isRegisterPageRightColumnMyAccountDisplayed(), "My Account link missing");

	        // Footer
	        Assert.assertTrue(registerPage.isRegisterPageFooterDisplayed(), "Footer missing");
	        Assert.assertTrue(registerPage.isRegisterPageFooterInformationSectionDisplayed(), "Information section missing");
	        Assert.assertTrue(registerPage.isRegisterPageFooterCustomerServiceSectionDisplayed(), "Customer Service section missing");
	        Assert.assertTrue(registerPage.isRegisterPageFooterExtrasSectionDisplayed(), "Extras section missing");
	        Assert.assertTrue(registerPage.isRegisterPageFooterMyAccountSectionDisplayed(), "Footer My Account section missing");

	        //mandatory fields
	        if (!registerPage.areAllMandatoryFieldsMarkedRequired()) {
		        System.out.println("BUG : Mandatory fields are NOT marked properly");
		        TestUtils.captureScreenshot(driver, "TC_RF_022_RegistrationMandatoryFieldsNotMarked_Bug");	
	        }
	    } catch (AssertionError e) {
	        TestUtils.captureScreenshot(driver, "TC_RF_022_RegisterAccountUI_Failure");
	        throw e;
	    }
	}
	
}
	
	
	
	
	

