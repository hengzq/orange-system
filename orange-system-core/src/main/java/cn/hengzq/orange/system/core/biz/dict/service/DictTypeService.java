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

    Long add(AddDictTypeParam param);

    Boolean removeById(Long id);

    Boolean updateById(Long id, UpdateDictTypeParam param);

    DictTypeVO getById(Long id);

    PageDTO<DictTypeVO> page(DictTypePageParam param);

    List<DictTypeVO> list(DictTypeListParam queryVo);

}
