package cn.hengzq.orange.system.dict.common.vo.data;

import cn.hengzq.orange.common.dto.BaseTenantDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
  * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "字典数据")
@Data
public class DictDataVO extends BaseTenantDTO {

    @Schema(description = "主键", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "字典类型不能为空")
    @Schema(description = "字典类型")
    private String dictType;

    @NotBlank(message = "字典标签不能为空")
    @Schema(description = "字典数据标签")
    private String dictLabel;

    @NotNull(message = "字典数据键值不能为空")
    @Schema(description = "字典数据键值")
    private String dictValue;

    @Schema(description = "回显样式")
    private String showStyle;

    @Schema(description = "模型启用状态 true:启用 false：不启用")
    private boolean enabled;

    @Schema(description = "模型启用状态 true:是 false：否")
    private boolean preset;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "描述")
    private String description;

}
