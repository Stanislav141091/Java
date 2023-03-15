package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PasswordPage extends Form {

    private static IElementFactory elementFactory = AqualityServices.getElementFactory();
    private static ITextBox passwordField = elementFactory.getTextBox(
            By.xpath("//div[@class='vkc__Password__Wrapper']//input[contains(@class,'TextField')]"), "Input password");
    private static IButton continueButton = elementFactory.getButton(
            By.xpath("//span[@class='vkuiButton__in']"), "Continue button");

    public PasswordPage() {
        super(By.xpath("//div[@class='vkc__AuthRoot__contentIn']"), "Password form");
    }

    public void inputPassword(String password){
        passwordField.clearAndType(password);
    }
    public void clickContinueButton(){
        continueButton.click();
    }
}
