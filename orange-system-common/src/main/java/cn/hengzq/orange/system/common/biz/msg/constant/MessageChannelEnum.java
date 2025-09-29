package cn.hengzq.orange.system.common.biz.msg.constant;


import cn.hengzq.orange.common.enums.BaseEnum;
import lombok.Getter;

/**
 * 消息渠道
 */
@Getter
public enum MessageChannelEnum implements BaseEnum<String> {


    WEB("站内消息"),

    EMAIL("邮件消息"),

    WECHAT("微信消息"),
    ;

    private final String msg;

    MessageChannelEnum(String msg) {
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.name();
    }

}
