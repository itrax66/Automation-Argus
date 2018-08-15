package FileManagers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonParser {

    private final JSONObject jo;
    private final Object obj;
    private static JsonParser parser;

    private JsonParser() throws IOException, ParseException {
        obj = new JSONParser().parse(new FileReader("src/main/resources/SystemProperties.json"));
        jo = (JSONObject) obj;
    }
    public static JsonParser getInstance(){
        if(parser == null) {
            try {
                parser = new JsonParser();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return parser;
    }
    public String getValueFromString(String parameter){
        return (String) jo.get(parameter);
    }

}
