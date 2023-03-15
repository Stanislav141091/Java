package testCases;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import aquality.selenium.browser.Browser;

public class BaseTest {

    @BeforeMethod
    public void getBrowser(){
        AqualityServices.getBrowser();
    }

    @AfterMethod
    public void closeBrowser(){
        Browser browser = AqualityServices.getBrowser();
        browser.quit();
    }
}