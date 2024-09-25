package cn.hengzq.orange.system.common.biz.menu.constant;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.exception.ErrorCode;

/**
 * @author hengzq
 */
public interface MenuErrorCode extends GlobalErrorCodeConstant {

    String PREFIX = "SystemPermissionMenu.";

    String MENU_PERMISSION_CANNOT_NULL_KEY = PREFIX + "0001";
    ErrorCode MENU_PERMISSION_CANNOT_NULL = new ErrorCode(MENU_PERMISSION_CANNOT_NULL_KEY, "菜单权限编码不能为空");

    String MENU_PERMISSION_CANNOT_REPEAT_KEY = PREFIX + "0002";
    ErrorCode MENU_PERMISSION_CANNOT_REPEAT = new ErrorCode(MENU_PERMISSION_CANNOT_REPEAT_KEY, "菜单权限编码不能重复");

    String MENU_NAME_CANNOT_NULL_KEY = PREFIX + "0003";
    ErrorCode MENU_NAME_CANNOT_NULL = new ErrorCode(MENU_NAME_CANNOT_NULL_KEY, "菜单名称不能为空");

    String MENU_PATH_CANNOT_NULL_KEY = PREFIX + "0004";
    ErrorCode MENU_PATH_CANNOT_NULL = new ErrorCode(MENU_PATH_CANNOT_NULL_KEY, "菜单路径不能为空");

    String MENU_DELETE_ERROR_EXIST_SUBSET_KEY = PREFIX + "0005";
    ErrorCode MENU_DELETE_ERROR_EXIST_SUBSET = new ErrorCode(MENU_DELETE_ERROR_EXIST_SUBSET_KEY, "存在子集菜单，不允许删除");

    String MENU_DELETE_ERROR_EXIST_BUTTON_KEY = PREFIX + "0006";
    ErrorCode MENU_DELETE_ERROR_EXIST_BUTTON = new ErrorCode(MENU_DELETE_ERROR_EXIST_BUTTON_KEY, "存在关联按钮，不允许删除");
}
