package cn.hengzq.orange.system.core.biz.dict.entity;

import cn.hengzq.orange.mybatis.entity.BaseTenantEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_dict_type")
public class DictTypeEntity extends BaseTenantEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 字典名称
     */
    @TableField("name")
    private String name;

    /**
     * 字典类型
     */
    @TableField("dict_type")
    private String dictType;

    /**
     * 模型启用状态 true:启用 false：不启用
     */
    private boolean enabled;

    /**
     * 模型启用状态 true:是 false：否
     */
    private boolean preset;

    /**
     * 描述
     */
    @TableField("description")
    private String description;
}
