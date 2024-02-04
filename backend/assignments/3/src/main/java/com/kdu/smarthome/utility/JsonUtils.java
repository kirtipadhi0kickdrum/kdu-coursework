package com.kdu.smarthome.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils()
    {

    }

    public static String convertObjectToJson(Object object)
    {
        try{
            return objectMapper.writeValueAsString(object);
        }catch (JsonProcessingException e)
        {
            throw new RuntimeException("Error converting object to JSON", e);
        }
    }

    public static <T> T convertJsonToObject(String json, Class<T> valueType)
    {
        try{
            return objectMapper.readValue(json, valueType);
        }catch (JsonProcessingException e)
        {
            throw new RuntimeException("Error converting JSON to object", e);
        }
    }
}
