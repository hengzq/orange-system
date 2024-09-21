package cn.hengzq.orange.system.permission.core.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.constant.SecurityConstant;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.exception.ServiceException;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.context.GlobalContextHelper;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.permission.common.exception.UserErrorCode;
import cn.hengzq.orange.system.permission.core.converter.UserConverter;
import cn.hengzq.orange.system.permission.core.entity.UserEntity;
import cn.hengzq.orange.system.permission.core.mapper.UserMapper;
import cn.hengzq.orange.system.permission.core.service.ButtonService;
import cn.hengzq.orange.system.permission.core.service.RoleService;
import cn.hengzq.orange.system.permission.core.service.UserDepartmentRlService;
import cn.hengzq.orange.system.permission.core.service.UserService;
import cn.hengzq.orange.system.permission.common.vo.user.UserDetailVO;
import cn.hengzq.orange.system.permission.common.vo.user.UserVO;
import cn.hengzq.orange.system.permission.common.vo.user.param.*;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

    private final ButtonService buttonService;

    @Override
    @Transactional
    public Long add(AddUserParam param) {
        UserEntity entity = UserConverter.INSTANCE.toEntity(param);
        // 密码加密
        String password = StrUtil.isBlank(param.getPassword()) ? SecurityConstant.DEFAULT_USER_PASSWORD : param.getPassword();
        entity.setPassword(passwordEncoder.encode(password));
        Long userId = userMapper.insertOne(entity);
        if (CollUtil.isNotEmpty(param.getDepartmentIds())) {
            userDepartmentRlService.addUserDepartmentRelation(userId, param.getDepartmentIds());
        }
        return userId;
    }

    @Override
    public Boolean updateById(Long id, UpdateUserParam param) {
        UserEntity entity = userMapper.selectById(id);
        Assert.nonNull(entity, UserErrorCode.GLOBAL_DATA_NOT_EXIST);
        entity = UserConverter.INSTANCE.toUpdateEntity(entity, param);
        return userMapper.updateOneById(entity);
    }

    @Override
    public Boolean updatePassword(UpdatePasswordParam param) {
        Long userId = Objects.isNull(param.getUserId()) ? GlobalContextHelper.getUserId() : param.getUserId();
        UserEntity entity = userMapper.selectById(userId);
        Assert.nonNull(entity, GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        boolean matches = passwordEncoder.matches(param.getOldPassword(), entity.getPassword());
        if (!matches) {
            log.error("password is error.");
            throw new ServiceException(GlobalErrorCodeConstant.GLOBAL_AUTH_PASSWORD_ERROR);
        }
        entity.setPassword(passwordEncoder.encode(param.getNewPassword()));
        return userMapper.updateOneById(entity);
    }

    @Override
    public Boolean resetPassword(ResetPasswordParam param) {
        param.checkParams();
        UserEntity entity = userMapper.selectById(param.getUserId());
        Assert.nonNull(entity, GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        entity.setPassword(passwordEncoder.encode(param.getNewPassword()));
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
    public UserVO getByUsername(String username) {
        return UserConverter.INSTANCE.toVO(userMapper.selectByUsername(username));
    }

    @Override
    public PageDTO<UserVO> page(UserPageParam query) {
        PageDTO<UserEntity> page = userMapper.selectPage(query,
                CommonWrappers.<UserEntity>lambdaQuery()
                        .eqIfPresent(UserEntity::getNickname, query.getNickname())
                        .likeIfPresent(UserEntity::getNickname, query.getNicknameLike())
        );
        return UserConverter.INSTANCE.toPage(page);
    }

    @Override
    public List<UserVO> list(UserListParam query) {
        List<UserEntity> entityList = userMapper.selectList(
                CommonWrappers.<UserEntity>lambdaQuery()
                        .eqIfPresent(UserEntity::getNickname, query.getNickname())
                        .likeIfPresent(UserEntity::getNickname, query.getNicknameLike())
                        .inIfPresent(UserEntity::getId, query.getIds())
        );
        return UserConverter.INSTANCE.toListV0(entityList);
    }


}
