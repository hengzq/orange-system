package cn.hengzq.orange.system.core.biz.role.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.exception.ServiceException;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.common.biz.menu.vo.MenuDetailVO;
import cn.hengzq.orange.system.common.biz.menu.vo.MenuVO;
import cn.hengzq.orange.system.common.biz.menu.vo.param.ButtonListParam;
import cn.hengzq.orange.system.common.biz.menu.vo.param.MenuListParam;
import cn.hengzq.orange.system.common.biz.role.constant.RoleErrorCode;
import cn.hengzq.orange.system.common.biz.role.vo.RoleDetailVO;
import cn.hengzq.orange.system.common.biz.role.vo.RoleVO;
import cn.hengzq.orange.system.common.biz.role.vo.param.*;
import cn.hengzq.orange.system.core.biz.menu.service.ButtonService;
import cn.hengzq.orange.system.core.biz.menu.service.MenuService;
import cn.hengzq.orange.system.core.biz.role.converter.RoleConverter;
import cn.hengzq.orange.system.core.biz.role.entity.RoleEntity;
import cn.hengzq.orange.system.core.biz.role.mapper.RoleMapper;
import cn.hengzq.orange.system.core.biz.role.service.RoleService;
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
    public String add(AddRoleParam param) {
        Assert.nonNull(param.getPermission(), RoleErrorCode.ROLE_PERMISSION_CANNOT_NULL);
        RoleEntity entity = roleMapper.selectByPermission(param.getPermission());
        Assert.isNull(entity, RoleErrorCode.ROLE_PERMISSION_CANNOT_REPEAT);
        entity = RoleConverter.INSTANCE.toEntity(param);

        return roleMapper.insertOne(entity);
    }

    @Override
    public Boolean updateById(String id, UpdateRoleParam param) {
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
    public List<RoleVO> listByUserId(String userId) {
        List<RoleEntity> entityList = roleMapper.selectListByUserId(userId);
        return RoleConverter.INSTANCE.toListVO(entityList);
    }

    @Override
    public Boolean removeById(String id) {
        return roleMapper.deleteOneById(id);
    }

    @Override
    public RoleDetailVO getById(String id, RoleDetailQueryParam param) {
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
