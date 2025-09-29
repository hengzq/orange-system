package cn.hengzq.orange.system.core.biz.msg.controller;


import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.response.ApiResponse;
import cn.hengzq.orange.system.common.biz.msg.dto.MessageItemResponse;
import cn.hengzq.orange.system.common.biz.msg.dto.request.MessageQueryRequest;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.core.biz.msg.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hengzq
 */
@Tag(name = "系统 - 消息管理", description = "消息管理 - 消息管理相关的增删改查操作")
@RestController
@RequiredArgsConstructor
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping(value = "/page")
    @Operation(summary = "分页查询模板", description = "根据条件分页查询信息", operationId = "system:message:page")
    public ApiResponse<PageDTO<MessageItemResponse>> page(@RequestBody MessageQueryRequest request) {
        PageDTO<MessageItemResponse> list = messageService.page(request);
        return ApiResponse.ok(list);
    }
}
