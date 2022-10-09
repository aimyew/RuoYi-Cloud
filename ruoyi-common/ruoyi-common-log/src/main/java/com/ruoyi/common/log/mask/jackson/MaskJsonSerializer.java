package com.ruoyi.common.log.mask.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.ruoyi.common.log.mask.DefaultMaskConfig;
import com.ruoyi.common.log.mask.MaskLog;
import com.ruoyi.common.log.mask.MaskRule;
import com.ruoyi.common.log.mask.util.MaskUtil;

import java.io.IOException;

/**
 * {@link MaskLog} 注解脱敏处理
 *
 * @date 2020-05-30
 */
public class MaskJsonSerializer extends JsonSerializer<Object> implements ContextualSerializer {

    private MaskLog maskLog;

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
        if (property != null) {
            maskLog = property.getAnnotation(MaskLog.class);
        }
        return this;
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        if (maskLog == null || value == null) {
            gen.writeObject(value);
            return;
        }

        int startLen;
        int endLen;
        String mask;
        if (isSetMaskAttributes(maskLog)) {
            startLen = maskLog.startLen();
            endLen = maskLog.endLen();
            mask = maskLog.mask();
        } else {
            MaskRule maskRule = maskLog.value();
            startLen = maskRule.getStartLen();
            endLen = maskRule.getEndLen();
            mask = maskRule.getMask();
        }

        gen.writeObject(MaskUtil.mask(value, startLen, endLen, mask));
    }

    private boolean isSetMaskAttributes(MaskLog maskLog) {
        return maskLog.startLen() != DefaultMaskConfig.START_LEN || maskLog.endLen() != DefaultMaskConfig.END_LEN
                || !DefaultMaskConfig.MASK_TEXT.equals(maskLog.mask());
    }

}
