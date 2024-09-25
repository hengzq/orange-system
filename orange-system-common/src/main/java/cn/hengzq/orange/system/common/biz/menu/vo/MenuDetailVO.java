package cn.hengzq.orange.system.common.biz.menu.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
