package cn.hengzq.orange.system.common.biz.user.vo.param;

import cn.hengzq.orange.system.common.biz.user.constant.UserGenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author hengzq
 */
@Data
@Schema(description = "用户管理-更新参数")
public class UpdateUserParam implements Serializable {

    @Schema(description = "用户名称")
    private String name;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "用户性别")
    private UserGenderEnum gender;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "用户关联的部门", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> departmentIds;
}
