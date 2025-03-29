package cn.hengzq.orange.system.common.biz.user.vo.param;

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
public class AssignRolesToOneUserParam implements Serializable {

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "用户ID不能为空.")
    private String userId;

    @Schema(description = "角色Id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "角色ID不能为空")
    private List<String> roleIds;


}