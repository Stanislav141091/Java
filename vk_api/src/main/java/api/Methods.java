package api;

public enum Methods {
    POST("wall.post"),
    EDITPOST("wall.edit"),
    CREATECOMMENT("wall.createComment"),
    GETLIKES("wall.getLikes"),
    DELETEPOST("wall.delete"),
    WALLUPLOADSERVER("photos.getWallUploadServer"),
    SAVEWALLPHOTO("photos.saveWallPhoto");

    private String value;

    Methods(String value){
        this.value = value;
    }

    public String getMethod(){
        return value;
    }
}
