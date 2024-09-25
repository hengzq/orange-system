package cn.hengzq.orange.system.permission.core.entity;

import cn.hengzq.orange.mybatis.entity.BaseTenantEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@TableName("sys_user_role_rl")
public class UserRoleRlEntity extends BaseTenantEntity {

    public UserRoleRlEntity(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("role_id")
    private Long roleId;
}
