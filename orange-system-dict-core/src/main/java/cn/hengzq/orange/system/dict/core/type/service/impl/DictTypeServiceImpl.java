package cn.hengzq.orange.system.dict.core.type.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.mybatis.entity.BaseEntity;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.dict.core.data.entity.DictDataEntity;
import cn.hengzq.orange.system.dict.core.data.mapper.DictDataMapper;
import cn.hengzq.orange.system.dict.core.type.converter.DictTypeConverter;
import cn.hengzq.orange.system.dict.core.type.entity.DictTypeEntity;
import cn.hengzq.orange.system.dict.core.type.mapper.DictTypeMapper;
import cn.hengzq.orange.system.dict.core.type.service.DictTypeService;
import cn.hengzq.orange.system.dict.common.constant.SystemDictErrorCode;
import cn.hengzq.orange.system.dict.common.vo.type.DictTypeVO;
import cn.hengzq.orange.system.dict.common.vo.type.param.AddDictTypeParam;
import cn.hengzq.orange.system.dict.common.vo.type.param.DictTypeListParam;
import cn.hengzq.orange.system.dict.common.vo.type.param.DictTypePageParam;
import cn.hengzq.orange.system.dict.common.vo.type.param.UpdateDictTypeParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class DictTypeServiceImpl implements DictTypeService {

    private final DictTypeMapper dictTypeMapper;

    private final DictDataMapper dictDataMapper;

    @Override
    public Long add(AddDictTypeParam param) {
        DictTypeEntity type = dictTypeMapper.selectByType(param.getDictType());
        Assert.isNull(type, SystemDictErrorCode.DICT_TYPE_TYPE_ALREADY_EXIST);
        DictTypeEntity entity = DictTypeConverter.INSTANCE.toEntity(param);
       
        return dictTypeMapper.insertOne(entity);
    }

    @Override
    public Boolean removeById(Long id) {
        DictTypeEntity entity = dictTypeMapper.selectById(id);
        Assert.nonNull(entity, SystemDictErrorCode.GLOBAL_DATA_NOT_EXIST);
        List<DictDataEntity> entityList = dictDataMapper.selectListByType(entity.getDictType());
        Assert.isEmpty(entityList, SystemDictErrorCode.DICT_TYPE_DELETE_ERROR_EXIST_DATA);
        return dictTypeMapper.deleteById(id) > 0;
    }

    @Override
    public Boolean updateById(Long id, UpdateDictTypeParam request) {
        DictTypeEntity entity = dictTypeMapper.selectById(id);
        Assert.nonNull(entity, GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        entity = DictTypeConverter.INSTANCE.toUpdateEntity(entity, request);
        return dictTypeMapper.updateOneById(entity);
    }

    @Override
    public DictTypeVO getById(Long id) {
        return DictTypeConverter.INSTANCE.toVO(dictTypeMapper.selectById(id));
    }

    @Override
    public PageDTO<DictTypeVO> page(DictTypePageParam param) {
        PageDTO<DictTypeEntity> page = dictTypeMapper.selectPage(param,
                CommonWrappers.<DictTypeEntity>lambdaQuery().orderByDesc(BaseEntity::getCreatedAt));
        return DictTypeConverter.INSTANCE.toPage(page);
    }

    @Override
    public List<DictTypeVO> list(DictTypeListParam queryVo) {
        List<DictTypeEntity> entityList = dictTypeMapper.selectList(CommonWrappers.<DictTypeEntity>lambdaQuery()
        );
        return DictTypeConverter.INSTANCE.toListVO(entityList);
    }

}
