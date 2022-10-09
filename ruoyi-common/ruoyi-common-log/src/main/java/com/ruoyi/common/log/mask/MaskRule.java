package com.ruoyi.common.log.mask;

/**
 * @desc mask 规则
 * @date 2019/11/05
 */
public enum MaskRule {

    /**
     * 手机号
     */
    MOBILE(3, 4, DefaultMaskConfig.MASK_TEXT),
    /**
     * 银行卡
     */
    BANKCARD(4, 4, DefaultMaskConfig.MASK_TEXT),
    /**
     * 身份证
     */
    IDCARD(4, 4, DefaultMaskConfig.MASK_TEXT),
    /**
     * ip 地址
     */
    IP(3, 0, DefaultMaskConfig.MASK_TEXT),
    /**
     * 名字
     */
    NAME(1, 0, DefaultMaskConfig.MASK_TEXT),
    /**
     * 密码
     */
    PASSWROD(0, 0, DefaultMaskConfig.MASK_TEXT),
    /**
     * 默认 (全部 mask)
     */
    DEFAULT(0, 0, DefaultMaskConfig.MASK_TEXT);

    /**
     * 开头保留的长度
     */
    private int startLen;
    /**
     * 结尾保留的长度
     */
    private int endLen;
    /**
     * 掩码值
     */
    private String mask;

    private MaskRule(int startLen, int endLen, String mask) {
    }

    public int getStartLen() {
        return startLen;
    }

    public int getEndLen() {
        return endLen;
    }

    public String getMask() {
        return mask;
    }
}