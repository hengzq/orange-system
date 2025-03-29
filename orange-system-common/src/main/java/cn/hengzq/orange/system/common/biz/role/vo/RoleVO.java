package cn.hengzq.orange.system.common.biz.role.vo;

import cn.hengzq.orange.common.dto.BaseTenantDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色")
@Data
public class RoleVO extends BaseTenantDTO {

    @Schema(description = "角色id", accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    @Schema(description = "角色名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "角色权限字符串")
    private String permission;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "模型启用状态 true:启用 false：不启用")
    private boolean enabled;

    @Schema(description = "模型启用状态 true:是 false：否")
    private boolean preset;

    @Schema(description = "备注")
    private String remark;

}
