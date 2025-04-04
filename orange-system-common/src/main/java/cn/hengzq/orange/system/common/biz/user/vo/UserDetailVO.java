package cn.hengzq.orange.system.common.biz.user.vo;

import cn.hengzq.orange.system.common.biz.role.vo.RoleVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户管理 - 用户信息VO")
public class UserDetailVO extends UserVO {

    @Schema(description = "用户关联的部门", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> departmentIds;

    @Schema(description = "用户拥有角色")
    private List<RoleVO> roles;


}
