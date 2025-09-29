package cn.hengzq.orange.system.core.biz.msg.controller;


import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.response.ApiResponse;
import cn.hengzq.orange.system.common.biz.msg.dto.MessageTemplateDetailResponse;
import cn.hengzq.orange.system.common.biz.msg.dto.MessageTemplateResponse;
import cn.hengzq.orange.system.common.biz.msg.dto.request.MessageTemplateCreateRequest;
import cn.hengzq.orange.system.common.biz.msg.dto.request.MessageTemplatePageRequest;
import cn.hengzq.orange.system.common.biz.msg.dto.request.MessageTemplateUpdateRequest;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.core.biz.msg.service.MessageTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author hengzq
 */
@Tag(name = "系统 - 消息管理 - 消息模板", description = "消息管理 - 消息模板相关的增删改查操作")
@RestController
@RequiredArgsConstructor
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/message-templates")
public class MsgTemplateController {

    private final MessageTemplateService messageTemplateService;

    @Operation(summary = "创建模板", description = "创建一个新的信息模板", operationId = "system:message-template:create")
    @PostMapping
    public ApiResponse<String> create(@RequestBody @Validated MessageTemplateCreateRequest request) {
        return ApiResponse.ok(messageTemplateService.create(request));
    }

    @Operation(summary = "删除消息模板", description = "根据模板ID删除指定的角色", operationId = "system:message-template:delete")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteById(@PathVariable("id") String id) {
        messageTemplateService.deleteById(id);
        return ApiResponse.ok();
    }

    @Operation(summary = "更新模板信息", description = "根据模板ID更新模板信息", operationId = "system:message-template:update")
    @PutMapping("/{id}")
    public ApiResponse<Void> updateById(@PathVariable("id") String id, @RequestBody @Validated MessageTemplateUpdateRequest request) {
        messageTemplateService.updateById(id, request);
        return ApiResponse.ok();
    }

    @Operation(summary = "根据ID查询", operationId = "system:message-template:get")
    @GetMapping("/{id}")
    public ApiResponse<MessageTemplateResponse> getById(@PathVariable("id") String id) {
        Optional<MessageTemplateResponse> response = messageTemplateService.getById(id);
        return ApiResponse.ok(response.orElse(null));
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:message-template:get-detail")
    @GetMapping("/{id}/detail")
    public ApiResponse<MessageTemplateDetailResponse> getDetailById(@PathVariable("id") String id) {
        return ApiResponse.ok(messageTemplateService.getDetailById(id));
    }

    @PostMapping(value = "/page")
    @Operation(summary = "分页查询模板", description = "根据条件分页查询模板信息", operationId = "system:message-template:page")
    public ApiResponse<PageDTO<MessageTemplateResponse>> page(@RequestBody MessageTemplatePageRequest request) {
        PageDTO<MessageTemplateResponse> list = messageTemplateService.page(request);
        return ApiResponse.ok(list);
    }
}
