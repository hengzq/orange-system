package cn.hengzq.orange.system.common.biz.role.dto.request;

import cn.hengzq.orange.common.dto.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色管理 - 分页查询参数")
@Data
public class RolePageRequest extends PageParam {

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色名称,模糊查询")
    private String nameLike;

    @Schema(description = "角色权限编码")
    private String permission;

    @Schema(description = "权限编码,模糊匹配")
    private String permissionLike;


}
