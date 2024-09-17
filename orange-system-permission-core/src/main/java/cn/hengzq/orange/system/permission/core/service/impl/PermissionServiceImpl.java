package cn.hengzq.orange.system.permission.core.service.impl;

import cn.hengzq.orange.common.util.CollUtils;
import cn.hengzq.orange.context.GlobalContextHelper;
import cn.hengzq.orange.system.permission.core.convert.PermissionConverter;
import cn.hengzq.orange.system.permission.core.entity.RoleEntity;
import cn.hengzq.orange.system.permission.core.entity.RoleResourceRlEntity;
import cn.hengzq.orange.system.permission.core.entity.UserEntity;
import cn.hengzq.orange.system.permission.core.entity.UserRoleRlEntity;
import cn.hengzq.orange.system.permission.core.mapper.RoleMapper;
import cn.hengzq.orange.system.permission.core.mapper.RoleResourceRlMapper;
import cn.hengzq.orange.system.permission.core.mapper.UserMapper;
import cn.hengzq.orange.system.permission.core.mapper.UserRoleRlMapper;
import cn.hengzq.orange.system.permission.core.service.MenuService;
import cn.hengzq.orange.system.permission.core.service.PermissionService;
import cn.hengzq.orange.system.permission.common.enums.ResourceTypeEnum;
import cn.hengzq.orange.system.permission.common.vo.menu.MenuDetailVO;
import cn.hengzq.orange.system.permission.common.vo.menu.param.MenuListParam;
import cn.hengzq.orange.system.permission.common.vo.permission.RouterVO;
import cn.hengzq.orange.system.permission.common.vo.role.param.AssignResourcesToOneRoleParam;
import cn.hengzq.orange.system.permission.common.vo.user.param.AssignRolesToOneUserParam;
import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final UserRoleRlMapper userRoleRlMapper;

    private final RoleResourceRlMapper roleResourceRlMapper;

    private final UserMapper userMapper;

    private final RoleMapper roleMapper;

    private final MenuService menuService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean assignRolesToOneUser(AssignRolesToOneUserParam param) {
        userRoleRlMapper.deleteByUserId(param.getUserId());
        List<UserRoleRlEntity> rlEntityList = new ArrayList<>(param.getRoleIds().size());
        for (Long roleId : param.getRoleIds()) {
            rlEntityList.add(new UserRoleRlEntity(param.getUserId(), roleId));
        }
        userRoleRlMapper.insert(rlEntityList);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean assignResourcesToOneRole(AssignResourcesToOneRoleParam param) {
        Long roleId = param.getRoleId();
        roleResourceRlMapper.deleteByRoleId(roleId);
        log.info("clean up. roleId:{}", roleId);

        List<Long> menuIds = param.getMenuIds();
        List<Long> buttonIds = param.getButtonIds();

        log.info("roleId:{},menuIds:{},buttonIds:{}", roleId, menuIds, buttonIds);
        List<RoleResourceRlEntity> roleResourceRlEntityList = new ArrayList<>();
        menuIds.forEach(i -> {
            RoleResourceRlEntity menu = new RoleResourceRlEntity(roleId, i, ResourceTypeEnum.MENU);
            roleResourceRlEntityList.add(menu);
        });

        if (CollUtil.isNotEmpty(buttonIds)) {
            buttonIds.forEach(i -> {
                RoleResourceRlEntity button = new RoleResourceRlEntity(roleId, i, ResourceTypeEnum.BUTTON);
                roleResourceRlEntityList.add(button);
            });
        }
        roleResourceRlMapper.insert(roleResourceRlEntityList);
        return Boolean.TRUE;
    }

    @Override
    public List<RouterVO> listUserRouters() {
        Long userId = GlobalContextHelper.getUserId();
        UserEntity user = userMapper.selectById(userId);
        if (Objects.isNull(user)) {
            log.info("user is null.userId:{}", userId);
            return List.of();
        }
        List<RoleEntity> roleEntityList = roleMapper.selectListByUserId(userId);
        if (CollUtil.isEmpty(roleEntityList)) {
            return List.of();
        }
        List<String> permissions = CollUtils.convertList(roleEntityList, RoleEntity::getPermission);
        List<Long> roleIds = CollUtils.convertList(roleEntityList, RoleEntity::getId);
        List<MenuDetailVO> menuVOList;
        // admin 拥有所有的权限
        if (permissions.contains("admin")) {
            menuVOList = menuService.list(MenuListParam.builder().build());
        } else {
            menuVOList = menuService.list(MenuListParam.builder().roleIds(roleIds).build());
        }
        if (CollUtil.isEmpty(menuVOList)) {
            return List.of();
        }
        return menuVOList.stream().filter(item -> Objects.nonNull(item.getHidden()) && !item.getHidden())
                .map(PermissionConverter.INSTANCE::toRouter)
                .sorted(Comparator.comparing(RouterVO::getSort)).collect(Collectors.toList());
    }
}
