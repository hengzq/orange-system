package cn.hengzq.orange.system.common.biz.storage.constant;

import cn.hengzq.orange.common.exception.ErrorCode;

/**
 * @author hengzq
 */
public interface StorageErrorCode {

    String PREFIX = "SystemStorage.";

    String ORIGINAL_NAME_CANNOT_NULL_CODE = PREFIX + "0001";
    ErrorCode ORIGINAL_NAME_CANNOT_NULL = new ErrorCode(ORIGINAL_NAME_CANNOT_NULL_CODE, "文件原名称不能为空");

    String ORIGINAL_CONTENT_CANNOT_NULL_CODE = PREFIX + "0002";
    ErrorCode ORIGINAL_CONTENT_CANNOT_NULL = new ErrorCode(ORIGINAL_NAME_CANNOT_NULL_CODE, "文件不能为空");


    String NO_STORAGE_SERVICE_IS_AVAILABLE_CODE = PREFIX + "0003";
    ErrorCode NO_STORAGE_SERVICE_IS_AVAILABLE = new ErrorCode(NO_STORAGE_SERVICE_IS_AVAILABLE_CODE, "没有可用的存储服务");


    String STORAGE_MODE_CANNOT_NULL_CODE = PREFIX + "0004";
    ErrorCode STORAGE_MODE_CANNOT_NULL = new ErrorCode(STORAGE_MODE_CANNOT_NULL_CODE, "存储方式不能为空");


    String FILE_UPLOAD_FAILED_CODE = PREFIX + "0005";
    ErrorCode FILE_UPLOAD_FAILED = new ErrorCode(FILE_UPLOAD_FAILED_CODE, "文件上传失败");

}
