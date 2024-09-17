package cn.hengzq.orange.system.permission.core.entity;

import cn.hengzq.orange.mybatis.entity.BaseTenantEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 按钮管理表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_button")
public class ButtonEntity extends BaseTenantEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("menu_id")
    private Long menuId;

    @TableField("name")
    private String name;

    @TableField("permission")
    private String permission;

    /**
     * 是否为内置数据 true:是 false：否
     */
    private boolean preset;

    private Integer sort;

}
