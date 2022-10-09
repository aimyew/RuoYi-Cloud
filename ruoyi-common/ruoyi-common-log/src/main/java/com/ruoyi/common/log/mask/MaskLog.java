package com.ruoyi.common.log.mask;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ruoyi.common.log.mask.jackson.MaskJsonSerializer;

import java.lang.annotation.*;

/**
 * @desc mask 日志标记注解
 * @date 2019/11/05
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = MaskJsonSerializer.class)
public @interface MaskLog {

    /**
     * mask 规则 (当 startLen endLen mask 中有任一个被赋值时，value失效)
     */
    MaskRule value() default MaskRule.DEFAULT;

    /**
     * 开头保留的长度
     */
    int startLen() default DefaultMaskConfig.START_LEN;

    /**
     * 结尾保留的长度
     */
    int endLen() default DefaultMaskConfig.END_LEN;

    /**
     * 掩码值
     */
    String mask() default DefaultMaskConfig.MASK_TEXT;

}