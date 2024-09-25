package cn.hengzq.orange.system.common.biz.dict.vo.type.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Data
@Schema(description = "字典类型-查询所有数据")
public class DictTypeListParam implements Serializable {

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "字典类型")
    private String dictType;
}
