package cn.hengzq.orange.system.permission.common.vo.permission;

import cn.hengzq.orange.common.dto.BaseTenantDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Schema(description = "权限管理 - 路由")
@Data
public class RouterVO implements Serializable {

    @Schema(description = "菜单ID", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "父级别ID")
    private Long parentId;

    @Schema(description = "路由名称")
    private String label;

    @Schema(description = "权限编码")
    private String permission;

    @Schema(description = "菜单路径")
    private String path;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "排序")
    private Integer sort;
}
