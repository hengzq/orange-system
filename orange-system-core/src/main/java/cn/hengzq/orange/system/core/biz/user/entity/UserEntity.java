package cn.hengzq.orange.system.core.biz.user.entity;

import cn.hengzq.orange.mybatis.entity.BaseTenantEntity;
import cn.hengzq.orange.mybatis.handler.EnumCodeTypeHandler;
import cn.hengzq.orange.system.common.biz.user.constant.UserGenderEnum;
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
    private String id;

    private String name;

    private String email;

    @TableField(typeHandler = EnumCodeTypeHandler.class)
    private UserGenderEnum gender;

    private String phone;

    /**
     * 登陆账号
     */
    @TableField("login_account")
    private String loginAccount;

    /**
     * 密码
     */
    @TableField("login_password")
    private String loginPassword;


}
