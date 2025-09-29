package cn.hengzq.orange.system.core.biz.dict.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.mybatis.entity.BaseEntity;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.common.biz.dict.constant.SystemDictTypeErrorCode;
import cn.hengzq.orange.system.common.biz.dict.dto.data.DictDataVO;
import cn.hengzq.orange.system.common.biz.dict.dto.type.DictTypeResponse;
import cn.hengzq.orange.system.common.biz.dict.dto.type.request.DictTypeCreateRequest;
import cn.hengzq.orange.system.common.biz.dict.dto.type.request.DictTypePageRequest;
import cn.hengzq.orange.system.common.biz.dict.dto.type.request.DictTypeSearchRequest;
import cn.hengzq.orange.system.common.biz.dict.dto.type.request.DictTypeUpdateRequest;
import cn.hengzq.orange.system.core.biz.dict.converter.DictTypeConverter;
import cn.hengzq.orange.system.core.biz.dict.entity.DictTypeEntity;
import cn.hengzq.orange.system.core.biz.dict.mapper.DictTypeMapper;
import cn.hengzq.orange.system.core.biz.dict.service.DictDataService;
import cn.hengzq.orange.system.core.biz.dict.service.DictTypeService;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class DictTypeServiceImpl implements DictTypeService {

    private final DictTypeMapper dictTypeMapper;

    private final DictDataService dictDataService;

    @Override
    public String create(DictTypeCreateRequest request) {
        DictTypeEntity type = dictTypeMapper.selectByType(request.getDictType());
        Assert.isNull(type, SystemDictTypeErrorCode.DICT_TYPE_TYPE_ALREADY_EXIST);

        DictTypeEntity entity = DictTypeConverter.INSTANCE.toEntity(request);

        return dictTypeMapper.insertOne(entity);
    }

    @Override
    public void deleteById(String id) {
        if (StrUtil.isBlank(id)) return;

        DictTypeEntity entity = dictTypeMapper.selectById(id);
        Assert.nonNull(entity, SystemDictTypeErrorCode.GLOBAL_DATA_NOT_EXIST);
        List<DictDataVO> dataList = dictDataService.searchByDictType(entity.getDictType());
        Assert.isEmpty(dataList, SystemDictTypeErrorCode.DICT_TYPE_DELETE_ERROR_EXIST_DATA);
        dictTypeMapper.deleteById(id);
    }

    @Override
    public Boolean updateById(String id, DictTypeUpdateRequest request) {
        DictTypeEntity entity = dictTypeMapper.selectById(id);
        Assert.nonNull(entity, GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        entity = DictTypeConverter.INSTANCE.toUpdateEntity(entity, request);
        return dictTypeMapper.updateOneById(entity);
    }

    @Override
    public Optional<DictTypeResponse> getById(String id) {
        if (StrUtil.isBlank(id)) {
            return Optional.empty();
        }

        DictTypeEntity entity = dictTypeMapper.selectById(id);
        return Optional.ofNullable(entity)
                .map(DictTypeConverter.INSTANCE::toVO);
    }

    @Override
    public PageDTO<DictTypeResponse> page(DictTypePageRequest param) {
        PageDTO<DictTypeEntity> page = dictTypeMapper.selectPage(param, CommonWrappers.<DictTypeEntity>lambdaQuery()
                .eqIfPresent(DictTypeEntity::getName, param.getName())
                .eqIfPresent(DictTypeEntity::getDictType, param.getDictType())
                .likeIfPresent(DictTypeEntity::getName, param.getNameLike())
                .orderByDesc(BaseEntity::getCreatedAt));
        return DictTypeConverter.INSTANCE.toPage(page);
    }

    @Override
    public List<DictTypeResponse> search(DictTypeSearchRequest param) {
        List<DictTypeEntity> entityList = dictTypeMapper.selectList(CommonWrappers.<DictTypeEntity>lambdaQuery()
                .eqIfPresent(DictTypeEntity::getName, param.getName())
                .eqIfPresent(DictTypeEntity::getDictType, param.getDictType()));
        return DictTypeConverter.INSTANCE.toListVO(entityList);
    }

}
