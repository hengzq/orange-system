package cn.hengzq.orange.system.core.biz.role.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.common.biz.menu.vo.MenuDetailVO;
import cn.hengzq.orange.system.common.biz.menu.vo.MenuVO;
import cn.hengzq.orange.system.common.biz.menu.vo.param.ButtonListParam;
import cn.hengzq.orange.system.common.biz.menu.vo.param.MenuListParam;
import cn.hengzq.orange.system.common.biz.role.constant.RoleErrorCode;
import cn.hengzq.orange.system.common.biz.role.dto.RoleDetailResponse;
import cn.hengzq.orange.system.common.biz.role.dto.RoleResponse;
import cn.hengzq.orange.system.common.biz.role.dto.request.RoleCreateRequest;
import cn.hengzq.orange.system.common.biz.role.dto.request.RolePageRequest;
import cn.hengzq.orange.system.common.biz.role.dto.request.RoleQueryRequest;
import cn.hengzq.orange.system.common.biz.role.dto.request.RoleUpdateRequest;
import cn.hengzq.orange.system.common.constant.RedisKeys;
import cn.hengzq.orange.system.core.biz.menu.service.ButtonService;
import cn.hengzq.orange.system.core.biz.menu.service.MenuService;
import cn.hengzq.orange.system.core.biz.role.converter.RoleConverter;
import cn.hengzq.orange.system.core.biz.role.entity.RoleEntity;
import cn.hengzq.orange.system.core.biz.role.mapper.RoleMapper;
import cn.hengzq.orange.system.core.biz.role.service.RoleService;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    private final MenuService menuService;

    private final ButtonService buttonService;

    @Override
    public String createRole(RoleCreateRequest param) {
        Assert.nonNull(param.getPermission(), RoleErrorCode.ROLE_PERMISSION_CANNOT_NULL);
        RoleEntity entity = roleMapper.selectByPermission(param.getPermission());
        Assert.isNull(entity, RoleErrorCode.ROLE_PERMISSION_CANNOT_REPEAT);
        entity = RoleConverter.INSTANCE.toEntity(param);
        return roleMapper.insertOne(entity);
    }

    @Override
    @CacheEvict(cacheNames = {RedisKeys.ROLE_BASIC_KEY_PREFIX}, key = "#id")
    public Boolean deleteRoleById(String id) {
        return roleMapper.deleteOneById(id);
    }

    @Override
    @CacheEvict(cacheNames = {RedisKeys.ROLE_BASIC_KEY_PREFIX}, key = "#id")
    public Boolean updateRoleById(String id, RoleUpdateRequest request) {
        RoleEntity entity = roleMapper.selectById(id);
        Assert.nonNull(entity.getId(), GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        if (StrUtil.isNotBlank(request.getPermission()) && !request.getPermission().equals(entity.getPermission())) {
            RoleEntity oldEntity = roleMapper.selectByPermission(request.getPermission());
            Assert.isNull(oldEntity, RoleErrorCode.ROLE_PERMISSION_CANNOT_REPEAT);
        }
        entity = RoleConverter.INSTANCE.toUpdateEntity(entity, request);
        return roleMapper.updateOneById(entity);
    }

    @Override
    @Cacheable(cacheNames = {RedisKeys.ROLE_BASIC_KEY_PREFIX}, key = "#id")
    public Optional<RoleResponse> getRoleById(String id) {
        RoleEntity roleEntity = roleMapper.selectById(id);
        return Optional.ofNullable(RoleConverter.INSTANCE.toVO(roleEntity));
    }

    @Override
    public RoleDetailResponse getRoleDetailById(String id) {
        RoleDetailResponse response = RoleConverter.INSTANCE.toDetail(roleMapper.selectById(id));
        Assert.nonNull(response, GlobalErrorCodeConstant.GLOBAL_PARAMETER_ID_IS_INVALID);

        List<MenuDetailVO> list = menuService.list(MenuListParam.builder().roleIds(List.of(id)).build());
        response.setMenus(list.stream().map(MenuVO.class::cast).toList());

        response.setButtons(buttonService.list(ButtonListParam.builder().roleIds(List.of(id)).build()));
        return response;
    }

    @Override
    public PageDTO<RoleResponse> pageRoles(RolePageRequest param) {
        PageDTO<RoleEntity> page = roleMapper.selectPage(param, CommonWrappers.<RoleEntity>lambdaQuery()
                .eqIfPresent(RoleEntity::getName, param.getName())
                .likeIfPresent(RoleEntity::getName, param.getNameLike())
                .eqIfPresent(RoleEntity::getPermission, param.getPermission())
                .likeIfPresent(RoleEntity::getPermission, param.getPermissionLike())
                .orderByAsc(RoleEntity::getSort));
        return RoleConverter.INSTANCE.toPage(page);
    }

    @Override
    public List<RoleResponse> listRoles(RoleQueryRequest request) {
        List<RoleEntity> entityList = roleMapper.selectList(CommonWrappers.<RoleEntity>lambdaQuery());
        return RoleConverter.INSTANCE.toListVO(entityList);
    }

    @Override
    public List<RoleResponse> listByUserId(String userId) {
        List<RoleEntity> entityList = roleMapper.selectListByUserId(userId);
        return RoleConverter.INSTANCE.toListVO(entityList);
    }


}
