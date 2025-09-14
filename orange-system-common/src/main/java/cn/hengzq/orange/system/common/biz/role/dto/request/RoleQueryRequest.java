package cn.hengzq.orange.system.common.biz.role.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Schema(description = "角色管理 - 获取所有数据请求参数")
@Data
public class RoleQueryRequest implements Serializable {

    @Schema(description = "角色名称")
    private String name;
}
