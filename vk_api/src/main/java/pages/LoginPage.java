package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginPage extends Form {

    private static IElementFactory elementFactory = AqualityServices.getElementFactory();
    private static ITextBox phoneOrEmailField = elementFactory.getTextBox(
            By.xpath("//input[@class='VkIdForm__input']"), "Phone or email field");
    private static IButton signInbutton = elementFactory.getButton(
            By.xpath("//span[contains(text(),'Sign in')]"), "Sign In button");

    public LoginPage() {
        super(By.xpath("//div[@id='index_rcolumn']"), "Login form");
    }

    public void inputPhoneOrEmail(String phone){
        phoneOrEmailField.clearAndType(phone);
    }
    public void clickSignInButton(){
        signInbutton.click();
    }
}
