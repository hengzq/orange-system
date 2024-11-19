package com.hengzq.orange.system.storage.local.starter.service;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.exception.ServiceException;
import cn.hengzq.orange.system.common.biz.storage.constant.StorageModeEnum;
import cn.hengzq.orange.system.common.biz.storage.service.StorageProperties;
import cn.hengzq.orange.system.common.biz.storage.service.impl.AbstractStorageService;
import cn.hengzq.orange.system.common.biz.storage.vo.UploadResult;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.URLUtil;
import com.hengzq.orange.system.storage.local.starter.config.LocalStorageProperties;

import java.io.File;
import java.io.InputStream;

public class LocalStorageServiceImpl extends AbstractStorageService {

    private final LocalStorageProperties properties;

    public LocalStorageServiceImpl(LocalStorageProperties localStorageProperties) {
        this.properties = localStorageProperties;
    }

    @Override
    public StorageModeEnum getStorageMode() {
        return StorageModeEnum.LOCAL;
    }

    @Override
    public StorageProperties getStorageProperties() {
        return this.properties;
    }

    @Override
    public UploadResult upload(byte[] content, String relativePath) {
        try {
            String absolutePath = FileUtil.normalize(properties.getBasePath() + File.separator + relativePath);
            FileUtil.writeBytes(content, absolutePath);
            return UploadResult.builder()
                    .size((long) content.length)
                    .type(FileUtil.extName(relativePath))
                    .relativePath(relativePath)
                    .build();
        } catch (Exception e) {
            throw new ServiceException(GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        }
    }

    @Override
    public byte[] getObjectByRelativePath(String relativePath) {
        return null;
    }
}
