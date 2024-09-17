package cn.hengzq.orange.system.permission.common.vo.button.param;

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
    private Long menuId;

    @Schema(description = "角色 Ids")
    private List<Long> roleIds;

    @Schema(description = "菜单IDS")
    private List<Long> menuIds;
}
