package cn.hengzq.orange.system.core.biz.dict.service;


import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.dict.dto.data.DictDataVO;
import cn.hengzq.orange.system.common.biz.dict.dto.data.param.DictDataCreateRequest;
import cn.hengzq.orange.system.common.biz.dict.dto.data.param.DictDataSearchReqeust;
import cn.hengzq.orange.system.common.biz.dict.dto.data.param.DictDataPageRequest;
import cn.hengzq.orange.system.common.biz.dict.dto.data.param.DictDataUpdateRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author hengzq
 */
public interface DictDataService {

    String create(DictDataCreateRequest param);

    Boolean deleteById(String id);

    Boolean updateById(String id, DictDataUpdateRequest param);

    Optional<DictDataVO> getById(String id);

    Map<String, List<DictDataVO>> getDictDataMapByTypes(List<String> dictTypeList);

    PageDTO<DictDataVO> page(DictDataPageRequest param);

    List<DictDataVO> search(DictDataSearchReqeust param);

    List<DictDataVO> searchByDictType(String dictType);
}
