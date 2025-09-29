package cn.hengzq.orange.system.common.biz.dict.dto.type.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Data
@Schema(description = "字典类型-修改参数")
public class DictTypeUpdateRequest implements Serializable {

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "启用状态 true:启用 false：不启用")
    private boolean enabled;

    @Schema(description = "描述")
    private String description;
}
