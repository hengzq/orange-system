package cn.hengzq.orange.system.common.biz.dict.vo.type.param;

import cn.hengzq.orange.common.dto.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
  * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "字典类型-分页查询")
public class DictTypePageParam extends PageParam {

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "字典类型")
    private String dictType;
}
