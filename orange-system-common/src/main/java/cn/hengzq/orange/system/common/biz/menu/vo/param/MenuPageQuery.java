package cn.hengzq.orange.system.common.biz.menu.vo.param;


import cn.hengzq.orange.common.dto.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "菜单管理 - 分页查询")
@Data
public class MenuPageQuery extends PageParam {

}
