package cn.hengzq.orange.system.permission.common.vo.menu.param;

import cn.hengzq.orange.common.constant.ValidatedGroupConstant;
import cn.hengzq.orange.system.permission.common.exception.MenuErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Schema(description = "菜单管理 - 新增参数")
@Data
public class AddMenuParam implements Serializable {

    @Schema(description = "父级别ID")
    private Long parentId;

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