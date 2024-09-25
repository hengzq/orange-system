package cn.hengzq.orange.system.common.biz.menu.vo;

import cn.hengzq.orange.common.dto.BaseTenantDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "按钮管理 - VO")
@Data
public class ButtonVO extends BaseTenantDTO {

    @Schema(description = "按钮ID", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "菜单ID不能为空.")
    @Schema(description = "菜单ID")
    private Long menuId;

    @NotNull(message = "菜单名称不能为空.")
    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "权限编码")
    private String permission;

    @Schema(description = "是否为内置数据 true:是 false：否")
    private boolean preset;

    @Schema(description = "排序")
    private Integer sort;

}
