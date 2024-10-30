package cn.hengzq.orange.system.core.biz.log.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.common.util.CollUtils;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.api.log.OperationLogApi;
import cn.hengzq.orange.system.core.biz.log.mapper.OperationLogMapper;
import cn.hengzq.orange.system.common.biz.log.vo.operation.OperationLogVO;
import cn.hengzq.orange.system.common.biz.log.vo.operation.param.AddOperationLogParam;
import cn.hengzq.orange.system.common.biz.log.vo.operation.param.OperationLogPageParam;
import cn.hengzq.orange.system.core.biz.log.converter.OperationLogConverter;
import cn.hengzq.orange.system.core.biz.log.entity.OperationLogEntity;
import cn.hengzq.orange.system.core.biz.log.service.OperationLogService;
import cn.hengzq.orange.system.core.biz.user.service.UserService;
import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class OperationLogServiceImpl implements OperationLogService, OperationLogApi {

    private final OperationLogMapper operationLogMapper;

    private final UserService userService;

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

        Map<Long, String> userNameMap = userService.getNameMapByIds(Set.of(vo.getUserId()));
        vo.setUserName(userNameMap.get(vo.getUserId()));
        return vo;
    }

    @Override
    public PageDTO<OperationLogVO> page(OperationLogPageParam param) {
        PageDTO<OperationLogEntity> page = operationLogMapper.selectPage(param,
                CommonWrappers.<OperationLogEntity>lambdaQuery()
                        .eqIfPresent(OperationLogEntity::getRequestId, param.getRequestId())
                        .eqIfPresent(OperationLogEntity::getResourceId, param.getResourceId())
                        .likeIfPresent(OperationLogEntity::getResourceName, param.getResourceNameLike())
                        .eqIfPresent(OperationLogEntity::getStatus, param.getStatus())
                        .ge(Objects.nonNull(param.getOperationStartTime()), OperationLogEntity::getStartTime, param.getOperationStartTime())
                        .le(Objects.nonNull(param.getOperationEndTime()), OperationLogEntity::getStartTime, param.getOperationEndTime())
                        .orderByDesc(OperationLogEntity::getStartTime)
        );
        PageDTO<OperationLogVO> pageDTO = OperationLogConverter.INSTANCE.toPage(page);
        if (Objects.isNull(pageDTO) || CollUtil.isEmpty(pageDTO.getRecords())) {
            return pageDTO;
        }
        List<OperationLogVO> records = pageDTO.getRecords();
        Map<Long, String> userNameMap = userService.getNameMapByIds(CollUtils.convertSet(records, OperationLogVO::getUserId));
        records.forEach(record -> {
            record.setUserName(userNameMap.get(record.getUserId()));
        });
        return pageDTO;
    }


}
