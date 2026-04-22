package com.tutorialsninja.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;
    private final String base_URL = "https://tutorialsninja.com/demo/";

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("firefox") String browser) {

    	if(browser.equalsIgnoreCase("firefox")) {
    		FirefoxOptions options = new FirefoxOptions();
            options.setBinary("/snap/firefox/current/usr/lib/firefox/firefox");
            
            //the website does not trigger server validation. The form submits and Firefox tries to save the password, so the browser shows the Manage Passwords popup.
            //So if "manage password" is comes from the browser and not from the website then we can't automate it or we can't inspect it
            //By clicking the "manage Password" it will redirect to new tab "about:login"
            //So for avoiding this problem we have to use below 2 conditions.
            options.addPreference("signon.rememberSignons", false);
            options.addPreference("signon.autofillForms", false);

            driver = new FirefoxDriver(options);
            
    	} else if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();

            // Disable password manager popup in Chrome
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-notifications");

            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("edge")) {

        	EdgeOptions options = new EdgeOptions();

            // Disable password save popup
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-notifications");

            driver = new EdgeDriver(options);

        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }
                
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(base_URL);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
