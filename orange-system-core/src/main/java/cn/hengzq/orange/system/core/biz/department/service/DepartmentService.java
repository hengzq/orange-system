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

    Long add(AddDepartmentParam request);

    Boolean removeById(Long id);

    Boolean updateById(Long id, UpdateDepartmentParam request);

    DepartmentVO getById(Long id);

    List<DepartmentVO> list(DepartmentListParam query);

    List<DepartmentTreeVO> listTree(DepartmentListParam request);

}
