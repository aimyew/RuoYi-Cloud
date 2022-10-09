package com.ruoyi.common.log.mask.jackson;

import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.ruoyi.common.log.mask.MaskLog;

import java.lang.annotation.Annotation;

/**
 * 使 {@link MaskLog} 注解生效 相当于 {@code @JacksonAnnotationsInside} 的作用
 *
 * @date 2020-05-30
 */
public class MaskJacksonAnnotationIntrospector extends JacksonAnnotationIntrospector {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean isAnnotationBundle(Annotation ann) {
        if (ann.annotationType() == MaskLog.class) {
            return true;
        } else {
            return super.isAnnotationBundle(ann);
        }
    }

}