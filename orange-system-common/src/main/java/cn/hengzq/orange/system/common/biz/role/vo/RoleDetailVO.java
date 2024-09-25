package cn.hengzq.orange.system.common.biz.role.vo;

import cn.hengzq.orange.system.common.biz.menu.vo.ButtonVO;
import cn.hengzq.orange.system.common.biz.menu.vo.MenuVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色管理 - 角色信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDetailVO extends RoleVO {

    @Schema(description = "角色拥有菜单信息")
    private List<MenuVO> menus;

    @Schema(description = "角色拥有的按钮信息")
    private List<ButtonVO> buttons;

}
