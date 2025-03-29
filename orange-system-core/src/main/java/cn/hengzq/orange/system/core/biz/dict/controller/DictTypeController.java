package cn.hengzq.orange.system.core.biz.dict.controller;


import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.system.common.biz.dict.vo.type.DictTypeVO;
import cn.hengzq.orange.system.common.biz.dict.vo.type.param.AddDictTypeParam;
import cn.hengzq.orange.system.common.biz.dict.vo.type.param.DictTypeListParam;
import cn.hengzq.orange.system.common.biz.dict.vo.type.param.DictTypePageParam;
import cn.hengzq.orange.system.common.biz.dict.vo.type.param.UpdateDictTypeParam;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.core.biz.dict.service.DictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author hengzq
 */
@Tag(name = "系统 - 字典-类型管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/dict-type")
public class DictTypeController {

    private final DictTypeService dictTypeService;

    @Operation(summary = "新建", operationId = "system:dict-type:add")
    @PostMapping
    public Result<String> add(@RequestBody @Validated AddDictTypeParam param) {
        return ResultWrapper.ok(dictTypeService.add(param));
    }

    @Operation(summary = "根据ID删除", operationId = "system:dict-type:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> removeById(@PathVariable("id") String id) {
        return ResultWrapper.ok(dictTypeService.removeById(id));
    }

    @Operation(summary = "根据ID更新", operationId = "system:dict-type:update")
    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable("id") String id, @RequestBody UpdateDictTypeParam param) {
        return ResultWrapper.ok(dictTypeService.updateById(id, param));
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:dict-type:get")
    @GetMapping("/{id}")
    public Result<DictTypeVO> getById(@PathVariable("id") String id) {
        return ResultWrapper.ok(dictTypeService.getById(id));
    }

    @Operation(summary = "分页查询", operationId = "system:dict-type:page")
    @PostMapping(value = "/page")
    public Result<PageDTO<DictTypeVO>> page(@RequestBody DictTypePageParam param) {
        PageDTO<DictTypeVO> result = dictTypeService.page(param);
        return ResultWrapper.ok(result);
    }

    @Operation(summary = "查询所有数据", operationId = "system:dict-type:all")
    @PostMapping(value = "/list")
    public Result<List<DictTypeVO>> list(@RequestBody DictTypeListParam queryVo) {
        List<DictTypeVO> result = dictTypeService.list(queryVo);
        return ResultWrapper.ok(result);
    }

}
