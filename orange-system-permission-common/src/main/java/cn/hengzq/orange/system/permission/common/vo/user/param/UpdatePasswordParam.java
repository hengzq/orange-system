package cn.hengzq.orange.system.permission.common.vo.user.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "用户管理-修改密码")
public class UpdatePasswordParam implements Serializable {

    @Schema(description = "用户ID,用户ID为空，修改当前用户密码")
    private Long userId;

    @Schema(description = "旧密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "旧密码不能为空.")
    private String oldPassword;

    @Schema(description = "新密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "新密码不能为空.")
    private String newPassword;
}
