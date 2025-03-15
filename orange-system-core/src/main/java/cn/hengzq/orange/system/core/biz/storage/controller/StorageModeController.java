package cn.hengzq.orange.system.core.biz.storage.controller;

import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.storage.constant.StorageMode;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.common.biz.storage.vo.StorageModeVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Tag(name = "系统 - 存储 - 存储方式")
@RestController
@AllArgsConstructor
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/storage-mode")
public class StorageModeController {

    @PostMapping(value = "/list")
    @Operation(summary = "查询所有的数据", operationId = "system:storage-mode:list", description = "返回所有的数据")
    public Result<List<StorageModeVO>> list() {
        List<StorageModeVO> list = Arrays.stream(StorageMode.values())
                .map(item -> new StorageModeVO(item.getMsg(), item.name())).toList();
        return ResultWrapper.ok(list);
    }
}
