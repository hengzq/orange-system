package cn.hengzq.orange.system.permission.core.entity;

import cn.hengzq.orange.mybatis.entity.BaseTenantEntity;
import cn.hengzq.orange.mybatis.handler.EnumCodeTypeHandler;
import cn.hengzq.orange.system.permission.common.enums.UserGenderEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户实体类
 *
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_user")
public class UserEntity extends BaseTenantEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String nickname;

    private String email;

    @TableField(typeHandler = EnumCodeTypeHandler.class)
    private UserGenderEnum gender;

    private String phone;

    /**
     * 登陆账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;


}
