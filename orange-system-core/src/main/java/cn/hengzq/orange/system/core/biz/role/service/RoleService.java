package cn.hengzq.orange.system.core.biz.role.service;

import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.role.dto.RoleDetailResponse;
import cn.hengzq.orange.system.common.biz.role.dto.RoleResponse;
import cn.hengzq.orange.system.common.biz.role.dto.request.*;

import java.util.List;
import java.util.Optional;

/**
 * @author 程序员橙子
 */
public interface RoleService {

    String createRole(RoleCreateRequest param);

    Boolean deleteRoleById(String id);

    Boolean updateRoleById(String id, RoleUpdateRequest param);

    Optional<RoleResponse> getRoleById(String id);

    RoleDetailResponse getRoleDetailById(String id);

    PageDTO<RoleResponse> pageRoles(RolePageRequest query);

    List<RoleResponse> listRoles(RoleQueryRequest param);

    List<RoleResponse> listByUserId(String userId);

}
