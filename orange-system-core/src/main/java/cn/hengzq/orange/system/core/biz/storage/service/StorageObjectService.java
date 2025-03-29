package cn.hengzq.orange.system.core.biz.storage.service;

import cn.hengzq.orange.system.common.biz.storage.vo.StorageByteObjectVO;
import cn.hengzq.orange.system.common.biz.storage.vo.StorageObjectVO;
import cn.hengzq.orange.system.common.biz.storage.vo.param.StorageObjectUploadParam;

import java.io.File;
import java.util.List;

/**
 * @author hengzq
 */
public interface StorageObjectService {

    StorageObjectVO upload(File file);

    StorageObjectVO upload(byte[] content, String originalName);

    List<StorageObjectVO> batchUpload(List<StorageObjectUploadParam> params);

    StorageByteObjectVO getByteArrayById(String id);

    StorageObjectVO getById(String id);

    StorageByteObjectVO getByteArrayByIFileName(String fileName);
}
