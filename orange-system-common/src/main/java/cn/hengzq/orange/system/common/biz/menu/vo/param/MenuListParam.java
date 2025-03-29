package cn.hengzq.orange.system.common.biz.menu.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


/**
 * @author hengzq
 */
@Schema(description = "菜单管理 - 查询参数")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuListParam implements Serializable {

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "菜单名称,模糊查询")
    private String nameLike;

    @Schema(description = "角色ID")
    private List<String> roleIds;

    @Schema(description = "是否返回菜单绑定的按钮.true:是,false:否(默认)")
    private boolean showButton;

}
