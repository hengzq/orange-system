package cn.hengzq.orange.system.permission.core.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.exception.ServiceException;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.permission.core.convert.RoleConverter;
import cn.hengzq.orange.system.permission.core.entity.RoleEntity;
import cn.hengzq.orange.system.permission.core.mapper.RoleMapper;
import cn.hengzq.orange.system.permission.core.service.ButtonService;
import cn.hengzq.orange.system.permission.core.service.MenuService;
import cn.hengzq.orange.system.permission.core.service.RoleService;
import cn.hengzq.orange.system.permission.common.exception.RoleErrorCode;
import cn.hengzq.orange.system.permission.common.vo.button.param.ButtonListParam;
import cn.hengzq.orange.system.permission.common.vo.menu.MenuDetailVO;
import cn.hengzq.orange.system.permission.common.vo.menu.MenuVO;
import cn.hengzq.orange.system.permission.common.vo.menu.param.MenuListParam;
import cn.hengzq.orange.system.permission.common.vo.role.RoleDetailVO;
import cn.hengzq.orange.system.permission.common.vo.role.RoleVO;
import cn.hengzq.orange.system.permission.common.vo.role.param.*;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


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
    public Long add(AddRoleParam param) {
        Assert.nonNull(param.getPermission(), RoleErrorCode.ROLE_PERMISSION_CANNOT_NULL);
        RoleEntity entity = roleMapper.selectByPermission(param.getPermission());
        Assert.isNull(entity, RoleErrorCode.ROLE_PERMISSION_CANNOT_REPEAT);
        entity = RoleConverter.INSTANCE.toEntity(param);

        return roleMapper.insertOne(entity);
    }

    @Override
    public Boolean updateById(Long id, UpdateRoleParam param) {
        RoleEntity entity = roleMapper.selectById(id);
        Assert.nonNull(entity.getId(), GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        if (StrUtil.isNotBlank(param.getPermission()) && !param.getPermission().equals(entity.getPermission())) {
            RoleEntity oldEntity = roleMapper.selectByPermission(param.getPermission());
            Assert.isNull(oldEntity, RoleErrorCode.ROLE_PERMISSION_CANNOT_REPEAT);
        }
        entity = RoleConverter.INSTANCE.toUpdateEntity(entity, param);
        return roleMapper.updateOneById(entity);
    }

    @Override
    public PageDTO<RoleVO> page(RolePageParam param) {
        PageDTO<RoleEntity> page = roleMapper.selectPage(param, CommonWrappers.<RoleEntity>lambdaQuery()
                .eqIfPresent(RoleEntity::getName, param.getName())
                .likeIfPresent(RoleEntity::getName, param.getNameLike())
                .eqIfPresent(RoleEntity::getPermission, param.getPermission())
                .likeIfPresent(RoleEntity::getPermission, param.getPermissionLike())
                .orderByAsc(RoleEntity::getSort));
        return RoleConverter.INSTANCE.toPage(page);
    }

    @Override
    public List<RoleVO> listByUserId(Long userId) {
        List<RoleEntity> entityList = roleMapper.selectListByUserId(userId);
        return RoleConverter.INSTANCE.toListVO(entityList);
    }

    @Override
    public Boolean removeById(Long id) {
        RoleEntity entity = roleMapper.selectById(id);
        if (Objects.isNull(entity)) {
            return true;
        }
        return roleMapper.deleteById(id) > 0;
    }

    @Override
    public RoleDetailVO getById(Long id, RoleDetailQueryParam param) {
        RoleDetailVO detailVO = BeanUtil.copyProperties(roleMapper.selectById(id), RoleDetailVO.class);
        if (Objects.isNull(detailVO)) {
            throw new ServiceException(GlobalErrorCodeConstant.GLOBAL_PARAMETER_ID_IS_INVALID);
        }
        if (param.isShowMenu()) {
            List<MenuDetailVO> list = menuService.list(MenuListParam.builder().roleIds(List.of(id)).build());
            detailVO.setMenus(list.stream().map(MenuVO.class::cast).toList());
        }
        if (param.isShowButton()) {
            detailVO.setButtons(buttonService.list(ButtonListParam.builder().roleIds(List.of(id)).build()));
        }
        return detailVO;
    }

    @Override
    public List<RoleVO> list(RoleListParam param) {
        List<RoleEntity> entityList = roleMapper.selectList(CommonWrappers.<RoleEntity>lambdaQuery());
        return RoleConverter.INSTANCE.toListVO(entityList);
    }

}
