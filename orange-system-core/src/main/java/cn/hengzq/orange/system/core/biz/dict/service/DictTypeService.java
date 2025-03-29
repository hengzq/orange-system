package cn.hengzq.orange.system.core.biz.dict.service;

import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.dict.vo.type.DictTypeVO;
import cn.hengzq.orange.system.common.biz.dict.vo.type.param.AddDictTypeParam;
import cn.hengzq.orange.system.common.biz.dict.vo.type.param.DictTypeListParam;
import cn.hengzq.orange.system.common.biz.dict.vo.type.param.DictTypePageParam;
import cn.hengzq.orange.system.common.biz.dict.vo.type.param.UpdateDictTypeParam;

import java.util.List;

/**
 * @author hengzq
 */
public interface DictTypeService {

    String add(AddDictTypeParam param);

    Boolean removeById(String id);

    Boolean updateById(String id, UpdateDictTypeParam param);

    DictTypeVO getById(String id);

    PageDTO<DictTypeVO> page(DictTypePageParam param);

    List<DictTypeVO> list(DictTypeListParam queryVo);

}
