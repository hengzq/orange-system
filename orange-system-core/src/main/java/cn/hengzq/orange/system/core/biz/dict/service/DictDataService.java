package cn.hengzq.orange.system.core.biz.dict.service;


import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.dict.vo.data.DictDataVO;
import cn.hengzq.orange.system.common.biz.dict.vo.data.param.AddDictDataParam;
import cn.hengzq.orange.system.common.biz.dict.vo.data.param.DictDataListParam;
import cn.hengzq.orange.system.common.biz.dict.vo.data.param.DictDataPageParam;
import cn.hengzq.orange.system.common.biz.dict.vo.data.param.UpdateDictDataParam;
import cn.hengzq.orange.system.common.biz.dict.vo.type.param.AddDictTypeParam;
import cn.hengzq.orange.system.common.biz.dict.vo.type.param.DictTypeListParam;

import java.util.List;

/**
 * @author hengzq
 */
public interface DictDataService {

    String add(AddDictDataParam param);

    Boolean removeById(String id);

    PageDTO<DictDataVO> page(DictDataPageParam param);

    DictDataVO getById(String id);

    List<DictDataVO> list(DictDataListParam param);

    Boolean updateById(String id, UpdateDictDataParam param);
}
