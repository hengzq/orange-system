package cn.hengzq.orange.system.core.biz.user.service;

import java.util.List;

public interface UserDepartmentRlService {

    void addUserDepartmentRelation(Long userId, List<Long> departmentIds);

    List<Long> listDepartmentIdsByUserId(Long userId);

    void removeByUserId(Long id);
}
