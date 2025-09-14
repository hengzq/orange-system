package cn.hengzq.orange.system.common.biz.user.dto;

import cn.hengzq.orange.system.common.biz.role.dto.RoleResponse;
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
public class UserDetailResponse extends UserResponse {

    @Schema(description = "用户关联的部门", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> departmentIds;

    @Schema(description = "用户拥有角色")
    private List<RoleResponse> roles;


}
