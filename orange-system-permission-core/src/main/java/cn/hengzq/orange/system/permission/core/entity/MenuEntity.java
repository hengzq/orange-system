package cn.hengzq.orange.system.permission.core.entity;

import cn.hengzq.orange.mybatis.entity.BaseTenantEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单管理表
 *
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_menu")
public class MenuEntity extends BaseTenantEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 父级别ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 权限编码
     */
    @TableField("permission")
    private String permission;

    /**
     * 是否为内置数据 true:是 false：否
     */
    private boolean preset;

    /**
     * 菜单路径
     */
    @TableField("path")
    private String path;

    @TableField("icon")
    private String icon;

    @TableField("hidden")
    private Boolean hidden;

    private Integer sort;
}
