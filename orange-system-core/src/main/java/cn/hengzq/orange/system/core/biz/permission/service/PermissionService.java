package cn.hengzq.orange.system.core.biz.permission.service;


import cn.hengzq.orange.system.common.biz.permission.vo.AuthUserInfoVO;
import cn.hengzq.orange.system.common.biz.role.vo.param.AssignResourcesToOneRoleParam;
import cn.hengzq.orange.system.common.biz.user.vo.param.AssignRolesToOneUserParam;

/**
 * @author hengzq
 */
public interface PermissionService {

    Boolean assignRolesToOneUser(AssignRolesToOneUserParam param);

    Boolean assignResourcesToOneRole(AssignResourcesToOneRoleParam param);

    AuthUserInfoVO getUserInfo();

}
