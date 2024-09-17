package cn.hengzq.orange.system.dict.core.data.entity;

import cn.hengzq.orange.mybatis.entity.BaseTenantEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
  * @author hengzq
 */
@Data
@TableName(value = "sys_dict_data")
public class DictDataEntity extends BaseTenantEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("dict_type")
    private String dictType;

    @TableField("dict_label")
    private String dictLabel;

    @TableField("dict_value")
    private String dictValue;

    /**
     * 回显样式
     */
    @TableField("show_style")
    private String showStyle;

    /**
     * 模型启用状态 true:启用 false：不启用
     */
    private boolean enabled;

    /**
     * 模型启用状态 true:是 false：否
     */
    private boolean preset;

    @TableField("sort")
    private Integer sort;

    @TableField("description")
    private String description;
}
