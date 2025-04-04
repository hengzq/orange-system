package cn.hengzq.orange.system.core.biz.user.service.impl;

import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.common.util.CollUtils;
import cn.hengzq.orange.system.common.biz.department.constant.DepartmentErrorCode;
import cn.hengzq.orange.system.common.biz.user.constant.UserErrorCode;
import cn.hengzq.orange.system.core.biz.user.entity.UserDepartmentRlEntity;
import cn.hengzq.orange.system.core.biz.user.mapper.UserDepartmentRlMapper;
import cn.hengzq.orange.system.core.biz.user.service.UserDepartmentRlService;
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
    public void addUserDepartmentRelation(String userId, List<String> departmentIds) {
        Assert.nonNull(userId, UserErrorCode.USER_ID_CANNOT_NULL);
        Assert.notEmpty(departmentIds, DepartmentErrorCode.DEPARTMENT_ID_CANNOT_NULL);
        List<UserDepartmentRlEntity> addList = departmentIds.stream().map(item -> new UserDepartmentRlEntity(userId, item)).toList();
        userDepartmentRlMapper.insert(addList);
    }

    @Override
    public List<String> listDepartmentIdsByUserId(String userId) {
        List<UserDepartmentRlEntity> entities = userDepartmentRlMapper.selectListByUserId(userId);
        return CollUtil.isEmpty(entities) ? List.of() : CollUtils.convertList(entities, UserDepartmentRlEntity::getDepartmentId);
    }

    @Override
    public void removeByUserId(String userId) {
        userDepartmentRlMapper.deleteByUserId(userId);
    }
}
