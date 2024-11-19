package cn.hengzq.orange.system.common.biz.storage.service;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.exception.ServiceException;
import cn.hengzq.orange.context.ApplicationContextHelper;
import cn.hengzq.orange.system.common.biz.storage.constant.StorageErrorCode;
import cn.hengzq.orange.system.common.biz.storage.constant.StorageModeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class StorageServiceFactory {

    private StorageServiceFactory() {
    }

    private static volatile Map<StorageModeEnum, StorageService> storageServiceMap;

    public static Map<StorageModeEnum, StorageService> getStorageServiceMap() {
        if (Objects.isNull(storageServiceMap)) {
            synchronized (StorageServiceFactory.class) {
                if (Objects.isNull(storageServiceMap)) {
                    storageServiceMap = new ConcurrentHashMap<>();
                    Map<String, StorageService> tempStorageServiceMap = ApplicationContextHelper.getBeansOfType(StorageService.class);
                    tempStorageServiceMap.values().forEach(storageService -> storageServiceMap.put(storageService.getStorageMode(), storageService));
                }
            }
        }
        return storageServiceMap;
    }

    /**
     * 根据存储方式 获取存储服务
     */
    public static StorageService getStorageService(StorageModeEnum storageMode) {
        if (Objects.isNull(storageMode)) {
            log.error("storageMode is null");
            throw new ServiceException(StorageErrorCode.STORAGE_MODE_CANNOT_NULL);
        }
        return Optional.ofNullable(getStorageServiceMap())
                .map(item -> item.get(storageMode))
                .orElseThrow(() -> {
                    log.error("The StorageService does not exist. storageMode:{}", storageMode);
                    return new ServiceException(StorageErrorCode.NO_STORAGE_SERVICE_IS_AVAILABLE);
                });
    }

    /**
     * 随机获取一个可用的存储服务
     */
    public static StorageService getStorageService() {
        return Optional.ofNullable(getStorageServiceMap())
                .filter(map -> !map.isEmpty())
                .flatMap(map -> map.values().stream().findFirst())
                .orElseThrow(() -> {
                    log.error("The StorageService does not exist.");
                    return new ServiceException(StorageErrorCode.NO_STORAGE_SERVICE_IS_AVAILABLE);
                });
    }

}
