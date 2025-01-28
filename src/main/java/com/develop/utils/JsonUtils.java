//package com.develop.utils;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
//import static com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN;
//import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
//import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
//import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
//
//public class JsonUtils {
//    public final static ObjectMapper mapper = new ObjectMapper()
//            .registerModule(new JavaTimeModule())
//            .disable(FAIL_ON_UNKNOWN_PROPERTIES)
//            .configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
//            .configure(WRITE_DATES_AS_TIMESTAMPS, false)
//            .setSerializationInclusion(NON_NULL)
//            .enable(WRITE_BIGDECIMAL_AS_PLAIN);
//
//    public static JsonNode from(String jsonString) {
//        try {
//            return mapper.readValue(jsonString, JsonNode.class);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//    public static JsonNode from(Object jsonObject) {
//        try {
//            return mapper.readValue(toString(jsonObject), JsonNode.class);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//    public static <T> T toObject(String jsonString, Class<T> object) {
//        try {
//            return mapper.readValue(jsonString, object);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//    public static <T> T toObject(Object jsonObject, Class<T> object) {
//        try {
//            return mapper.readValue(toString(jsonObject), object);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//    public static <T> T convertValue(Object object, Class<T> tClass) {
//        try {
//            return mapper.convertValue(object, tClass);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//    public static <T> T convertValue(Object object, TypeReference<T> typeReference) {
//        try {
//            return mapper.convertValue(object, typeReference);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//    public static <T> T readValue(String jsonString, JavaType javaType) {
//        try {
//            return mapper.readValue(jsonString, javaType);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//    public static <T> T readValue(String jsonString, TypeReference<T> typeReference) {
//        try {
//            return mapper.readValue(jsonString, typeReference);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//    public static String toString(Object object) {
//        try {
//            return mapper.writeValueAsString(object);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return object.toString();
//    }
//
//    public static Object toJsonOrString(Object object) {
//        try {
//            return from(object);
//        } catch (Exception e) {
//            return object.toString();
//        }
//    }
//
//    public static void main(String[] args) {
//        String text = "true}";
//        Map<String,Object> map = new HashMap<>();
//        map.put("key",toJsonOrString(text));
//        System.out.println(map);
//        System.out.println("******");
//    }
//}
