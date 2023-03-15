package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class GetSettings {

    static final JSONParser parser = new JSONParser();

    public static String settings( String settings){
        try (Reader reader = new FileReader("src/main/resources/settings.json")){
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            String chrome = (String) jsonObject.get(settings);
            return chrome;
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
