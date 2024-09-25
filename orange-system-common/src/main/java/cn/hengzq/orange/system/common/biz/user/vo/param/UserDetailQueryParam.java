package cn.hengzq.orange.system.common.biz.user.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Data
@Schema(description = "用户管理 - 用户信息查询参数")
public class UserDetailQueryParam implements Serializable {

    @Schema(description = "是否返回用户拥有角色.true:是,false:否(默认)")
    private boolean showRole;
}
