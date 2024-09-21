package cn.hengzq.orange.system.permission.core.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.common.util.CollUtils;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.permission.core.converter.ButtonConverter;
import cn.hengzq.orange.system.permission.core.entity.ButtonEntity;
import cn.hengzq.orange.system.permission.core.entity.RoleResourceRlEntity;
import cn.hengzq.orange.system.permission.core.mapper.ButtonMapper;
import cn.hengzq.orange.system.permission.core.mapper.RoleResourceRlMapper;
import cn.hengzq.orange.system.permission.core.service.ButtonService;
import cn.hengzq.orange.system.permission.common.enums.ResourceTypeEnum;
import cn.hengzq.orange.system.permission.common.exception.ButtonErrorCode;
import cn.hengzq.orange.system.permission.common.vo.button.ButtonVO;
import cn.hengzq.orange.system.permission.common.vo.button.param.AddButtonParam;
import cn.hengzq.orange.system.permission.common.vo.button.param.ButtonListParam;
import cn.hengzq.orange.system.permission.common.vo.button.param.ButtonPageParam;
import cn.hengzq.orange.system.permission.common.vo.button.param.UpdateButtonParam;
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
    public Long add(AddButtonParam param) {
        Assert.nonNull(param.getPermission(), ButtonErrorCode.BUTTON_PERMISSION_CANNOT_NULL);
        ButtonEntity entity = buttonMapper.selectByPermission(param.getPermission());
        Assert.isNull(entity, ButtonErrorCode.BUTTON_PERMISSION_CANNOT_REPEAT);
        entity = ButtonConverter.INSTANCE.toEntity(param);
        return buttonMapper.insertOne(entity);
    }

    @Override
    public Boolean updateById(Long id, UpdateButtonParam param) {
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
        List<Long> buttonIds = List.of();
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
    public Boolean removeById(Long id) {
        ButtonEntity entity = buttonMapper.selectById(id);
        if (Objects.isNull(entity)) {
            return true;
        }
        return buttonMapper.deleteById(entity) > 0;
    }

    @Override
    public ButtonVO getById(Long id) {
        return ButtonConverter.INSTANCE.toVO(buttonMapper.selectById(id));
    }

}
