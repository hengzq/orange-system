package cn.hengzq.orange.system.api.biz.storage.client;

import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.system.common.biz.storage.vo.StorageObjectVO;
import cn.hengzq.orange.system.common.biz.storage.vo.param.StorageObjectUploadParam;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

public interface StorageObjectClient {

    String BASE_URL_PREFIX = SystemConstant.V1_0_URL_PREFIX + "/storage-object";

    @PostExchange(BASE_URL_PREFIX + "/upload")
    Result<StorageObjectVO> upload(MultipartFile file);


    @PostExchange(BASE_URL_PREFIX + "/batch-upload")
    Result<List<StorageObjectVO>> batchUpload(@RequestBody List<StorageObjectUploadParam> params);

}
