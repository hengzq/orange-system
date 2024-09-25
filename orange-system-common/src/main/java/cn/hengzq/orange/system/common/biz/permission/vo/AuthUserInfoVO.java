package cn.hengzq.orange.system.common.biz.permission.vo;

import cn.hengzq.orange.system.common.biz.user.vo.UserVO;
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
@Schema(description = "登陆用户的信息")
public class AuthUserInfoVO extends UserVO {

    @Schema(description = "角色权限标识")
    private List<String> rolePermissions;

    @Schema(description = "用户拥有的菜单")
    private List<Menu> menus;

    @Schema(description = "菜单权限标识")
    private List<String> menuPermissions;

    @Schema(description = "按钮权限标识")
    private List<String> buttonPermissions;

    @Data
    public static class Menu {
        @Schema(description = "菜单ID", accessMode = Schema.AccessMode.READ_ONLY)
        private Long id;

        @Schema(description = "父级别ID")
        private Long parentId;

        @Schema(description = "菜单名称")
        private String name;

        @Schema(description = "权限编码")
        private String permission;

        @Schema(description = "菜单路径")
        private String path;

        @Schema(description = "菜单图标")
        private String icon;

        @Schema(description = "排序")
        private Integer sort;
    }
}
