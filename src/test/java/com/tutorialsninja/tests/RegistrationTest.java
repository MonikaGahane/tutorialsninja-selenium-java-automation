package com.tutorialsninja.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tutorialsninja.base.BaseTest;
import com.tutorialsninja.pages.*;
import com.tutorialsninja.utils.TestConstants;
import com.tutorialsninja.utils.TestUtils;

public class RegistrationTest extends BaseTest {

	
	// TC_RF_001 i.e test case 001
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

	// TC_RF_003 i.e. test case 003
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
	@Test
	public void verifyUserCannotRegisterWithoutFillingMandatoryFields() {
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.clickContinueWithoutFillingAnything();

		Assert.assertEquals(registerPage.getFirstNameWarningMessage(), TestConstants.EXPECTED_FIRST_NAME_WARNING);
		Assert.assertEquals(registerPage.getLastNameWarningMessage(), TestConstants.EXPECTED_LAST_NAME_WARNING);
		Assert.assertEquals(registerPage.getEmailWarningMessage(), TestConstants.EXPECTED_EMAIL_WARNING);
		Assert.assertEquals(registerPage.getTelephoneWarningMessage(), TestConstants.EXPECTED_TELEPHONE_WARNING);
		Assert.assertEquals(registerPage.getPasswordWarningMessage(), TestConstants.EXPECTED_PASSWORD_WARNING);
		Assert.assertEquals(registerPage.getPrivacyPolicyWarningMessage(), TestConstants.EXPECTED_PRIVACY_POLICY_WARNING);

	}

	// TC_RF_005 i.e. test case 005
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
		
		//Way 3 = Navigate using LoginPage sidebar Register option
		homePage.navigateToRegisterPageUsingLoginLink();
		Assert.assertTrue(loginPage.isLoginPageDisplayed());
		loginPage.clickRegisterLinkFromLoginSidebar();
		Assert.assertTrue(registerPage.isRegisterPageDisplayed());
				
	}

	
	// TC_RF_008 i.e. test case 008
	@Test
	public void verifyRegistrationFailsWhenPasswordsDoNotMatch() {
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegisterPageUsingRegisterLink();
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.registerUser(TestConstants.FIRST_NAME, TestConstants.LAST_NAME, TestUtils.generateNewEmail(), TestConstants.TELEPHONE, TestConstants.PASSWORD, "678u90");
		
		Assert.assertEquals(registerPage.getConfirmPasswordWarningMessage(), TestConstants.EXPECTED_CONFIRM_PASSWORD_WARNING);
		
		
	}


}
