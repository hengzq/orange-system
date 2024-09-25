package cn.hengzq.orange.system.common.biz.dict.vo.data.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Data
@Schema(description = "字典数据-全部查询参数")
public class DictDataListParam implements Serializable {


    @Schema(description = "字典类型")
    private String dictType;

}
