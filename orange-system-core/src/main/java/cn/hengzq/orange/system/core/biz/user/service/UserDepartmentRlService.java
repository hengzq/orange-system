package cn.hengzq.orange.system.core.biz.user.service;

import java.util.List;

public interface UserDepartmentRlService {

    void addUserDepartmentRelation(String userId, List<String> departmentIds);

    List<String> listDepartmentIdsByUserId(String userId);

    void removeByUserId(String id);
}
