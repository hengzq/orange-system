package cn.hengzq.orange.system.core.biz.dict.service;

import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.dict.dto.type.DictTypeResponse;
import cn.hengzq.orange.system.common.biz.dict.dto.type.request.DictTypeCreateRequest;
import cn.hengzq.orange.system.common.biz.dict.dto.type.request.DictTypeSearchRequest;
import cn.hengzq.orange.system.common.biz.dict.dto.type.request.DictTypePageRequest;
import cn.hengzq.orange.system.common.biz.dict.dto.type.request.DictTypeUpdateRequest;

import java.util.List;
import java.util.Optional;

/**
 * @author hengzq
 */
public interface DictTypeService {

    String create(DictTypeCreateRequest request);

    void deleteById(String id);

    Boolean updateById(String id, DictTypeUpdateRequest request);

    Optional<DictTypeResponse> getById(String id);

    PageDTO<DictTypeResponse> page(DictTypePageRequest request);

    List<DictTypeResponse> search(DictTypeSearchRequest request);

}
