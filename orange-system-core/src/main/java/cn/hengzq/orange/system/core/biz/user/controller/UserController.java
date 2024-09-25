package cn.hengzq.orange.system.core.biz.user.controller;


import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.system.common.biz.user.vo.UserDetailVO;
import cn.hengzq.orange.system.common.biz.user.vo.UserVO;
import cn.hengzq.orange.system.common.biz.user.vo.param.*;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.permission.core.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hengzq
 */
@Tag(name = "用户管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "新建", operationId = "system:user:add")
    @PostMapping
    public Result<Long> add(@RequestBody @Validated AddUserParam param) {
        return ResultWrapper.ok(userService.add(param));
    }

    @Operation(summary = "根据ID删除", operationId = "system:user:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> removeById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(true);
    }

    @Operation(summary = "根据ID更新", operationId = "system:user:update")
    @PutMapping("/{id}")
    public Result<Boolean> updateById(@PathVariable("id") Long id, @RequestBody @Validated UpdateUserParam param) {
        return ResultWrapper.ok(userService.updateById(id, param));
    }

    @Operation(summary = "根据ID查询", operationId = "system:user:get")
    @GetMapping("/{id}")
    public Result<UserDetailVO> getById(@PathVariable("id") Long id, @ParameterObject UserDetailQueryParam param) {
        return ResultWrapper.ok(userService.getById(id, param));
    }

    @PostMapping(value = "/page")
    @Operation(summary = "分页查询", operationId = "system:user:page")
    public Result<PageDTO<UserVO>> page(@RequestBody UserPageParam param) {
        PageDTO<UserVO> list = userService.page(param);
        return ResultWrapper.ok(list);
    }

    @PostMapping(value = "/list")
    @Operation(summary = "查询所有的数据", operationId = "system:user:list", description = "返回所有的数据")
    public Result<List<UserVO>> list(@RequestBody UserListParam param) {
        List<UserVO> list = userService.list(param);
        return ResultWrapper.ok(list);
    }
}
