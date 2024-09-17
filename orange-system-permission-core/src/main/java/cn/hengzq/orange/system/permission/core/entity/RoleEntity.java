package cn.hengzq.orange.system.permission.core.entity;

import cn.hengzq.orange.mybatis.entity.BaseTenantEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
  * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_role")
public class RoleEntity extends BaseTenantEntity {

    /**
     * 角色id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色权限字符串
     */
    private String permission;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 模型启用状态 true:启用 false：不启用
     */
    private boolean enabled;

    /**
     * 模型启用状态 true:是 false：否
     */
    private boolean preset;

    /**
     * 备注
     */
    private String remark;

}
