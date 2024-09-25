package cn.hengzq.orange.system.common.biz.user.constant;


import cn.hengzq.orange.common.enums.BaseEnum;

/**
 * 用户性别
 *
 * @author hengzq
 */
public enum UserGenderEnum implements BaseEnum<Integer> {
    /**
     * 男
     */
    MALE(1, "男"),

    /**
     * 女
     */
    FEMALE(2, "女"),

    /**
     * 未知
     */
    UNKNOWN(3, "未知");

    private final Integer code;
    private final String msg;

    UserGenderEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return msg;
    }
}
