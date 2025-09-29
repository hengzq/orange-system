package cn.hengzq.orange.system.common.biz.msg.constant;

import cn.hengzq.orange.common.exception.ErrorCode;

/**
 * @author hengzq
 */
public final class MessageErrorCode {

    private MessageErrorCode() {
        throw new AssertionError("Cannot be instantiated!");
    }

    public static final String TEMPLATE_CODE_CANNOT_NULL_KEY = "SystemMsg.0001";

    public static final ErrorCode TEMPLATE_CODE_CANNOT_NULL = new ErrorCode(TEMPLATE_CODE_CANNOT_NULL_KEY, "角色权限编码不能为空");

    public static final String TEMPLATE_CODE_CANNOT_REPEAT_KEY = "SystemMsg.0002";

    public static final ErrorCode TEMPLATE_CODE_CANNOT_REPEAT = new ErrorCode(TEMPLATE_CODE_CANNOT_REPEAT_KEY, "模板编码重复");


}
