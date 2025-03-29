package cn.hengzq.orange.system.core.biz.department.service;



import cn.hengzq.orange.system.common.biz.department.vo.DepartmentTreeVO;
import cn.hengzq.orange.system.common.biz.department.vo.DepartmentVO;
import cn.hengzq.orange.system.common.biz.department.vo.param.AddDepartmentParam;
import cn.hengzq.orange.system.common.biz.department.vo.param.DepartmentListParam;
import cn.hengzq.orange.system.common.biz.department.vo.param.UpdateDepartmentParam;

import java.util.List;

/**
 * @author hengzq
 */
public interface DepartmentService {

    String add(AddDepartmentParam request);

    Boolean removeById(String id);

    Boolean updateById(String id, UpdateDepartmentParam request);

    DepartmentVO getById(String id);

    List<DepartmentVO> list(DepartmentListParam query);

    List<DepartmentTreeVO> listTree(DepartmentListParam request);

}
