package cn.hengzq.orange.system.core.biz.msg.service;

import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.msg.dto.MessageItemResponse;
import cn.hengzq.orange.system.common.biz.msg.dto.request.MessageQueryRequest;

/**
 * @author 衡哥敲AI代码
 */
public interface MessageService {

    PageDTO<MessageItemResponse> page(MessageQueryRequest request);

}
