package cn.hengzq.orange.system.common.biz.role.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author hengzq
 */
@Data
public class AssignResourcesToOneRoleParam implements Serializable {


    @NotNull(message = "角色ID不能为空.")
    @Schema(description = "角色ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String roleId;

    @NotEmpty(message = "菜单ID不能为空")
    @Schema(description = "菜单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> menuIds;

    @Schema(description = "按钮ID")
    private List<String> buttonIds;
}
