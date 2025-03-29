package cn.hengzq.orange.system.core.biz.menu.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.common.util.CollUtils;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.common.biz.menu.constant.ButtonErrorCode;
import cn.hengzq.orange.system.common.biz.menu.constant.ResourceTypeEnum;
import cn.hengzq.orange.system.common.biz.menu.vo.ButtonVO;
import cn.hengzq.orange.system.common.biz.menu.vo.param.AddButtonParam;
import cn.hengzq.orange.system.common.biz.menu.vo.param.ButtonListParam;
import cn.hengzq.orange.system.common.biz.menu.vo.param.ButtonPageParam;
import cn.hengzq.orange.system.common.biz.menu.vo.param.UpdateButtonParam;
import cn.hengzq.orange.system.core.biz.menu.converter.ButtonConverter;
import cn.hengzq.orange.system.core.biz.menu.entity.ButtonEntity;
import cn.hengzq.orange.system.core.biz.menu.mapper.ButtonMapper;
import cn.hengzq.orange.system.core.biz.menu.service.ButtonService;
import cn.hengzq.orange.system.core.biz.role.entity.RoleResourceRlEntity;
import cn.hengzq.orange.system.core.biz.role.mapper.RoleResourceRlMapper;
import cn.hutool.core.collection.CollUtil;
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
public class ButtonServiceImpl implements ButtonService {

    private final ButtonMapper buttonMapper;

    private final RoleResourceRlMapper roleResourceRlMapper;

    @Override
    public PageDTO<ButtonVO> page(ButtonPageParam param) {
        PageDTO<ButtonEntity> page = buttonMapper.selectPage(param, CommonWrappers.<ButtonEntity>lambdaQuery()
                .orderByAsc(ButtonEntity::getSort));
        return ButtonConverter.INSTANCE.toPage(page);
    }

    @Override
    public String add(AddButtonParam param) {
        Assert.nonNull(param.getPermission(), ButtonErrorCode.BUTTON_PERMISSION_CANNOT_NULL);
        ButtonEntity entity = buttonMapper.selectByPermission(param.getPermission());
        Assert.isNull(entity, ButtonErrorCode.BUTTON_PERMISSION_CANNOT_REPEAT);
        entity = ButtonConverter.INSTANCE.toEntity(param);
        return buttonMapper.insertOne(entity);
    }

    @Override
    public Boolean updateById(String id, UpdateButtonParam param) {
        ButtonEntity entity = buttonMapper.selectById(id);
        Assert.nonNull(entity.getId(), GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        if (StrUtil.isNotBlank(param.getPermission()) && !param.getPermission().equals(entity.getPermission())) {
            ButtonEntity oldEntity = buttonMapper.selectByPermission(param.getPermission());
            Assert.isNull(oldEntity, ButtonErrorCode.BUTTON_PERMISSION_CANNOT_REPEAT);
        }
        entity = ButtonConverter.INSTANCE.toUpdateEntity(entity, param);
        return buttonMapper.updateOneById(entity);
    }

    @Override
    public List<ButtonVO> list(ButtonListParam param) {
        List<String> buttonIds = List.of();
        if (CollUtil.isNotEmpty(param.getRoleIds())) {
            List<RoleResourceRlEntity> resourceRlEntityList = roleResourceRlMapper.selectListByTypeAndRoleIds(ResourceTypeEnum.BUTTON, param.getRoleIds());
            buttonIds = CollUtils.convertList(resourceRlEntityList, RoleResourceRlEntity::getResourceId);
            if (CollUtil.isEmpty(buttonIds)) {
                return List.of();
            }
        }
        List<ButtonEntity> entityList = buttonMapper.selectList(CommonWrappers.<ButtonEntity>lambdaQuery()
                .eqIfPresent(ButtonEntity::getMenuId, param.getMenuId())
                .inIfPresent(ButtonEntity::getId, buttonIds)
                .inIfPresent(ButtonEntity::getMenuId, param.getMenuIds())
                .orderByAsc(ButtonEntity::getSort));
        return ButtonConverter.INSTANCE.toListVO(entityList);
    }

    @Override
    public Boolean removeById(String id) {
        return buttonMapper.deleteOneById(id);
    }

    @Override
    public ButtonVO getById(String id) {
        return ButtonConverter.INSTANCE.toVO(buttonMapper.selectById(id));
    }

}
