package cn.hengzq.orange.system.dict.common.vo.type;

import cn.hengzq.orange.common.dto.BaseTenantDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DictTypeVO extends BaseTenantDTO {

    @Schema(description = "主键", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "模型启用状态 true:启用 false：不启用")
    private boolean enabled;

    @Schema(description = "模型启用状态 true:是 false：否")
    private boolean preset;

    @Schema(description = "描述")
    private String description;
}
