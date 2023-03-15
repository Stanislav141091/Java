package utils;

import java.util.HashMap;

public class ResponseParser {

    public static HashMap<String, String> responseParser(String object){
        HashMap<String, String> dataFromResponse = new HashMap<>();
        try {
            org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
            org.json.simple.JSONObject result = (org.json.simple.JSONObject) parser.parse(object);
            Long server = (Long) result.get("server");
            String serverData = server.toString();
            dataFromResponse.put("server", serverData);
            String photo = (String) result.get("photo");
            dataFromResponse.put("photo", photo);
            String hash = (String) result.get("hash");
            dataFromResponse.put("hash", hash);
        } catch (org.json.simple.parser.ParseException e) {
            System.out.println("Error: " + e);
        }
        return dataFromResponse;
    }
}
