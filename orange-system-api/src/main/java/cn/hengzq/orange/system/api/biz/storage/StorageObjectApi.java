package cn.hengzq.orange.system.api.biz.storage;

import cn.hengzq.orange.system.common.biz.storage.vo.StorageObjectVO;
import cn.hengzq.orange.system.common.biz.storage.vo.param.StorageObjectUploadParam;

import java.io.File;
import java.util.List;

public interface StorageObjectApi {

    StorageObjectVO upload(File file);

    List<StorageObjectVO> batchUpload(List<StorageObjectUploadParam> params);

}
