package com.tutorialsninja.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    private final String base_URL = "https://tutorialsninja.com/demo/";

    @BeforeMethod
    public void setUp() {

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/snap/firefox/current/usr/lib/firefox/firefox");
        
        //the website does not trigger server validation. The form submits and Firefox tries to save the password, so the browser shows the Manage Passwords popup.
        //so if manage password is comes from the browser and not from the website then we cant automate it or we cant inspect it
        //by clicking the "manage Password" it will redirect to new tab "about:login"
        //so for avoiding this problem we have to use below 2 conditions.
        options.addPreference("signon.rememberSignons", false);
        options.addPreference("signon.autofillForms", false);

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(base_URL);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
