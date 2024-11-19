package cn.hengzq.orange.system.common.biz.storage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "系统 - 存储 - 存储方式VO")
public class StorageModeVO implements Serializable {

    @Schema(description = "存储方式名称")
    private String name;

    @Schema(description = "存储方式编码")
    private String code;


}
