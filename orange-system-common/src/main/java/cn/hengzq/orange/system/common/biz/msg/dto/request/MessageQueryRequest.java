package cn.hengzq.orange.system.common.biz.msg.dto.request;

import cn.hengzq.orange.common.dto.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "消息管理 - 分页查询参数")
@Data
public class MessageQueryRequest extends PageParam {


}
