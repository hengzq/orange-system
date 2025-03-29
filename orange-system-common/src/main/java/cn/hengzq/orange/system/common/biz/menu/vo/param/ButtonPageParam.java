package cn.hengzq.orange.system.common.biz.menu.vo.param;

import cn.hengzq.orange.common.dto.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Schema(description = "按钮管理 - 分页查询请求参数")
@Data
public class ButtonPageParam extends PageParam {

    @Schema(description = "菜单ID")
    private String menuId;
}
