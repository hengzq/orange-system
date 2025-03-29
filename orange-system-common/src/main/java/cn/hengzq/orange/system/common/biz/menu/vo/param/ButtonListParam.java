package cn.hengzq.orange.system.common.biz.menu.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Schema(description = "按钮管理 - 查询所有数据参数")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ButtonListParam implements Serializable {

    @Schema(description = "菜单ID")
    private String menuId;

    @Schema(description = "角色 Ids")
    private List<String> roleIds;

    @Schema(description = "菜单IDS")
    private List<String> menuIds;
}
