package com.tutorialsninja.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.tutorialsninja.base.BaseTest;

public class TestUtils implements ITestListener{
	
	//for generating new email every-time while registration
	public static String generateNewEmail() {
		return new Date().toString().replaceAll(" ", "").replaceAll(":", "") + "@gmail.com";
	}
	
	//For capturing Screenshots
	public static void captureScreenshot(WebDriver driver, String fileName) {
		try {
	        File folder = new File("screenshots");  
	        if (!folder.exists()) {
	            folder.mkdir();
	        }
	        
	        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        
	        //Take screenshot of current browser and Store it as a temporary file	        
	        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        
	        //Creating destination path
	        File destination = new File("Screenshots/" + fileName + "_" + timeStamp + ".png");
	        
	        //Copy screenshot from temporary location → your folder
	        //This is the line that actually saves the screenshot
	        FileUtils.copyFile(source, destination);
	        
		} catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	//onTestFailure method is defined in ITestListener interface
	@Override
	public void onTestFailure(ITestResult result) {
		Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        String testName = result.getName();
        captureScreenshot(driver, testName);

        System.out.println("Screenshot captured for failed test: " + testName);
    
	}
	
	public static void pressTabKey(WebDriver driver, int count) {
		Actions actions = new Actions(driver);
		for (int i = 1; i <= count; i++) {
			actions.sendKeys(Keys.TAB).perform();
		}
	}
	
	public static void navigateBack(WebDriver driver) {
		driver.navigate().back();
	}
	
	//for getting the HTML code of the page
	public static String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	
}

