package cn.hengzq.orange.system.common.biz.menu.vo.param;

import cn.hengzq.orange.common.constant.ValidatedGroupConstant;
import cn.hengzq.orange.system.common.biz.menu.constant.MenuErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Schema(description = "菜单管理 - 修改参数")
@Data
public class UpdateMenuParam implements Serializable {

    @Schema(description = "父级别ID")
    private String parentId;

    @NotNull(groups = {ValidatedGroupConstant.ADD.class}, message = MenuErrorCode.MENU_NAME_CANNOT_NULL_KEY)
    @Schema(description = "菜单名称")
    private String name;

    @NotNull(groups = {ValidatedGroupConstant.ADD.class}, message = MenuErrorCode.MENU_PERMISSION_CANNOT_NULL_KEY)
    @Schema(description = "权限编码")
    private String permission;

    @Schema(description = "菜单路径")
    private String path;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "是否隐藏 true：隐藏 false：不隐藏")
    private Boolean hidden;

    @Schema(description = "排序")
    private Integer sort;
}
