package cn.hengzq.orange.system.core.biz.permission.service;


import cn.hengzq.orange.system.common.biz.permission.vo.AuthUserInfoResponse;
import cn.hengzq.orange.system.common.biz.role.dto.request.AssignResourcesToOneRoleParam;
import cn.hengzq.orange.system.common.biz.user.dto.request.AssignRolesToOneUserParam;

/**
 * @author hengzq
 */
public interface PermissionService {

    Boolean assignRolesToOneUser(AssignRolesToOneUserParam param);

    Boolean assignResourcesToOneRole(AssignResourcesToOneRoleParam param);

    AuthUserInfoResponse getUserInfo();

}
