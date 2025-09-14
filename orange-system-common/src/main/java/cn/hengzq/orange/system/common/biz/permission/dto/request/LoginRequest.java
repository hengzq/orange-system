package cn.hengzq.orange.system.common.biz.permission.dto.request;


import cn.hengzq.orange.common.constant.TenantConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author hengzq
 */
@Data
@Schema(description = "登陆请求参数")
public class LoginRequest {

    @Schema(description = "登陆账号")
    @NotBlank(message = "登陆账号不能为空.")
    private String loginAccount;

    @Schema(description = "登陆密码")
    @NotBlank(message = "登陆密码不能为空.")
    private String loginPassword;

    @Schema(description = "租户ID, 默认:-100", defaultValue = "-100")
    private String tenantId = TenantConstant.DEFAULT_TENANT_ID;

}
