package cn.hengzq.orange.system.permission.common.vo.button.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Schema(description = "按钮管理 - 修改请求参数")
@Data
public class UpdateButtonParam implements Serializable {

    @Schema(description = "按钮名称")
    private String name;

    @Schema(description = "权限编码")
    private String permission;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "备注")
    private String remark;
}
