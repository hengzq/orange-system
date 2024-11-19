package cn.hengzq.orange.system.common.biz.storage.vo.param;

import cn.hengzq.orange.common.dto.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "系统 - 存储 - 配置分页查询")
public class StoragePageParam extends PageParam {

    @Schema(description = "用户名称")
    private String name;

    @Schema(description = "用户名称 模糊查询")
    private String nameLike;

}
