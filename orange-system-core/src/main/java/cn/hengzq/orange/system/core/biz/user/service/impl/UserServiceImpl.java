package cn.hengzq.orange.system.core.biz.user.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.constant.SecurityConstant;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.exception.ServiceException;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.common.util.CollUtils;
import cn.hengzq.orange.context.GlobalContextHelper;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.common.biz.user.constant.UserErrorCode;
import cn.hengzq.orange.system.common.biz.user.vo.UserDetailVO;
import cn.hengzq.orange.system.common.biz.user.vo.UserVO;
import cn.hengzq.orange.system.common.biz.user.vo.param.*;
import cn.hengzq.orange.system.core.biz.role.service.RoleService;
import cn.hengzq.orange.system.core.biz.user.converter.UserConverter;
import cn.hengzq.orange.system.core.biz.user.entity.UserEntity;
import cn.hengzq.orange.system.core.biz.user.mapper.UserMapper;
import cn.hengzq.orange.system.core.biz.user.service.UserDepartmentRlService;
import cn.hengzq.orange.system.core.biz.user.service.UserService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserDepartmentRlService userDepartmentRlService;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    @Override
    @Transactional
    public Long add(AddUserParam param) {
        UserEntity entity = UserConverter.INSTANCE.toEntity(param);
        if (StrUtil.isNotBlank(param.getLoginAccount())) {
            String password = StrUtil.isBlank(param.getLoginPassword()) ? SecurityConstant.DEFAULT_USER_PASSWORD : param.getLoginPassword();
            entity.setLoginPassword(passwordEncoder.encode(password));
        }
        Long userId = userMapper.insertOne(entity);
        if (CollUtil.isNotEmpty(param.getDepartmentIds())) {
            userDepartmentRlService.addUserDepartmentRelation(userId, param.getDepartmentIds());
        }
        return userId;
    }

    @Override
    @Transactional
    public Boolean updateById(Long id, UpdateUserParam param) {
        UserEntity entity = userMapper.selectById(id);
        Assert.nonNull(entity, UserErrorCode.GLOBAL_DATA_NOT_EXIST);
        entity = UserConverter.INSTANCE.toUpdateEntity(entity, param);
        if (CollUtil.isNotEmpty(param.getDepartmentIds())) {
            userDepartmentRlService.removeByUserId(id);
            userDepartmentRlService.addUserDepartmentRelation(id, param.getDepartmentIds());
        }
        return userMapper.updateOneById(entity);
    }

    @Override
    public Boolean updatePassword(UpdatePasswordParam param) {
        Long userId = Objects.isNull(param.getUserId()) ? GlobalContextHelper.getUserId() : param.getUserId();
        UserEntity entity = userMapper.selectById(userId);
        Assert.nonNull(entity, GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        boolean matches = passwordEncoder.matches(param.getOldPassword(), entity.getLoginPassword());
        if (!matches) {
            log.error("password is error.");
            throw new ServiceException(GlobalErrorCodeConstant.GLOBAL_AUTH_PASSWORD_ERROR);
        }
        entity.setLoginPassword(passwordEncoder.encode(param.getNewPassword()));
        return userMapper.updateOneById(entity);
    }

    @Override
    public Boolean resetPassword(ResetPasswordParam param) {
        param.checkParams();
        UserEntity entity = userMapper.selectById(param.getUserId());
        Assert.nonNull(entity, GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        entity.setLoginPassword(passwordEncoder.encode(param.getNewPassword()));
        return userMapper.updateOneById(entity);
    }

    @Override
    public UserDetailVO getById(Long userId, UserDetailQueryParam param) {
        UserEntity entity = userMapper.selectById(userId);
        UserDetailVO userDetailVO = BeanUtil.copyProperties(entity, UserDetailVO.class);
        Assert.nonNull(userDetailVO, GlobalErrorCodeConstant.GLOBAL_PARAMETER_ID_IS_INVALID);

        userDetailVO.setDepartmentIds(userDepartmentRlService.listDepartmentIdsByUserId(userId));
        if (param.isShowRole()) {
            userDetailVO.setRoles(roleService.listByUserId(userId));
        }
        return userDetailVO;
    }

    @Override
    public UserVO getByLoginAccount(String loginAccount) {
        return UserConverter.INSTANCE.toVO(userMapper.selectByLoginAccount(loginAccount));
    }

    @Override
    public PageDTO<UserVO> page(UserPageParam query) {
        PageDTO<UserEntity> page = userMapper.selectPage(query,
                CommonWrappers.<UserEntity>lambdaQuery()
                        .eqIfPresent(UserEntity::getName, query.getName())
                        .likeIfPresent(UserEntity::getName, query.getNameLike())
                        .likeIfPresent(UserEntity::getLoginAccount, query.getLoginAccountLike())
        );
        return UserConverter.INSTANCE.toPage(page);
    }

    @Override
    public List<UserVO> list(UserListParam query) {
        List<UserEntity> entityList = userMapper.selectList(
                CommonWrappers.<UserEntity>lambdaQuery()
                        .eqIfPresent(UserEntity::getName, query.getName())
                        .likeIfPresent(UserEntity::getName, query.getNameLike())
                        .inIfPresent(UserEntity::getId, query.getIds())
        );
        return UserConverter.INSTANCE.toListV0(entityList);
    }

    @Override
    public Map<Long, String> getNameMapByIds(Set<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Map.of();
        }
        List<UserEntity> entityList = userMapper.selectList(CommonWrappers.<UserEntity>lambdaQuery().in(UserEntity::getId, ids));
        if (CollUtil.isEmpty(entityList)) {
            return Map.of();
        }
        return CollUtils.convertMap(entityList, UserEntity::getId, UserEntity::getName);
    }

    @Override
    public Boolean removeById(Long id) {
        return userMapper.deleteOneById(id);
    }


}
