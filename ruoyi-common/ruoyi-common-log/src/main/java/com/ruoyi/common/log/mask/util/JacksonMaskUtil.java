package com.ruoyi.common.log.mask.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ruoyi.common.log.mask.jackson.MaskJacksonAnnotationIntrospector;

import java.io.Serializable;

/**
 * jackson mask 工具类
 *
 * @date 2020-05-30
 */
public final class JacksonMaskUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setAnnotationIntrospector(new MaskJacksonAnnotationIntrospector());
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    private JacksonMaskUtil() {
    }

    /**
     * 对象转 json (脱敏序列化)
     *
     * @param value
     * @return
     */
    public static final String mask(Object value) {
        if (value == null) {
            return null;
        }
        String result = null;
        if (value instanceof Serializable) {
            try {
                result = OBJECT_MAPPER.writeValueAsString(value);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            result = value.toString();
        }

        return result;
    }

    /**
     * json 转对象
     *
     * @param content
     * @param valueType
     * @return
     */
    public static <T> T parseObject(String content, Class<T> valueType) {
        T t = null;
        try {
            t = OBJECT_MAPPER.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return t;
    }

    public static JsonNode parseObject(String content) {
        JsonNode t = null;
        try {
            t = OBJECT_MAPPER.readTree(content);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return t;
    }

}