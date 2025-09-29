package cn.hengzq.orange.system.core.biz.dict.controller;


import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.response.ApiResponse;
import cn.hengzq.orange.system.common.biz.dict.dto.data.DictDataVO;
import cn.hengzq.orange.system.common.biz.dict.dto.data.param.DictDataCreateRequest;
import cn.hengzq.orange.system.common.biz.dict.dto.data.param.DictDataPageRequest;
import cn.hengzq.orange.system.common.biz.dict.dto.data.param.DictDataSearchReqeust;
import cn.hengzq.orange.system.common.biz.dict.dto.data.param.DictDataUpdateRequest;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.core.biz.dict.service.DictDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 衡哥敲AI代码
 */
@Tag(name = "系统 - 字典-数据管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/dict-data")
public class DictDataController {

    private final DictDataService dictDataService;

    @Operation(summary = "新建字典数据", operationId = "system:dict-data:create")
    @PostMapping
    public ApiResponse<String> create(@Validated @RequestBody DictDataCreateRequest request) {
        return ApiResponse.ok(dictDataService.create(request));
    }

    @Operation(summary = "根据ID删除字典数据", operationId = "system:dict-data:delete")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteById(@Parameter(description = "主键ID") @PathVariable("id") String id) {
        dictDataService.deleteById(id);
        return ApiResponse.ok();
    }

    @Operation(summary = "根据ID更新字典数据", operationId = "system:dict-data:update")
    @PutMapping("/{id}")
    public ApiResponse<Void> updateById(@Parameter(description = "主键ID") @PathVariable("id") String id,
                                        @RequestBody DictDataUpdateRequest request) {
        dictDataService.updateById(id, request);
        return ApiResponse.ok();
    }

    @Operation(summary = "根据ID获取字典数据详情", operationId = "system:dict-data:get")
    @GetMapping("/{id}")
    public ApiResponse<DictDataVO> getById(@PathVariable("id") String id) {
        return ApiResponse.ok(dictDataService.getById(id).orElse(null));
    }

    @Operation(summary = "分页查询字典数据", operationId = "system:dict-data:page")
    @PostMapping(value = "/page")
    public ApiResponse<PageDTO<DictDataVO>> page(@RequestBody DictDataPageRequest request) {
        PageDTO<DictDataVO> result = dictDataService.page(request);
        return ApiResponse.ok(result);
    }

    @Operation(summary = "根据条件查询字典数据列表", operationId = "system:dict-data:search")
    @PostMapping(value = "/search")
    public ApiResponse<List<DictDataVO>> search(@RequestBody DictDataSearchReqeust request) {
        List<DictDataVO> dataVOList = dictDataService.search(request);
        return ApiResponse.ok(dataVOList);
    }

    @Operation(summary = "根据字典类型获取数据列表", operationId = "system:dict-data:get-by-type")
    @GetMapping("/type/{dictType}")
    public ApiResponse<List<DictDataVO>> searchByDictType(@PathVariable String dictType) {
        List<DictDataVO> response = dictDataService.searchByDictType(dictType);
        return ApiResponse.ok(response);
    }
}
