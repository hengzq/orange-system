package cn.hengzq.orange.system.permission.common.exception;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.exception.ErrorCode;

/**
 * @author hengzq
 */
public interface ButtonErrorCode extends GlobalErrorCodeConstant {

    String PREFIX = "SystemPermissionButton.";
    ErrorCode BUTTON_PERMISSION_CANNOT_NULL = new ErrorCode(PREFIX + "0001", "按钮权限编码不能为空");

    ErrorCode BUTTON_PERMISSION_CANNOT_REPEAT = new ErrorCode(PREFIX + "0002", "按钮权限编码重复");

}
