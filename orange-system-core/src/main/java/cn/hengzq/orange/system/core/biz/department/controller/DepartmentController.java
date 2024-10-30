package cn.hengzq.orange.system.core.biz.department.controller;


import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.system.common.biz.department.vo.DepartmentTreeVO;
import cn.hengzq.orange.system.common.biz.department.vo.DepartmentVO;
import cn.hengzq.orange.system.common.biz.department.vo.param.AddDepartmentParam;
import cn.hengzq.orange.system.common.biz.department.vo.param.DepartmentListParam;
import cn.hengzq.orange.system.common.biz.department.vo.param.UpdateDepartmentParam;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.core.biz.department.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hengzq
 */
@Tag(name = "系统 - 部门管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(summary = "新建", operationId = "system:department:add")
    @PostMapping
    public Result<Long> add(@RequestBody @Validated AddDepartmentParam request) {
        return ResultWrapper.ok(departmentService.add(request));
    }

    @Operation(summary = "根据ID删除", operationId = "system:department:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> removeById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(departmentService.removeById(id));
    }

    @Operation(summary = "根据ID更新", operationId = "system:department:update")
    @PutMapping("/{id}")
    public Result<Boolean> updateById(@PathVariable("id") Long id, @RequestBody @Validated UpdateDepartmentParam request) {
        return ResultWrapper.ok(departmentService.updateById(id, request));
    }

    @Operation(summary = "根据ID查询", operationId = "system:department:get")
    @GetMapping("/{id}")
    public Result<DepartmentVO> getById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(departmentService.getById(id));
    }

    @PostMapping(value = "/list-tree")
    @Operation(summary = "树型结构数据", operationId = "system:department:list-tree", description = "返回所有的数据")
    public Result<List<DepartmentTreeVO>> listTree(@RequestBody DepartmentListParam query) {
        List<DepartmentTreeVO> treeVoList = departmentService.listTree(query);
        return ResultWrapper.ok(treeVoList);
    }

    @PostMapping(value = "/list")
    @Operation(summary = "查询所有的数据", operationId = "system:department:list", description = "返回所有的数据")
    public Result<List<DepartmentVO>> list(@RequestBody DepartmentListParam query) {
        List<DepartmentVO> list = departmentService.list(query);
        return ResultWrapper.ok(list);
    }
}
