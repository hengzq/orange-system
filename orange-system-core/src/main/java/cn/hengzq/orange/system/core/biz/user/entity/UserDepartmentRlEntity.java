package cn.hengzq.orange.system.core.biz.user.entity;

import cn.hengzq.orange.mybatis.entity.BaseTenantEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user_department_rl")
public class UserDepartmentRlEntity extends BaseTenantEntity {

    public UserDepartmentRlEntity(String userId, String departmentId) {
        this.userId = userId;
        this.departmentId = departmentId;
    }

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @TableField("department_id")
    private String departmentId;

    @TableField("user_id")
    private String userId;

}
