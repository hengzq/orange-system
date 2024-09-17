package cn.hengzq.orange.system.permission.common.vo.role.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Data
@Schema(description = "角色管理 - 角色信息查询参数")
public class RoleDetailQueryParam implements Serializable {

    @Schema(description = "是否返回角色拥有的菜单信息.true:是,false:否(默认)")
    private boolean showMenu;

    @Schema(description = "是否返回角色拥有的按钮信息.true:是,false:否(默认)")
    private boolean showButton;
}
