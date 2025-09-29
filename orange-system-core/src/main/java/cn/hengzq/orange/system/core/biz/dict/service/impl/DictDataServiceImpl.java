package cn.hengzq.orange.system.core.biz.dict.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.mybatis.entity.BaseEntity;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.common.biz.dict.dto.data.DictDataVO;
import cn.hengzq.orange.system.common.biz.dict.dto.data.param.DictDataCreateRequest;
import cn.hengzq.orange.system.common.biz.dict.dto.data.param.DictDataPageRequest;
import cn.hengzq.orange.system.common.biz.dict.dto.data.param.DictDataSearchReqeust;
import cn.hengzq.orange.system.common.biz.dict.dto.data.param.DictDataUpdateRequest;
import cn.hengzq.orange.system.common.constant.RedisKeys;
import cn.hengzq.orange.system.core.biz.dict.converter.DictDataConverter;
import cn.hengzq.orange.system.core.biz.dict.entity.DictDataEntity;
import cn.hengzq.orange.system.core.biz.dict.mapper.DictDataMapper;
import cn.hengzq.orange.system.core.biz.dict.service.DictDataService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class DictDataServiceImpl implements DictDataService {

    private final DictDataMapper dictDataMapper;

    @Override
    public String create(DictDataCreateRequest param) {
        DictDataEntity entity = DictDataConverter.INSTANCE.toEntity(param);
        return dictDataMapper.insertOne(entity);
    }

    @Override
    @CacheEvict(cacheNames = {RedisKeys.DICT_DATA_BASIC_KEY_PREFIX}, allEntries = true)
    public Boolean deleteById(String id) {
        return dictDataMapper.deleteOneById(id);
    }

    @Override
    @CacheEvict(cacheNames = {RedisKeys.DICT_DATA_BASIC_KEY_PREFIX}, allEntries = true)
    public Boolean updateById(String id, DictDataUpdateRequest param) {
        DictDataEntity entity = dictDataMapper.selectById(id);
        Assert.nonNull(entity, GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        entity = DictDataConverter.INSTANCE.toUpdateEntity(entity, param);
        return dictDataMapper.updateOneById(entity);
    }

    @Override
    @Cacheable(cacheNames = {RedisKeys.DICT_DATA_BASIC_KEY_PREFIX}, key = "#id")
    public Optional<DictDataVO> getById(String id) {
        if (StrUtil.isBlank(id)) {
            return Optional.empty();
        }
        DictDataEntity entity = dictDataMapper.selectById(id);
        return Optional.ofNullable(entity)
                .map(DictDataConverter.INSTANCE::toVO);
    }


    @Override
    public Map<String, List<DictDataVO>> getDictDataMapByTypes(List<String> dictTypeList) {
        if (CollUtil.isEmpty(dictTypeList)) {
            return Map.of();
        }
        List<DictDataEntity> dataEntityList = dictDataMapper.selectList(CommonWrappers.<DictDataEntity>lambdaQuery().in(DictDataEntity::getDictType, dictTypeList));
        List<DictDataVO> listVO = DictDataConverter.INSTANCE.toListVO(dataEntityList);
        if (CollUtil.isEmpty(listVO)) {
            return Map.of();
        }
        return listVO.stream().collect(Collectors.groupingBy(DictDataVO::getDictType));
    }


    @Override
    public PageDTO<DictDataVO> page(DictDataPageRequest param) {
        PageDTO<DictDataEntity> page = dictDataMapper.selectPage(param, CommonWrappers.<DictDataEntity>lambdaQuery()
                .eqIfPresent(DictDataEntity::getDictType, param.getDictType())
                .eqIfPresent(DictDataEntity::getDictLabel, param.getDictLabel())
                .likeIfPresent(DictDataEntity::getDictLabel, param.getDictLabelLike())
                .orderByDesc(BaseEntity::getUpdatedAt));
        return DictDataConverter.INSTANCE.toPage(page);
    }

    @Override
    public List<DictDataVO> search(DictDataSearchReqeust param) {
        List<DictDataEntity> entityList = dictDataMapper.selectList(CommonWrappers.<DictDataEntity>lambdaQuery()
                .eq(DictDataEntity::getDictType, param.getDictType()));
        return DictDataConverter.INSTANCE.toListVO(entityList);
    }

    @Override
    @Cacheable(cacheNames = {RedisKeys.DICT_DATA_BASIC_KEY_PREFIX}, key = "#dictType")
    public List<DictDataVO> searchByDictType(String dictType) {
        if (StrUtil.isBlank(dictType)) {
            return List.of();
        }
        List<DictDataEntity> entityList = dictDataMapper.selectList(CommonWrappers.<DictDataEntity>lambdaQuery()
                .eq(DictDataEntity::getDictType, dictType));
        return DictDataConverter.INSTANCE.toListVO(entityList);
    }
}
