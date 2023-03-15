package browserUtils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import utils.GetSettings;

public class BrowserUtils {

    public void goToSite(){
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(GetSettings.settings("remoteConnectionUrl"));
    }
}
