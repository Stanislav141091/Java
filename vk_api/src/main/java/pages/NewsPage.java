package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NewsPage extends Form {

    private static IElementFactory elementFactory = AqualityServices.getElementFactory();
    private static IButton personalPage = elementFactory.getButton(
            By.xpath("//li[@id='l_pr']//span[@class='LeftMenu__itemLabel']"), "Personal page button");

    public NewsPage() {
        super(By.xpath("//div[@class='narrow_column_wrap']"), "News settings");
    }

    public void clickPersonalPage(){
        personalPage.click();
    }
}