package cn.hengzq.orange.system.common.biz.department.constant;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.exception.ErrorCode;

/**
 * @author hengzq
 */
public interface DepartmentErrorCode extends GlobalErrorCodeConstant {
    String PREFIX = "SystemPermissionDepartment.";

    String DEPARTMENT_ID_CANNOT_NULL_KEY = PREFIX + "0001";
    ErrorCode DEPARTMENT_ID_CANNOT_NULL = new ErrorCode(DEPARTMENT_ID_CANNOT_NULL_KEY, "部门ID不能为空.");

    String DEPARTMENT_PARENT_DATA_NONEXISTENCE_KEY = PREFIX + "0002";
    ErrorCode DEPARTMENT_PARENT_DATA_NONEXISTENCE = new ErrorCode(DEPARTMENT_PARENT_DATA_NONEXISTENCE_KEY, "该父级数据不存在,请检查数据");

    String DEPARTMENT_NAME_CANNOT_NULL_KEY = PREFIX + "0003";
    ErrorCode ROLE_NAME_CANNOT_NULL = new ErrorCode(DEPARTMENT_NAME_CANNOT_NULL_KEY, "部门名称不能为空");

    String DEPARTMENT_DELETE_ERROR_EXIST_SUBSET_KEY = PREFIX + "0004";
    ErrorCode DEPARTMENT_DELETE_ERROR_EXIST_SUBSET = new ErrorCode(DEPARTMENT_DELETE_ERROR_EXIST_SUBSET_KEY, "存在子集部门，不允许删除");

}
