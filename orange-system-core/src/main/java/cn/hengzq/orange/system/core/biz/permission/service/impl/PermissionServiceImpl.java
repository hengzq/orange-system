package cn.hengzq.orange.system.core.biz.permission.service.impl;

import cn.hengzq.orange.common.constant.GlobalConstant;
import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.common.util.CollUtils;
import cn.hengzq.orange.context.GlobalContextHelper;
import cn.hengzq.orange.system.common.biz.menu.constant.ResourceTypeEnum;
import cn.hengzq.orange.system.common.biz.menu.vo.ButtonVO;
import cn.hengzq.orange.system.common.biz.menu.vo.MenuDetailVO;
import cn.hengzq.orange.system.common.biz.menu.vo.param.ButtonListParam;
import cn.hengzq.orange.system.common.biz.menu.vo.param.MenuListParam;
import cn.hengzq.orange.system.common.biz.permission.vo.AuthUserInfoVO;
import cn.hengzq.orange.system.common.biz.role.vo.RoleVO;
import cn.hengzq.orange.system.common.biz.role.vo.param.AssignResourcesToOneRoleParam;
import cn.hengzq.orange.system.common.biz.user.vo.param.AssignRolesToOneUserParam;
import cn.hengzq.orange.system.core.biz.menu.service.ButtonService;
import cn.hengzq.orange.system.core.biz.menu.service.MenuService;
import cn.hengzq.orange.system.core.biz.permission.converter.PermissionConverter;
import cn.hengzq.orange.system.core.biz.permission.service.PermissionService;
import cn.hengzq.orange.system.core.biz.role.entity.RoleResourceRlEntity;
import cn.hengzq.orange.system.core.biz.role.mapper.RoleResourceRlMapper;
import cn.hengzq.orange.system.core.biz.role.service.RoleService;
import cn.hengzq.orange.system.core.biz.user.entity.UserEntity;
import cn.hengzq.orange.system.core.biz.user.entity.UserRoleRlEntity;
import cn.hengzq.orange.system.core.biz.user.mapper.UserMapper;
import cn.hengzq.orange.system.core.biz.user.mapper.UserRoleRlMapper;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final UserRoleRlMapper userRoleRlMapper;

    private final RoleResourceRlMapper roleResourceRlMapper;

    private final UserMapper userMapper;

    private final RoleService roleService;

    private final MenuService menuService;

    private final ButtonService buttonService;

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
    public AuthUserInfoVO getUserInfo() {
        Long userId = GlobalContextHelper.getUserId();
        UserEntity entity = userMapper.selectById(userId);
        AuthUserInfoVO authUserInfoVO = BeanUtil.copyProperties(entity, AuthUserInfoVO.class);
        Assert.nonNull(authUserInfoVO, GlobalErrorCodeConstant.GLOBAL_PARAMETER_ID_IS_INVALID);

        // 封装角色
        List<RoleVO> roleVOS = roleService.listByUserId(userId);
        List<String> rolePerms = CollUtils.convertList(roleVOS, RoleVO::getPermission);
        if (CollUtil.isEmpty(rolePerms)) {
            return authUserInfoVO;
        }
        authUserInfoVO.setRolePermissions(rolePerms);

        // 封装菜单
        List<Long> roleIds = CollUtils.convertList(roleVOS, RoleVO::getId);

        List<MenuDetailVO> menuVOList;
        // admin 拥有所有的权限
        if (rolePerms.contains(GlobalConstant.SUPER_ADMIN_ROLE)) {
            menuVOList = menuService.list(MenuListParam.builder().build());
        } else {
            menuVOList = menuService.list(MenuListParam.builder().roleIds(roleIds).build());
        }
        if (CollUtil.isEmpty(menuVOList)) {
            return authUserInfoVO;
        }
        List<AuthUserInfoVO.Menu> menus = menuVOList.stream().filter(item -> Objects.nonNull(item.getHidden()) && !item.getHidden())
                .map(PermissionConverter.INSTANCE::toMenu)
                .sorted(Comparator.comparing(AuthUserInfoVO.Menu::getSort)).toList();
        authUserInfoVO.setMenus(menus);
        authUserInfoVO.setMenuPermissions(CollUtils.convertList(menuVOList, MenuDetailVO::getPermission));

        // 封装按钮
        List<Long> menuIds = CollUtils.convertList(menuVOList, MenuDetailVO::getId);
        List<ButtonVO> buttonVOS;
        if (rolePerms.contains(GlobalConstant.SUPER_ADMIN_ROLE)) {
            buttonVOS = buttonService.list(ButtonListParam.builder().menuIds(menuIds).build());
        } else {
            buttonVOS = buttonService.list(ButtonListParam.builder().roleIds(roleIds).build());
        }
        authUserInfoVO.setButtonPermissions(CollUtils.convertList(buttonVOS, ButtonVO::getPermission));
        return authUserInfoVO;
    }
}
