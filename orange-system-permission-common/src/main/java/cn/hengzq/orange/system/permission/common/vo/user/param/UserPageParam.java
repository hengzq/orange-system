package cn.hengzq.orange.system.permission.common.vo.user.param;

import cn.hengzq.orange.common.dto.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "用户管理-查询所有的数据")
public class UserPageParam extends PageParam {

    @Schema(description = "用户名称")
    private String nickname;

    @Schema(description = "用户名称 模糊查询")
    private String nicknameLike;

}
