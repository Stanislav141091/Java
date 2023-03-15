package utils;

public class UpdateString {

    public static String updateString(String object){
        object = object.replace("<html>", "");
        object = object.replace("<body>", "");
        object = object.replace("</body>", "");
        object = object.replace("</html>", "");
        object = object.replace("</html>", "");
        object = object.replace("</html>", "");
        return object;
    }
}
