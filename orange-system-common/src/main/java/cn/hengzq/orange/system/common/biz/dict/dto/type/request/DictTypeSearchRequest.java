package cn.hengzq.orange.system.common.biz.dict.dto.type.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hengzq
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "字典类型-查询所有数据")
public class DictTypeSearchRequest implements Serializable {

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "字典类型")
    private String dictType;
}
