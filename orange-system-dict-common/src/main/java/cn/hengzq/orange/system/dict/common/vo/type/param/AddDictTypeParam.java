package cn.hengzq.orange.system.dict.common.vo.type.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Data
@Schema(description = "字典类型-新增参数")
public class AddDictTypeParam implements Serializable {

    @Schema(description = "字典名称")
    private String name;

    @NotBlank(message = "xxx")
    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "模型启用状态 true:启用 false：不启用")
    private boolean enabled;

    @Schema(description = "描述")
    private String description;
}
