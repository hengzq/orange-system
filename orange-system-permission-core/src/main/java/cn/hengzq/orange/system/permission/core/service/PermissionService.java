package cn.hengzq.orange.system.permission.core.service;

import cn.hengzq.orange.system.permission.common.vo.permission.RouterVO;
import cn.hengzq.orange.system.permission.common.vo.role.param.AssignResourcesToOneRoleParam;
import cn.hengzq.orange.system.permission.common.vo.user.param.AssignRolesToOneUserParam;

import java.util.List;

/**
 * @author hengzq
 */
public interface PermissionService {


    Boolean assignRolesToOneUser(AssignRolesToOneUserParam param);

    Boolean assignResourcesToOneRole(AssignResourcesToOneRoleParam param);


    List<RouterVO> listUserRouters();

}
