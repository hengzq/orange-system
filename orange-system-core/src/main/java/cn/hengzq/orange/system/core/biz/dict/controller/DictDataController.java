package cn.hengzq.orange.system.core.biz.dict.controller;


import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.system.common.biz.dict.vo.data.DictDataVO;
import cn.hengzq.orange.system.common.biz.dict.vo.data.param.AddDictDataParam;
import cn.hengzq.orange.system.common.biz.dict.vo.data.param.DictDataListParam;
import cn.hengzq.orange.system.common.biz.dict.vo.data.param.DictDataPageParam;
import cn.hengzq.orange.system.common.biz.dict.vo.data.param.UpdateDictDataParam;
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
 * @author hengzq
 */
@Tag(name = "字典-数据管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/dict-data")
public class DictDataController {

    private final DictDataService dictDataService;

    @Operation(summary = "新建", operationId = "system:dict-data:add")
    @PostMapping
    public Result<Long> add(@Validated @RequestBody AddDictDataParam param) {
        return ResultWrapper.ok(dictDataService.add(param));
    }

    @Operation(summary = "根据ID删除", operationId = "system:dict-data:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@Parameter(description = "主键ID") @PathVariable("id") Long id) {
        return ResultWrapper.ok(dictDataService.removeById(id));
    }


    @Operation(summary = "根据ID修改", operationId = "system:dict-data:update")
    @PutMapping("/{id}")
    public Result<Boolean> update(@Parameter(description = "主键ID") @PathVariable("id") Long id, @RequestBody UpdateDictDataParam param) {
        return ResultWrapper.ok(dictDataService.updateById(id, param));
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:dict-data:get")
    @GetMapping("/{id}")
    public Result<DictDataVO> getById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(dictDataService.getById(id));
    }

    @Operation(summary = "分页查询", operationId = "system:dict-data:page")
    @PostMapping(value = "/page")
    public Result<PageDTO<DictDataVO>> page(@RequestBody DictDataPageParam param) {
        PageDTO<DictDataVO> result = dictDataService.page(param);
        return ResultWrapper.ok(result);
    }

    @Operation(summary = "根据参数查询数据", operationId = "system:dict-data:list")
    @PostMapping(value = "/list")
    public Result<List<DictDataVO>> list(@RequestBody DictDataListParam param) {
        List<DictDataVO> dataVOList = dictDataService.list(param);
        return ResultWrapper.ok(dataVOList);
    }
//
//    @Operation(summary = "根据字典类型获取数据", operationId = "system:dict-data:query-by-type")
//    @GetMapping("/query-by-type/{dictType}")
//    public Result<List<DictDataVO>> queryByType(@PathVariable String dictType) {
//        List<DictDataEntity> entityList = dictDataService.listByType(dictType);
//        return ResultWrapper.ok(DictDataConverter.INSTANCE.toListVo(entityList));
//    }
}
