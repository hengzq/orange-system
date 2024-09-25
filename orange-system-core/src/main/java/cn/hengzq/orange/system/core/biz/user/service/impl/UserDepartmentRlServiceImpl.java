package cn.hengzq.orange.system.permission.core.service.impl;

import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.common.util.CollUtils;
import cn.hengzq.orange.system.common.biz.department.constant.DepartmentErrorCode;
import cn.hengzq.orange.system.common.biz.user.constant.UserErrorCode;
import cn.hengzq.orange.system.permission.core.entity.UserDepartmentRlEntity;
import cn.hengzq.orange.system.permission.core.mapper.UserDepartmentRlMapper;
import cn.hengzq.orange.system.permission.core.service.UserDepartmentRlService;
import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserDepartmentRlServiceImpl implements UserDepartmentRlService {

    private final UserDepartmentRlMapper userDepartmentRlMapper;

    @Override
    public void addUserDepartmentRelation(Long userId, List<Long> departmentIds) {
        Assert.nonNull(userId, UserErrorCode.USER_ID_CANNOT_NULL);
        Assert.notEmpty(departmentIds, DepartmentErrorCode.DEPARTMENT_ID_CANNOT_NULL);
        List<UserDepartmentRlEntity> addList = departmentIds.stream().map(item -> new UserDepartmentRlEntity(userId, item)).toList();
        userDepartmentRlMapper.insert(addList);
    }

    @Override
    public List<Long> listDepartmentIdsByUserId(Long userId) {
        List<UserDepartmentRlEntity> entities = userDepartmentRlMapper.selectListByUserId(userId);
        return CollUtil.isEmpty(entities) ? List.of() : CollUtils.convertList(entities, UserDepartmentRlEntity::getDepartmentId);
    }
}
