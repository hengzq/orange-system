package cn.hengzq.orange.system.core.biz.storage.controller;


import cn.hengzq.orange.common.exception.ServiceException;
import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.system.common.biz.storage.constant.StorageErrorCode;
import cn.hengzq.orange.system.common.biz.storage.vo.StorageByteObjectVO;
import cn.hengzq.orange.system.common.biz.storage.vo.StorageObjectVO;
import cn.hengzq.orange.system.common.biz.storage.vo.param.StorageObjectUploadParam;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.core.biz.storage.service.StorageObjectService;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author hengzq
 */
@Tag(name = "系统 - 存储 - 对象管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/storage-object")
public class StorageObjectController {

    private final StorageObjectService storageObjectService;

    @Operation(summary = "上传文件")
    @PostMapping("/upload")
    public Result<StorageObjectVO> upload(@RequestParam("file") MultipartFile file) {
        if (Objects.isNull(file) || StrUtil.isBlank(file.getOriginalFilename())) {
            throw new ServiceException(StorageErrorCode.FILE_UPLOAD_FAILED);
        }
        try {
            return ResultWrapper.ok(storageObjectService.upload(file.getBytes(), file.getOriginalFilename()));
        } catch (IOException e) {
            throw new ServiceException(StorageErrorCode.FILE_UPLOAD_FAILED);
        }
    }

    @Operation(summary = "批量上传文件")
    @PostMapping("/batch-upload")
    public Result<List<StorageObjectVO>> batchUpload(@RequestBody @Valid List<StorageObjectUploadParam> params) {
        return ResultWrapper.ok(storageObjectService.batchUpload(params));
    }

    @Operation(summary = "根据ID查询", operationId = "system:storage-object:get")
    @GetMapping("/{id}")
    public Result<StorageObjectVO> getById(@PathVariable("id") String id) {
        return ResultWrapper.ok(storageObjectService.getById(id));
    }


    @Operation(summary = "下载或预览文件")
    @GetMapping("/static/**")
    public ResponseEntity<InputStreamResource> staticFile(HttpServletRequest request) {
        String fileName = request.getRequestURI().substring(request.getRequestURI().indexOf("/static/") + "/static/".length());
        StorageByteObjectVO byteObjectVO = storageObjectService.getByteArrayByIFileName(fileName);
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(byteObjectVO.getContent()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + byteObjectVO.getOriginalName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }


//    /**
//     * 文件下载接口
//     * 依赖：{@link cn.hengzq.orange.system.common.biz.storage.constant.StorageConstant.DOWNLOAD_BY_ID_URL}
//     */
//    @Operation(summary = "根据文件ID下载文件")
//    @GetMapping("/download/{id}")
//    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String id) {
//        StorageByteObjectVO byteObjectVO = storageObjectService.getByteArrayById(id);
//        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(byteObjectVO.getContent()));
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + byteObjectVO.getOriginalName())
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(resource);
//
//    }

//    /**
//     * 用于图片预览接口
//     * 依赖：{@link cn.hengzq.orange.system.common.biz.storage.constant.StorageConstant.PREVIEW_IMAGE_BY_ID_URL}
//     */
//    @Operation(summary = "根据图片ID预览")
//    @GetMapping(value = "/preview-image/{id}")
//    public ResponseEntity<InputStreamResource> previewImage(@PathVariable String id) {
//        StorageByteObjectVO byteObjectVO = storageObjectService.getByteArrayById(id);
//        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(byteObjectVO.getContent()));
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + byteObjectVO.getOriginalName())
//                .contentType(MediaType.IMAGE_PNG) // 根据实际图片格式调整
//                .body(resource);
//
//    }

}
