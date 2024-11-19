package cn.hengzq.orange.system.common.biz.storage.service;

import cn.hengzq.orange.system.common.biz.storage.constant.StorageModeEnum;
import cn.hengzq.orange.system.common.biz.storage.vo.UploadResult;

import java.io.File;
import java.io.InputStream;

/**
 * 存储服务
 *
 * @author hengzq
 */
public interface StorageService {

    /**
     * 获取存储方式
     */
    StorageModeEnum getStorageMode();

    /**
     * 获取存储配置
     */
    StorageProperties getStorageProperties();

    /**
     * 文件上传
     */
    UploadResult upload(byte[] content, String relativePath);

    byte[] getObjectByRelativePath(String relativePath);


//
//    /**
//     * 获得文件的内容
//     *
//     * @param path 相对路径
//     * @return 文件的内容
//     */
//    byte[] getContent(String path);

}
