package cn.hengzq.orange.system.core.biz.dict.controller;


import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.response.ApiResponse;
import cn.hengzq.orange.system.common.biz.dict.dto.type.DictTypeResponse;
import cn.hengzq.orange.system.common.biz.dict.dto.type.request.DictTypeCreateRequest;
import cn.hengzq.orange.system.common.biz.dict.dto.type.request.DictTypePageRequest;
import cn.hengzq.orange.system.common.biz.dict.dto.type.request.DictTypeSearchRequest;
import cn.hengzq.orange.system.common.biz.dict.dto.type.request.DictTypeUpdateRequest;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.core.biz.dict.service.DictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


/**
 * @author hengzq
 */
@Tag(name = "系统 - 字典-类型管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/dict-types")
public class DictTypeController {

    private final DictTypeService dictTypeService;

    @Operation(summary = "创建字典类型", operationId = "system:dict-type:create")
    @PostMapping
    public ApiResponse<String> create(@RequestBody @Validated DictTypeCreateRequest request) {
        return ApiResponse.ok(dictTypeService.create(request));
    }

    @Operation(summary = "根据ID删除字典类型", operationId = "system:dict-type:delete")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteById(@PathVariable("id") String id) {
        dictTypeService.deleteById(id);
        return ApiResponse.ok();
    }

    @Operation(summary = "根据ID更新字典类型", operationId = "system:dict-type:update")
    @PutMapping("/{id}")
    public ApiResponse<Void> updateById(@PathVariable("id") String id, @RequestBody DictTypeUpdateRequest request) {
        dictTypeService.updateById(id, request);
        return ApiResponse.ok();
    }

    @Operation(summary = "根据ID查询字典类型详情", operationId = "system:dict-type:get")
    @GetMapping("/{id}")
    public ApiResponse<DictTypeResponse> getById(@PathVariable("id") String id) {
        Optional<DictTypeResponse> response = dictTypeService.getById(id);
        return ApiResponse.ok(response.orElse(null));
    }

    @Operation(summary = "分页查询字典类型", operationId = "system:dict-type:page")
    @PostMapping(value = "/page")
    public ApiResponse<PageDTO<DictTypeResponse>> page(@RequestBody DictTypePageRequest request) {
        PageDTO<DictTypeResponse> result = dictTypeService.page(request);
        return ApiResponse.ok(result);
    }

    @Operation(
            summary = "搜索字典类型（支持条件筛选，无条件时返回全部）",
            description = "所有参数均为可选。若不传任何条件，则返回所有字典类型列表。"
    )
    @PostMapping(value = "/search")
    public ApiResponse<List<DictTypeResponse>> search(@RequestBody(required = false) DictTypeSearchRequest request) {
        if (Objects.isNull(request)) {
            request = new DictTypeSearchRequest();
        }
        List<DictTypeResponse> result = dictTypeService.search(request);
        return ApiResponse.ok(result);
    }

}
