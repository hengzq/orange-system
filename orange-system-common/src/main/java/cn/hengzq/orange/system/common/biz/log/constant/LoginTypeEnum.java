package cn.hengzq.orange.system.common.biz.log.constant;


import cn.hengzq.orange.common.enums.BaseEnum;
import lombok.Getter;

/**
 * 登陆日志操作类型
 */
public enum LoginTypeEnum implements BaseEnum<Integer> {
    /**
     * 登陆
     */
    LOGIN(0, "登录"),
    /**
     * 登出
     */
    LOGOUT(1, "登出");


    private final Integer code;

    @Getter
    private final String msg;

    LoginTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

}
