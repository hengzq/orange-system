package cn.hengzq.orange.system.permission.common.vo.role.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Schema(description = "角色更新参数")
@Data
public class UpdateRoleParam implements Serializable {

    @Schema(description = "角色名称")
    private String name;

    //    @NotNull(message = RoleErrorCode.ROLE_PERMISSION_CANNOT_NULL_KEY)
    @Schema(description = "角色权限编码")
    private String permission;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "模型启用状态 true:启用 false：不启用")
    private boolean enabled;

    @Schema(description = "备注")
    private String remark;

}
