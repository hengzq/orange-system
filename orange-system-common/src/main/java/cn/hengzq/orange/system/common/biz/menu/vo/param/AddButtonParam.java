package cn.hengzq.orange.system.common.biz.menu.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Schema(description = "按钮管理 - 新增请求参数")
@Data
public class AddButtonParam implements Serializable {

    @NotNull(message = "菜单ID不能为空.")
    @Schema(description = "菜单ID")
    private String menuId;

    @NotNull(message = "菜单名称不能为空.")
    @Schema(description = "菜单名称")
    private String name;

    @NotNull(message = "权限编码不能为空.")
    @Schema(description = "权限编码")
    private String permission;

    @Schema(description = "排序")
    private Integer sort;

}
