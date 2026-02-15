package com.foodics.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.List;

/**
 * JSON utility class for serialization and deserialization
 */
public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Convert object to JSON string
     * @param object Object to serialize
     * @return JSON string
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    /**
     * Convert JSON string to object
     * @param json JSON string
     * @param clazz Target class
     * @param <T> Type of the object
     * @return Deserialized object
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON to object", e);
        }
    }

    /**
     * Convert JSON string to list of objects
     * @param json JSON string
     * @param clazz Target class
     * @param <T> Type of the objects in the list
     * @return List of deserialized objects
     */
    public static <T> List<T> fromJsonToList(String json, Class<T> clazz) {
        try {
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            CollectionType collectionType = typeFactory.constructCollectionType(List.class, clazz);
            return objectMapper.readValue(json, collectionType);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON to list", e);
        }
    }

    /**
     * Get ObjectMapper instance
     * @return ObjectMapper instance
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
