package testCases;

import browserUtils.BrowserUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import api.*;
import testData.TestData;
import utils.*;

import java.util.HashMap;

public class testVK extends BaseTest{
    @Test
    public static void testVK(){
        String phone = TestData.getPhone();
        String password = TestData.getPassword();
        String user = TestData.getUser();
        String filePath = TestData.getPath();
        String userID = TestData.getUserID();
        Integer valueOfCharacters = TestData.getvalue();
        Integer value = RandomUtils.getRandomValue(valueOfCharacters);
        String randomText = RandomUtils.getRandomString(value);
        BrowserUtils browserUtils = new BrowserUtils();
        LoginPage mainPage = new LoginPage();
        PasswordPage passwordPage = new PasswordPage();
        NewsPage newsPage = new NewsPage();
        WallApi wallApi = new WallApi();
        PersonalPage personalPage = new PersonalPage();
        browserUtils.goToSite();
        mainPage.inputPhoneOrEmail(phone);
        mainPage.clickSignInButton();
        passwordPage.inputPassword(password);
        passwordPage.clickContinueButton();
        newsPage.clickPersonalPage();
        int postId =  wallApi.createNewTextPost(randomText);
        Assert.assertEquals(personalPage.getPostAuthor(postId), user, "Author is matched");
        Assert.assertEquals(personalPage.getTextFromPost(postId), randomText, "Text is matched");
        String uploadURL = wallApi.getWallUploadServer();
        HashMap<String, String> response = wallApi.uploadPhoto(uploadURL, filePath);
        String server = response.get("server");
        String photo = response.get("photo");
        String hash = response.get("hash");
        int attachId = wallApi.savePhoto(photo, server, hash);
        Integer editedValue = RandomUtils.getRandomValue(valueOfCharacters);
        String editText = RandomUtils.getRandomString(editedValue);
        wallApi.editPost(editText, postId, attachId);
        Assert.assertEquals(personalPage.getTextFromPost(postId), editText, "Text is matched");
        personalPage.getPhotoFromPost(postId);
        Assert.assertEquals(personalPage.linkToAttach(userID, attachId), personalPage.getPhotoFromPost(postId),
                "Attach is matched");
        Integer commentValue = RandomUtils.getRandomValue(100);
        String comment = RandomUtils.getRandomString(commentValue);
        wallApi.addComment(comment, postId);
        Assert.assertEquals(personalPage.getCommentAuthor(postId), user, "Author is matched");
        Assert.assertEquals(personalPage.getComment(postId), comment, "Comment is matched");
        personalPage.clickLikeButton();
        wallApi.getLikedUser(postId);
        wallApi.deletePost(postId);
        Assert.assertTrue(personalPage.checkPost(postId),"Post is deleted");
    }
}
