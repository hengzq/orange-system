package cn.hengzq.orange.system.core.biz.menu.service.impl;

import cn.hengzq.orange.common.constant.GlobalConstant;
import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.exception.ServiceException;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.common.util.CollUtils;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.common.biz.menu.constant.MenuErrorCode;
import cn.hengzq.orange.system.common.biz.menu.constant.ResourceTypeEnum;
import cn.hengzq.orange.system.common.biz.menu.vo.ButtonVO;
import cn.hengzq.orange.system.common.biz.menu.vo.MenuDetailVO;
import cn.hengzq.orange.system.common.biz.menu.vo.MenuVO;
import cn.hengzq.orange.system.common.biz.menu.vo.param.AddMenuParam;
import cn.hengzq.orange.system.common.biz.menu.vo.param.ButtonListParam;
import cn.hengzq.orange.system.common.biz.menu.vo.param.MenuListParam;
import cn.hengzq.orange.system.common.biz.menu.vo.param.UpdateMenuParam;
import cn.hengzq.orange.system.core.biz.menu.converter.MenuConverter;
import cn.hengzq.orange.system.core.biz.menu.entity.MenuEntity;
import cn.hengzq.orange.system.core.biz.menu.mapper.MenuMapper;
import cn.hengzq.orange.system.core.biz.menu.service.ButtonService;
import cn.hengzq.orange.system.core.biz.menu.service.MenuService;
import cn.hengzq.orange.system.core.biz.role.entity.RoleResourceRlEntity;
import cn.hengzq.orange.system.core.biz.role.mapper.RoleResourceRlMapper;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;

    private final RoleResourceRlMapper roleResourceRlMapper;

    private final ButtonService buttonService;

    @Override
    public List<MenuDetailVO> list(MenuListParam param) {
        List<Long> menuIds = List.of();
        if (CollUtil.isNotEmpty(param.getRoleIds())) {
            List<RoleResourceRlEntity> resourceRlEntityList = roleResourceRlMapper.selectListByTypeAndRoleIds(ResourceTypeEnum.MENU, param.getRoleIds());
            menuIds = CollUtils.convertList(resourceRlEntityList, RoleResourceRlEntity::getResourceId);
            if (CollUtil.isEmpty(menuIds)) {
                return List.of();
            }
        }
        List<MenuEntity> entityList = menuMapper.selectList(CommonWrappers.<MenuEntity>lambdaQuery()
                .eqIfPresent(MenuEntity::getName, param.getName())
                .likeIfPresent(MenuEntity::getName, param.getNameLike())
                .inIfPresent(MenuEntity::getId, menuIds)
                .orderByAsc(MenuEntity::getSort));
        List<MenuVO> listVO = MenuConverter.INSTANCE.toListVO(entityList);
        if (CollUtil.isEmpty(listVO)) {
            return List.of();
        }
        List<MenuDetailVO> detailVOList = listVO.stream().map(item -> BeanUtil.copyProperties(item, MenuDetailVO.class)).toList();
        if (param.isShowButton()) {
            assembleButton(detailVOList);
        }
        return detailVOList;
    }

    private void assembleButton(List<MenuDetailVO> menuVOS) {
        if (CollUtil.isEmpty(menuVOS)) {
            return;
        }
        List<Long> menuIds = CollUtils.convertList(menuVOS, MenuVO::getId);
        List<ButtonVO> buttonVOList = buttonService.list(ButtonListParam.builder().menuIds(menuIds).build());
        if (CollUtil.isEmpty(buttonVOList)) {
            return;
        }
        Map<Long, List<ButtonVO>> buttonMap = buttonVOList.stream().collect(Collectors.groupingBy(ButtonVO::getMenuId));
        // 组装按钮
        menuVOS.forEach(item -> {
            if (buttonMap.containsKey(item.getId())) {
                item.setButtons(buttonMap.get(item.getId()));
            }
        });
    }

    @Override
    public Long add(AddMenuParam param) {
        Assert.nonNull(param.getPermission(), MenuErrorCode.MENU_PERMISSION_CANNOT_NULL);
        MenuEntity entity = menuMapper.selectByPermission(param.getPermission());
        Assert.isNull(entity, MenuErrorCode.MENU_PERMISSION_CANNOT_REPEAT);
        param.setParentId(Objects.isNull(param.getParentId()) ? GlobalConstant.DEFAULT_PARENT_ID : param.getParentId());
        entity = MenuConverter.INSTANCE.toEntity(param);

        return menuMapper.insertOne(entity);
    }

    @Override
    public Boolean updateById(Long id, UpdateMenuParam param) {
        MenuEntity entity = menuMapper.selectById(id);
        Assert.nonNull(entity.getId(), GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        if (StrUtil.isNotBlank(param.getPermission()) && !param.getPermission().equals(entity.getPermission())) {
            MenuEntity oldEntity = menuMapper.selectByPermission(param.getPermission());
            Assert.isNull(oldEntity, MenuErrorCode.MENU_PERMISSION_CANNOT_REPEAT);
        }
        entity = MenuConverter.INSTANCE.toUpdateEntity(entity, param);

        return menuMapper.updateOneById(entity);
    }

    @Override
    public Boolean removeById(Long id) {
        MenuEntity entity = menuMapper.selectById(id);
        Assert.nonNull(entity, GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        // 预置数据 不允许删除
        if (entity.isPreset()) {
            throw new ServiceException(GlobalErrorCodeConstant.GLOBAL_DATA_PRESET_CANNOT_DELETE);
        }
        // 存在子集菜单 不允许删除
        List<MenuEntity> entityList = menuMapper.selectListByParentId(id);
        if (CollUtil.isNotEmpty(entityList)) {
            throw new ServiceException(MenuErrorCode.MENU_DELETE_ERROR_EXIST_SUBSET);
        }
        // 存在按钮关联按钮 不允许删除
//        List<ButtonEntity> buttonEntityList = buttonManager.listByParams(ButtonListQuery.builder().menuId(id).build());
//        if (CollUtil.isNotEmpty(buttonEntityList)) {
//            throw new ServiceException(MenuErrorCode.MENU_DELETE_ERROR_EXIST_BUTTON);
//        }
        return menuMapper.deleteById(id) > 0;
    }

    @Override
    public MenuVO getById(Long id) {
        return MenuConverter.INSTANCE.toVO(menuMapper.selectById(id));
    }
}
