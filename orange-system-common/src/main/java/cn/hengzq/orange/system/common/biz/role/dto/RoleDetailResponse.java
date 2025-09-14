package cn.hengzq.orange.system.common.biz.role.dto;

import cn.hengzq.orange.common.dto.BaseTenantDTO;
import cn.hengzq.orange.system.common.biz.menu.vo.ButtonVO;
import cn.hengzq.orange.system.common.biz.menu.vo.MenuVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色管理 - 角色详情信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDetailResponse extends BaseTenantDTO {

    @Schema(description = "角色id", accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    @Schema(description = "角色名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "角色权限字符串")
    private String permission;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "模型启用状态 true:启用 false：不启用")
    private boolean enabled;

    @Schema(description = "模型启用状态 true:是 false：否")
    private boolean preset;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "角色拥有菜单信息")
    private List<MenuVO> menus;

    @Schema(description = "角色拥有的按钮信息")
    private List<ButtonVO> buttons;

}
