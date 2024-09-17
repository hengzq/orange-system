package cn.hengzq.orange.system.permission.common.vo.user.param;

import cn.hengzq.orange.system.permission.common.enums.UserGenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author hengzq
 */
@Data
@Schema(description = "用户管理-新增参数")
public class AddUserParam implements Serializable {

    @Schema(description = "用户名称")
    private String nickname;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "用户性别 参考字典:sys_common_user_sex")
    private UserGenderEnum gender;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "登陆账号")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "用户关联的部门", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<Long> departmentIds;
}
