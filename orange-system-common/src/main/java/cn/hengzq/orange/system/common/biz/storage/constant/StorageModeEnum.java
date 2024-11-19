package cn.hengzq.orange.system.common.biz.storage.constant;

import cn.hengzq.orange.common.enums.BaseEnum;
import lombok.Getter;

import java.util.Locale;

/**
 * 存储方式
 *
 * @author hengzq
 */
@Getter
public enum StorageModeEnum implements BaseEnum<String> {

    /**
     * 本地
     */
    LOCAL("本地存储"),

    /**
     * 阿里云
     */
    ALIYUN("阿里云存储");


    private final String msg;

    StorageModeEnum(String msg) {
        this.msg = msg;
    }

    public static StorageModeEnum getByName(String name) {
        return valueOf(name.toUpperCase(Locale.ROOT));
    }

    @Override
    public String getCode() {
        return this.name();
    }
}
