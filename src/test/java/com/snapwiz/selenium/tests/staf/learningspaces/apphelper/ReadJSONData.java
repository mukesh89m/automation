package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * Created by mukesh on 7/7/16.
 */
public class ReadJSONData extends Driver {

    public static Map<String,Object> convertJSONStringTOMap(String jsonString){
        return convertJSONStringTOMap(jsonString, false);
    }

    public static Map<String,Object> convertJSONStringTOMap(String jsonString, boolean isSingleQuotedJson){
        if(jsonString == null){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        if (isSingleQuotedJson) {
            mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        }
        try
        {
            Map<String,Object> map = (Map<String,Object>)mapper.readValue(jsonString, Map.class);
            return map;
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

