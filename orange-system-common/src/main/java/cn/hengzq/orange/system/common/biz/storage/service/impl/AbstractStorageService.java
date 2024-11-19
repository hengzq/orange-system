package cn.hengzq.orange.system.common.biz.storage.service.impl;

import cn.hengzq.orange.system.common.biz.storage.service.StorageService;
import cn.hengzq.orange.system.common.biz.storage.vo.UploadResult;
import cn.hengzq.orange.system.common.util.StorageUtil;
import cn.hutool.core.io.FileUtil;

import java.io.File;

public abstract class AbstractStorageService implements StorageService {

//    @Override
//    public UploadResult upload(File file) {
//        String newFileName = StorageUtil.getRelativePath() + File.separator + StorageUtil.getNewFileName(file.getName());
//        UploadResult result = this.upload(FileUtil.readBytes(file), newFileName);
//        result.setOriginalName(file.getName());
//        return result;
//    }
}
