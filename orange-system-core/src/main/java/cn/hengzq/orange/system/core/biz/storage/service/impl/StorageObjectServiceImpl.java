package cn.hengzq.orange.system.core.biz.storage.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.system.api.biz.storage.StorageObjectApi;
import cn.hengzq.orange.system.common.biz.storage.constant.StorageConstant;
import cn.hengzq.orange.system.common.biz.storage.service.StorageService;
import cn.hengzq.orange.system.common.biz.storage.service.StorageServiceFactory;
import cn.hengzq.orange.system.common.biz.storage.vo.StorageByteObjectVO;
import cn.hengzq.orange.system.common.biz.storage.vo.StorageObjectVO;
import cn.hengzq.orange.system.common.biz.storage.vo.UploadResult;
import cn.hengzq.orange.system.common.biz.storage.vo.param.StorageObjectUploadParam;
import cn.hengzq.orange.system.common.util.StorageUtil;
import cn.hengzq.orange.system.core.biz.storage.converter.StorageObjectConverter;
import cn.hengzq.orange.system.core.biz.storage.entity.StorageObjectEntity;
import cn.hengzq.orange.system.core.biz.storage.mapper.StorageObjectMapper;
import cn.hengzq.orange.system.core.biz.storage.service.StorageObjectService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.URLUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class StorageObjectServiceImpl implements StorageObjectService, StorageObjectApi {

    private final StorageObjectMapper storageObjectMapper;

    @Override
    public StorageObjectVO upload(File file) {
        return this.upload(FileUtil.readBytes(file), file.getName());
    }

    @Override
    public List<StorageObjectVO> batchUpload(List<StorageObjectUploadParam> params) {
        if (CollUtil.isEmpty(params)) {
            return List.of();
        }
        List<StorageObjectVO> voList = new ArrayList<>(params.size());
        params.stream().filter(Objects::nonNull).forEach(param -> {
            StorageObjectVO vo = this.upload(param.getContent(), param.getOriginalName());
            voList.add(vo);
        });
        return voList;
    }

    @Override
    public StorageByteObjectVO getByteArrayById(Long id) {
        StorageObjectEntity entity = storageObjectMapper.selectById(id);
        if (entity == null) {
            return null;
        }
        StorageService storageService = StorageServiceFactory.getStorageService(entity.getMode());
        byte[] content = storageService.getObjectByRelativePath(entity.getRelativePath());
        StorageByteObjectVO result = StorageObjectConverter.INSTANCE.toByteVO(entity);
        result.setContent(content);
        return result;
    }

    @Override
    public StorageObjectVO getById(Long id) {
        StorageObjectEntity entity = storageObjectMapper.selectById(id);
        Assert.nonNull(entity, GlobalErrorCodeConstant.GLOBAL_PARAMETER_ID_IS_INVALID);
        return StorageObjectConverter.INSTANCE.toVO(entity);
    }


    @Override
    public StorageObjectVO upload(byte[] content, String originalName) {
        String newFileName = StorageUtil.getNewFileName(originalName);
        String relativePath = StorageUtil.getRelativePath() + File.separator + newFileName;

        StorageService storageService = StorageServiceFactory.getStorageService();
        UploadResult result = storageService.upload(content, relativePath);

        StorageObjectEntity entity = StorageObjectConverter.INSTANCE.toEntity(result);
        long id = IdUtil.getSnowflakeNextId();
        entity.setId(id);
        entity.setOriginalName(originalName);
        entity.setMode(storageService.getStorageMode());
        entity.setPreviewUrl(URLUtil.normalize(storageService.getStorageProperties().getServicePath() + "/" + result.getRelativePath(), false, true));
        entity.setDownloadUrl(URLUtil.normalize(storageService.getStorageProperties().getServicePath() + "/" + result.getRelativePath(), false, true));
        storageObjectMapper.insertOne(entity);
        return StorageObjectConverter.INSTANCE.toVO(entity);
    }

}
