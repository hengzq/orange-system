package cn.hengzq.orange.system.permission.common.vo.user.param;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.exception.ServiceException;
import cn.hengzq.orange.system.permission.common.exception.UserErrorCode;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "用户管理-重置密码")
public class ResetPasswordParam implements Serializable {

    @Schema(description = "用户ID")
    @NotNull(message = "用户ID,用户ID为空")
    private Long userId;

    @Schema(description = "新密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "新密码不能为空.")
    private String newPassword;

    @Schema(description = "确认密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "确认密码不能为空.")
    private String confirmPassword;

    public void checkParams() {
        if (StrUtil.isBlank(newPassword)) {
            throw new ServiceException(GlobalErrorCodeConstant.GLOBAL_PARAMETER_CANNOT_NULL);
        }
        if (StrUtil.isBlank(confirmPassword)) {
            throw new ServiceException(GlobalErrorCodeConstant.GLOBAL_PARAMETER_CANNOT_NULL);
        }
        if (!confirmPassword.equals(newPassword)) {
            throw new ServiceException(UserErrorCode.USER_RESET_PASSWORD_INCONSISTENT);
        }
    }
}
