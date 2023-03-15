package api;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testData.TestData;
import utils.ResponseParser;
import utils.UpdateString;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class WallApi {

    private final String postMethod = Methods.POST.getMethod();
    private final String editPostMethod = Methods.EDITPOST.getMethod();
    private final String createComment = Methods.CREATECOMMENT.getMethod();
    private final String getLikes = Methods.GETLIKES.getMethod();
    private final String deletePost = Methods.DELETEPOST.getMethod();
    private final String wallUploadServer = Methods.WALLUPLOADSERVER.getMethod();
    private final String saveWallPhoto = Methods.SAVEWALLPHOTO.getMethod();
    private final String token = TestData.getToken();
    private final String ownerID = TestData.getUserID();
    private final String version = TestData.getApiVersion();

    public Integer createNewTextPost(String text){
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpec200());
        Response response = given()
                .param("access_token", token)
                .param("owner_id", ownerID)
                .param("v", version)
                .param("message", text).
        when()
                .get(postMethod).
        then()
                .extract()
                .response();
        Integer pId = response.path("response.post_id");
        System.out.println(pId);
        return pId;
    }

    public String getWallUploadServer(){
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpec200());
        Response response = given()
                .param("access_token", token)
                .param("v", version).
        when()
                .get(wallUploadServer).
        then()
                .extract()
                .response();
        String uploadURL = response.path("response.upload_url");
        return uploadURL;
    }

    public HashMap<String, String> uploadPhoto(String uploadURL, String path){
        File file = new File(path);
        Response response = given()
                .contentType(ContentType.MULTIPART)
                .multiPart("photo", file)
        .when()
                .post(uploadURL)
        .then()
                .contentType(ContentType.HTML)
                .statusCode(CodeResponse.OK.getCodeResponse())
                .extract()
                .response();
        response.prettyPrint();
        String obj = response.body().asPrettyString();
        String updatedObj = UpdateString.updateString(obj);
        HashMap<String, String>dataFromResponse = ResponseParser.responseParser(updatedObj);
        return dataFromResponse;
    }

    public int savePhoto(String photo, String server, String hash){
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpec200());
        Response response = given()
                .param("access_token", token)
                .param("user_id", ownerID)
                .param("photo", photo)
                .param("server", server)
                .param("hash", hash)
                .param("v", version)
        .when()
                .get(saveWallPhoto)
        .then()
                .extract()
                .response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> attachments = jsonPath.get("response.id");
        int attachId = attachments.get(0);
        return attachId;
    }

    public void editPost(String text, int postID, int photo){
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpec200());
        Response response = given()
                .param("access_token", token)
                .param("owner_id", ownerID)
                .param("v", version)
                .param("post_id", postID)
                .param("message", text)
                .param("attachments","photo" + ownerID + "_" + photo)
        .when()
                .get(editPostMethod).
        then()
                .extract()
                .response();
    }

    public void addComment(String comment, int postID){
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpec200());
        Response response = given()
                .param("access_token", token)
                .param("post_id", postID)
                .param("message", comment)
                .param("v", version).
        when()
                .get(createComment).
        then()
                .extract()
                .response();
    }

    public void getLikedUser(int postID){
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpec200());
        Response response = given()
                .param("access_token", token)
                .param("post_id", postID)
                .param("v", version).
        when()
                .get(getLikes).
        then()
                .extract()
                .response();
    }

    public void deletePost(int postID){
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpec200());
        Response response = given()
                .param("access_token", token)
                .param("owner_id", ownerID)
                .param("post_id", postID)
                .param("v", version).
        when()
                .get(deletePost).
        then()
                .extract()
                .response();
    }
}