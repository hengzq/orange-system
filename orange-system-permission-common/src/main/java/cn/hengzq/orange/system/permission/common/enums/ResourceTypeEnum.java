package cn.hengzq.orange.system.permission.common.enums;


import cn.hengzq.orange.common.enums.BaseEnum;

/**
 * 资源类型
 *
 * @author hengzq
 */
public enum ResourceTypeEnum implements BaseEnum<Integer> {
    /**
     * 菜单
     */
    MENU(1, "菜单"),

    /**
     * 按钮
     */
    BUTTON(2, "按钮");

    private final Integer code;
    private final String msg;

    ResourceTypeEnum(Integer code, String msg) {
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
