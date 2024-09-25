package cn.hengzq.orange.system.common.biz.user.vo;

import cn.hengzq.orange.common.dto.BaseTenantDTO;
import cn.hengzq.orange.system.common.biz.user.constant.UserGenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "用户管理 - VO")
public class UserVO extends BaseTenantDTO {

    @Schema(description = "主键", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "用户名称")
    private String name;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "用户性别 参考字典:sys_common_user_sex")
    private UserGenderEnum gender;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "登陆账号")
    private String loginAccount;

}
