package cn.hengzq.orange.system.common.biz.menu.vo;

import cn.hengzq.orange.common.dto.BaseTenantDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "菜单管理 - VO")
@Data
public class MenuVO extends BaseTenantDTO {

    @Schema(description = "菜单ID", accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    @Schema(description = "父级别ID")
    private String parentId;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "权限编码")
    private String permission;

    @Schema(description = "是否为内置数据 true:是 false：否")
    private boolean preset;

    @Schema(description = "菜单路径")
    private String path;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "是否隐藏 true：隐藏 false：不隐藏")
    private Boolean hidden;

    @Schema(description = "排序")
    private Integer sort;
}
