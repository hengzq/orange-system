package cn.hengzq.orange.system.log.core.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.log.common.vo.operation.OperationLogVO;
import cn.hengzq.orange.system.log.common.vo.operation.param.AddOperationLogParam;
import cn.hengzq.orange.system.log.common.vo.operation.param.OperationLogPageParam;
import cn.hengzq.orange.system.log.core.converter.OperationLogConverter;
import cn.hengzq.orange.system.log.core.entity.OperationLogEntity;
import cn.hengzq.orange.system.log.core.mapper.OperationLogMapper;
import cn.hengzq.orange.system.log.core.service.OperationLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class OperationLogServiceImpl implements OperationLogService {

    private final OperationLogMapper operationLogMapper;


    @Override
    public Long add(AddOperationLogParam param) {
        OperationLogEntity entity = OperationLogConverter.INSTANCE.toEntity(param);
        return operationLogMapper.insertOne(entity);
    }

    @Override
    public OperationLogVO getById(Long id) {
        OperationLogEntity entity = operationLogMapper.selectById(id);
        OperationLogVO vo = OperationLogConverter.INSTANCE.toVO(entity);
        Assert.nonNull(vo, GlobalErrorCodeConstant.GLOBAL_PARAMETER_ID_IS_INVALID);
        return vo;
    }

    @Override
    public PageDTO<OperationLogVO> page(OperationLogPageParam param) {
        PageDTO<OperationLogEntity> page = operationLogMapper.selectPage(param,
                CommonWrappers.<OperationLogEntity>lambdaQuery()
                        .eqIfPresent(OperationLogEntity::getRequestId, param.getRequestId())
        );
        return OperationLogConverter.INSTANCE.toPage(page);
    }


}
