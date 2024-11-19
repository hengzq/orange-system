package cn.hengzq.orange.system.common.biz.storage.vo.param;

import cn.hengzq.orange.system.common.biz.storage.constant.StorageModeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author hengzq
 */
@Data
@Schema(description = "系统 - 存储 - 配置修改参数")
public class UpdateStorageConfigParam implements Serializable {

    //    @NotNull(message = "配置名称不能为空")
    @Schema(description = "配置名称")
    private String name;

//    @Schema(description = "配置参数")
//    @NotNull(message = "存储配置不能为空")
//    private Map<String, Object> config;

    @Schema(description = "是否设置为主节点")
    private Boolean master;

    @Schema(description = "描述")
//    @Size(max = 500, message = "长度必须小于等于500")
    private String description;
}
