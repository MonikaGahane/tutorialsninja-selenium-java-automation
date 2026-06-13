package com.tutorialsninja.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.base.BaseTest;
import com.tutorialsninja.pages.AccountPage;
import com.tutorialsninja.pages.ForgottenPasswordPage;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.LoginPage;
import com.tutorialsninja.pages.LogoutPage;
import com.tutorialsninja.utils.TestConstants;
import com.tutorialsninja.utils.TestUtils;

public class LoginTest extends BaseTest{
	
	private HomePage homePage;
	private LoginPage loginPage;
	private AccountPage accountPage;
	private LogoutPage logoutPage;
	private ForgottenPasswordPage forgottenPasswordPage;
	
	@BeforeMethod
	public void initializePages() {
		homePage = new HomePage(driver);
		accountPage = new AccountPage(driver);
		loginPage = new LoginPage(driver);
		logoutPage = new LogoutPage(driver);
		forgottenPasswordPage = new ForgottenPasswordPage(driver);
	}
	@BeforeMethod
	public void navigateToLoginPage() {
		homePage.navigateToLoginPageUsingLoginLink();
		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed");
	}
	
	// TC_LF_001
	// Verify logging into the Application using valid credentials
	@Test
	public void verifyUserCanLoginWithValidCredentials() {
		System.out.println(" TC_LF_001 : Verify logging into the Application using valid credentials");
		
		loginPage.loginUser(TestConstants.EMAIL_ADDRESS, TestConstants.PASSWORD);
		
		Assert.assertTrue(accountPage.isAccountPageDisplayed(), "Account Page is not displayed");
		
		accountPage.logoutUser();
		
		Assert.assertTrue(logoutPage.isLogoutPageDisplayed(), "Logout Page is not displayed");
		logoutPage.clickOnContinueButton();
		
	}
	
	// TC_LF_002
	// Verify logging into the Application using invalid credentials (i.e. Invalid email address and Invalid Password)
	@Test
	public void verifyUserCannotLoginWithInvalidCredentials() {
		System.out.println(" TC_LF_002 : Verify logging into the Application using invalid credentials (i.e. Invalid email address and Invalid Password)");
		
		loginPage.loginUser(TestConstants.INVALID_LOGIN_EMAIL_ADDRESS, TestConstants.INVALID_LOGIN_PASSWORD);
		
		String actualInvalidLoginWarning = loginPage.getInvalidLoginWarningMessage();
		 
		Assert.assertEquals(actualInvalidLoginWarning, TestConstants.EXPECTED_INVALID_LOGIN_WARNING);
		
	}
	
	// TC_LF_003
	// Verify logging into the Application using invalid email address and valid Password
	@Test
	public void verifyUserCannotLoginwithInvalidEmailAddressAndValidPassword() {
		System.out.println(" TC_LF_003 : "
				+ "Verify logging into the Application using invalid email address and valid Password");
		
		loginPage.loginUser(TestConstants.INVALID_LOGIN_EMAIL_ADDRESS, TestConstants.PASSWORD);
		
		String actualInvalidLoginWarning = loginPage.getInvalidLoginWarningMessage();
		
		Assert.assertEquals(actualInvalidLoginWarning, TestConstants.EXPECTED_INVALID_LOGIN_WARNING);
		
	}
	
	// TC_LF_004
	// Verify logging into the Application using valid email address and invalid Password
	@Test
	public void verifyUserCannotLoginwithValidEmailAddressAndInvalidPassword() {
		System.out.println(" TC_LF_004 : "
				+ "Verify logging into the Application using valid email address and invalid Password");
		
		loginPage.loginUser(TestConstants.EXISTING_EMAIL, TestConstants.INVALID_LOGIN_PASSWORD);
		
		String actualInvalidLoginWarning = loginPage.getInvalidLoginWarningMessage();
		
		Assert.assertEquals(actualInvalidLoginWarning, TestConstants.EXPECTED_INVALID_LOGIN_WARNING);
		
	}
	
	// TC_LF_005
	// Verify logging into the Application without providing any credentials
	@Test
	public void verifyUserCannotLoginWithoutProvidingAnyCredentials() {
		System.out.println(" TC_LF_005 : " 
				+ "Verify logging into the Application without providing any credentials");

		loginPage.loginUserWithoutProvidingAnyData();

		String actualInvalidLoginWarning = loginPage.getInvalidLoginWarningMessage();

		Assert.assertEquals(actualInvalidLoginWarning, TestConstants.EXPECTED_INVALID_LOGIN_WARNING);
	}	
	
	// TC_LF_006
	// Verify 'Forgotten Password' link is available in the Login page and is working
	@Test
	public void verifyForgottenPasswordLinkIsWorkingOnLoginPage() {
		System.out.println(" TC_LF_006 : "
				+ "Verify 'Forgotten Password' link is available in the Login page and is working");
			
		loginPage.clickOnForgottenPasswordLink();
				
		Assert.assertTrue(forgottenPasswordPage.isForgottenPasswordPageDisplayed(), "Forgotten Password Page is not displayed");
		
	}
	
	// TC_LF_007
	// Verify logging into the Application using Keyboard keys (Tab and Enter)
	@Test
	public void verifyUserCanLoginUsingTabAndEnterKeys() {
		System.out.println(" TC_LF_007 : "
				+ "Verify logging into the Application using Keyboard keys (Tab and Enter)");
		
		loginPage.loginUserUsingKeyboardKeys(TestConstants.EMAIL_ADDRESS, TestConstants.PASSWORD);
		
		Assert.assertTrue(accountPage.isAccountPageDisplayed(), "Account Page is not displayed");
		
		accountPage.logoutUser();
		
		Assert.assertTrue(logoutPage.isLogoutPageDisplayed(), "Logout Page is not displayed");
		logoutPage.clickOnContinueButton();
	}
	
	// TC_LF_008
	// Verify E-Mail Address and Password text fields in the Login page have the place holder text
	@Test
	public void verifyLoginFieldsPlaceholders() {
		System.out.println(" TC_LF_008 : "
				+ "Verify E-Mail Address and Password text fields in the Login page have the place holder text");
			
		Assert.assertEquals(loginPage.getLoginEmailAddressPlaceholder(), TestConstants.EXPECTED_LOGIN_EMAIL_ADDRESS_PLACEHOLDER);
		Assert.assertEquals(loginPage.getLoginPasswordPlaceholder(), TestConstants.EXPECTED_LOGIN_PASSWORD_PLACEHOLDER);		
	}
	
	// TC_LF_009
	// Verify Logging into the Application and browsing back using Browser back button 
	@Test
	public void verifyUserRemainsLoggedInOnBackNavigation() {
		System.out.println(" TC_LF_009 : " 
				+ "Verify Logging into the Application and browsing back using Browser back button");

		loginPage.loginUser(TestConstants.EMAIL_ADDRESS, TestConstants.PASSWORD);

		Assert.assertTrue(accountPage.isAccountPageDisplayed(), "Account Page is not displayed");

		TestUtils.navigateBack(driver);

		loginPage.clickSidebarMyAccountLink();

		Assert.assertTrue(accountPage.isAccountPageDisplayed(), "Account Page is not displayed");

		accountPage.logoutUser();

		Assert.assertTrue(logoutPage.isLogoutPageDisplayed(), "Logout Page is not displayed");
		logoutPage.clickOnContinueButton();

	}
		
	// TC_LF_010
	// Verify Logging out from the Application and browsing back using Browser back button
	@Test
	public void verifyUserRemainsLoggedOutOnBackNavigation() {
		System.out.println(" TC_LF_010 : " 
				+ "Verify Logging out from the Application and browsing back using Browser back button");

		loginPage.loginUser(TestConstants.EMAIL_ADDRESS, TestConstants.PASSWORD);

		Assert.assertTrue(accountPage.isAccountPageDisplayed(), "Account Page is not displayed");
		
		accountPage.logoutUser();
		Assert.assertTrue(logoutPage.isLogoutPageDisplayed(), "Logout Page is not displayed");
		
		TestUtils.navigateBack(driver);
		
		accountPage.navigateToEditAccountInfoPage();
		
		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login Page is not displayed");

	}
	
	// TC_LF_011
	// Verify the number of unsuccessful login attempts (Valid E-mail Address and Invalid Password)
	@Test
	public void verifyLoginFailureAfterMultipleAttempts() {
		System.out.println(" TC_LF_011 : " 
				+ "Verify the number of unsuccessful login attempts");
		
		int warningMessageForInvalidLoginCount = 0;
		boolean warningMessageForMultipleInvalidLogin = false;
		int attemptCount = 0;
		
		while(!warningMessageForMultipleInvalidLogin && attemptCount < 10) {
			attemptCount++;
			homePage.navigateToLoginPageUsingLoginLink();

			Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login Page is not displayed");
			
			loginPage.loginUser(TestConstants.EMAIL_ADDRESS, TestConstants.INVALID_LOGIN_PASSWORD);
			
			if(loginPage.getInvalidLoginWarningMessage().equals(TestConstants.EXPECTED_INVALID_LOGIN_WARNING)) {
				warningMessageForInvalidLoginCount++;
				
			} else if(loginPage.getInvalidLoginWarningForMultipleAttempts().equals(TestConstants.EXPECTED_INVALID_LOGIN_WARNING_fOR_MULTIPLE_ATTEMPTS)) {
				warningMessageForMultipleInvalidLogin = true;
				break;
			}			
		}
		System.out.println("Invalid Login Warning message appeared: " + warningMessageForInvalidLoginCount + " times");
		System.out.println("Multiple Login Attempts Warning appeared at attempt number: " + attemptCount);
		
	}
	
	// TC_LF_012
	// Verify the text into the Password field is toggled to hide its visibility
	@Test
	public void verifyPasswordInputIsHiddenOnLoginPage() {
		System.out.println(" TC_LF_012 : " 
				+ "Verify the text into the Password field is toggled to hide its visibility");
			
		loginPage.enterLoginPassword();
		
		Assert.assertEquals(loginPage.getLoginPasswordFieldType(), "password", "Password field is not hidden (type should be 'password')");		
	}
	
	// TC_LF_013
	// Verify that text entered into the Password field cannot be copied (copy-paste is restricted)
	@Test
	public void verifyPasswordInputCannotBeCopied() {
		System.out.println(" TC_LF_013 : " 
				+ "Verify that text entered into the Password field cannot be copied (copy-paste is restricted)");
		
		loginPage.copyPasswordTextUsingKeboardKeys();
		
		Assert.assertNotEquals(loginPage.getEmailAddressTextValue(), loginPage.getPasswordTextValue(), "Both text from Email field and Password field are same");

		loginPage.copyPasswordTextUsingRightClick();
		
		Assert.assertNotEquals(loginPage.getEmailAddressTextValue(), loginPage.getPasswordTextValue(), "Both text from Email field and Password field are same");
		
	}
		
	
}
