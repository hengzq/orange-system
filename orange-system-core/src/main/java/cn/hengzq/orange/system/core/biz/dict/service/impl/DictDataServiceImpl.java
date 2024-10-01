package cn.hengzq.orange.system.core.biz.dict.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.mybatis.entity.BaseEntity;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.common.biz.dict.vo.data.DictDataVO;
import cn.hengzq.orange.system.common.biz.dict.vo.data.param.AddDictDataParam;
import cn.hengzq.orange.system.common.biz.dict.vo.data.param.DictDataListParam;
import cn.hengzq.orange.system.common.biz.dict.vo.data.param.DictDataPageParam;
import cn.hengzq.orange.system.common.biz.dict.vo.data.param.UpdateDictDataParam;
import cn.hengzq.orange.system.core.biz.dict.converter.DictDataConverter;
import cn.hengzq.orange.system.core.biz.dict.entity.DictDataEntity;
import cn.hengzq.orange.system.core.biz.dict.mapper.DictDataMapper;
import cn.hengzq.orange.system.core.biz.dict.service.DictDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class DictDataServiceImpl implements DictDataService {

    private final DictDataMapper dictDataMapper;

    @Override
    public Long add(AddDictDataParam param) {
        DictDataEntity entity = DictDataConverter.INSTANCE.toEntity(param);
       
        return dictDataMapper.insertOne(entity);
    }

    @Override
    public Boolean removeById(Long id) {
        return dictDataMapper.deleteOneById(id)  ;
    }

    @Override
    public PageDTO<DictDataVO> page(DictDataPageParam param) {
        PageDTO<DictDataEntity> page = dictDataMapper.selectPage(param, CommonWrappers.<DictDataEntity>lambdaQuery()
                .eqIfPresent(DictDataEntity::getDictType, param.getDictType())
                .eqIfPresent(DictDataEntity::getDictLabel, param.getDictLabel())
                .likeIfPresent(DictDataEntity::getDictLabel, param.getDictLabelLike())
                .orderByDesc(BaseEntity::getUpdatedAt));
        return DictDataConverter.INSTANCE.toPage(page);
    }

    @Override
    public DictDataVO getById(Long id) {
        DictDataEntity entity = dictDataMapper.selectById(id);
        return DictDataConverter.INSTANCE.toVO(entity);
    }

    @Override
    public List<DictDataVO> list(DictDataListParam param) {
        List<DictDataEntity> entityList = dictDataMapper.selectList(CommonWrappers.<DictDataEntity>lambdaQuery()
                .eq(DictDataEntity::getDictType, param.getDictType()));
        return DictDataConverter.INSTANCE.toListVO(entityList);
    }

    @Override
    public Boolean updateById(Long id, UpdateDictDataParam param) {
        DictDataEntity entity = dictDataMapper.selectById(id);
        Assert.nonNull(entity, GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        entity = DictDataConverter.INSTANCE.toUpdateEntity(entity, param);
        return dictDataMapper.updateOneById(entity);
    }
}
