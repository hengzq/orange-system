package cn.hengzq.orange.system.permission.core.service;


import cn.hengzq.orange.system.permission.common.vo.department.DepartmentTreeVO;
import cn.hengzq.orange.system.permission.common.vo.department.DepartmentVO;
import cn.hengzq.orange.system.permission.common.vo.department.param.AddDepartmentParam;
import cn.hengzq.orange.system.permission.common.vo.department.param.DepartmentListParam;
import cn.hengzq.orange.system.permission.common.vo.department.param.UpdateDepartmentParam;

import java.util.List;

/**
 * @author hengzq
 */
public interface DepartmentService {

    Long add(AddDepartmentParam request);

    Boolean removeById(Long id);

    Boolean updateById(Long id, UpdateDepartmentParam request);

    DepartmentVO getById(Long id);

    List<DepartmentVO> list(DepartmentListParam query);

    List<DepartmentTreeVO> listTree(DepartmentListParam request);

}
