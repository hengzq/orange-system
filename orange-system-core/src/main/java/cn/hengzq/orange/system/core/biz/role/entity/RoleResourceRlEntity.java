package cn.hengzq.orange.system.core.biz.role.entity;

import cn.hengzq.orange.mybatis.entity.BaseTenantEntity;
import cn.hengzq.orange.system.common.biz.menu.constant.ResourceTypeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 角色资源关系
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_role_resource_rl")
public class RoleResourceRlEntity extends BaseTenantEntity {

    public RoleResourceRlEntity(String roleId, String resourceId, ResourceTypeEnum resourceType) {
        this.roleId = roleId;
        this.resourceId = resourceId;
        this.resourceType = resourceType;
    }

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @TableField("role_id")
    private String roleId;

    @TableField("resource_id")
    private String resourceId;

    @TableField("resource_type")
    private ResourceTypeEnum resourceType;
}
