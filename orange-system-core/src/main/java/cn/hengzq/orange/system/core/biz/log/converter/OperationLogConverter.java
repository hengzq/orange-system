package cn.hengzq.orange.system.core.biz.log.converter;

import cn.hengzq.orange.common.converter.Converter;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.core.biz.log.entity.OperationLogEntity;
import cn.hengzq.orange.system.common.biz.log.vo.operation.OperationLogVO;
import cn.hengzq.orange.system.common.biz.log.vo.operation.param.AddOperationLogParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hengzq
 */
@Mapper
public interface OperationLogConverter extends Converter {
    OperationLogConverter INSTANCE = Mappers.getMapper(OperationLogConverter.class);

    OperationLogEntity toEntity(AddOperationLogParam param);

    OperationLogVO toVO(OperationLogEntity entity);

    PageDTO<OperationLogVO> toPage(PageDTO<OperationLogEntity> page);
}
