package cn.hengzq.orange.system.permission.common.exception;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.exception.ErrorCode;

/**
 * @author hengzq
 */
public interface UserErrorCode extends GlobalErrorCodeConstant {

    String PREFIX = "SystemPermissionUser.";

    String USER_ID_CANNOT_NULL_KEY = PREFIX + "0001";
    ErrorCode USER_ID_CANNOT_NULL = new ErrorCode(USER_ID_CANNOT_NULL_KEY, "用户ID不能为空.");

    String USER_RESET_PASSWORD_INCONSISTENT_KEY = PREFIX + "0002";
    ErrorCode USER_RESET_PASSWORD_INCONSISTENT  = new ErrorCode(USER_RESET_PASSWORD_INCONSISTENT_KEY, "确认密码和新密码不一致");



}
