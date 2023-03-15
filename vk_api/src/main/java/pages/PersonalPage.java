package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import testData.TestData;

import java.util.List;

public class PersonalPage extends Form {

    private static IElementFactory elementFactory = AqualityServices.getElementFactory();
    private static String postAuthor = "//a[@class='author']";
    private static String postText = "//div[contains(@class,'wall_post_text')]";
    private static IElement post = elementFactory.getTextBox(
            By.xpath("//div[@id='page_wall_posts']//div[1]"),"Last post");
    private static String commentAuthor = "//div[@class='reply_content']//div[@class='reply_author']//a[@class='author']";
        private static String comment = "//div[@class='reply_content']//div[@class='wall_reply_text']";
    private static IElement likeButton = elementFactory.getButton(
            By.xpath("//div[contains(@class,'PostBottomActionContainer')]"), "Like button");
    private static String attach = "//div[@class='wall_text']//div[contains(@class,'page_post_sized_thumbs ')]//a";
    private static String postXpath = "//div[@id='";

    public PersonalPage() {
        super(By.xpath("//div[@class='ProfileHeader__in']"), "Profile header");
    }

    public Integer getPostId(){
        String id = post.getAttribute("id");
        int getSumOdSymbols = id.length();
        int indexOfSymbol = id.lastIndexOf("_");
        int getID = getSumOdSymbols - indexOfSymbol - 1;
        int idOfPost = Integer.parseInt(StringUtils.right(id, getID));
        return idOfPost;
    }

    public String getPostAuthor(int ID){
        String authorXpath = postXpath + TestData.getUserID() + "_" + ID + "']" + postAuthor;
        IElement postAuthor = elementFactory.getTextBox(By.xpath(authorXpath), "Author of a post");
        String author = postAuthor.getText();
        return author;
    }

    public String getTextFromPost(int ID){
        String textXpath = postXpath + TestData.getUserID() + "_" + ID + "']" + postText;
        IElement textFromPost = elementFactory.getTextBox(By.xpath(textXpath), "Text from post");
        String text = textFromPost.getText();
        return text;
    }

    public String getPhotoFromPost(int ID){
        String attachXpath = postXpath + TestData.getUserID() + "_" + ID + "']" + attach;
        IElement attachFromPost = elementFactory.getComboBox(By.xpath(attachXpath), "Attach from post");
        String attachId = attachFromPost.getAttribute("href");
        System.out.println(attachId);
        return attachId;
    }

    public String getCommentAuthor(int ID){
        String commentAuthorXpath = postXpath + TestData.getUserID() + "_" + ID + "']" + commentAuthor;
        IElement commentAuthor = elementFactory.getTextBox(By.xpath(commentAuthorXpath), "Comment author");
        String author = commentAuthor.getText();
        return author;
    }

    public String getComment(int ID){
        String commentXpath = postXpath + TestData.getUserID() + "_" + ID + "']" + comment;
        IElement commentFromPost = elementFactory.getTextBox(By.xpath(commentXpath), "Comment from post");
        String getComment = commentFromPost.getText();
        return getComment;
    }

    public void clickLikeButton(){
        likeButton.click();
    }

    public boolean checkPost(int ID){
        String post = postXpath + TestData.getUserID() + "_" + ID + "']";
        List<IElement> posts = elementFactory.findElements(By.xpath(post), ElementType.LABEL);
        if (posts.size() > 0){
            return true;
        }
        else{
            return false;
        }
    }
    public String linkToAttach(String userID, int photoID) {
        String linkToAttach = "https://vk.com/photo" + userID + "_" + photoID;
        return linkToAttach;
    }
}
