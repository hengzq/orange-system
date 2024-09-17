package cn.hengzq.orange.system.permission.common.vo.user.param;

import cn.hengzq.orange.system.permission.common.enums.UserGenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Data
@Schema(description = "用户管理-更新参数")
public class UpdateUserParam implements Serializable {

    @Schema(description = "用户名称")
    private String nickname;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "用户性别")
    private UserGenderEnum gender;

    @Schema(description = "手机号")
    private String phone;

}
