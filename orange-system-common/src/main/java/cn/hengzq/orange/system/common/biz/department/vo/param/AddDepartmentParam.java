package cn.hengzq.orange.system.common.biz.department.vo.param;

import cn.hengzq.orange.system.common.biz.department.constant.DepartmentErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Data
@Schema(description = "部门管理-新增参数")
public class AddDepartmentParam implements Serializable {

    @Schema(description = "父级ID,默认:-1（为顶级部门）")
    private String parentId;

    @NotBlank(message = DepartmentErrorCode.DEPARTMENT_NAME_CANNOT_NULL_CODE)
    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "部门描述")
    @Size(max = 500, message = "长度必须小于等于500")
    private String description;
}
