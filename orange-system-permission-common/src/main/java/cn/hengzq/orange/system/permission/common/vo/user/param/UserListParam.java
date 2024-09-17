package cn.hengzq.orange.system.permission.common.vo.user.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author hengzq
 */
@Data
@Schema(description = "用户管理-查询所有的数据")
public class UserListParam implements Serializable {


    @Schema(description = "批量 - 用户ID")
    private List<Long> ids;

    @Schema(description = "用户名称")
    private String nickname;

    @Schema(description = "用户名称 模糊查询")
    private String nicknameLike;


}
