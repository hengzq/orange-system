package cn.hengzq.orange.system.api.biz.storage.impl;

import cn.hengzq.orange.common.file.CommonMultipartFile;
import cn.hengzq.orange.system.api.biz.storage.StorageObjectApi;
import cn.hengzq.orange.system.api.biz.storage.client.StorageObjectClient;
import cn.hengzq.orange.system.api.client.SystemRestClient;
import cn.hengzq.orange.system.common.biz.storage.vo.StorageObjectVO;
import cn.hengzq.orange.system.common.biz.storage.vo.param.StorageObjectUploadParam;

import java.io.File;
import java.util.List;

public class StorageObjectClientImpl implements StorageObjectApi {

    private static volatile StorageObjectClient storageObjectClient;

    private StorageObjectClient getStorageObjectClient() {
        if (storageObjectClient == null) {
            synchronized (StorageObjectClientImpl.class) {
                if (storageObjectClient == null) {
                    storageObjectClient = SystemRestClient.httpServiceProxyFactory().createClient(StorageObjectClient.class);
                }
            }
        }
        return storageObjectClient;
    }

    @Override
    public StorageObjectVO upload(File file) {
        return getStorageObjectClient().upload(new CommonMultipartFile(file)).getData();
    }

    @Override
    public List<StorageObjectVO> batchUpload(List<StorageObjectUploadParam> params) {
        return getStorageObjectClient().batchUpload(params).getData();
    }
}
