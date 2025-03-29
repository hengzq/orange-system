package cn.hengzq.orange.system.core.biz.role.service;

import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.role.vo.RoleDetailVO;
import cn.hengzq.orange.system.common.biz.role.vo.RoleVO;
import cn.hengzq.orange.system.common.biz.role.vo.param.*;

import java.util.List;

/**
 * @author 程序员橙子
 */
public interface RoleService {

    String add(AddRoleParam param);

    Boolean updateById(String id, UpdateRoleParam param);

    PageDTO<RoleVO> page(RolePageParam query);

    List<RoleVO> listByUserId(String userId);

    Boolean removeById(String id);

    RoleDetailVO getById(String id, RoleDetailQueryParam param);

    List<RoleVO> list(RoleListParam param);

}
