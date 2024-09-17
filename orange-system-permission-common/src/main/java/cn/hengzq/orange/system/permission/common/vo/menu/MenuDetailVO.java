package cn.hengzq.orange.system.permission.common.vo.menu;

import cn.hengzq.orange.common.dto.BaseTenantDTO;
import cn.hengzq.orange.system.permission.common.vo.button.ButtonVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "菜单管理 - 详情")
@Data
public class MenuDetailVO extends MenuVO {

    @Schema(description = "菜单关联按钮")
    private List<ButtonVO> buttons;
}
