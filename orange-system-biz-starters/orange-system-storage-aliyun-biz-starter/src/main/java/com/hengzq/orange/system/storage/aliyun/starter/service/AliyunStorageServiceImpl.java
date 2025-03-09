package com.hengzq.orange.system.storage.aliyun.starter.service;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.exception.ServiceException;
import cn.hengzq.orange.system.common.biz.storage.constant.StorageModeEnum;
import cn.hengzq.orange.system.common.biz.storage.service.StorageProperties;
import cn.hengzq.orange.system.common.biz.storage.service.impl.AbstractStorageService;
import cn.hengzq.orange.system.common.biz.storage.vo.UploadResult;
import cn.hutool.core.io.FileUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.hengzq.orange.system.storage.aliyun.starter.config.AliyunStorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AliyunStorageServiceImpl extends AbstractStorageService {


    private static final Logger log = LoggerFactory.getLogger(AliyunStorageServiceImpl.class);
    private final AliyunStorageProperties properties;

    public AliyunStorageServiceImpl(AliyunStorageProperties properties) {
        this.properties = properties;
    }

    @Override
    public StorageModeEnum getStorageMode() {
        return StorageModeEnum.ALIYUN;
    }

    @Override
    public StorageProperties getStorageProperties() {
        return this.properties;
    }

    @Override
    public UploadResult upload(byte[] content, String relativePath) {
        OSS client = new OSSClientBuilder().build(properties.getEndPoint(), properties.getAccessKeyId(), properties.getAccessKeySecret());
        try {
            relativePath = FileUtil.normalize(relativePath);
            client.putObject(properties.getBucketName(), relativePath, new ByteArrayInputStream(content));
            return UploadResult.builder()
                    .size((long) content.length)
                    .type(FileUtil.extName(relativePath))
                    .fileName(relativePath)
                    .build();
        } catch (Exception e) {
            throw new ServiceException(GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
    }

    @Override
    public byte[] getObjectByRelativePath(String relativePath) {
        OSS client = new OSSClientBuilder().build(properties.getEndPoint(), properties.getAccessKeyId(), properties.getAccessKeySecret());
        relativePath = FileUtil.normalize(relativePath);
        OSSObject ossObject = client.getObject(properties.getBucketName(), relativePath);
        InputStream inputStream = ossObject.getObjectContent();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            // 读取文件内容到字节数组。
            byte[] readBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(readBuffer)) != -1) {
                byteArrayOutputStream.write(readBuffer, 0, bytesRead);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new ServiceException(GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        } finally {
            try {
                inputStream.close();
                byteArrayOutputStream.close();
                ossObject.close();
            } catch (IOException e) {
                log.warn("stream close error", e);
            }
            client.shutdown();
        }
    }

}
