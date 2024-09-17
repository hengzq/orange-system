package cn.hengzq.orange.system.permission.core.service;

import java.util.List;

public interface UserDepartmentRlService {

    void addUserDepartmentRelation(Long userId, List<Long> departmentIds);

    List<Long> listDepartmentIdsByUserId(Long userId);
}
