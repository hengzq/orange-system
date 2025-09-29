package cn.hengzq.orange.system.common.biz.msg.constant;


import cn.hengzq.orange.common.enums.BaseEnum;
import lombok.Getter;

/**
 * 消息类型
 */
@Getter
public enum MessageTypeEnum implements BaseEnum<String> {
    /**
     * 适用场景：系统升级、节假日安排、政策变更
     */
    NOTICE("公告/通知"),
    /**
     * 登出
     */
    LOGOUT("登出");

    private final String msg;

    MessageTypeEnum(String msg) {
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.name();
    }

}
