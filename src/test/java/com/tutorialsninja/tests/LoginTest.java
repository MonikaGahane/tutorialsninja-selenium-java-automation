package com.tutorialsninja.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tutorialsninja.base.BaseTest;
import com.tutorialsninja.pages.*;
import com.tutorialsninja.utils.*;

public class LoginTest extends BaseTest{
	
	// TC_LF_001
	// Verify logging into the Application using valid credentials
	@Test
	public void verifyUserCanLoginWithValidCredentials() {
		System.out.println(" TC_LF_001 : Verify logging into the Application using valid credentials");
		
		HomePage homePage = new HomePage(driver);
		homePage.navigateToLoginPageUsingLoginLink();
		
		LoginPage loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed");
		
		loginPage.loginUser(TestConstants.EMAIL_ADDRESS, TestConstants.PASSWORD);
		
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.isAccountPageDisplayed(), "Account Page is not displayed");
		
		accountPage.logoutUser();
		
		LogoutPage logoutPage = new LogoutPage(driver);
		Assert.assertTrue(logoutPage.isLogoutPageDisplayed(), "Logout Page is not displayed");
		logoutPage.clickOnContinueButton();
		
	}
	
	// TC_LF_002
	// Verify logging into the Application using invalid credentials (i.e. Invalid email address and Invalid Password)
	@Test
	public void verifyUserCannotLoginWithInvalidCredentials() {
		System.out.println(" TC_LF_002 : Verify logging into the Application using invalid credentials (i.e. Invalid email address and Invalid Password)");
		
		HomePage homePage = new HomePage(driver);
		homePage.navigateToLoginPageUsingLoginLink();
		
		LoginPage loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login Pgae is not displayed");
		
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
		
		HomePage homePage = new HomePage(driver);
		homePage.navigateToLoginPageUsingLoginLink();
		
		LoginPage loginPage = new LoginPage(driver);
		
		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login Page is not displayed");
		
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
		
		HomePage homePage = new HomePage(driver);
		homePage.navigateToLoginPageUsingLoginLink();
		
		LoginPage loginPage = new LoginPage(driver);
		
		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login Page is not displayed");
		
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

		HomePage homePage = new HomePage(driver);
		homePage.navigateToLoginPageUsingLoginLink();

		LoginPage loginPage = new LoginPage(driver);

		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login Page is not displayed");

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
		
		HomePage homePage = new HomePage(driver);
		homePage.navigateToLoginPageUsingLoginLink();
		
		LoginPage loginPage = new LoginPage(driver);
		
		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login Page is not displayed");
		
		loginPage.clickOnForgottenPasswordLink();
		
		ForgottenPasswordPage forgottenPasswordPage = new ForgottenPasswordPage(driver);
		Assert.assertTrue(forgottenPasswordPage.isForgottenPasswordPageDisplayed(), "Forgotten Password Page is not displayed");
		
	}
	
	// TC_LF_007
	// Verify logging into the Application using Keyboard keys (Tab and Enter)
	@Test
	public void verifyUserCanLoginUsingTabAndEnterKeys() {
		System.out.println(" TC_LF_007 : "
				+ "Verify logging into the Application using Keyboard keys (Tab and Enter)");
		
		HomePage homePage = new HomePage(driver);
		homePage.navigateToLoginPageUsingLoginLink();
		
		LoginPage loginPage = new LoginPage(driver);
		
		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login Page is not displayed");
		
		loginPage.loginUserUsingKeyboardKeys(TestConstants.EMAIL_ADDRESS, TestConstants.PASSWORD);
		
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.isAccountPageDisplayed(), "Account Page is not displayed");
		
		accountPage.logoutUser();
		
		LogoutPage logoutPage = new LogoutPage(driver);
		Assert.assertTrue(logoutPage.isLogoutPageDisplayed(), "Logout Page is not displayed");
		logoutPage.clickOnContinueButton();
	}
	
	
	
	
	
}
