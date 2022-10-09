package com.ruoyi.common.log.mask.util;

import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.log.mask.MaskRule;
import org.springframework.util.StringUtils;

import java.util.Collections;

/**
 * @desc mask 工具类
 * @date 2019-11-05
 */
public final class MaskUtil {

    private MaskUtil() {
    }

    /**
     * 根据 {@link MaskRule} 进行截取替换
     *
     * @param s
     * @param maskRule
     * @return
     */
    public static String mask(String s, MaskRule maskRule) {
        return mask(s, maskRule.getStartLen(), maskRule.getEndLen(), maskRule.getMask());
    }

    /**
     * 根据传入的 mask 规则 替换字符串
     *
     * @param obj
     * @param start
     * @param end
     * @param mask
     * @return
     */
    public static String mask(Object obj, int start, int end, String mask) {
        if (!StringUtils.hasText(mask)) {
            mask = Constants.asterisk;
        }
        if (mask.length() != 1) {
            mask = mask.substring(0, 1);
        }
        if (obj == null) {
            return mask;
        }
        return mask(obj.toString(), start, end, mask);
    }

    /**
     * 根据传入的 mask 规则 替换字符串
     *
     * example:
     *
     * System.out.println(MaskUtil.mask("13112345678", 3, 4, "***"));// 131****5678
     *
     *
     * @param s
     * @param start
     * @param end
     * @param mask
     * @return
     */
    public static String mask(String s, int start, int end, String mask) {
        if (!StringUtils.hasText(mask)) {
            mask = Constants.asterisk;
        }
        if (mask.length() != 1) {
            mask = mask.substring(0, 1);
        }
        if (s == null || s.length() == 0) {
            return mask;
        }
        int len = s.length();
        if (len <= start) {
            return String.join(Constants.blank, Collections.nCopies(len, mask));
        }
        if (len <= start + end) {
            return s.substring(0, start) + String.join(Constants.blank, Collections.nCopies(len - start, mask));
        }

        return s.substring(0, start) + String.join(Constants.blank, Collections.nCopies(len - start - end, mask)) + s.substring(len - end);
    }

    /**
     * 对敏感数据进行脱敏
     *
     * example:
     *
     * @Data
     * public class UniqueSchool implements Serializable {
     *     private String address;
     *     @MaskLog(startLen = 1, endLen = 1, mask = "###")
     *     private String country;
     *     private Integer year;
     * }
     *
     * UniqueSchool uniqueSchool = new UniqueSchool();
     * uniqueSchool.setAddress("北京市北京东路");
     * uniqueSchool.setCountry("中国北京市");
     * uniqueSchool.setYear(2022);
     *
     * String maskResult = MaskUtil.mask(uniqueSchool);
     * UniqueSchool uniqueSchoolVO = JacksonMaskUtil.parseObject(maskResult, UniqueSchool.class);
     *
     * System.out.println(uniqueSchoolVO.getAddress());// 北京市北京东路
     * System.out.println(uniqueSchoolVO.getCountry());// 中###市
     * System.out.println(uniqueSchoolVO.getYear());// 2022
     *
     * @param object
     * @return
     */
    public static final String mask(Object object) {
        return JacksonMaskUtil.mask(object);
    }

}