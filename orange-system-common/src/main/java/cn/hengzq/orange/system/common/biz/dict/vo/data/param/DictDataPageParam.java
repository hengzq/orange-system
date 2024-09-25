package cn.hengzq.orange.system.common.biz.dict.vo.data.param;

import cn.hengzq.orange.common.dto.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
  * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "字典数据-分页查询")
public class DictDataPageParam extends PageParam {
    
    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "字典标签")
    private String dictLabel;

    @Schema(description = "字典标签,模糊查询")
    private String dictLabelLike;
}
